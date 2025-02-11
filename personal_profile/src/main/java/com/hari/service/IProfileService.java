package com.hari.service;

import java.util.List;

import com.hari.entities.Education;
import com.hari.entities.Experience;
import com.hari.entities.Project;
import com.hari.entities.Skill;
import com.hari.entities.UserProfile;

public interface IProfileService {

	UserProfile getProfile(Integer profileId);
	UserProfile saveProfile(UserProfile profile);
	void deleteProfile(Integer profileId);

	List<Education> getEducationByProfile(Integer profileId);
	Education saveEducation(Education education);
	void deleteEducation(Integer educationId);

	List<Experience> getExperienceByProfile(Integer profileId);
	Experience saveExperience(Experience experience);
	void deleteExperience(Integer experienceId);

	List<Project> getProjectsByProfile(Integer profileId);
	Project saveProject(Project project);
	void deleteProject(Integer projectId);

	List<Skill> getSkillsByProfile(Integer profileId);
	Skill saveSkill(Skill skill);
	void deleteSkill(Integer skillId);

}