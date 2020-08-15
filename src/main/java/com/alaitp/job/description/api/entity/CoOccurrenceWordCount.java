package com.alaitp.job.description.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.stream.Stream;

/**
 * co_occurrence_sorted_word_to_idx
 *
 * @author
 */
@Data
public class CoOccurrenceWordCount implements Serializable {
    private static final long serialVersionUID = -1886926942065230286L;

    @TableId
    private String word;

    private String wordCounts;

    /**
     * sorted indices string format, use comma as delimiter
     */
    private String sortedIndices;

    @TableField(exist = false)
    private int[] wordCountArray;

    @TableField(exist = false)
    private int[] sortedIndexArray;

    public int getWordCount(int idx) {
        return wordCountArray[idx];
    }

    public CoOccurrenceWordCount init() {
        wordCountArray = Stream.of(wordCounts.split(",")).mapToInt(Integer::parseInt).toArray();
        sortedIndexArray = Stream.of(sortedIndices.split(",")).mapToInt(Integer::parseInt).toArray();
        return this;
    }
}