package com.andreidodu.iteuropass.service.impl;

import com.andreidodu.iteuropass.constants.ApplicationConst;
import com.andreidodu.iteuropass.constants.ResumeConst;
import com.andreidodu.iteuropass.dto.*;
import com.andreidodu.iteuropass.service.ResumeService;
import com.andreidodu.iteuropass.util.DateUtil;
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
        result.put(ResumeConst.FIELD_EMAIL_LIST, listToListMap(resumeDTO.getEmailMap()));
        result.put(ResumeConst.FIELD_URL_LIST, listToListMap(resumeDTO.getUrlMap()));
        result.put(ResumeConst.FIELD_PHONE_NUMBER_LIST, listToListMap(resumeDTO.getPhoneNumberMap()));
        result.put(ResumeConst.FIELD_BIRTH_DATE, resumeDTO.getBirthDate().toString());
        result.put("yearsOld", String.valueOf(calculateYearsOld(resumeDTO.getBirthDate(), LocalDate.now())));

        if (resumeDTO.getIntroduction() != null) {
            result.put("introductionTitle", resumeDTO.getIntroduction().getTitle());
            result.put("introductionContent", resumeDTO.getIntroduction().getContent());
        }
        result.put("applicationName", ApplicationConst.APPLICATION_NAME);
        result.put("generatedOn", DateUtil.formatLocalDate(LocalDate.now(), DateUtil.PATTERN_DD_MM_YYYY));
        result.put("jobTitle", resumeDTO.getJobTitle());

        result.put("mainSkills", listToString(resumeDTO.getMainSkillList()));
        result.put("languages", listToString(resumeDTO.getLanguageList()));

        if (resumeDTO.getExperience() != null) {
            resumeDTO.getExperience().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put("experienceTitle", resumeDTO.getExperience().getTitle());
            result.put("experienceDescription", resumeDTO.getExperience().getDescription());
            result.put("experienceList", experiencesToListMap(resumeDTO.getExperience().getExperienceList()));
        }

        if (resumeDTO.getPersonalProjects() != null) {
            resumeDTO.getPersonalProjects().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put("personalProjectsDescription", resumeDTO.getPersonalProjects().getDescription());
            result.put("personalProjectsTitle", resumeDTO.getPersonalProjects().getTitle());
            result.put("personalProjectList", experiencesToListMap(resumeDTO.getPersonalProjects().getExperienceList()));
        }

        if (resumeDTO.getEducation() != null) {
            resumeDTO.getEducation().getEducationList().sort(Comparator.comparing(EducationItemDTO::getDateFrom).reversed());
            result.put("educationTitle", resumeDTO.getEducation().getTitle());
            result.put("educationDescription", resumeDTO.getEducation().getDescription());
            result.put("educationList", educationToListMap(resumeDTO.getEducation().getEducationList()));
        }

        if (resumeDTO.getOtherSkills() != null) {
            result.put("otherSkillsTitle", resumeDTO.getOtherSkills().getTitle());
            result.put("otherSkillsDescription", resumeDTO.getOtherSkills().getDescription());
            result.put("otherSkillSocialList", listToString(resumeDTO.getOtherSkills().getSocialList()));
            result.put("otherSkillOrganizationalList", listToString(resumeDTO.getOtherSkills().getOrganizationalList()));
            result.put("otherSkillOtherList", listToString(resumeDTO.getOtherSkills().getOtherList()));
        }

        if (resumeDTO.getSkillsMatrix() != null) {
            result.put("skillsMatrixTitle", resumeDTO.getSkillsMatrix().getTitle());
            result.put("skillsMatrixDescription", resumeDTO.getSkillsMatrix().getDescription());
            result.put("skillsMatrixList", skillsMatrixListToListMap(resumeDTO.getSkillsMatrix().getSkillsMatrixList()));

        }

        return result;
    }

    private List<Map<String, Object>> skillsMatrixListToListMap(List<SkillMatrixItemDTO> skillsMatrixList) {
        return skillsMatrixList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            item.getValues().sort(String::compareTo);

            result.put("key", item.getName());
            result.put("value", listToString(item.getValues()));

            return result;
        }).toList();
    }


    private List<Map<String, Object>> educationToListMap(List<EducationItemDTO> educationItemDTOList) {
        return educationItemDTOList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put("dateFrom", DateUtil.formatLocalDate(item.getDateFrom(), DateUtil.PATTERN_MMM_YYYY));
            result.put("dateTo", calculateDateTo(item.getDateTo()));
            result.put("name", item.getName());
            result.put("description", item.getDescription());

            return result;
        }).toList();
    }

    private List<Map<String, Object>> experiencesToListMap(List<ExperienceItemDTO> experienceList) {
        return experienceList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put("dateFrom", DateUtil.formatLocalDate(item.getDateFrom(), DateUtil.PATTERN_MMM_YYYY));
            result.put("dateTo", calculateDateTo(item.getDateTo()));
            result.put("jobTitle", item.getJobTitle());
            result.put("name", item.getName());
            result.put("url", item.getUrl());
            result.put("description", item.getDescription());
            result.put("mainActivities", item.getMainActivities());
            result.put("customer", item.getCustomer());
            result.put("sector", item.getSector());
            result.put("backEndTechnologyList", listToString(item.getBackEndTechnologyList()));
            result.put("frontEndTechnologyList", listToString(item.getFrontEndTechnologyList()));
            result.put("toolList", listToString(item.getToolList()));
            if (item.getUrlList() != null && !item.getUrlList().isEmpty()) {
                result.put("urlList", urlListToListMap(item.getUrlList()));
            }

            return result;
        }).toList();
    }

    private List<Map<String, String>> urlListToListMap(List<UrlDTO> urlList) {

        return urlList.stream().map(item -> {
            Map<String, String> result = new HashMap<>();
            result.put("url", item.getUrl());
            result.put("description", item.getDescription());
            return result;
        }).toList();
    }

    private String calculateDateTo(LocalDate dateTo) {
        if (dateTo == null) {
            return "oggi";
        }
        return DateUtil.formatLocalDate(dateTo, DateUtil.PATTERN_MMM_YYYY);
    }

    private List<Map<String, Object>> listToListMap(Map<String, String> listOfMap) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : listOfMap.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put(ResumeConst.FIELD_NAME, key);
            map.put(ResumeConst.FIELD_VALUE, listOfMap.get(key));
            result.add(map);
        }

        return result;
    }

    private List<Map<String, Object>> listToList(List<String> list) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (String item : list) {
            Map<String, Object> map = new HashMap<>();
            map.put(ResumeConst.FIELD_VALUE, item);
            result.add(map);
        }

        return result;
    }

    private String listToString(List<String> list) {
        if (list.isEmpty()) {
            return null;
        }
        return StringUtils.join(list, '•')
                .replace("•", " • ");
    }

    private int calculateYearsOld(LocalDate from, LocalDate to) {
        return from.until(to)
                .getYears();
    }


}
