package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class TemplateSettingFillerUtil {


    private final TemplateConfiguration templateConfiguration;

    public void fillUpTemplateSetting(ResumeDTO resumeDTO, Map<String, Object> result) {
        result.put(ResumeConst.FIELD_ENABLE_SUMMARY_RESULTS_TECH_FREQUENCY, templateConfiguration.getEnableSummaryResultsTechFrequency().toString());
        result.put(ResumeConst.FIELD_ENABLE_SUMMARY_RESULTS_TECH_YEARS_EXPERIENCE, templateConfiguration.getEnableSummaryResultsTechYearsExperience().toString());
        result.put(ResumeConst.FIELD_ENABLE_SUMMARY, templateConfiguration.getEnableSummary().toString());
    }
}
