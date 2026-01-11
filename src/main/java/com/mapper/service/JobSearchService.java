package com.mapper.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class JobSearchService {

    @Value("${jobs.api.url}")
    private String jobsApiUrl;

    @Value("${jobs.api.key}")
    private String jobsApiKey;

    @Value("${jobs.api.host}")
    private String jobsApiHost;

    private final RestTemplate restTemplate = new RestTemplate();


    /**
     * Fetch jobs with pagination (4 pages), India-only, and newest jobs first.
     */
    public Map<String, Object> fetchJobs(List<String> skills, int page, int numPages, String city) {
        try {
            if (skills == null || skills.isEmpty()) {
                return Map.of("error", "No skills provided");
            }

            // Build job search query
            String query = String.join(" OR ", skills);
            if (!"All".equalsIgnoreCase(city)) {
                query += " AND " + city;
            }

            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

            // Build URL
            String url = jobsApiUrl
                    + "?query=" + encodedQuery
                    + "&page=" + page
                    + "&num_pages=" + numPages
                    + "&country=in"
                    + "&date_posted=all";

            System.out.println("Calling URL: " + url);

            // Headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", jobsApiKey);
            headers.set("X-RapidAPI-Host", jobsApiHost);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            // FIX: Type-safe response extraction
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<Map<String, Object>>() {}
            );

            return response.getBody();

        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("error", "Failed to fetch jobs: " + e.getMessage());
        }
    }
}