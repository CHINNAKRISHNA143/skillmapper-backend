package com.mapper.model;

import java.util.List;

public class SkillInput {

    private List<String> skills;

    public SkillInput() {
    }

    public SkillInput(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
