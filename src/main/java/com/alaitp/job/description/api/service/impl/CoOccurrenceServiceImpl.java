package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.cache.WordToCategoryCache;
import com.alaitp.job.description.api.entity.CoOccurrenceIdxToWord;
import com.alaitp.job.description.api.entity.CoOccurrenceWordCount;
import com.alaitp.job.description.api.mapper.CoOccurrenceIdxToWordDao;
import com.alaitp.job.description.api.mapper.CoOccurrenceSortedWordToIdxDao;
import com.alaitp.job.description.api.mapper.StandardWordDao;
import com.alaitp.job.description.api.service.CoOccurrenceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.alaitp.job.description.api.constant.ControllerConst.CATEGORY;
import static com.alaitp.job.description.api.constant.ControllerConst.COUNT;

@Slf4j
@Service
public class CoOccurrenceServiceImpl implements CoOccurrenceService {

    @Autowired
    private StandardWordDao standardWordDao;

    @Autowired
    private CoOccurrenceSortedWordToIdxDao coOccurrenceSortedWordToIdxDao;

    @Autowired
    private CoOccurrenceIdxToWordDao coOccurrenceIdxToWordDao;

    @Autowired
    private ValueOperations<String, String> valueOperations;

    /**
     * get top n co-occurred word indices with the target word
     *
     * @param topN top n
     * @return top n word indices
     */
    @Override
    public List<Integer> getTopIndicesInCategory(CoOccurrenceWordCount coOccurrenceWordCount, int topN, List<String> categories) {
        List<Integer> topIndices = new ArrayList<>();
        String sortedIndices = coOccurrenceWordCount.getSortedIndices();
        for (int i = 0; i < sortedIndices.length(); i++) {
            String idx = String.valueOf(sortedIndices.charAt(i));
            if (!",".equals(idx)) {
                int index = Integer.parseInt(idx);
                CoOccurrenceIdxToWord coOccurrenceIdxToWord = getCoOccurrenceIdxToWord(index);
                if (categories.contains(coOccurrenceIdxToWord.getCategory())) {
                    topIndices.add(index);
                    if (topIndices.size() == topN + 1) {
                        break;
                    }
                }
            }
        }
        return topIndices;
    }

    @Override
    public String getStandardWord(String word) {
        String standardWord = valueOperations.get(word);
        if (standardWord == null) {
            standardWord = standardWordDao.selectStandardWord(word);
            if (standardWord != null) {
                valueOperations.set(word, standardWord);
            } else {
                standardWord = word;
                log.info("no standard word found for word: {}", word);
            }
        }
        return standardWord;
    }

    @Override
    public CoOccurrenceIdxToWord getCoOccurrenceIdxToWord(int idx) {
        QueryWrapper<CoOccurrenceIdxToWord> queryWrapper = new QueryWrapper<>();
        return coOccurrenceIdxToWordDao.selectOne(queryWrapper
                .lambda()
                .eq(CoOccurrenceIdxToWord::getIdx, idx));
    }

    @Override
    public Map<String, Map<String, Object>> getTopRelatedWords(String word, int topN, Set<String> categories) {
        Map<String, Map<String, Object>> topWordMap = new HashMap<>();
        CoOccurrenceWordCount coOccurrenceWordCount = getCoOccurrenceCountByWord(word);
        if (coOccurrenceWordCount == null) {
            return topWordMap;
        }
        for (int index : coOccurrenceWordCount.getSortedIndexArray()) {
            CoOccurrenceIdxToWord coOccurrenceIdxToWord = WordToCategoryCache.getWord(index);
            // check co-occurred word is in desired categories and it's not the word itself
            if (categories.contains(coOccurrenceIdxToWord.getCategory()) && !word.equals(coOccurrenceIdxToWord.getWord())) {
                topWordMap.put(coOccurrenceIdxToWord.getWord(), Map.of(
                        CATEGORY, coOccurrenceIdxToWord.getCategory(),
                        COUNT, coOccurrenceWordCount.getWordCount(index)));
                if (topWordMap.size() == topN) {
                    break;
                }
            }
        }
        return topWordMap;
    }

    @Override
    public CoOccurrenceWordCount getCoOccurrenceCountByWord(String word) {
        QueryWrapper<CoOccurrenceWordCount> queryWrapper = new QueryWrapper<>();
        CoOccurrenceWordCount coOccurrenceWordCount = coOccurrenceSortedWordToIdxDao.selectOne(queryWrapper
                .lambda()
                .eq(CoOccurrenceWordCount::getWord, word));
        return coOccurrenceWordCount.init();
    }

}
