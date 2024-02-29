package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TranslationConst;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.global.ThreadContext;
import com.andreidodu.minervaeuropass.service.TranslationService;
import com.andreidodu.minervaeuropass.service.impl.FillerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Order(10)
@Component
@RequiredArgsConstructor
public class TranslationFillerUtil implements FillerUtil {
    private final TranslationService translationService;

    public boolean accept(ResumeDTO resumeDTO) {
        return true;
    }

    @Override
    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        String locale = ThreadContext.getRequestContext().getLocale();
        result.put(ResumeConst.TRANSLATION_SUMMARY_JOB_EXPERIENCES, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_JOB_EXPERIENCES, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_PERSONAL_PROJECTS, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_PERSONAL_PROJECTS, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_CERTIFICATES, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_CERTIFICATES, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_YEARS_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_YEARS_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_YEARS_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_YEARS_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_NOTE_1, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_NOTE_1, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_NOTE_2, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_NOTE_2, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_DATES, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_DATES, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_ROLE, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_ROLE, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_MAIN_ACTIVITIES, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_MAIN_ACTIVITIES, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_NAME, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_NAME, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_DESCRIPTION, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_DESCRIPTION, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_FRONT_END_TECHNOLOGIES, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_FRONT_END_TECHNOLOGIES, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_BACK_END_TECHNOLOGIES, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_BACK_END_TECHNOLOGIES, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_TOOLS, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_TOOLS, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_WORKING_METHODOLOGY, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_WORKING_METHODOLOGY, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_COMPANY, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_COMPANY, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_SECTOR, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_SECTOR, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_URL, translationService.retrieveTranslation(TranslationConst.KEY_EXPERIENCE_URL, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_FIRST_NAME, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_FIRST_NAME, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_LAST_NAME, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_LAST_NAME, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_NATIONALITY, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_NATIONALITY, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_DATE_OF_BIRTH, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_DATE_OF_BIRTH, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_YEARS_IT_EXPERIENCES, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_YEARS_IT_EXPERIENCES, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_PHONE_NUMBERS, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_PHONE_NUMBERS, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_EMAILS, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_EMAILS, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_WEBSITES, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_WEBSITES, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_MAIN_SKILLS, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_MAIN_SKILLS, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_MAIN_BACK_END_SKILLS, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_MAIN_BACK_END_SKILLS, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_MAIN_FRONT_END_SKILLS, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_MAIN_FRONT_END_SKILLS, locale));
        result.put(ResumeConst.TRANSLATION_PROFILE_LANGUAGES, translationService.retrieveTranslation(TranslationConst.KEY_PROFILE_LANGUAGES, locale));
        result.put(ResumeConst.TRANSLATION_RESUME, translationService.retrieveTranslation(TranslationConst.KEY_RESUME, locale));
        result.put(ResumeConst.TRANSLATION_GENERATED_ON, translationService.retrieveTranslation(TranslationConst.KEY_GENERATED_ON, locale));
        result.put(ResumeConst.TRANSLATION_PAGE_OF, translationService.retrieveTranslation(TranslationConst.KEY_PAGE_OF, locale));
        result.put(ResumeConst.TRANSLATION_CONSENT_PROCESSING_DATA, translationService.retrieveTranslation(TranslationConst.KEY_CONSENT_PROCESSING_DATA, locale));
        result.put(ResumeConst.TRANSLATION_YEARS, translationService.retrieveTranslation(TranslationConst.KEY_YEARS, locale));
        result.put(ResumeConst.TRANSLATION_LIST_ORDER_DESCRIPTION, translationService.retrieveTranslation(TranslationConst.KEY_LIST_ORDER_DESCRIPTION, locale));
    }
}
