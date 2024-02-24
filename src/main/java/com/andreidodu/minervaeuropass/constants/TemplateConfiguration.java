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

    @Value("${com.andreidodu.minerva-europass.resume-template-name}")
    private String resumeTemplateName;
}
