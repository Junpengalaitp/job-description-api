package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.entity.CoOccurrenceWordCount;

import java.util.List;

public interface CoOccurrenceService {

    /**
     * CoOccurrenceWordCount for target word
     *
     * @param word target word
     * @return CoOccurrenceWordCount for target word
     */
    CoOccurrenceWordCount getCoOccurrenceCountByWord(String word);

    /**
     * get top n co-occurred word indices with the target word
     *
     * @param coOccurrenceWordCount coOccurrenceWordCount for target word
     * @param topN                  top n
     * @return top n word indices
     */
    List<Integer> getTopIndices(CoOccurrenceWordCount coOccurrenceWordCount, int topN);

}
