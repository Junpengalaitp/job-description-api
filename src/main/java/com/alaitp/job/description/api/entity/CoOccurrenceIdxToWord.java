package com.alaitp.job.description.api.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * co_occurrence_idx_to_word
 */
@Data
public class CoOccurrenceIdxToWord implements Serializable {

    private static final long serialVersionUID = -567200261530217948L;

    private Integer idx;

    private String word;

    private String category;
}