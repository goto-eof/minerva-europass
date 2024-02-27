package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ApplicationConst;
import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import com.andreidodu.minervaeuropass.util.DateUtil;
import com.andreidodu.minervaeuropass.util.FileUtil;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProfileFillerUtil {
    private final FileUtil fileUtil;

    public void fillUpProfile(ResumeDTO resumeDTO, Map<String, Object> result) throws IOException {
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
}
