package com.alaitp.job.description.api.dto;

import lombok.Data;

@Data
public class CoOccurredWordDto {
    private String word;
    private String category;
    private Integer count;

    public CoOccurredWordDto(String wordDtoStr) {
        // remove '(' and ')'
        wordDtoStr = wordDtoStr.substring(1, wordDtoStr.length() - 2);
        String[] fields = wordDtoStr.split("\\|");
        this.word = fields[0];
        this.category = fields[1];
        this.count = Integer.parseInt(fields[2]);
    }
}
