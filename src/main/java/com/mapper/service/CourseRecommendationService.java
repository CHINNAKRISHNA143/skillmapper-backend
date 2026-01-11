package com.mapper.service;

import com.mapper.model.Course;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.*;

@Service
public class CourseRecommendationService {

    // static mapping: normalized skill -> list of courses
    private final Map<String, List<Course>> courseMap = new HashMap<>();

    @PostConstruct
    public void init() {
        // Populate static mapping (add/edit entries as you like)
        courseMap.put("java", Arrays.asList(
                new Course("Java", "Java Full Course - for Beginners", "8 hours", "Beginner",
                        "https://www.youtube.com/watch?v=grEKMHGYyns", "YouTube"),
                new Course("Java", "Core Java Tutorial", "6 hours", "Beginner",
                        "https://www.youtube.com/watch?v=UmnCZ7-9yDY", "YouTube")
        ));

        courseMap.put("spring boot", Arrays.asList(
                new Course("Spring Boot", "Spring Boot Tutorial - Full Course", "4 hours", "Beginner",
                        "https://www.youtube.com/watch?v=vtPkZShrvXQ", "YouTube"),
                new Course("Spring Boot", "Spring Framework & Spring Boot", "6 hours", "Intermediate",
                        "https://www.udemy.com/course/spring-hibernate-tutorial", "Udemy (free/promo)")
        ));

        courseMap.put("sql", Arrays.asList(
                new Course("SQL", "SQL Tutorial - Full Database Course", "4 hours", "Beginner",
                        "https://www.youtube.com/watch?v=HXV3zeQKqGY", "YouTube"),
                new Course("SQL", "Mode Analytics SQL Tutorial", "3 hours", "Beginner",
                        "https://mode.com/sql-tutorial/", "Mode (free)")
        ));

        courseMap.put("react", Arrays.asList(
                new Course("React", "React JS - Full Course for Beginners", "6 hours", "Beginner",
                        "https://www.youtube.com/watch?v=DLX62G4lc44", "YouTube"),
                new Course("React", "React - The Complete Guide", "8 hours", "Intermediate",
                        "https://www.udemy.com/course/react-the-complete-guide-incl-redux/", "Udemy")
        ));

        courseMap.put("javascript", Arrays.asList(
        	    new Course(
        	        "JavaScript",
        	        "JavaScript Full Course for Beginners",
        	        "6 hours",
        	        "Beginner",
        	        "https://www.youtube.com/watch?v=PkZNo7MFNFg",
        	        "YouTube"
        	    ),
        	    new Course(
        	        "JavaScript",
        	        "JavaScript Crash Course",
        	        "1.5 hours",
        	        "Beginner",
        	        "https://www.youtube.com/watch?v=hdI2bqOjy3c",
        	        "YouTube"
        	    )
        	));

        
        courseMap.put("html", Arrays.asList(
        	    new Course(
        	        "HTML",
        	        "HTML Full Course - Beginner to Advanced",
        	        "4 hours",
        	        "Beginner",
        	        "https://www.youtube.com/watch?v=pQN-pnXPaVg",
        	        "YouTube"
        	    ),
        	    new Course(
        	        "HTML",
        	        "HTML Crash Course",
        	        "2 hours",
        	        "Beginner",
        	        "https://www.youtube.com/watch?v=UB1O30fR-EE",
        	        "YouTube"
        	    )
        	));
        
        courseMap.put("css", Arrays.asList(
        	    new Course(
        	        "CSS",
        	        "CSS Full Course for Beginners",
        	        "5 hours",
        	        "Beginner",
        	        "https://www.youtube.com/watch?v=OXGznpKZ_sA",
        	        "YouTube"
        	    ),
        	    new Course(
        	        "CSS",
        	        "CSS Crash Course",
        	        "2 hours",
        	        "Beginner",
        	        "https://www.youtube.com/watch?v=yfoY53QXEnI",
        	        "YouTube"
        	    )
        	));


        courseMap.put("python", Arrays.asList(
                new Course("Python", "Python Full Course - Beginner to Advanced", "8 hours", "Beginner",
                        "https://www.youtube.com/watch?v=_uQrJ0TkZlc", "YouTube")
        ));

        courseMap.put("data analysis", Arrays.asList(
                new Course("Data Analysis", "Data Analysis with Python - Full Course", "6 hours", "Beginner",
                        "https://www.youtube.com/watch?v=r-uOLxNrNk8", "YouTube")
        ));

        courseMap.put("git", Arrays.asList(
                new Course("Git", "Git & GitHub Crash Course", "2 hours", "Beginner",
                        "https://www.youtube.com/watch?v=RGOj5yH7evk", "YouTube")
        ));

        courseMap.put("docker", Arrays.asList(
                new Course("Docker", "Docker Full Course for Beginners", "3.5 hours", "Beginner",
                        "https://www.youtube.com/watch?v=fqMOX6JJhGo", "YouTube")
        ));

        courseMap.put("aws", Arrays.asList(
                new Course("AWS", "AWS Certified Cloud Practitioner - Full Course", "7 hours", "Beginner",
                        "https://www.youtube.com/watch?v=Ia-UEYYR44s", "YouTube")
        ));

        // add more mappings as needed
    }

    /**
     * Return recommended courses for the provided skills.
     * Skill matching is case-insensitive and does substring matching.
     *
     * @param skills list of skills (may be raw strings)
     * @return map skill -> list of Course
     */
    public Map<String, List<Course>> recommendCoursesForSkills(List<String> skills) {
        Map<String, List<Course>> result = new LinkedHashMap<>();

        if (skills == null || skills.isEmpty()) {
            return result;
        }

        for (String raw : skills) {
            if (raw == null) continue;
            String s = raw.trim().toLowerCase();
            // attempt exact match
            if (courseMap.containsKey(s)) {
                result.put(raw, courseMap.get(s));
                continue;
            }
            // attempt contains match (e.g., "spring" -> "spring boot")
            List<Course> found = new ArrayList<>();
            for (Map.Entry<String, List<Course>> entry : courseMap.entrySet()) {
                if (entry.getKey().contains(s) || s.contains(entry.getKey())) {
                    found.addAll(entry.getValue());
                }
            }
            if (!found.isEmpty()) {
                result.put(raw, found);
            } else {
                // fallback: no mapping found
                result.put(raw, Collections.emptyList());
            }
        }

        return result;
    }
}
