package com.hari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hari.entities.Education;
import com.hari.entities.Experience;
import com.hari.entities.Project;
import com.hari.entities.Skill;
import com.hari.entities.UserProfile;
import com.hari.repository.EducationRepository;
import com.hari.repository.ExperienceRepository;
import com.hari.repository.ProjectRepository;
import com.hari.repository.SkillReposirory;
import com.hari.repository.UserProfileRepository;

@Service
public class ProfileServiceImpl implements IProfileService {

	@Autowired
	private UserProfileRepository profileRepository;
	@Autowired
	private SkillReposirory skillReposirory;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ExperienceRepository experienceRepository;
	@Autowired
	private EducationRepository educationRepository;

	@Override
	public UserProfile getProfile(Integer profileId) {
		return profileRepository.findById(profileId).orElseThrow();
	}

	@Override
	public UserProfile saveProfile(UserProfile profile) {
		return profileRepository.save(profile);
	}

	@Override
	public void deleteProfile(Integer profileId) {
		profileRepository.deleteById(profileId);
	}

	@Override
	public List<Education> getEducationByProfile(Integer profileId) {
		return educationRepository.findByProfileId(profileId);
	}

	@Override
	public Education saveEducation(Education education) {
		return educationRepository.save(education);
	}

	@Override
	public void deleteEducation(Integer educationId) {
		educationRepository.deleteById(educationId);
	}

	@Override
	public List<Experience> getExperienceByProfile(Integer profileId) {
		return experienceRepository.findByProfileId(profileId);
	}

	@Override
	public Experience saveExperience(Experience experience) {
		return experienceRepository.save(experience);
	}

	@Override
	public void deleteExperience(Integer experienceId) {
		experienceRepository.deleteById(experienceId);
	}

	@Override
	public List<Project> getProjectsByProfile(Integer profileId) {
		return projectRepository.findByProfileId(profileId);
	}

	@Override
	public Project saveProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public void deleteProject(Integer projectId) {
		projectRepository.deleteById(projectId);
	}

	@Override
	public List<Skill> getSkillsByProfile(Integer profileId) {
		return skillReposirory.findByProfileId(profileId);
	}

	@Override
	public Skill saveSkill(Skill skill) {
		return skillReposirory.save(skill);
	}

	@Override
	public void deleteSkill(Integer skillId) {
		skillReposirory.deleteById(skillId);
	}

}
