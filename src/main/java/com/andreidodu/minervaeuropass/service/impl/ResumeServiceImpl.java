package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.constants.ApplicationConst;
import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.*;
import com.andreidodu.minervaeuropass.service.ResumeService;
import com.andreidodu.minervaeuropass.util.DateUtil;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {


    @Override
    public Map<String, Object> processResumeAndReturnMap(ResumeDTO resumeDTO) {
        Map<String, Object> result = new HashMap<>();
        result.put(ResumeConst.FIELD_FIRST_NAME, resumeDTO.getFirstName());
        result.put(ResumeConst.FIELD_LAST_NAME, resumeDTO.getLastName());
        result.put(ResumeConst.FIELD_CITY, resumeDTO.getCity());
        result.put(ResumeConst.FIELD_COUNTY, resumeDTO.getCounty());
        result.put(ResumeConst.FIELD_COUNTRY, resumeDTO.getCountry());
        result.put(ResumeConst.FIELD_CITIZENSHIP, StringUtils.join(resumeDTO.getCitizenshipList(), ',').replace(",", ", "));
        result.put(ResumeConst.FIELD_EMAIL_LIST, ResumeUtil.listToListMap(resumeDTO.getEmailMap()));
        result.put(ResumeConst.FIELD_URL_LIST, ResumeUtil.listToListMap(resumeDTO.getUrlMap()));
        result.put(ResumeConst.FIELD_PHONE_NUMBER_LIST, ResumeUtil.listToListMap(resumeDTO.getPhoneNumberMap()));
        result.put(ResumeConst.FIELD_BIRTH_DATE, resumeDTO.getBirthDate().toString());
        result.put(ResumeConst.FIELD_YEARS_OLD, String.valueOf(DateUtil.calculateYearsOld(resumeDTO.getBirthDate(), LocalDate.now())));

        if (resumeDTO.getIntroduction() != null) {
            result.put(ResumeConst.FIELD_INTRODUCTION_TITLE, resumeDTO.getIntroduction().getTitle());
            result.put(ResumeConst.FIELD_INTRODUCTION_CONTENT, resumeDTO.getIntroduction().getContent());
        }
        result.put(ResumeConst.FIELD_APPLICATION_NAME, ApplicationConst.APPLICATION_NAME);
        result.put(ResumeConst.FIELD_GENERATED_ON, DateUtil.formatLocalDate(LocalDate.now(), DateUtil.PATTERN_DD_MM_YYYY));
        result.put(ResumeConst.FIELD_JOB_TITLE, resumeDTO.getJobTitle());

        result.put(ResumeConst.FIELD_MAIN_SKILLS, ResumeUtil.listToString(resumeDTO.getMainSkillList()));
        result.put(ResumeConst.FIELD_LANGUAGES, ResumeUtil.listToString(resumeDTO.getLanguageList()));

        if (resumeDTO.getExperience() != null) {
            resumeDTO.getExperience().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_EXPERIENCE_TITLE, resumeDTO.getExperience().getTitle());
            result.put(ResumeConst.FIELD_EXPERIENCE_DESCRIPTION, resumeDTO.getExperience().getDescription());
            result.put(ResumeConst.FIELD_EXPERIENCE_LIST, experiencesToListMap(resumeDTO.getExperience().getExperienceList()));
        }

        if (resumeDTO.getPersonalProjects() != null) {
            resumeDTO.getPersonalProjects().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECTS_DESCRIPTION, resumeDTO.getPersonalProjects().getDescription());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECTS_TITLE, resumeDTO.getPersonalProjects().getTitle());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECT_LIST, experiencesToListMap(resumeDTO.getPersonalProjects().getExperienceList()));
        }

        if (resumeDTO.getEducation() != null) {
            resumeDTO.getEducation().getEducationList().sort(Comparator.comparing(EducationItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_EDUCATION_TITLE, resumeDTO.getEducation().getTitle());
            result.put(ResumeConst.FIELD_EDUCATION_DESCRIPTION, resumeDTO.getEducation().getDescription());
            result.put(ResumeConst.FIELD_EDUCATION_LIST, educationToListMap(resumeDTO.getEducation().getEducationList()));
        }

        if (resumeDTO.getOtherSkills() != null) {
            result.put(ResumeConst.FIELD_OTHER_SKILLS_TITLE, resumeDTO.getOtherSkills().getTitle());
            result.put(ResumeConst.FIELD_OTHER_SKILLS_DESCRIPTION, resumeDTO.getOtherSkills().getDescription());
            result.put(ResumeConst.FIELD_OTHER_SKILL_SOCIAL_LIST, ResumeUtil.listToString(resumeDTO.getOtherSkills().getSocialList()));
            result.put(ResumeConst.FIELD_OTHER_SKILL_ORGANIZATIONAL_LIST, ResumeUtil.listToString(resumeDTO.getOtherSkills().getOrganizationalList()));
            result.put(ResumeConst.FIELD_OTHER_SKILL_OTHER_LIST, ResumeUtil.listToString(resumeDTO.getOtherSkills().getOtherList()));
        }

        if (resumeDTO.getSkillsMatrix() != null) {
            result.put(ResumeConst.FIELD_SKILLS_MATRIX_TITLE, resumeDTO.getSkillsMatrix().getTitle());
            result.put(ResumeConst.FIELD_SKILLS_MATRIX_DESCRIPTION, resumeDTO.getSkillsMatrix().getDescription());
            result.put(ResumeConst.FIELD_SKILLS_MATRIX_LIST, skillsMatrixListToListMap(resumeDTO.getSkillsMatrix().getSkillsMatrixList()));

        }

        List<Map<String, String>> res = calculateTopXTechnologiesFromExperience(resumeDTO);
        result.put(ResumeConst.KEY_TOP_X_TECHNOLOGIES_FROM_EXPERIENCE, res);

        res = calculateTopXTechnologiesFromPersonalProjects(resumeDTO);
        result.put(ResumeConst.KEY_TOP_X_TECHNOLOGIES_FROM_PERSONAL_PROJECTS, res);

        return result;
    }

    private List<Map<String, String>> calculateTopXTechnologiesFromPersonalProjects(ResumeDTO resumeDTO) {
        List<Map<String, String>> res = new ArrayList<>();

        Map<String, String> topX = new HashMap<>();
        List<String> getTopXBackEndTechnologies = ResumeUtil.calculateTopXBackEndPersonalProjectsTechnologies(resumeDTO);
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_BACK_END);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXBackEndTechnologies));
        res.add(topX);

        topX = new HashMap<>();
        List<String> getTopXFrontEndTechnologies = ResumeUtil.calculateTopXFrontEndPersonalProjectsTechnologies(resumeDTO);
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_FRONT_END);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXFrontEndTechnologies));
        res.add(topX);
        return res;
    }

    private List<Map<String, String>> calculateTopXTechnologiesFromExperience(ResumeDTO resumeDTO) {
        List<Map<String, String>> res = new ArrayList<>();

        Map<String, String> topX = new HashMap<>();
        List<String> getTopXBackEndTechnologies = ResumeUtil.calculateTopXBackEndExperienceTechnologies(resumeDTO);
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_BACK_END);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXBackEndTechnologies));
        res.add(topX);

        topX = new HashMap<>();
        List<String> getTopXFrontEndTechnologies = ResumeUtil.calculateTopXFrontEndExperienceTechnologies(resumeDTO);
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_FRONT_END);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXFrontEndTechnologies));
        res.add(topX);
        return res;
    }


    private List<Map<String, Object>> skillsMatrixListToListMap(List<SkillMatrixItemDTO> skillsMatrixList) {
        return skillsMatrixList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            item.getValues().sort(String::compareTo);

            result.put(ResumeConst.FIELD_KEY, item.getName());
            result.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(item.getValues()));

            return result;
        }).toList();
    }


    private List<Map<String, Object>> educationToListMap(List<EducationItemDTO> educationItemDTOList) {
        return educationItemDTOList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put(ResumeConst.FIELD_DATE_FROM, DateUtil.formatLocalDate(item.getDateFrom(), DateUtil.PATTERN_MMM_YYYY));
            result.put(ResumeConst.FIELD_DATE_TO, DateUtil.calculateDateTo(item.getDateTo()));
            result.put(ResumeConst.FIELD_NAME, item.getName());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());

            return result;
        }).toList();
    }

    private List<Map<String, Object>> experiencesToListMap(List<ExperienceItemDTO> experienceList) {
        return experienceList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put(ResumeConst.FIELD_DATE_FROM, DateUtil.formatLocalDate(item.getDateFrom(), DateUtil.PATTERN_MMM_YYYY));
            result.put(ResumeConst.FIELD_DATE_TO, DateUtil.calculateDateTo(item.getDateTo()));
            result.put(ResumeConst.FIELD_JOB_TITLE, item.getJobTitle());
            result.put(ResumeConst.FIELD_NAME, item.getName());
            result.put(ResumeConst.FIELD_URL, item.getUrl());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());
            result.put(ResumeConst.FIELD_MAIN_ACTIVITIES, item.getMainActivities());
            result.put(ResumeConst.FIELD_CUSTOMER, item.getCustomer());
            result.put(ResumeConst.FIELD_SECTOR, item.getSector());
            result.put(ResumeConst.FIELD_BACK_END_TECHNOLOGY_LIST, ResumeUtil.listToString(item.getBackEndTechnologyList()));
            result.put(ResumeConst.FIELD_FRONT_END_TECHNOLOGY_LIST, ResumeUtil.listToString(item.getFrontEndTechnologyList()));
            result.put(ResumeConst.FIELD_TOOL_LIST, ResumeUtil.listToString(item.getToolList()));
            if (item.getUrlList() != null && !item.getUrlList().isEmpty()) {
                result.put(ResumeConst.FIELD_URL_LIST, urlListToListMap(item.getUrlList()));
            }

            return result;
        }).toList();
    }

    private List<Map<String, String>> urlListToListMap(List<UrlDTO> urlList) {

        return urlList.stream().map(item -> {
            Map<String, String> result = new HashMap<>();
            result.put(ResumeConst.FIELD_URL, item.getUrl());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());
            return result;
        }).toList();
    }

}
