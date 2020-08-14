package com.alaitp.job.description.api.cache;

import com.alaitp.job.description.api.entity.CoOccurrenceIdxToWord;
import com.alaitp.job.description.api.mapper.CoOccurrenceIdxToWordDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * init on app start, keep word to category map, for fast finding word category
 */
@Component
public class WordToCategoryCache {

    private static final Map<String, String> WORD_CATEGORY_MAP = new HashMap<>();
    @Autowired
    private CoOccurrenceIdxToWordDao coOccurrenceIdxToWordDao;

    @PostConstruct
    private void init() {
        WORD_CATEGORY_MAP.putAll(coOccurrenceIdxToWordDao.selectList(new QueryWrapper<>())
                .stream()
                .collect(Collectors.toMap(CoOccurrenceIdxToWord::getWord, CoOccurrenceIdxToWord::getCategory)));
        System.out.println(WORD_CATEGORY_MAP);
    }

    public String getWordCategory(String word) {
        return WORD_CATEGORY_MAP.get(word);
    }

}
