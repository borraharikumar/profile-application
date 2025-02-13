package com.hari.util;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hari.entities.Education;
import com.hari.entities.Experience;
import com.hari.entities.Project;
import com.hari.entities.Skill;
import com.hari.entities.UserProfile;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PdfUtil {

    public byte[] generateResume(UserProfile profile, List<Education> educationList,
                                 List<Experience> experienceList, List<Skill> skills,
                                 List<Project> projects) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Title - Name & Contact Info
            addTitle(document, profile);

            // Professional Summary
            addSectionTitle(document, "Professional Summary");
            document.add(new Paragraph(profile.getProfileSummary(), getNormalFont()));
            document.add(new Paragraph("\n"));

            // Technical Skills Table
            addSectionTitle(document, "Technical Skills");
            addSkillsTable(document, skills);
            document.add(new Paragraph("\n"));

            // Professional Experience
            addSectionTitle(document, "Professional Experience");
            addExperienceSection(document, experienceList);
            document.add(new Paragraph("\n"));

            // Projects
            addSectionTitle(document, "Projects");
            addProjectsSection(document, projects);
            document.add(new Paragraph("\n"));

            // Education
            addSectionTitle(document, "Education");
            addEducationSection(document, educationList);
            document.add(new Paragraph("\n"));

            // Declaration
            addSectionTitle(document, "Declaration");
            document.add(new Paragraph("I hereby declare that the information provided is accurate to the best of my knowledge.", getNormalFont()));
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph(profile.getFullName(), getBoldFont()));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    private void addTitle(Document document, UserProfile profile) throws DocumentException {
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, new BaseColor(0, 76, 153));
        Paragraph title = new Paragraph(profile.getFullName(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Font infoFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY);
        Paragraph contactInfo = new Paragraph("Phone: " + profile.getMobileNumber() + "  |  Email: " + profile.getEmail(), infoFont);
        contactInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(contactInfo);
        document.add(new Paragraph("\n"));
    }

    private void addSectionTitle(Document document, String title) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(51, 102, 204));
        Paragraph sectionTitle = new Paragraph(title, sectionFont);
        sectionTitle.setSpacingBefore(10);
        document.add(sectionTitle);
        document.add(new Paragraph("\n"));
    }

    private void addSkillsTable(Document document, List<Skill> skills) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.addCell(createTableHeader("Category"));
        table.addCell(createTableHeader("Skills"));
        for (Skill skill : skills) {
            table.addCell(skill.getCategory());
            table.addCell(String.join(", ", skill.getSkills()));
        }
        document.add(table);
    }

    private void addExperienceSection(Document document, List<Experience> experienceList) throws DocumentException {
        for (Experience exp : experienceList) {
            document.add(new Paragraph(exp.getJobTitle(), getBoldFont()));
            document.add(new Paragraph(exp.getCompanyName() + " (" + exp.getFromDate() + " - " + exp.getToDate() + ")", getItalicFont()));
            for (String role : exp.getRolesAndResp()) {
                document.add(new Paragraph("• " + role, getNormalFont()));
            }
            document.add(new Paragraph("\n"));
        }
    }

    private void addProjectsSection(Document document, List<Project> projects) throws DocumentException {
        for (Project project : projects) {
            document.add(new Paragraph("Project: " + project.getProjectName(), getBoldFont()));
            document.add(new Paragraph("Client: " + project.getClient(), getItalicFont()));
            document.add(new Paragraph("Role: " + project.getRole(), getNormalFont()));
            document.add(new Paragraph("\n"));
            for (String role : project.getRolesAndResp()) {
                document.add(new Paragraph("• " + role, getNormalFont()));
            }
            document.add(new Paragraph("\n"));
        }
    }

    private void addEducationSection(Document document, List<Education> educationList) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.addCell(createTableHeader("Degree"));
        table.addCell(createTableHeader("Institution"));
        for (Education edu : educationList) {
            table.addCell(edu.getDegree());
            table.addCell(edu.getInstitution());
        }
        document.add(table);
    }

    private PdfPCell createTableHeader(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(new BaseColor(0, 102, 204));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private Font getNormalFont() {
        return new Font(Font.FontFamily.HELVETICA, 12);
    }

    private Font getBoldFont() {
        return new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    }

    private Font getItalicFont() {
        return new Font(Font.FontFamily.HELVETICA, 11, Font.ITALIC);
    }
}
