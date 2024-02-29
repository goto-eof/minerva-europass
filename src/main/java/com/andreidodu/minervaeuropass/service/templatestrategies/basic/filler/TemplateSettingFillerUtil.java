package com.andreidodu.minervaeuropass.service.templatestrategies.basic.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.service.FillerUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

@Order(0)
@Component
@RequiredArgsConstructor
public class TemplateSettingFillerUtil implements FillerUtil {
    private final TemplateConfiguration templateConfiguration;

    public boolean accept(ResumeDTO resumeDTO) {
        return true;
    }

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        result.put(ResumeConst.FIELD_ENABLE_SUMMARY_RESULTS_TECH_FREQUENCY, templateConfiguration.getEnableSummaryResultsTechFrequency().toString());
        result.put(ResumeConst.FIELD_ENABLE_SUMMARY_RESULTS_TECH_YEARS_EXPERIENCE, templateConfiguration.getEnableSummaryResultsTechYearsExperience().toString());
        result.put(ResumeConst.FIELD_ENABLE_SUMMARY, templateConfiguration.getEnableSummary().toString());
    }
}
