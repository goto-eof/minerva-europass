package com.andreidodu.minervaeuropass.service.impl.filler;

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
        result.put("enableSummaryResultsTechFrequency", templateConfiguration.getEnableSummaryResultsTechFrequency().toString());
        result.put("enableSummaryResultsTechYearsExperience", templateConfiguration.getEnableSummaryResultsTechYearsExperience().toString());
        result.put("enableSummary", templateConfiguration.getEnableSummary().toString());
    }
}
