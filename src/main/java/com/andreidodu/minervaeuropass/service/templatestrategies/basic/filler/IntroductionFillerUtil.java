package com.andreidodu.minervaeuropass.service.templatestrategies.basic.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.service.FillerUtil;
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
        result.put(ResumeConst.FIELD_ENABLE_INTRODUCTION, ResumeConst.VALUE_TRUE);
        result.put(ResumeConst.FIELD_INTRODUCTION_TITLE, resumeDTO.getIntroduction().getTitle());
        result.put(ResumeConst.FIELD_INTRODUCTION_CONTENT, resumeDTO.getIntroduction().getContent());
        result.put(ResumeConst.FIELD_INTRODUCTION_FOOTER, resumeDTO.getIntroduction().getFooter());
    }
}
