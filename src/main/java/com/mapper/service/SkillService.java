package com.mapper.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.model.JobSkillMapping;
import com.mapper.model.RoleMatch;
import com.mapper.repository.JobSkillRepository;

@Service
public class SkillService {

    @Autowired
    private JobSkillRepository jobSkillRepository;

    public List<RoleMatch> getMatchingRoles(List<String> userSkills) {

        // Normalize user skills (lowercase)
        List<String> normalizedSkills = new ArrayList<>();
        for (String skill : userSkills) {
            normalizedSkills.add(skill.toLowerCase());
        }

        // Fetch all mappings from DB
        List<JobSkillMapping> allMappings = jobSkillRepository.findAll();

        // Map<Role, Set<required-skills>>
        Map<String, Set<String>> roleSkillMap = new HashMap<>();

        for (JobSkillMapping mapping : allMappings) {
            roleSkillMap
                .computeIfAbsent(mapping.getRole(), k -> new HashSet<>())
                .add(mapping.getSkill().toLowerCase());
        }

        List<RoleMatch> result = new ArrayList<>();

        for (String role : roleSkillMap.keySet()) {

            Set<String> requiredSkillSet = roleSkillMap.get(role);

            // Convert to List for JSON output
            List<String> requiredSkills = new ArrayList<>(requiredSkillSet);

            // Matched Skills
            List<String> matchedSkills = new ArrayList<>();
            for (String userSkill : normalizedSkills) {
                if (requiredSkillSet.contains(userSkill)) {
                    matchedSkills.add(userSkill);
                }
            }

            // Skip if there is no match at all
            if (matchedSkills.isEmpty()) {
                continue;
            }

            // Missing Skills = required - matched
            List<String> missingSkills = new ArrayList<>();
            for (String req : requiredSkillSet) {
                if (!matchedSkills.contains(req)) {
                    missingSkills.add(req);
                }
            }

            // Score calculation
            double score = (double) matchedSkills.size() / requiredSkills.size();

            // Create RoleMatch object
            RoleMatch roleMatch = new RoleMatch(
                role,
                matchedSkills,
                requiredSkills,
                missingSkills,
                score
            );

            result.add(roleMatch);
        }

        // Sort: highest score first
        result.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));

        return result;
    }
}
