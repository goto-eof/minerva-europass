package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import com.andreidodu.minervaeuropass.dto.SkillMatrixItemDTO;
import com.andreidodu.minervaeuropass.util.ResumeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SkillMatrixFilleUtil {

    public void fillUpSkillsMatrix(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getSkillsMatrix() != null) {
            result.put(ResumeConst.FIELD_SKILLS_MATRIX_TITLE, resumeDTO.getSkillsMatrix().getTitle());
            result.put(ResumeConst.FIELD_SKILLS_MATRIX_DESCRIPTION, resumeDTO.getSkillsMatrix().getDescription());
            result.put(ResumeConst.FIELD_SKILLS_MATRIX_LIST, skillsMatrixListToListMap(resumeDTO.getSkillsMatrix().getSkillsMatrixList()));

        }
    }

    private static List<Map<String, Object>> skillsMatrixListToListMap(List<SkillMatrixItemDTO> skillsMatrixList) {
        return skillsMatrixList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            item.getValues().sort(String::compareTo);

            result.put(ResumeConst.FIELD_KEY, item.getName());
            result.put(ResumeConst.FIELD_VALUE, ResumeUtil.listToString(item.getValues()));

            return result;
        }).toList();
    }

}
