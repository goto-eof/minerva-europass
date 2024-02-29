package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.dto.resume.SkillMatrixItemDTO;
import com.andreidodu.minervaeuropass.service.FillerUtil;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(60)
@Component
@RequiredArgsConstructor
public class SkillMatrixFillerUtil implements FillerUtil {
    private final ResumeUtil resumeUtil;

    public boolean accept(ResumeDTO resumeDTO) {
        return resumeDTO.getSkillsMatrix() != null;
    }

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        result.put(ResumeConst.FIELD_ENABLE_SKILL_MATRIX, ResumeConst.VALUE_TRUE);
        result.put(ResumeConst.FIELD_SKILLS_MATRIX_TITLE, resumeDTO.getSkillsMatrix().getTitle());
        result.put(ResumeConst.FIELD_SKILLS_MATRIX_DESCRIPTION, resumeDTO.getSkillsMatrix().getDescription());
        result.put(ResumeConst.FIELD_SKILLS_MATRIX_LIST, skillsMatrixListToListMap(resumeDTO.getSkillsMatrix().getSkillsMatrixList()));
    }

    private List<Map<String, Object>> skillsMatrixListToListMap(List<SkillMatrixItemDTO> skillsMatrixList) {
        return skillsMatrixList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            item.getValues().sort(String::compareTo);

            result.put(ResumeConst.FIELD_KEY, item.getName());
            result.put(ResumeConst.FIELD_VALUE, resumeUtil.listToString(item.getValues()));

            return result;
        }).toList();
    }

}
