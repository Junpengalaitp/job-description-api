package com.alaitp.job.description.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * dice_jobs
 * @author 
 */
@Data
public class DiceJob implements Serializable {

    private static final long serialVersionUID = -90000084L;

    private String jobId;

    private String title;

    private String company;

    private String location;

    private String tags;

    private String category;

    private String jobDesc;

    private String rawDesc;

    private String link;

    private String source;

    private String state;

    private String salary;

    private String employmentType;

    private String remoteAvailable;

    private String jobDate;

    private Date crawledTime;
}