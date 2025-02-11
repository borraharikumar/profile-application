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
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
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
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            // Add Watermark
            addWatermark(writer, profile);

            // Title - Name
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, new BaseColor(0, 102, 204));
            Paragraph title = new Paragraph(profile.getFullName(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Contact Info
            Font infoFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY);
            Paragraph contactInfo = new Paragraph("Email: " + profile.getEmail() + " | Phone: " + profile.getMobileNumber(), infoFont);
            contactInfo.setAlignment(Element.ALIGN_CENTER);
            document.add(contactInfo);

            document.add(new Paragraph("\n"));

            // Profile Summary
            addSectionTitle(document, "Profile Summary");
            document.add(new Paragraph(profile.getProfileSummary(), new Font(Font.FontFamily.HELVETICA, 12)));

            document.add(new Paragraph("\n"));

            // Education (Using Table)
            addSectionTitle(document, "Education");
            PdfPTable eduTable = new PdfPTable(3);
            eduTable.setWidthPercentage(100);
            eduTable.setSpacingBefore(10);
            eduTable.addCell(createTableHeader("Degree"));
            eduTable.addCell(createTableHeader("Institution"));
            eduTable.addCell(createTableHeader("Duration"));

            for (Education edu : educationList) {
                eduTable.addCell(edu.getDegree());
                eduTable.addCell(edu.getInstitution());
                eduTable.addCell(edu.getStartYear() + " - " + edu.getEndYear());
            }
            document.add(eduTable);

            document.add(new Paragraph("\n"));

            // Experience
            addSectionTitle(document, "Experience");
            for (Experience exp : experienceList) {
                document.add(new Paragraph(exp.getJobTitle() + " at " + exp.getCompanyName() + 
                        " (" + exp.getFromDate() + " - " + exp.getToDate() + ")", 
                        new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

                List<String> roles = exp.getRolesAndResp();
                for (String role : roles) {
                    document.add(new Paragraph("• " + role, new Font(Font.FontFamily.HELVETICA, 11)));
                }
                document.add(new Paragraph("\n"));
            }

            // Skills (Using Table)
            addSectionTitle(document, "Skills");
            PdfPTable skillTable = new PdfPTable(2);
            skillTable.setWidthPercentage(100);
            skillTable.setSpacingBefore(10);
            skillTable.addCell(createTableHeader("Category"));
            skillTable.addCell(createTableHeader("Skill"));

            for (Skill skill : skills) {
                skillTable.addCell(skill.getCategory());
                skillTable.addCell(skill.getSkills().toString());
            }
            document.add(skillTable);

            document.add(new Paragraph("\n"));

            // Projects
            addSectionTitle(document, "Projects");
            for (Project project : projects) {
                document.add(new Paragraph(project.getProjectName() + " (Client: " + project.getClient() + ")", 
                        new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

                document.add(new Paragraph("Role: " + project.getRole(), new Font(Font.FontFamily.HELVETICA, 11, Font.ITALIC)));
                document.add(new Paragraph(project.getProjectDescription(), new Font(Font.FontFamily.HELVETICA, 11)));

                List<String> roles = project.getRolesAndResp();
                for (String role : roles) {
                    document.add(new Paragraph("• " + role, new Font(Font.FontFamily.HELVETICA, 11)));
                }
                document.add(new Paragraph("\n"));
            }

            // Declaration
            addSectionTitle(document, "Declaration");
            document.add(new Paragraph("I hereby declare that the above-mentioned information is true and correct to the best of my knowledge and belief.", new Font(Font.FontFamily.HELVETICA, 12)));

            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph(profile.getFullName(), new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    private void addSectionTitle(Document document, String title) throws DocumentException {
        Font sectionFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(51, 153, 255)); // Light Blue
        Paragraph sectionTitle = new Paragraph(title, sectionFont);
        sectionTitle.setSpacingBefore(10);
        document.add(sectionTitle);
        document.add(new Paragraph("________________________________________________________________________"));
        document.add(new Paragraph("\n"));
    }

    private PdfPCell createTableHeader(String text) {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(new BaseColor(0, 102, 204)); // Dark Blue
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }

    private void addWatermark(PdfWriter writer, UserProfile profile) {
        PdfContentByte canvas = writer.getDirectContentUnder();
        Font watermarkFont = new Font(Font.FontFamily.HELVETICA, 60, Font.BOLD, new GrayColor(0.75f)); // Light Gray

        Phrase watermark = new Phrase(profile.getFullName(), watermarkFont);
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 
                                   297.5f, 421, writer.getPageNumber() % 2 == 1 ? 45 : -45);
    }
}
