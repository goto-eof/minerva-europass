package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TranslationConst;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.global.ThreadContext;
import com.andreidodu.minervaeuropass.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TranslationFillerUtil {

    public static final String KEY_EXPERIENCE_DATES = "com.me.experience.dates";
    public static final String KEY_EXPERIENCE_ROLE = "com.me.experience.role";
    public static final String KEY_EXPERIENCE_MAIN_ACTIVITIES = "com.me.experience.main.activities";
    public static final String KEY_EXPERIENCE_NAME = "com.me.experience.name";
    public static final String KEY_EXPERIENCE_DESCRIPTION = "com.me.experience.description";
    public static final String KEY_EXPERIENCE_FRONT_END_TECHNOLOGIES = "com.me.experience.front-end.technologies";
    public static final String KEY_EXPERIENCE_BACK_END_TECHNOLOGIES = "com.me.experience.back-end.technologies";
    public static final String KEY_EXPERIENCE_TOOLS = "com.me.experience.tools";
    public static final String KEY_EXPERIENCE_WORKING_METHODOLOGY = "com.me.experience.working.methodology";
    public static final String KEY_EXPERIENCE_COMPANY = "com.me.experience.company";
    public static final String KEY_EXPERIENCE_SECTOR = "com.me.experience.sector";
    public static final String KEY_EXPERIENCE_URL = "com.me.experience.url";
    private final TranslationService translationService;

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        String locale = ThreadContext.getRequestContext().getLocale();
        result.put(ResumeConst.TRANSLATION_SUMMARY_JOB_EXPERIENCES, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_JOB_EXPERIENCES, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_PERSONAL_PROJECTS, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_PERSONAL_PROJECTS, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_CERTIFICATES, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_CERTIFICATES, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_YEARS_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_YEARS_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_YEARS_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_YEARS_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_NOTE_1, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_NOTE_1, locale));
        result.put(ResumeConst.TRANSLATION_SUMMARY_NOTE_2, translationService.retrieveTranslation(TranslationConst.KEY_SUMMARY_NOTE_2, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_DATES, translationService.retrieveTranslation(KEY_EXPERIENCE_DATES, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_ROLE, translationService.retrieveTranslation(KEY_EXPERIENCE_ROLE, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_MAIN_ACTIVITIES, translationService.retrieveTranslation(KEY_EXPERIENCE_MAIN_ACTIVITIES, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_NAME, translationService.retrieveTranslation(KEY_EXPERIENCE_NAME, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_DESCRIPTION, translationService.retrieveTranslation(KEY_EXPERIENCE_DESCRIPTION, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_FRONT_END_TECHNOLOGIES, translationService.retrieveTranslation(KEY_EXPERIENCE_FRONT_END_TECHNOLOGIES, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_BACK_END_TECHNOLOGIES, translationService.retrieveTranslation(KEY_EXPERIENCE_BACK_END_TECHNOLOGIES, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_TOOLS, translationService.retrieveTranslation(KEY_EXPERIENCE_TOOLS, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_WORKING_METHODOLOGY, translationService.retrieveTranslation(KEY_EXPERIENCE_WORKING_METHODOLOGY, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_COMPANY, translationService.retrieveTranslation(KEY_EXPERIENCE_COMPANY, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_SECTOR, translationService.retrieveTranslation(KEY_EXPERIENCE_SECTOR, locale));
        result.put(ResumeConst.TRANSLATION_EXPERIENCE_URL, translationService.retrieveTranslation(KEY_EXPERIENCE_URL, locale));
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
    }
}
