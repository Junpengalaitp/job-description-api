package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.dao.CoOccurrenceMatrixDao;
import com.alaitp.job.description.api.dao.StandardWordDao;
import com.alaitp.job.description.api.dto.CoOccurredWordDto;
import com.alaitp.job.description.api.entity.CoOccurrenceMatrixRow;
import com.alaitp.job.description.api.service.CoOccurrenceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.alaitp.job.description.api.constant.ControllerConst.CATEGORY;
import static com.alaitp.job.description.api.constant.ControllerConst.COUNT;

@Slf4j
@Service
public class CoOccurrenceServiceImpl implements CoOccurrenceService {

    private final StandardWordDao standardWordDao;

    private final CoOccurrenceMatrixDao coOccurrenceMatrixDao;

    private final ValueOperations<String, String> valueOperations;

    @Autowired
    public CoOccurrenceServiceImpl(StandardWordDao standardWordDao, CoOccurrenceMatrixDao coOccurrenceMatrixDao, ValueOperations<String, String> valueOperations) {
        this.standardWordDao = standardWordDao;
        this.coOccurrenceMatrixDao = coOccurrenceMatrixDao;
        this.valueOperations = valueOperations;
    }


    /**
     * get standard word from cache if exists, else get it from database
     *
     * @param word target word, might not be in standard format
     * @return standard word if standard word exists, else return word
     */
    @Override
    public String getStandardWord(String word) {
        try {
            String standardWord = valueOperations.get(word);
            if (standardWord == null) {
                standardWord = standardWordDao.selectStandardWord(word);
                if (standardWord != null) {
                    valueOperations.set(word, standardWord);
                } else {
                    standardWord = word;
                    log.debug("no standard word found for word: {}", word);
                }
            }
            return standardWord;
        } catch (Exception e) {
            log.info("error word: {}", word);
            e.printStackTrace();
            return word;
        }
    }

    /**
     * get top n for correlated words for word by the categories
     *
     * @param word       the root word for correlated words
     * @param topN       top n
     * @param categories correlated words category
     * @return map of top n keyword (key: keyword, value: json of count and category))
     */
    @Override
    public Map<String, Map<String, Object>> getTopRelatedWords(String word, int topN, Set<String> categories) {
        Map<String, Map<String, Object>> topWordMap = new HashMap<>();
        CoOccurrenceMatrixRow coOccurrenceWordCount = getCoOccurrenceRowByWord(word);
        if (coOccurrenceWordCount == null) {
            return topWordMap;
        }
        for (String coOccurrenceWord : coOccurrenceWordCount.getCoOccurredWordInfoSorted().split(",")) {
            CoOccurredWordDto coOccurredWordDto = new CoOccurredWordDto(coOccurrenceWord);
            // check co-occurred word is in desired categories and it's not the word itself
            if (categories.contains(coOccurredWordDto.getCategory()) && !word.equals(coOccurredWordDto.getWord())) {
                topWordMap.put(coOccurredWordDto.getWord(), Map.of(
                        CATEGORY, coOccurredWordDto.getCategory(),
                        COUNT, coOccurredWordDto.getCount()));
                if (topWordMap.size() == topN) {
                    break;
                }
            }
        }
        return topWordMap;
    }


    @Override
    public CoOccurrenceMatrixRow getCoOccurrenceRowByWord(String word) {
        QueryWrapper<CoOccurrenceMatrixRow> queryWrapper = new QueryWrapper<>();
        return coOccurrenceMatrixDao.selectOne(queryWrapper
                .lambda()
                .eq(CoOccurrenceMatrixRow::getWord, word));
    }

}
