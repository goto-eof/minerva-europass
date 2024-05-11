package com.andreidodu.minervaeuropass.service.templatestrategies.basic.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TranslationConst;
import com.andreidodu.minervaeuropass.dto.resume.ExperienceItemDTO;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.dto.resume.SkillMatrixItemDTO;
import com.andreidodu.minervaeuropass.global.ThreadContext;
import com.andreidodu.minervaeuropass.service.FillerUtil;
import com.andreidodu.minervaeuropass.service.TranslationService;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Order(60)
@Component
@RequiredArgsConstructor
public class SkillMatrixFillerUtil implements FillerUtil {
    private final ResumeUtil resumeUtil;
    private final TranslationService translationService;

    public boolean accept(ResumeDTO resumeDTO) {
        return resumeDTO.getSkillsMatrix() != null &&
                BooleanUtils.isTrue(resumeDTO.getSkillsMatrix().getEnabled());
    }

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        result.put(ResumeConst.FIELD_ENABLE_SKILL_MATRIX, ResumeConst.VALUE_TRUE);
        result.put(ResumeConst.FIELD_SKILLS_MATRIX_TITLE, resumeDTO.getSkillsMatrix().getTitle());
        result.put(ResumeConst.FIELD_SKILLS_MATRIX_DESCRIPTION, resumeDTO.getSkillsMatrix().getDescription());
        result.put(ResumeConst.FIELD_SKILLS_MATRIX_LIST, skillsMatrixListToListMap(resumeDTO, resumeDTO.getSkillsMatrix().getSkillsMatrixList()));
    }

    private List<Map<String, Object>> skillsMatrixListToListMap(ResumeDTO resumeDTO, List<SkillMatrixItemDTO> skillsMatrixList) {
        List<Map<String, Object>> list = skillsMatrixList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            item.getValues().sort(String::compareTo);

            result.put(ResumeConst.FIELD_KEY, item.getName());
            result.put(ResumeConst.FIELD_VALUE, resumeUtil.listToString(item.getValues()));

            return result;
        }).collect(Collectors.toList());

        List<String> allTechnologyList = skillMatrixToList(skillsMatrixList);

        prepareUncategorizedSkillsFrontEndIfNecessary(list, resumeDTO, allTechnologyList);
        prepareUncategorizedSkillsBackEndIfNecessary(list, resumeDTO, allTechnologyList);
        return list;
    }

    private List<String> skillMatrixToList(List<SkillMatrixItemDTO> skillsMatrixList) {
        return skillsMatrixList.stream()
                .flatMap(skillMatrixItemDTO -> skillMatrixItemDTO.getValues().stream().map(String::toLowerCase))
                .collect(Collectors.toSet())
                .stream().toList();
    }

    private void prepareUncategorizedSkillsIfNecessary(List<Map<String, Object>> resultListOfMaps,
                                                       ResumeDTO resumeDTO,
                                                       String keyValue,
                                                       Function<ExperienceItemDTO, Boolean> filterCondition,
                                                       Function<ExperienceItemDTO, List<String>> retrieveFunction,
                                                       List<String> allSkills) {
        Optional.ofNullable(resumeDTO.getExperience())
                .flatMap(experienceDTO -> Optional.ofNullable(experienceDTO.getExperienceList()))
                .ifPresent(experienceList -> {
                    List<String> experiences = experienceList.stream()
                            .filter(experience -> BooleanUtils.isTrue(filterCondition.apply(experience)))
                            .map(retrieveFunction)
                            .flatMap(Collection::stream)
                            .filter(skill -> !allSkills.contains(skill.toLowerCase()))
                            .distinct()
                            .sorted()
                            .toList();

                    if (!experiences.isEmpty()) {
                        Map<String, Object> result = new HashMap<>();
                        result.put(ResumeConst.FIELD_KEY, keyValue);
                        result.put(ResumeConst.FIELD_VALUE, resumeUtil.listToString(experiences.stream().toList()));
                        resultListOfMaps.add(result);
                    }
                });
    }

    private void prepareUncategorizedSkillsFrontEndIfNecessary(List<Map<String, Object>> list, ResumeDTO resumeDTO, List<String> allSkills) {
        String locale = ThreadContext.getRequestContext().getLocale();
        String keyValue = translationService.retrieveTranslation(TranslationConst.KEY_UNCATEGORIZED_FRONT_END, locale);
        this.prepareUncategorizedSkillsIfNecessary(
                list,
                resumeDTO,
                keyValue,
                ExperienceItemDTO::getIsWorkedAsFrontEndDeveloper,
                ExperienceItemDTO::getFrontEndTechnologyList,
                allSkills
        );
    }

    private void prepareUncategorizedSkillsBackEndIfNecessary(List<Map<String, Object>> list, ResumeDTO resumeDTO, List<String> allSkills) {
        String locale = ThreadContext.getRequestContext().getLocale();
        String keyValue = translationService.retrieveTranslation(TranslationConst.KEY_UNCATEGORIZED_BACK_END, locale);
        this.prepareUncategorizedSkillsIfNecessary(
                list,
                resumeDTO,
                keyValue,
                ExperienceItemDTO::getIsWorkedAsBackEndDeveloper,
                ExperienceItemDTO::getBackEndTechnologyList,
                allSkills
        );
    }

}
