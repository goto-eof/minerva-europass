package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.constants.TranslationConst;
import com.andreidodu.minervaeuropass.dto.resume.ExperienceItemDTO;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.global.ThreadContext;
import com.andreidodu.minervaeuropass.service.TranslationService;
import com.andreidodu.minervaeuropass.types.ExperienceType;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ExperienceFillerUtil {

    private final TemplateConfiguration templateConfiguration;
    private final ResumeUtil resumeUtil;
    private final ExperienceCommonFillerUtil experienceCommonFillerUtil;
    private final TranslationService translationService;

    public void fillUpExperience(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getExperience() != null) {
            resumeDTO.getExperience().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_EXPERIENCE_TITLE, resumeDTO.getExperience().getTitle());
            result.put(ResumeConst.FIELD_EXPERIENCE_DESCRIPTION, resumeDTO.getExperience().getDescription());
            result.put(ResumeConst.FIELD_EXPERIENCE_LIST, experienceCommonFillerUtil.experiencesToListMap(resumeDTO.getExperience().getExperienceList()));
            List<Map<String, String>> res = calculateTopXTechnologiesFromExperience(resumeDTO);
            result.put(ResumeConst.KEY_TOP_X_TECHNOLOGIES_FROM_EXPERIENCE, res);
            result.put(ResumeConst.FIELD_TOP_ROLES_BY_EXPERIENCE, resumeUtil.listToListMap(resumeUtil.calculateTopRolesByExperience(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_EXPERIENCE_BY_EXPERIENCE, resumeUtil.listToListMap(resumeUtil.calculateYearsExperienceByExperience(resumeDTO)));
            String yearsOfExperiencePerSingleBackEndTechnology = resumeUtil.listToString(resumeUtil.technologiesToYearsOfExperience(resumeDTO.getExperience().getExperienceList(), ExperienceType.BACK_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience()));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY_IN_EXPERIENCE, yearsOfExperiencePerSingleBackEndTechnology);
            String yearsOfExperiencePerSingleFrontEndTechnology = resumeUtil.listToString(resumeUtil.technologiesToYearsOfExperience(resumeDTO.getExperience().getExperienceList(), ExperienceType.FRONT_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience()));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY_IN_EXPERIENCE, yearsOfExperiencePerSingleFrontEndTechnology);
        }
    }

    private List<Map<String, String>> calculateTopXTechnologiesFromExperience(ResumeDTO resumeDTO) {
        List<Map<String, String>> res = new ArrayList<>();

        Map<String, String> topX = new HashMap<>();
        List<String> getTopXBackEndTechnologies = resumeUtil.calculateTopXBackEndExperienceTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY,
                this.translationService.retrieveTranslation(TranslationConst.KEY_FREQUENCY_BACK_END_TECHNOLOGY, ThreadContext.getRequestContext().getLocale())
        );
        topX.put(ResumeConst.FIELD_VALUE, resumeUtil.listToString(getTopXBackEndTechnologies));
        res.add(topX);

        topX = new HashMap<>();
        List<String> getTopXFrontEndTechnologies = resumeUtil.calculateTopXFrontEndExperienceTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY,
                this.translationService.retrieveTranslation(TranslationConst.KEY_FREQUENCY_FRONT_END_TECHNOLOGY, ThreadContext.getRequestContext().getLocale())
        );
        topX.put(ResumeConst.FIELD_VALUE, resumeUtil.listToString(getTopXFrontEndTechnologies));
        res.add(topX);
        return res;
    }

}
