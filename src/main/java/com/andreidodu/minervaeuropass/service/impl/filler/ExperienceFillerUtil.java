package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.dto.ExperienceItemDTO;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import com.andreidodu.minervaeuropass.types.ExperienceType;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ExperienceFillerUtil {

    private final TemplateConfiguration templateConfiguration;

    public void fillUpExperience(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getExperience() != null) {
            resumeDTO.getExperience().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_EXPERIENCE_TITLE, resumeDTO.getExperience().getTitle());
            result.put(ResumeConst.FIELD_EXPERIENCE_DESCRIPTION, resumeDTO.getExperience().getDescription());
            result.put(ResumeConst.FIELD_EXPERIENCE_LIST, ExperienceCommonFillerUtil.experiencesToListMap(resumeDTO.getExperience().getExperienceList()));
            List<Map<String, String>> res = calculateTopXTechnologiesFromExperience(resumeDTO);
            result.put(ResumeConst.KEY_TOP_X_TECHNOLOGIES_FROM_EXPERIENCE, res);
            result.put(ResumeConst.FIELD_TOP_ROLES_BY_EXPERIENCE, ResumeUtil.listToListMap(ResumeUtil.calculateTopRolesByExperience(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_EXPERIENCE_BY_EXPERIENCE, ResumeUtil.listToListMap(ResumeUtil.calculateYearsExperienceByExperience(resumeDTO)));
            String yearsOfExperiencePerSingleBackEndTechnology = ResumeUtil.listToString(ResumeUtil.technologiesToYearsOfExperience(resumeDTO.getExperience().getExperienceList(), ExperienceType.BACK_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience()));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY_IN_EXPERIENCE, yearsOfExperiencePerSingleBackEndTechnology);
            String yearsOfExperiencePerSingleFrontEndTechnology = ResumeUtil.listToString(ResumeUtil.technologiesToYearsOfExperience(resumeDTO.getExperience().getExperienceList(), ExperienceType.FRONT_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience()));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY_IN_EXPERIENCE, yearsOfExperiencePerSingleFrontEndTechnology);
        }
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

}
