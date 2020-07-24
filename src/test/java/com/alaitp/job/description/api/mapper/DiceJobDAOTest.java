package com.alaitp.job.description.api.mapper;

import com.alaitp.job.description.api.BaseJunitTest;
import com.alaitp.job.description.api.entity.JobDescription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DiceJobDAOTest extends BaseJunitTest {

    @Autowired
    private DiceJobDAO diceJobDAO;

    @Test
    void findJobDescriptionsByJobTitle() {
        String title = "software developer";
        List<JobDescription> jobDescriptionList = diceJobDAO.findJobDescriptionsByJobTitle(title);
        System.out.println(jobDescriptionList);
        assertTrue(jobDescriptionList.size() > 1);
    }
}