package com.hari.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Education {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer educationId;
	private Integer profileId;
	
	private String degree;
	private String institution;
	private Integer startYear;
	private Integer endYear;
	private Double cgpa;

}
