package com.mapper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapper.model.JobSkillMapping;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkillMapping, Long> {

    // find all skills for a given role
    List<JobSkillMapping> findByRole(String role);
}
