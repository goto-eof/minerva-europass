package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.constants.TranslationConst;
import com.andreidodu.minervaeuropass.dto.ExperienceItemDTO;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import com.andreidodu.minervaeuropass.service.TranslationService;
import com.andreidodu.minervaeuropass.types.ExperienceType;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class PersonalProjectsFillerUtil {

    private final TemplateConfiguration templateConfiguration;
    private final ExperienceCommonFillerUtil experienceCommonFillerUtil;
    private final ResumeUtil resumeUtil;
    private final TranslationService translationService;


    public void fillUpPersonalProjects(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getPersonalProjects() != null) {
            resumeDTO.getPersonalProjects().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECTS_DESCRIPTION, resumeDTO.getPersonalProjects().getDescription());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECTS_TITLE, resumeDTO.getPersonalProjects().getTitle());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECT_LIST, experienceCommonFillerUtil.experiencesToListMap(resumeDTO.getPersonalProjects().getExperienceList(), resumeDTO.getLocaleName()));
            List<Map<String, String>> res = calculateTopXTechnologiesFromPersonalProjects(resumeDTO);
            result.put(ResumeConst.KEY_TOP_X_TECHNOLOGIES_FROM_PERSONAL_PROJECTS, res);
            result.put(ResumeConst.FIELD_TOP_ROLES_BY_PERSONAL_PROJECTS, resumeUtil.listToListMap(resumeUtil.calculateTopRolesByPersonalProjects(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_EXPERIENCE_BY_PERSONAL_PROJECTS, resumeUtil.listToListMap(resumeUtil.calculateYearsExperienceByPersonalProjects(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY_IN_PERSONAL_PROJECTS, resumeUtil.listToString(resumeUtil.technologiesToYearsOfExperience(resumeDTO.getPersonalProjects().getExperienceList(), ExperienceType.BACK_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience(), resumeDTO.getLocaleName())));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY_IN_PERSONAL_PROJECTS, resumeUtil.listToString(resumeUtil.technologiesToYearsOfExperience(resumeDTO.getPersonalProjects().getExperienceList(), ExperienceType.FRONT_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience(), resumeDTO.getLocaleName())));

        }
    }

    private List<Map<String, String>> calculateTopXTechnologiesFromPersonalProjects(ResumeDTO resumeDTO) {
        List<Map<String, String>> res = new ArrayList<>();

        Map<String, String> topX = new HashMap<>();
        List<String> getTopXBackEndTechnologies = resumeUtil.calculateTopXFrequencyBackEndPersonalProjectsTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY,
                this.translationService.retrieveTranslation(TranslationConst.KEY_FREQUENCY_BACK_END_TECHNOLOGY, resumeDTO.getLocaleName())
        );
        topX.put(ResumeConst.FIELD_VALUE, resumeUtil.listToString(getTopXBackEndTechnologies));
        res.add(topX);

        topX = new HashMap<>();
        List<String> getTopXFrontEndTechnologies = resumeUtil.calculateTopXFrequencyFrontEndPersonalProjectsTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY,
                this.translationService.retrieveTranslation(TranslationConst.KEY_FREQUENCY_FRONT_END_TECHNOLOGY, resumeDTO.getLocaleName())
        );
        topX.put(ResumeConst.FIELD_VALUE, resumeUtil.listToString(getTopXFrontEndTechnologies));
        res.add(topX);
        return res;
    }


}
