package com.alaitp.job.description.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * co_occurrence_matrix
 *
 * @author
 */
@TableName("co_occurrence_matrix")
@Data
public class CoOccurrenceMatrixRow implements Serializable {

    private static final long serialVersionUID = -5853853009900133501L;

    /**
     * root word for co-occurrence words
     */
    private String word;

    /**
     * list of co-occurred word info sorted by count desc,
     * each info is in the format of (word|category|count),
     * use comma as delimiter.
     */
    private String coOccurredWordInfoSorted;

}