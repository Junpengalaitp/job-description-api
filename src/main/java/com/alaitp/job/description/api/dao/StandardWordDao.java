package com.alaitp.job.description.api.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface StandardWordDao {
    String selectStandardWord(String word);
}