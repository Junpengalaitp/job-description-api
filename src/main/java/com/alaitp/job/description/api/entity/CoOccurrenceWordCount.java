package com.alaitp.job.description.api.entity;

import lombok.Data;

import java.io.Serializable;

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

}