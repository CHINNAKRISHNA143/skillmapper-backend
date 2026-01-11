package com.mapper.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapper.service.JobSearchService;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:5173")
public class JobSearchController {

    @Autowired
    private JobSearchService jobSearchService;

    @PostMapping("/search")
    public Map<String, Object> getJobs(@RequestBody Map<String, Object> payload) {

        // Safe extraction of roles list
        List<String> roles = new ArrayList<>();
        Object rolesObj = payload.get("roles");

        if (rolesObj instanceof List<?>) {
            for (Object item : (List<?>) rolesObj) {
                if (item != null) {
                    roles.add(item.toString());
                }
            }
        }

        // Optional parameters with defaults
        int page = payload.get("page") instanceof Number ? ((Number) payload.get("page")).intValue() : 1;
        int numPages = payload.get("numPages") instanceof Number ? ((Number) payload.get("numPages")).intValue() : 4;
        String city = payload.get("city") != null ? payload.get("city").toString() : "All";

        return jobSearchService.fetchJobs(roles, page, numPages, city);
    }
}