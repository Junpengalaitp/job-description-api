package com.alaitp.job.description.api.cache;

import com.alaitp.job.description.api.entity.CoOccurrenceIdxToWord;
import com.alaitp.job.description.api.mapper.CoOccurrenceIdxToWordDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * init on app start, keep word to category map, for fast finding word category
 */
@Component
public class WordToCategoryCache {

    private static final List<CoOccurrenceIdxToWord> WORD_CATEGORY_LIST = new ArrayList<>();

    @Autowired
    private CoOccurrenceIdxToWordDao coOccurrenceIdxToWordDao;

    public static CoOccurrenceIdxToWord getWord(int idx) {
        return WORD_CATEGORY_LIST.get(idx);
    }

    @PostConstruct
    private void init() {
        WORD_CATEGORY_LIST.addAll(coOccurrenceIdxToWordDao.selectList(new QueryWrapper<>()));
    }

}
