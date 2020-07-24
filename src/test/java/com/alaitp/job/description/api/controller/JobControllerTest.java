package com.alaitp.job.description.api.controller;

import com.alaitp.job.description.api.BaseJunitTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@AutoConfigureMockMvc
class JobControllerTest extends BaseJunitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetJobListByJobTitle() throws Exception {
        String[] testJobTitles = {"software engineer", "software engineer", "devops", "java", "python", "javascript"};
        for (String title : testJobTitles) {
            mockMvc.perform(
                    MockMvcRequestBuilders.get("/job-list/" + title + "/testId")
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[*].jobTitle", Matchers.hasSize(Matchers.greaterThanOrEqualTo(10))));
        }
    }
}