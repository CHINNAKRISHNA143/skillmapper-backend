package com.mapper.model;

public class Course {
    private String skill;
    private String title;
    private String duration;
    private String level;
    private String link;
    private String source; // e.g., YouTube, FreeCourseSite, etc.

    public Course() {}

    public Course(String skill, String title, String duration, String level, String link, String source) {
        this.skill = skill;
        this.title = title;
        this.duration = duration;
        this.level = level;
        this.link = link;
        this.source = source;
    }

    // getters and setters
    public String getSkill() { return skill; }
    public void setSkill(String skill) { this.skill = skill; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
}
