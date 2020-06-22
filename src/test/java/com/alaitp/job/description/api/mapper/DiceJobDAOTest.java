package com.alaitp.job.description.api.mapper;

import com.alaitp.job.description.api.BaseJunitTest;
import com.alaitp.job.description.api.entity.JobDescription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void notCachedJobDescriptionsByJobTitle() {
        String title = "devops";
        List<String> cachedIds = new ArrayList<>();
        cachedIds.add("34b00a2fece7b3549be964a13b9d5911afa0a1e103707f417c2a1681140591f4");
        cachedIds.add("5a38f0fe6b4a4dfc7e33790456028f2de41276d155e74cd99a626dc3e84b0f03");
        List<JobDescription> jobDescriptionList = diceJobDAO.notCachedJobDescriptionsByJobTitle(title, cachedIds);
        System.out.println(jobDescriptionList);
        assertEquals(4, jobDescriptionList.size());
    }
}