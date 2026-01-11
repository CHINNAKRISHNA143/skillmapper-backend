package com.mapper.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ResumeSkillExtractor {

    // Primary skills only (used for scoring)
    private static final List<String> PRIMARY_SKILLS = Arrays.asList(
            "java", "spring boot", "spring", "mysql", "sql",
            "react", "javascript", "html", "css",
            "python", "pandas", "node", "express",
            "aws", "docker"
    );

    // Old method (not used now, but keep if frontend expects)
    public List<String> extractSkillsFromText(String text) {
        return extractPrimarySkillsFromText(text);
    }

    // NEW → Only primary skills (recommended)
    public List<String> extractPrimarySkillsFromText(String text) {
        if (text == null) return Collections.emptyList();

        String data = text.toLowerCase();
        Set<String> matchedSkills = new LinkedHashSet<>();

        for (String skill : PRIMARY_SKILLS) {
            if (data.contains(skill.toLowerCase())) {
                matchedSkills.add(skill);
            }
        }

        return new ArrayList<>(matchedSkills);
    }
}
