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
public class PersonalProjectsFillerUtil {

    private final TemplateConfiguration templateConfiguration;


    public void fillUpPersonalProjects(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getPersonalProjects() != null) {
            resumeDTO.getPersonalProjects().getExperienceList().sort(Comparator.comparing(ExperienceItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECTS_DESCRIPTION, resumeDTO.getPersonalProjects().getDescription());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECTS_TITLE, resumeDTO.getPersonalProjects().getTitle());
            result.put(ResumeConst.FIELD_PERSONAL_PROJECT_LIST, ExperienceCommonFillerUtil.experiencesToListMap(resumeDTO.getPersonalProjects().getExperienceList()));
            List<Map<String, String>> res = calculateTopXTechnologiesFromPersonalProjects(resumeDTO);
            result.put(ResumeConst.KEY_TOP_X_TECHNOLOGIES_FROM_PERSONAL_PROJECTS, res);
            result.put(ResumeConst.FIELD_TOP_ROLES_BY_PERSONAL_PROJECTS, ResumeUtil.listToListMap(ResumeUtil.calculateTopRolesByPersonalProjects(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_EXPERIENCE_BY_PERSONAL_PROJECTS, ResumeUtil.listToListMap(ResumeUtil.calculateYearsExperienceByPersonalProjects(resumeDTO)));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_BACK_END_TECHNOLOGY_IN_PERSONAL_PROJECTS, ResumeUtil.listToString(ResumeUtil.technologiesToYearsOfExperience(resumeDTO.getPersonalProjects().getExperienceList(), ExperienceType.BACK_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience())));
            result.put(ResumeConst.FIELD_YEARS_OF_EXPERIENCE_PER_SINGLE_FRONT_END_TECHNOLOGY_IN_PERSONAL_PROJECTS, ResumeUtil.listToString(ResumeUtil.technologiesToYearsOfExperience(resumeDTO.getPersonalProjects().getExperienceList(), ExperienceType.FRONT_END, templateConfiguration.getMaxSummaryResultsTechYearsExperience())));

        }
    }

    private List<Map<String, String>> calculateTopXTechnologiesFromPersonalProjects(ResumeDTO resumeDTO) {
        List<Map<String, String>> res = new ArrayList<>();

        Map<String, String> topX = new HashMap<>();
        List<String> getTopXBackEndTechnologies = ResumeUtil.calculateTopXBackEndPersonalProjectsTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_BACK_END_TECHNOLOGIES);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXBackEndTechnologies));
        res.add(topX);

        topX = new HashMap<>();
        List<String> getTopXFrontEndTechnologies = ResumeUtil.calculateTopXFrontEndPersonalProjectsTechnologies(resumeDTO, templateConfiguration.getMaxSummaryResultsTechFrequency());
        topX.put(ResumeConst.FIELD_KEY, ResumeConst.VALUE_FRONT_END_TECHNOLOGIES);
        topX.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(getTopXFrontEndTechnologies));
        res.add(topX);
        return res;
    }


}
