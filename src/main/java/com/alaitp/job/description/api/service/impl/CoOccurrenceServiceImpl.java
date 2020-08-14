package com.alaitp.job.description.api.service.impl;

import com.alaitp.job.description.api.entity.CoOccurrenceWordCount;
import com.alaitp.job.description.api.mapper.CoOccurrenceSortedWordToIdxDao;
import com.alaitp.job.description.api.service.CoOccurrenceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoOccurrenceServiceImpl implements CoOccurrenceService {

    @Autowired
    private CoOccurrenceSortedWordToIdxDao coOccurrenceSortedWordToIdxDao;

    /**
     * get top n co-occurred word indices with the target word
     *
     * @param topN top n
     * @return top n word indices
     */
    @Override
    public List<Integer> getTopIndices(CoOccurrenceWordCount coOccurrenceWordCount, int topN) {
        List<Integer> topIndices = new ArrayList<>();
        String sortedIndices = coOccurrenceWordCount.getSortedIndices();
        for (int i = 0; i < sortedIndices.length(); i++) {
            char idx = sortedIndices.charAt(i);
            if (',' != idx) {
                topIndices.add((int) idx);
                if (topIndices.size() == topN + 1) {
                    break;
                }
            }
        }
        return topIndices;
    }

    @Override
    public CoOccurrenceWordCount getCoOccurrenceCountByWord(String word) {
        QueryWrapper<CoOccurrenceWordCount> queryWrapper = new QueryWrapper<>();
        return coOccurrenceSortedWordToIdxDao.selectOne(queryWrapper
                .lambda()
                .eq(CoOccurrenceWordCount::getWord, word));
    }
}
