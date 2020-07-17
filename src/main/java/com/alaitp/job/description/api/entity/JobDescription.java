package com.alaitp.job.description.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("dice_job")
@Data
public class JobDescription {

    private String jobId;

    @TableField("title")
    private String jobTitle;

    private String company;

    private String tags;

    @TableField("job_desc")
    private String jobDescriptionText;

    private String requestId;
}
