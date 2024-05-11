package com.andreidodu.minervaeuropass.service.templatestrategies.basic.filler;

import com.andreidodu.minervaeuropass.constants.ApplicationConst;
import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.dto.resume.KeyValueDTO;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.service.FillerUtil;
import com.andreidodu.minervaeuropass.types.ExperienceType;
import com.andreidodu.minervaeuropass.util.DateUtil;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Order(20)
@Component
@RequiredArgsConstructor
public class ProfileFillerUtil implements FillerUtil {

    private final TemplateConfiguration templateConfiguration;
    private final ResumeUtil resumeUtil;

    public boolean accept(ResumeDTO resumeDTO) {
        return resumeDTO.getProfile() != null &&
                BooleanUtils.isTrue(resumeDTO.getProfile().getEnabled());
    }

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        result.put(ResumeConst.FIELD_ENABLE_PROFILE, ResumeConst.VALUE_TRUE);
        result.put(ResumeConst.FIELD_FIRST_NAME, resumeDTO.getProfile().getFirstName());
        result.put(ResumeConst.FIELD_LAST_NAME, resumeDTO.getProfile().getLastName());
        result.put(ResumeConst.FIELD_CITY, resumeDTO.getProfile().getCity());
        result.put(ResumeConst.FIELD_COUNTY, resumeDTO.getProfile().getCounty());
        result.put(ResumeConst.FIELD_COUNTRY, resumeDTO.getProfile().getCountry());
        result.put(ResumeConst.FIELD_CITIZENSHIP, resumeUtil.listToString(resumeDTO.getProfile().getCitizenshipList()));
        result.put(ResumeConst.FIELD_EMAIL_LIST, resumeUtil.listToListMap(listOfKeyValueToMap(resumeDTO.getProfile().getEmailMap())));
        result.put(ResumeConst.FIELD_URL_LIST, resumeUtil.listToListMap(listOfKeyValueToMap(resumeDTO.getProfile().getUrlMap())));
        result.put(ResumeConst.FIELD_PHONE_NUMBER_LIST, resumeUtil.listToListMap(listOfKeyValueToMap(resumeDTO.getProfile().getPhoneNumberMap())));
        result.put(ResumeConst.FIELD_BIRTH_DATE, DateUtil.formatLocalDate(resumeDTO.getProfile().getBirthDate(), DateUtil.PATTERN_DD_MM_YYYY));
        result.put(ResumeConst.FIELD_YEARS_OLD, toString(DateUtil.calculateYearsOld(resumeDTO.getProfile().getBirthDate(), LocalDate.now())));

        result.put(ResumeConst.FIELD_APPLICATION_NAME, ApplicationConst.APPLICATION_NAME);
        result.put(ResumeConst.FIELD_GENERATED_ON, DateUtil.formatLocalDate(LocalDate.now(), DateUtil.PATTERN_DD_MM_YYYY));
        result.put(ResumeConst.FIELD_JOB_TITLE, resumeDTO.getProfile().getJobTitle());

        result.put(ResumeConst.FIELD_MAIN_SKILLS, resumeUtil.listToString(resumeDTO.getProfile().getMainSkillList()));
        result.put(ResumeConst.FIELD_LANGUAGES, resumeUtil.listToString(resumeDTO.getProfile().getLanguageList()));

        if (resumeDTO.getExperience() != null) {
            result.put(ResumeConst.FIELD_YEARS_AND_MONTHS_OF_EXPERIENCE, resumeUtil.calculateYearsExperienceFrontEndAndBackEnd(resumeDTO.getExperience()));
        }
        if (templateConfiguration.getShowTopBackEndTechnologies()) {
            String topXMainBackEndTechnologies = resumeUtil.listToString(resumeUtil.technologiesToYearsOfExperienceLight(resumeDTO.getExperience().getExperienceList(), ExperienceType.BACK_END, templateConfiguration.getMaxNumberTopBackEndTechnologies()));
            result.put(ResumeConst.FIELD_TOP_X_MAIN_BACK_END_TECHNOLOGIES, topXMainBackEndTechnologies);
        }
        if (templateConfiguration.getShowTopFrontEndTechnologies()) {
            String topXMainFrontEndTechnologies = resumeUtil.listToString(resumeUtil.technologiesToYearsOfExperienceLight(resumeDTO.getExperience().getExperienceList(), ExperienceType.FRONT_END, templateConfiguration.getMaxNumberTopFrontEndTechnologies()));
            result.put(ResumeConst.FIELD_TOP_X_MAIN_FRONT_END_TECHNOLOGIES, topXMainFrontEndTechnologies);
        }

    }

    private Map<String, String> listOfKeyValueToMap(List<KeyValueDTO> list) {
        if (list == null) {
            return new HashMap<>();
        }
        return list.stream().collect(Collectors.toMap(KeyValueDTO::getKey, KeyValueDTO::getValue));
    }


    private String toString(Number number) {
        if (number == null) {
            return null;
        }
        return number.toString();
    }
}
