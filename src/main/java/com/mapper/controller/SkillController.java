package com.mapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mapper.model.SkillInput;
import com.mapper.model.RoleMatch;
import com.mapper.service.SkillService;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:5173")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/match")
    public List<RoleMatch> getMatchingRoles(@RequestBody SkillInput skillInput) {
        return skillService.getMatchingRoles(skillInput.getSkills());
    }
}
