package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.OtherItemDTO;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class OtherFillerUtil {

    public void fillUpOther(ResumeDTO resumeDTO, Map<String, Object> result) {
        if (resumeDTO.getOther() != null) {
            result.put(ResumeConst.FIELD_OTHER_TITLE, resumeDTO.getOther().getTitle());
            result.put(ResumeConst.FIELD_OTHER_DESCRIPTION, resumeDTO.getOther().getDescription());
            result.put(ResumeConst.FIELD_OTHER_LIST, otherListToListOfMaps(resumeDTO.getOther().getOtherList()));
        }
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
