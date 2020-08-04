package com.alaitp.job.description.api.thread;


import com.alaitp.job.description.api.entity.JobDescription;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.List;
import java.util.concurrent.*;

public class JobTransitionThreadPool {

    private JobTransitionThreadPool() {
    }

    /**
     * manually create a fixed thread pool, corePoolSize == maximumPoolSize, keepAliveTime = 0
     */
    private static final int MAX_THREAD = Runtime.getRuntime().availableProcessors();
    private static final ThreadFactory namedThreadFactory = new CustomizableThreadFactory("job-pool-%d");
    private static final ExecutorService executorService = new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD,
            0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), namedThreadFactory);

    public static void submit(List<JobDescription> jobDescriptionList) {
        JobTransitionThread jobTransitionThread = new JobTransitionThread(jobDescriptionList);
        executorService.submit(jobTransitionThread);
    }
}
