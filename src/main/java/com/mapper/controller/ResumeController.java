package com.mapper.controller;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.mapper.service.ResumeSkillExtractor;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin(origins = "http://localhost:5173")
public class ResumeController {

    private final Tika tika = new Tika();

    @Autowired
    private ResumeSkillExtractor resumeSkillExtractor;

    @PostMapping("/upload")
    public List<String> extractSkills(@RequestParam("file") MultipartFile file) {
        try {
            // 1️⃣ Extract full text from resume
            String text = tika.parseToString(file.getInputStream());

            // 2️⃣ Extract only PRIMARY skills (ignores git, github, testing, jira…)
            List<String> primarySkills = resumeSkillExtractor.extractPrimarySkillsFromText(text);

            // 3️⃣ Return extracted skills to frontend
            return primarySkills;

        } catch (Exception e) {
            e.printStackTrace();
            return List.of("error");
        }
    }
}
