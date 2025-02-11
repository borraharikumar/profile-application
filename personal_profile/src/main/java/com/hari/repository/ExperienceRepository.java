package com.hari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.entities.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Integer> {
	
	List<Experience> findByProfileId(Integer profileId);

}
