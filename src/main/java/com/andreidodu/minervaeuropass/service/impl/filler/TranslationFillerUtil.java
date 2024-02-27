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
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
//        result.put(ResumeConst.TRANSLATION_, translationService.retrieveTranslation("", locale));
    }
}
