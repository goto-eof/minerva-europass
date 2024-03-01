package com.andreidodu.minervaeuropass.dto.resume;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResumeDTO {
    private ProfileDTO profile;
    private IntroductionDTO introduction;
    private ExperienceDTO experience;
    private PersonalProjectsDTO personalProjects;
    private EducationDTO education;
    private OtherSkillsDTO otherSkills;
    private SkillsMatrixDTO skillsMatrix;
    private OtherDTO other;
    private CertificatesDTO certificates;
    private String localeName;
}
