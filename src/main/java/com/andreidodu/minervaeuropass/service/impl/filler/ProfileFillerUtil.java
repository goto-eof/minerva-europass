package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ApplicationConst;
import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.service.FillerUtil;
import com.andreidodu.minervaeuropass.types.ExperienceType;
import com.andreidodu.minervaeuropass.util.DateUtil;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Order(20)
@Component
@RequiredArgsConstructor
public class ProfileFillerUtil implements FillerUtil {

    private final TemplateConfiguration templateConfiguration;
    private final ResumeUtil resumeUtil;

    public boolean accept(ResumeDTO resumeDTO) {
        return true;
    }

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        result.put(ResumeConst.FIELD_ENABLE_PROFILE, ResumeConst.VALUE_TRUE);
        result.put(ResumeConst.FIELD_FIRST_NAME, resumeDTO.getFirstName());
        result.put(ResumeConst.FIELD_LAST_NAME, resumeDTO.getLastName());
        result.put(ResumeConst.FIELD_CITY, resumeDTO.getCity());
        result.put(ResumeConst.FIELD_COUNTY, resumeDTO.getCounty());
        result.put(ResumeConst.FIELD_COUNTRY, resumeDTO.getCountry());
        result.put(ResumeConst.FIELD_CITIZENSHIP, resumeUtil.listToString(resumeDTO.getCitizenshipList()));
        result.put(ResumeConst.FIELD_EMAIL_LIST, resumeUtil.listToListMap(resumeDTO.getEmailMap()));
        result.put(ResumeConst.FIELD_URL_LIST, resumeUtil.listToListMap(resumeDTO.getUrlMap()));
        result.put(ResumeConst.FIELD_PHONE_NUMBER_LIST, resumeUtil.listToListMap(resumeDTO.getPhoneNumberMap()));
        result.put(ResumeConst.FIELD_BIRTH_DATE, DateUtil.formatLocalDate(resumeDTO.getBirthDate(), DateUtil.PATTERN_DD_MM_YYYY));
        result.put(ResumeConst.FIELD_YEARS_OLD, String.valueOf(DateUtil.calculateYearsOld(resumeDTO.getBirthDate(), LocalDate.now())));

        result.put(ResumeConst.FIELD_APPLICATION_NAME, ApplicationConst.APPLICATION_NAME);
        result.put(ResumeConst.FIELD_GENERATED_ON, DateUtil.formatLocalDate(LocalDate.now(), DateUtil.PATTERN_DD_MM_YYYY));
        result.put(ResumeConst.FIELD_JOB_TITLE, resumeDTO.getJobTitle());

        result.put(ResumeConst.FIELD_MAIN_SKILLS, resumeUtil.listToString(resumeDTO.getMainSkillList()));
        result.put(ResumeConst.FIELD_LANGUAGES, resumeUtil.listToString(resumeDTO.getLanguageList()));

        result.put(ResumeConst.FIELD_YEARS_AND_MONTHS_OF_EXPERIENCE, resumeUtil.calculateYearsExperienceFrontEndAndBackEnd(resumeDTO.getExperience()));

        if (templateConfiguration.getShowTopBackEndTechnologies()) {
            String topXMainBackEndTechnologies = resumeUtil.listToString(resumeUtil.technologiesToYearsOfExperienceLight(resumeDTO.getExperience().getExperienceList(), ExperienceType.BACK_END, templateConfiguration.getMaxNumberTopBackEndTechnologies()));
            result.put(ResumeConst.FIELD_TOP_X_MAIN_BACK_END_TECHNOLOGIES, topXMainBackEndTechnologies);
        }
        if (templateConfiguration.getShowTopFrontEndTechnologies()) {
            String topXMainFrontEndTechnologies = resumeUtil.listToString(resumeUtil.technologiesToYearsOfExperienceLight(resumeDTO.getExperience().getExperienceList(), ExperienceType.FRONT_END, templateConfiguration.getMaxNumberTopFrontEndTechnologies()));
            result.put(ResumeConst.FIELD_TOP_X_MAIN_FRONT_END_TECHNOLOGIES, topXMainFrontEndTechnologies);
        }

    }
}
