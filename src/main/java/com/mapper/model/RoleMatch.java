package com.mapper.model;

import java.util.List;

public class RoleMatch {

    private String role;
    private List<String> matchedSkills;
    private List<String> requiredSkills;
    private List<String> missingSkills;
    private double score;

    public RoleMatch() {
    }

    public RoleMatch(String role, List<String> matchedSkills, List<String> requiredSkills, List<String> missingSkills, double score) {
        this.role = role;
        this.matchedSkills = matchedSkills;
        this.requiredSkills = requiredSkills;
        this.missingSkills = missingSkills;
        this.score = score;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
