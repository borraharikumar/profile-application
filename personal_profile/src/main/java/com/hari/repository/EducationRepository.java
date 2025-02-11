package com.hari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.entities.Education;

public interface EducationRepository extends JpaRepository<Education, Integer> {
	
	List<Education> findByProfileId(Integer profileId);

}
