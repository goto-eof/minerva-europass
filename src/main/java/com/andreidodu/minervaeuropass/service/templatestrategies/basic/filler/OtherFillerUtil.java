package com.andreidodu.minervaeuropass.service.templatestrategies.basic.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.OtherItemDTO;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.service.FillerUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(90)
@Component
@RequiredArgsConstructor
public class OtherFillerUtil implements FillerUtil {

    public boolean accept(ResumeDTO resumeDTO) {
        return resumeDTO.getOther() != null &&
                BooleanUtils.isTrue(resumeDTO.getOther().getEnabled());
    }

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        result.put(ResumeConst.FIELD_ENABLE_OTHER, ResumeConst.VALUE_TRUE);
        result.put(ResumeConst.FIELD_OTHER_TITLE, resumeDTO.getOther().getTitle());
        result.put(ResumeConst.FIELD_OTHER_DESCRIPTION, resumeDTO.getOther().getDescription());
        result.put(ResumeConst.FIELD_OTHER_LIST, otherListToListOfMaps(resumeDTO.getOther().getOtherList()));
    }

    private static List<Map<String, String>> otherListToListOfMaps(List<OtherItemDTO> otherList) {
        return otherList.stream().map(item -> {
            Map<String, String> result = new HashMap<>();
            result.put(ResumeConst.FIELD_KEY, item.getKey());
            result.put(ResumeConst.FIELD_VALUE, item.getValue());
            return result;
        }).toList();
    }
}
