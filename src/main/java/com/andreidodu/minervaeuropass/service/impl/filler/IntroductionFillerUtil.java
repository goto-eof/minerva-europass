package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.service.impl.FillerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Order(30)
@Component
@RequiredArgsConstructor
public class IntroductionFillerUtil implements FillerUtil {

    public boolean accept(ResumeDTO resumeDTO) {
        return resumeDTO.getIntroduction() != null;
    }

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        result.put(ResumeConst.FIELD_INTRODUCTION_TITLE, resumeDTO.getIntroduction().getTitle());
        result.put(ResumeConst.FIELD_INTRODUCTION_CONTENT, resumeDTO.getIntroduction().getContent());
        result.put(ResumeConst.FIELD_INTRODUCTION_FOOTER, resumeDTO.getIntroduction().getFooter());
    }
}
