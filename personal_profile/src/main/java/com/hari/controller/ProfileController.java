package com.hari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hari.entities.Education;
import com.hari.entities.Experience;
import com.hari.entities.Project;
import com.hari.entities.Skill;
import com.hari.entities.UserProfile;
import com.hari.service.IProfileService;
import com.hari.util.PdfUtil;

@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private IProfileService profileService;
	@Autowired
	private PdfUtil pdfUtil;
	
	@GetMapping
	public String showProfileView(Model model) {
		model.addAttribute("profile", profileService.getProfile(1000));
		model.addAttribute("education", profileService.getEducationByProfile(1000));
		model.addAttribute("skills", profileService.getSkillsByProfile(1000));
		model.addAttribute("experience", profileService.getExperienceByProfile(1000));
		model.addAttribute("projects", profileService.getProjectsByProfile(1000));
		return "index";
	}
	
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadResume() {
        UserProfile profile = profileService.getProfile(1000);
        List<Education> educationList = profileService.getEducationByProfile(1000);
        List<Experience> experienceList = profileService.getExperienceByProfile(1000);
        List<Skill> skills = profileService.getSkillsByProfile(1000);
        List<Project> projects = profileService.getProjectsByProfile(1000);

        byte[] pdfBytes = pdfUtil.generateResume(profile, educationList, experienceList, skills, projects);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "harikumar_borra_resume.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

}
