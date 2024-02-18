package com.andreidodu.iteuropass.constants;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TemplateConfiguration {

    @Value("${com.andreidodu.iteuropass.templates-directory}")
    private String templatesDirectory;

    @Value("${com.andreidodu.iteuropass.resume-template-name}")
    private String resumeTemplateName;
}
