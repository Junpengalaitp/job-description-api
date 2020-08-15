package com.alaitp.job.description.api.constant;

import java.util.Map;

public interface ControllerConst {
    String REMOTIVE_URL = "http;s://remotive.io/api/remote-jobs?category=software-dev&search=";
    // dummy response for no correlated word found
    Map<String, Map<String, Object>> NO_WORD_FOUND_RES = Map.of(
            "oops, no correlated word found", Map.of("count", 1, "category", "AI"));

    String WORDS = "words";
    String JOBS = "jobs";
    String CATEGORY = "category";
    String COUNT = "count";

}
