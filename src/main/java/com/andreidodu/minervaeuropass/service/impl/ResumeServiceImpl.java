package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.constants.ApplicationConst;
import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.dto.*;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.ResumeService;
import com.andreidodu.minervaeuropass.service.TemplateStrategy;
import com.andreidodu.minervaeuropass.types.ExperienceType;
import com.andreidodu.minervaeuropass.util.DateUtil;
import com.andreidodu.minervaeuropass.util.FileUtil;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final FileUtil fileUtil;
    private final List<TemplateStrategy> templateStrategyList;
    private final TemplateConfiguration templateConfiguration;

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
        fillUpProfile(resumeDTO, result);
        fillUpExperience(resumeDTO, result);
        fillUpPersonalProjects(resumeDTO, result);
        fillUpEducation(resumeDTO, result);
        fillUpOtherSkills(resumeDTO, result);
        fillUpSkillsMatrix(resumeDTO, result);
        fillUpOther(resumeDTO, result);
        fillUppCertificate(resumeDTO, result);
        return result;
    }

    private void fillUppCertificate(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getCertificates() != null) {
            result.put(ResumeConst.FIELD_CERTIFICATES_TITLE, resumeDTO.getCertificates().getTitle());
            result.put(ResumeConst.FIELD_CERTIFICATES_DESCRIPTION, resumeDTO.getCertificates().getDescription());
            result.put(ResumeConst.FIELD_CERTIFICATES_LIST, certificatesListToListOfMaps(resumeDTO.getCertificates().getCertificateList()));
        }
    }

    private List<Map<String, Object>> certificatesListToListOfMaps(List<CertificateItemDTO> certificateList) {
        certificateList.sort(Comparator.comparing(CertificateItemDTO::getDate).reversed());
        return certificateList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put(ResumeConst.FIELD_NAME, item.getName());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());
            result.put(ResumeConst.FIELD_DATE, DateUtil.formatLocalDate(item.getDate(), DateUtil.PATTERN_MMM_YYYY));
            result.put(ResumeConst.FIELD_LINK, item.getLink());
            result.put(ResumeConst.FIELD_BACK_END_TECHNOLOGY_LIST, ResumeUtil.listToString(item.getBackEndTechnologyList()));
            result.put(ResumeConst.FIELD_FRONT_END_TECHNOLOGY_LIST, ResumeUtil.listToString(item.getFrontEndTechnologyList()));

            return result;
        }).toList();


    }

    private static void fillUpOther(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getOther() != null) {
            result.put(ResumeConst.FIELD_OTHER_TITLE, resumeDTO.getOther().getTitle());
            result.put(ResumeConst.FIELD_OTHER_DESCRIPTION, resumeDTO.getOther().getDescription());
            result.put(ResumeConst.FIELD_OTHER_LIST, otherListToListOfMaps(resumeDTO.getOther().getOtherList()));
        }
    }

    private static List<Map<String, String>> otherListToListOfMaps(List<OtherItemDTO> otherList) {
        return otherList.stream().map(item -> {
            Map<String, String> result = new HashMap<>();
            result.put(ResumeConst.FIELD_KEY, item.getKey());
            result.put(ResumeConst.FIELD_VALUE, item.getValue());
            return result;
        }).toList();
    }

    private void fillUpProfile(ResumeDTO resumeDTO, Map<String, Object> result) throws IOException {
        result.put(ResumeConst.FIELD_FIRST_NAME, resumeDTO.getFirstName());
        result.put(ResumeConst.FIELD_LAST_NAME, resumeDTO.getLastName());
        result.put(ResumeConst.FIELD_CITY, resumeDTO.getCity());
        result.put(ResumeConst.FIELD_COUNTY, resumeDTO.getCounty());
        result.put(ResumeConst.FIELD_COUNTRY, resumeDTO.getCountry());
        result.put(ResumeConst.FIELD_CITIZENSHIP, ResumeUtil.listToString(resumeDTO.getCitizenshipList()));
        result.put(ResumeConst.FIELD_EMAIL_LIST, ResumeUtil.listToListMap(resumeDTO.getEmailMap()));
        result.put(ResumeConst.FIELD_URL_LIST, ResumeUtil.listToListMap(resumeDTO.getUrlMap()));
        result.put(ResumeConst.FIELD_PHONE_NUMBER_LIST, ResumeUtil.listToListMap(resumeDTO.getPhoneNumberMap()));
        result.put(ResumeConst.FIELD_BIRTH_DATE, DateUtil.formatLocalDate(resumeDTO.getBirthDate(), DateUtil.PATTERN_DD_MM_YYYY));
        result.put(ResumeConst.FIELD_YEARS_OLD, String.valueOf(DateUtil.calculateYearsOld(resumeDTO.getBirthDate(), LocalDate.now())));

        if (resumeDTO.getIntroduction() != null) {
            result.put(ResumeConst.FIELD_INTRODUCTION_TITLE, resumeDTO.getIntroduction().getTitle());
            result.put(ResumeConst.FIELD_INTRODUCTION_CONTENT, resumeDTO.getIntroduction().getContent());
            result.put(ResumeConst.FIELD_INTRODUCTION_FOOTER, resumeDTO.getIntroduction().getFooter());
        }
        result.put(ResumeConst.FIELD_APPLICATION_NAME, ApplicationConst.APPLICATION_NAME);
        result.put(ResumeConst.FIELD_GENERATED_ON, DateUtil.formatLocalDate(LocalDate.now(), DateUtil.PATTERN_DD_MM_YYYY));
        result.put(ResumeConst.FIELD_JOB_TITLE, resumeDTO.getJobTitle());

        result.put(ResumeConst.FIELD_MAIN_SKILLS, ResumeUtil.listToString(resumeDTO.getMainSkillList()));
        result.put(ResumeConst.FIELD_LANGUAGES, ResumeUtil.listToString(resumeDTO.getLanguageList()));

        String path = fileUtil.saveImage(resumeDTO.getImage());
        result.put(ResumeConst.FIELD_PROFILE_PICTURE_PATH, path);

        result.put(ResumeConst.FIELD_YEARS_AND_MONTHS_OF_EXPERIENCE, ResumeUtil.calculateYearsExperienceFrontEndAndBackEnd(resumeDTO.getExperience()));
    }

    private static void fillUpSkillsMatrix(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getSkillsMatrix() != null) {
            result.put(ResumeConst.FIELD_SKILLS_MATRIX_TITLE, resumeDTO.getSkillsMatrix().getTitle());
            result.put(ResumeConst.FIELD_SKILLS_MATRIX_DESCRIPTION, resumeDTO.getSkillsMatrix().getDescription());
            result.put(ResumeConst.FIELD_SKILLS_MATRIX_LIST, skillsMatrixListToListMap(resumeDTO.getSkillsMatrix().getSkillsMatrixList()));

        }
    }

    private static void fillUpOtherSkills(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getOtherSkills() != null) {
            result.put(ResumeConst.FIELD_OTHER_SKILLS_TITLE, resumeDTO.getOtherSkills().getTitle());
            result.put(ResumeConst.FIELD_OTHER_SKILLS_DESCRIPTION, resumeDTO.getOtherSkills().getDescription());
            result.put(ResumeConst.FIELD_OTHER_SKILL_SOCIAL_LIST, ResumeUtil.listToString(resumeDTO.getOtherSkills().getSocialList()));
            result.put(ResumeConst.FIELD_OTHER_SKILL_ORGANIZATIONAL_LIST, ResumeUtil.listToString(resumeDTO.getOtherSkills().getOrganizationalList()));
            result.put(ResumeConst.FIELD_OTHER_SKILL_OTHER_LIST, ResumeUtil.listToString(resumeDTO.getOtherSkills().getOtherList()));
        }
    }

    private static void fillUpEducation(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getEducation() != null) {
            resumeDTO.getEducation().getEducationList().sort(Comparator.comparing(EducationItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_EDUCATION_TITLE, resumeDTO.getEducation().getTitle());
            result.put(ResumeConst.FIELD_EDUCATION_DESCRIPTION, resumeDTO.getEducation().getDescription());
            result.put(ResumeConst.FIELD_EDUCATION_LIST, educationToListMap(resumeDTO.getEducation().getEducationList()));
        }
    }

    private void fillUpPersonalProjects(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getPersonalProjects() != null) {
            resumeDTO.getPersonalProjects().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECTS_DESCRIPTION, resumeDTO.getPersonalProjects().getDescription());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECTS_TITLE, resumeDTO.getPersonalProjects().getTitle());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECT_LIST, experiencesToListMap(resumeDTO.getPersonalProjects().getExperienceList()));
            List<Map<String, String>> res = calculateTopXTechnologiesFromPersonalProjects(resumeDTO);
            result.put(ResumeConst.KEY_TOP_X_TECHNOLOGIES_FROM_PERSONAL_PROJECTS, res);
            result.put(ResumeConst.FIELD_TOP_ROLES_BY_PERSONAL_PROJECTS, ResumeUtil.listToListMap(ResumeUtil.calculateTopRolesByPersonalProjects(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_EXPERIENCE_BY_PERSONAL_PROJECTS, ResumeUtil.listToListMap(ResumeUtil.calculateYearsExperienceByPersonalProjects(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY_IN_PERSONAL_PROJECTS, ResumeUtil.listToString(ResumeUtil.technologiesToYearsOfExperience(resumeDTO.getPersonalProjects().getExperienceList(), ExperienceType.BACK_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience())));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY_IN_PERSONAL_PROJECTS, ResumeUtil.listToString(ResumeUtil.technologiesToYearsOfExperience(resumeDTO.getPersonalProjects().getExperienceList(), ExperienceType.FRONT_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience())));

        }
    }

    private void fillUpExperience(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getExperience() != null) {
            resumeDTO.getExperience().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_EXPERIENCE_TITLE, resumeDTO.getExperience().getTitle());
            result.put(ResumeConst.FIELD_EXPERIENCE_DESCRIPTION, resumeDTO.getExperience().getDescription());
            result.put(ResumeConst.FIELD_EXPERIENCE_LIST, experiencesToListMap(resumeDTO.getExperience().getExperienceList()));
            List<Map<String, String>> res = calculateTopXTechnologiesFromExperience(resumeDTO);
            result.put(ResumeConst.KEY_TOP_X_TECHNOLOGIES_FROM_EXPERIENCE, res);
            result.put(ResumeConst.FIELD_TOP_ROLES_BY_EXPERIENCE, ResumeUtil.listToListMap(ResumeUtil.calculateTopRolesByExperience(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_EXPERIENCE_BY_EXPERIENCE, ResumeUtil.listToListMap(ResumeUtil.calculateYearsExperienceByExperience(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY_IN_EXPERIENCE, ResumeUtil.listToString(ResumeUtil.technologiesToYearsOfExperience(resumeDTO.getExperience().getExperienceList(), ExperienceType.BACK_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience())));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY_IN_EXPERIENCE, ResumeUtil.listToString(ResumeUtil.technologiesToYearsOfExperience(resumeDTO.getExperience().getExperienceList(), ExperienceType.FRONT_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience())));
        }
    }


    private List<Map<String, String>> calculateTopXTechnologiesFromPersonalProjects(ResumeDTO resumeDTO) {
        List<Map<String, String>> res = new ArrayList<>();

        Map<String, String> topX = new HashMap<>();
        List<String> getTopXBackEndTechnologies = ResumeUtil.calculateTopXBackEndPersonalProjectsTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_BACK_END_TECHNOLOGIES);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXBackEndTechnologies));
        res.add(topX);

        topX = new HashMap<>();
        List<String> getTopXFrontEndTechnologies = ResumeUtil.calculateTopXFrontEndPersonalProjectsTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_FRONT_END_TECHNOLOGIES);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXFrontEndTechnologies));
        res.add(topX);
        return res;
    }

    private List<Map<String, String>> calculateTopXTechnologiesFromExperience(ResumeDTO resumeDTO) {
        List<Map<String, String>> res = new ArrayList<>();

        Map<String, String> topX = new HashMap<>();
        List<String> getTopXBackEndTechnologies = ResumeUtil.calculateTopXBackEndExperienceTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_BACK_END_TECHNOLOGIES);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXBackEndTechnologies));
        res.add(topX);

        topX = new HashMap<>();
        List<String> getTopXFrontEndTechnologies = ResumeUtil.calculateTopXFrontEndExperienceTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_FRONT_END_TECHNOLOGIES);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXFrontEndTechnologies));
        res.add(topX);
        return res;
    }


    private static List<Map<String, Object>> skillsMatrixListToListMap(List<SkillMatrixItemDTO> skillsMatrixList) {
        return skillsMatrixList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            item.getValues().sort(String::compareTo);

            result.put(ResumeConst.FIELD_KEY, item.getName());
            result.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(item.getValues()));

            return result;
        }).toList();
    }


    private static List<Map<String, Object>> educationToListMap(List<EducationItemDTO> educationItemDTOList) {
        return educationItemDTOList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put(ResumeConst.FIELD_DATE_FROM, DateUtil.formatLocalDate(item.getDateFrom(), DateUtil.PATTERN_MMM_YYYY));
            result.put(ResumeConst.FIELD_DATE_TO, DateUtil.calculateDateTo(item.getDateTo()));
            result.put(ResumeConst.FIELD_NAME, item.getName());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());

            return result;
        }).toList();
    }

    private static List<Map<String, Object>> experiencesToListMap(List<ExperienceItemDTO> experienceList) {
        return experienceList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put(ResumeConst.FIELD_DATE_FROM, DateUtil.formatLocalDate(item.getDateFrom(), DateUtil.PATTERN_MMM_YYYY));
            result.put(ResumeConst.FIELD_DATE_TO, DateUtil.calculateDateTo(item.getDateTo()));
            result.put(ResumeConst.FIELD_JOB_TITLE, item.getJobTitle());
            result.put(ResumeConst.FIELD_WORKING_METHODOLOGY, item.getWorkingMethodology());
            result.put(ResumeConst.FIELD_NAME, item.getName());
            result.put(ResumeConst.FIELD_URL, item.getUrl());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());
            result.put(ResumeConst.FIELD_MAIN_ACTIVITIES, item.getMainActivities());
            result.put(ResumeConst.FIELD_COMPANY, item.getCustomer());
            result.put(ResumeConst.FIELD_SECTOR, item.getSector());
            result.put(ResumeConst.FIELD_IS_WORKED_AS_FRONT_END_DEVELOPER, ResumeUtil.toBooleanString(item.getIsWorkedAsFrontEndDeveloper()));
            result.put(ResumeConst.FIELD_IS_WORKED_AS_BACK_END_DEVELOPER, ResumeUtil.toBooleanString(item.getIsWorkedAsBackEndDeveloper()));
            result.put(ResumeConst.FIELD_BACK_END_TECHNOLOGY_LIST, ResumeUtil.listToString(item.getBackEndTechnologyList()));
            result.put(ResumeConst.FIELD_FRONT_END_TECHNOLOGY_LIST, ResumeUtil.listToString(item.getFrontEndTechnologyList()));
            result.put(ResumeConst.FIELD_TOOL_LIST, ResumeUtil.listToString(item.getToolList()));
            if (item.getUrlList() != null && !item.getUrlList().isEmpty()) {
                result.put(ResumeConst.FIELD_URL_LIST, urlListToListMap(item.getUrlList()));
            }

            int monthsBetween = DateUtil.calculateMonthsBetween(item.getDateFrom(), item.getDateTo());
            int yearsBetween = DateUtil.calculateYearsBetween(item.getDateFrom(), item.getDateTo());

            result.put(ResumeConst.FIELD_JOB_DURATION, ResumeUtil.calculateTimeAgoString(yearsBetween, monthsBetween + 1));

            return result;
        }).toList();
    }


    private static List<Map<String, String>> urlListToListMap(List<UrlDTO> urlList) {
        return urlList.stream().map(item -> {
            Map<String, String> result = new HashMap<>();
            result.put(ResumeConst.FIELD_URL, item.getUrl());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());
            return result;
        }).toList();
    }

}
