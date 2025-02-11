package com.hari.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserProfile {

	@Id
	private Integer profileId;

	private String fullName;
	private String email;
	private String mobileNumber;
	private String profileSummary;

}
