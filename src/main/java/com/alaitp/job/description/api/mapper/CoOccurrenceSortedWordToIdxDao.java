package com.alaitp.job.description.api.mapper;

import com.alaitp.job.description.api.entity.CoOccurrenceWordCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface CoOccurrenceSortedWordToIdxDao extends BaseMapper<CoOccurrenceWordCount> {
}