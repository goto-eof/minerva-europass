package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.EducationItemDTO;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.service.impl.FillerUtil;
import com.andreidodu.minervaeuropass.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Order(50)
@Component
@RequiredArgsConstructor
public class EducationFillerUtil implements FillerUtil {
    private final DateUtil dateUtil;

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getEducation() != null) {
            resumeDTO.getEducation().getEducationList().sort(Comparator.comparing(EducationItemDTO::getDateFrom).reversed());
            result.put(ResumeConst.FIELD_EDUCATION_TITLE, resumeDTO.getEducation().getTitle());
            result.put(ResumeConst.FIELD_EDUCATION_DESCRIPTION, resumeDTO.getEducation().getDescription());
            result.put(ResumeConst.FIELD_EDUCATION_LIST, educationToListMap(resumeDTO.getEducation().getEducationList()));
        }
    }

    private List<Map<String, Object>> educationToListMap(List<EducationItemDTO> educationItemDTOList) {
        return educationItemDTOList.stream().map(item -> {
            Map<String, Object> result = new HashMap<>();

            result.put(ResumeConst.FIELD_DATE_FROM, DateUtil.formatLocalDate(item.getDateFrom(), DateUtil.PATTERN_MMM_YYYY));
            result.put(ResumeConst.FIELD_DATE_TO, dateUtil.calculateDateTo(item.getDateTo()));
            result.put(ResumeConst.FIELD_NAME, item.getName());
            result.put(ResumeConst.FIELD_DESCRIPTION, item.getDescription());

            return result;
        }).toList();
    }

}
