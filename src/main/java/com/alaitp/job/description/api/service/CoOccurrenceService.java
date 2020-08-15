package com.alaitp.job.description.api.service;

import com.alaitp.job.description.api.entity.CoOccurrenceMatrixRow;

import java.util.Map;
import java.util.Set;

public interface CoOccurrenceService {

    /**
     * get standard word from cache if exists, else get it from database
     *
     * @param word target word, might not be in standard format
     * @return standard word if standard word exists, else return word
     */
    String getStandardWord(String word);

    /**
     * get top n for correlated words for word by the categories
     *
     * @param word       the root word for correlated words
     * @param topN       top n
     * @param categories correlated words category
     * @return map of top n keyword (key: keyword, value: json of count and category))
     */
    Map<String, Map<String, Object>> getTopRelatedWords(String word, int topN, Set<String> categories);


    CoOccurrenceMatrixRow getCoOccurrenceRowByWord(String word);
}
