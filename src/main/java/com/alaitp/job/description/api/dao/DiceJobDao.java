package com.alaitp.job.description.api.dao;

import com.alaitp.job.description.api.entity.JobDescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiceJobDao extends BaseMapper<JobDescription> {
    List<JobDescription> findJobDescriptionsByJobTitle(@NonNull String jobTitle);
}