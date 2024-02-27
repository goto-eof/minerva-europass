package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.*;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.ResumeService;
import com.andreidodu.minervaeuropass.service.TemplateStrategy;
import com.andreidodu.minervaeuropass.service.impl.filler.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ProfileFillerUtil profileFillerUtil;
    private final ExperienceFillerUtil experienceFillerUtil;
    private final PersonalProjectsFillerUtil personalProjectsFillerUtil;
    private final EducationFillerUtil educationFillerUtil;
    private final OtherSkillsFillerUtil otherSkillsFillerUtil;
    private final SkillMatrixFilleUtil skillMatrixFilleUtil;
    private final OtherFillerUtil otherFillerUtil;
    private final CertificateFillerUtil certificateFillerUtil;

    private final List<TemplateStrategy> templateStrategyList;

    @Override
    public byte[] generateBytes(ResumeDTO resumeDTO, String templateName) throws IOException {
        Map<String, Object> resumeMap = this.processResumeAndReturnMap(resumeDTO);

        byte[] pdfBytes = templateStrategyList
                .stream()
                .filter(strategy -> strategy.accept(templateName))
                .findFirst()
                .map(templateStrategy -> templateStrategy.generate(resumeMap))
                .orElseThrow(() -> new ApplicationException(String.format("Strategy [%s] not found", templateName)));

        String imagePathAndFilename = (String) resumeMap.get(ResumeConst.FIELD_PROFILE_PICTURE_PATH);
        Files.delete(new File(imagePathAndFilename).toPath());
        return pdfBytes;
    }

    @Override
    public Map<String, Object> processResumeAndReturnMap(ResumeDTO resumeDTO) throws IOException {
        Map<String, Object> result = new HashMap<>();
        profileFillerUtil.fillUpProfile(resumeDTO, result);
        experienceFillerUtil.fillUpExperience(resumeDTO, result);
        personalProjectsFillerUtil.fillUpPersonalProjects(resumeDTO, result);
        educationFillerUtil.fillUpEducation(resumeDTO, result);
        otherSkillsFillerUtil.fillUpOtherSkills(resumeDTO, result);
        skillMatrixFilleUtil.fillUpSkillsMatrix(resumeDTO, result);
        otherFillerUtil.fillUpOther(resumeDTO, result);
        certificateFillerUtil.fillUppCertificate(resumeDTO, result);
        return result;
    }


}
