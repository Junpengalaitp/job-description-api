package com.alaitp.jobdescriptionapi.mapper;

import com.alaitp.jobdescriptionapi.entity.DiceJob;
import com.alaitp.jobdescriptionapi.entity.JobDescription;
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
    DiceJob selectByJobId(String jobId);

    JobDescription selectDiceByJobId(String jobId);

    List<JobDescription> findJobDescriptionsByJobTitle(@NonNull String jobTitle);
}