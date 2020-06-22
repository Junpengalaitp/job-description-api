package com.alaitp.job.description.api.mapper;

import com.alaitp.job.description.api.entity.DiceJob;
import com.alaitp.job.description.api.entity.JobDescription;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DiceJobsDAO继承基类
 */
@Mapper
@Repository
public interface DiceJobDAO extends MyBatisBaseDao<DiceJob, String> {

    List<JobDescription> findJobDescriptionsByJobTitle(@NonNull String jobTitle);

    List<JobDescription> notCachedJobDescriptionsByJobTitle(String jobTitle, List<String> cachedIds);
}