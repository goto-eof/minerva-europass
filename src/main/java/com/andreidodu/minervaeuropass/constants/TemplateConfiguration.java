package com.andreidodu.minervaeuropass.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TemplateConfiguration {

    @Value("${com.andreidodu.minerva-europass.templates-directory}")
    private String templatesDirectory;

    @Value("${com.andreidodu.minerva-europass.basic-template-name}")
    private String basicTemplateName;

    @Value("${com.andreidodu.minerva-europass.basic-template-directory}")
    private String basicTemplateDirectory;

    @Value("${com.andreidodu.minerva-europass.max-summary-results-tech-years-experience}")
    private Integer maxSummaryResultsTechYearsExperience;

    @Value("${com.andreidodu.minerva-europass.max-summary-results-tech-frequency}")
    private Integer maxSummaryResultsTechFrequency;

    @Value("${com.andreidodu.minerva-europass.enable-summary-results-tech-years-experience}")
    private Boolean enableSummaryResultsTechYearsExperience;

    @Value("${com.andreidodu.minerva-europass.enable-summary-results-tech-frequency}")
    private Boolean enableSummaryResultsTechFrequency;

    @Value("${com.andreidodu.minerva-europass.enable-summary}")
    private Boolean enableSummary;

    @Value("${com.andreidodu.minerva-europass.show-top-back-end-technologies}")
    private Boolean showTopBackEndTechnologies;

    @Value("${com.andreidodu.minerva-europass.max-number-top-back-end-technologies}")
    private Integer maxNumberTopBackEndTechnologies;

    @Value("${com.andreidodu.minerva-europass.show-top-front-end-technologies}")
    private Boolean showTopFrontEndTechnologies;

    @Value("${com.andreidodu.minerva-europass.max-number-top-front-end-technologies}")
    private Integer maxNumberTopFrontEndTechnologies;
}
