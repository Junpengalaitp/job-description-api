package com.alaitp.job.description.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * standard_word
 *
 * @author
 */
@Data
public class StandardWord implements Serializable {
    private static final long serialVersionUID = -3611766728413639649L;

    private String standardWord;

    private String otherWords;

    private String category;

    private Date modificationTime;
}