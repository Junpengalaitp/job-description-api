package com.alaitp.job.description.api.mapper;

import com.alaitp.job.description.api.entity.JobDescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DiceJobsDAO继承基类
 */
@Repository
public interface DiceJobDAO extends BaseMapper<JobDescription> {

    List<JobDescription> findJobDescriptionsByJobTitle(@NonNull String jobTitle);
}