package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.entity.CoOccurrenceIdxToWord;
import com.alaitp.job.description.api.entity.CoOccurrenceWordCount;
import com.alaitp.job.description.api.mapper.CoOccurrenceIdxToWordDao;
import com.alaitp.job.description.api.mapper.CoOccurrenceSortedWordToIdxDao;
import com.alaitp.job.description.api.service.CoOccurrenceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoOccurrenceServiceImpl implements CoOccurrenceService {

    @Autowired
    private CoOccurrenceSortedWordToIdxDao coOccurrenceSortedWordToIdxDao;

    @Autowired
    private CoOccurrenceIdxToWordDao coOccurrenceIdxToWordDao;

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
    public CoOccurrenceIdxToWord getCoOccurrenceIdxToWord(int idx) {
        QueryWrapper<CoOccurrenceIdxToWord> queryWrapper = new QueryWrapper<>();
        return coOccurrenceIdxToWordDao.selectOne(queryWrapper
                .lambda()
                .eq(CoOccurrenceIdxToWord::getIdx, idx));
    }

    @Override
    public Map<String, Map<String, Object>> getTopRelatedWords(String word, int topN, List<String> categories) {
        Map<String, Map<String, Object>> topWordMap = new HashMap<>();
        CoOccurrenceWordCount coOccurrenceWordCount = getCoOccurrenceCountByWord(word);
        String sortedIndices = coOccurrenceWordCount.getSortedIndices();
        for (int i = 0; i < sortedIndices.length(); i++) {
            String idx = String.valueOf(sortedIndices.charAt(i));
            if (!",".equals(idx)) {
                int index = Integer.parseInt(idx);
                CoOccurrenceIdxToWord coOccurrenceIdxToWord = getCoOccurrenceIdxToWord(index);
                if (categories.contains(coOccurrenceIdxToWord.getCategory())) {
                    topWordMap.put(coOccurrenceIdxToWord.getWord(), Map.of(coOccurrenceIdxToWord.getCategory(), coOccurrenceWordCount.getWordCountList().get(index)));
                    if (topWordMap.size() == topN + 1) {
                        break;
                    }
                }
            }
        }
        return topWordMap;
    }

    @Override
    public CoOccurrenceWordCount getCoOccurrenceCountByWord(String word) {
        QueryWrapper<CoOccurrenceWordCount> queryWrapper = new QueryWrapper<>();
        return coOccurrenceSortedWordToIdxDao.selectOne(queryWrapper
                .lambda()
                .eq(CoOccurrenceWordCount::getWord, word));
    }

}
