package com.alaitp.job.description.api.dto;

import lombok.Data;

import static com.alaitp.job.description.api.constant.CategoryConst.CATEGORY_MAP;

@Data
public class CoOccurredWordDto {
    private String word;
    private String category;
    private Integer count;

    public CoOccurredWordDto(String wordDtoStr) {
        String[] fields = wordDtoStr.split("\\|");
        this.word = fields[0];
        this.category = CATEGORY_MAP.getOrDefault(fields[1], fields[1]);
        this.count = Integer.parseInt(fields[2]);
    }
}
