package com.hari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hari.entities.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	List<Project> findByProfileId(Integer profileId);

}
