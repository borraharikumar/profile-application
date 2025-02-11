package com.hari.entities;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer projectId;
	private Integer profileId;
	
	private String projectName;
	private String client;
	private String role;
	private String projectDescription;
	
	@ElementCollection
	@CollectionTable(name = "project_roles", joinColumns = @JoinColumn(name = "project_id"))
	@Column(name = "role")
	private List<String> rolesAndResp;

}
