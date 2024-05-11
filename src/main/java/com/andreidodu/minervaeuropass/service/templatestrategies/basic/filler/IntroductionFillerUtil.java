package com.andreidodu.minervaeuropass.service.templatestrategies.basic.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.service.FillerUtil;
import liquibase.util.BooleanUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Order(30)
@Component
@RequiredArgsConstructor
public class IntroductionFillerUtil implements FillerUtil {

    public boolean accept(ResumeDTO resumeDTO) {
        return resumeDTO.getIntroduction() != null &&
                BooleanUtils.isTrue(resumeDTO.getIntroduction().getEnabled());
    }

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        result.put(ResumeConst.FIELD_ENABLE_INTRODUCTION, ResumeConst.VALUE_TRUE);
        result.put(ResumeConst.FIELD_INTRODUCTION_TITLE, resumeDTO.getIntroduction().getTitle());
        result.put(ResumeConst.FIELD_INTRODUCTION_CONTENT, resumeDTO.getIntroduction().getDescription());
        result.put(ResumeConst.FIELD_INTRODUCTION_FOOTER, resumeDTO.getIntroduction().getFooter());
    }
}
