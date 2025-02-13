package com.hari.entities;

import java.time.LocalDate;
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
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer experienceId;
	private Integer profileId;

	private String companyName;
	private String jobTitle;
	private LocalDate fromDate;
	private LocalDate toDate;

	@ElementCollection
	@CollectionTable(name = "experience_roles", joinColumns = @JoinColumn(name = "experience_id"))
	@Column(name = "role")
	private List<String> rolesAndResp;

}