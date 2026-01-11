package com.mapper.controller;

import com.mapper.model.Course;
import com.mapper.service.CourseRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

    @Autowired
    private CourseRecommendationService recommendationService;

    /**
     * POST /api/courses/recommend
     * Body: { "skills": ["Java","Spring Boot"] }
     * Response: { "Java": [Course,...], "Spring Boot": [Course,...] }
     */
    @PostMapping("/recommend")
    public Map<String, List<Course>> recommend(@RequestBody Map<String, Object> payload) {
        Object skillsObj = payload.get("skills");
        List<String> skills = List.of();
        if (skillsObj instanceof List<?>) {
            skills = ((List<?>) skillsObj).stream()
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .toList();
        }
        return recommendationService.recommendCoursesForSkills(skills);
    }
}
