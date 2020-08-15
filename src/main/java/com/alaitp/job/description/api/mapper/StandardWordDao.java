package com.alaitp.job.description.api.mapper;

import com.alaitp.job.description.api.entity.StandardWord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface StandardWordDao extends BaseMapper<StandardWord> {
    String selectStandardWord(String word);
}