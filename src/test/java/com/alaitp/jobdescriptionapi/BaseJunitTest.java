package com.alaitp.jobdescriptionapi;

import com.alaitp.job.description.api.JobDescriptionApiApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JobDescriptionApiApplication.class)
public class BaseJunitTest {
}
