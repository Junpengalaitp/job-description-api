package com.alaitp.jobdescriptionapi.mapper;

import com.alaitp.jobdescriptionapi.BaseJunitTest;
import com.alaitp.jobdescriptionapi.entity.DiceJob;
import com.alaitp.jobdescriptionapi.entity.JobDescription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiceJobDAOTest extends BaseJunitTest {

    @Autowired
    private DiceJobDAO diceJobDAO;

    @Test
    void selectByJobId() {
        String testId = "a8f64c93e1d3fa0cfd4e1aa9fec7b3cd6f86b1e0e47855f6967a0403ccc4325e";
        DiceJob diceJob = diceJobDAO.selectByJobId(testId);
        System.out.println(diceJob);
        assertEquals(diceJob.getJobId(), testId);
    }

    @Test
    void selectDiceByJobId() {
        String testId = "a8f64c93e1d3fa0cfd4e1aa9fec7b3cd6f86b1e0e47855f6967a0403ccc4325e";
        JobDescription diceJob = diceJobDAO.selectDiceByJobId(testId);
        System.out.println(diceJob);
        assertEquals(diceJob.getJobId(), testId);
    }

    @Test
    void findJobDescriptionsByJobTitle() {
        String title = "software developer";
        List<JobDescription> jobDescriptionList = diceJobDAO.findJobDescriptionsByJobTitle(title);
        System.out.println(jobDescriptionList);
        assertTrue(jobDescriptionList.size() > 1);
    }
}