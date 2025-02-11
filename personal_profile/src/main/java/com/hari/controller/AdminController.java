package com.hari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hari.entities.Education;
import com.hari.entities.Experience;
import com.hari.entities.Project;
import com.hari.entities.Skill;
import com.hari.entities.UserProfile;
import com.hari.service.IProfileService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IProfileService profileService;

	@GetMapping("/dashboard")
	public String showAdminDashboard() {
		return "dashboard";
	}

	@GetMapping("/edit")
	public String showProfileEditPage(Model model) {
		UserProfile userProfile = new UserProfile();
		userProfile.setProfileId(1000);
		model.addAttribute("profile", userProfile);
		return "user-profile";
	}

	@PostMapping("/save-profile")
	public String saveProfileObject(@ModelAttribute UserProfile profile, RedirectAttributes redirectAttributes) {
		profileService.saveProfile(profile);
		redirectAttributes.addFlashAttribute("message", "Profile data saved successfully...!");
		return "redirect:dashboard";
	}

	@GetMapping("/edit-education")
	public String showEditEducationPage(Model model) {
		List<Education> educations = profileService.getEducationByProfile(1000);
		model.addAttribute("educations", educations);
		return "edit-education";
	}

	@PostMapping("/save-education")
	public ResponseEntity<String> saveEducation(@ModelAttribute Education education) {
		profileService.saveEducation(education);
		return ResponseEntity.ok("Saved");
	}

	@DeleteMapping("/delete-education/{educationId}")
	public ResponseEntity<String> deleteEducation(@PathVariable Integer educationId) {
		profileService.deleteEducation(educationId);
		return ResponseEntity.ok("Deleted");
	}

	@GetMapping("/edit-experience")
	public String showExperiencePage(Model model) {
		model.addAttribute("experiences", profileService.getExperienceByProfile(1000));
		return "edit-experience";
	}

	@PostMapping("/save-experience")
	public ResponseEntity<String> saveExperience(@ModelAttribute Experience experience) {
		profileService.saveExperience(experience);
		return ResponseEntity.ok("Saved");
	}

	@DeleteMapping("/delete-experience/{experienceId}")
	public ResponseEntity<String> deleteExperience(@PathVariable Integer experienceId) {
		profileService.deleteExperience(experienceId);
		return ResponseEntity.ok("Deleted");
	}

	@GetMapping("/edit-projects")
	public String showProjectsPage(Model model) {
		model.addAttribute("projects", profileService.getProjectsByProfile(1000));
		return "edit-projects";
	}

	@PostMapping("/save-project")
	public ResponseEntity<String> saveProject(@ModelAttribute Project project) {
		profileService.saveProject(project);
		return ResponseEntity.ok("Saved");
	}

	@DeleteMapping("/delete-project/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) {
		profileService.deleteProject(projectId);
		return ResponseEntity.ok("Deleted");
	}

	@GetMapping("/edit-skills")
	public String showSkillsPage(Model model) {
		model.addAttribute("skills", profileService.getSkillsByProfile(1000));
		return "edit-skills";
	}

	@PostMapping("/save-skill")
	public ResponseEntity<String> saveSkill(@ModelAttribute Skill skill) {
		profileService.saveSkill(skill);
		return ResponseEntity.ok("Saved");
	}

	@DeleteMapping("/delete-skill/{skillId}")
	public ResponseEntity<String> deleteSkill(@PathVariable Integer skillId) {
		profileService.deleteSkill(skillId);
		return ResponseEntity.ok("Deleted");
	}

}