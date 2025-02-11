package com.hari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.entities.Skill;

public interface SkillReposirory extends JpaRepository<Skill, Integer> {
	
	List<Skill> findByProfileId(Integer profileId);

}
