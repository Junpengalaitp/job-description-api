package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.entity.CoOccurrenceIdxToWord;
import com.alaitp.job.description.api.entity.CoOccurrenceWordCount;

import java.util.List;
import java.util.Map;

public interface CoOccurrenceService {

    CoOccurrenceIdxToWord getCoOccurrenceIdxToWord(int idx);

    /**
     * get top n for correlated words for word by the categories
     *
     * @param word       the root word for correlated words
     * @param topN       top n
     * @param categories correlated words category
     * @return map of top n keyword (key: keyword, value: json of count and category))
     */
    Map<String, Map<String, Object>> getTopRelatedWords(String word, int topN, List<String> categories);

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
    List<Integer> getTopIndicesInCategory(CoOccurrenceWordCount coOccurrenceWordCount, int topN, List<String> categories);

}
