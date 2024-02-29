package com.andreidodu.minervaeuropass.service.templatestrategies;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.PdfGeneratorService;
import com.andreidodu.minervaeuropass.service.TemplateStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BasicTemplateStrategy implements TemplateStrategy {

    final static private String TEMPLATE_NAME = "basic";

    private final PdfGeneratorService pdfGeneratorService;
    private final TemplateConfiguration templateConfiguration;

    @Override
    public boolean accept(final String templateName) {
        return TEMPLATE_NAME.equals(templateName);
    }

    @Override
    public byte[] generate(Map<String, Object> resumeMap) {
        return this.pdfGeneratorService.generatePDF(templateConfiguration.getBasicTemplateDirectory(), templateConfiguration.getBasicTemplateName(), resumeMap);
    }

    @Override
    public void postGenerate(Map<String, Object> resumeMap) {
        try {
            String imagePathAndFilename = (String) resumeMap.get(ResumeConst.FIELD_PROFILE_PICTURE_PATH);
            if (imagePathAndFilename != null) {
                Files.delete(new File(imagePathAndFilename).toPath());
            }
        } catch (IOException e) {
            throw new ApplicationException(e.getMessage());
        }
    }
}
