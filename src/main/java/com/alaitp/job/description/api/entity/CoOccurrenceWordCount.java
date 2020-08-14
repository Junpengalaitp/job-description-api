package com.alaitp.job.description.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * co_occurrence_sorted_word_to_idx
 *
 * @author
 */
@Data
public class CoOccurrenceWordCount implements Serializable {
    private static final long serialVersionUID = -1886926942065230286L;

    private String word;

    private String wordCounts;

    /**
     * sorted indices string format, use comma as delimiter
     */
    private String sortedIndices;

    public List<Integer> getWordCountList() {
        List<Integer> wordCountList = new ArrayList<>();
        for (char c : wordCounts.toCharArray()) {
            if (',' != c) {
                wordCountList.add(Integer.parseInt(String.valueOf(c)));
            }
        }
        return wordCountList;
    }

}