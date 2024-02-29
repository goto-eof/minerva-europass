package com.andreidodu.minervaeuropass.service.templatestrategies.basic;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.FillerUtil;
import com.andreidodu.minervaeuropass.service.PdfGeneratorService;
import com.andreidodu.minervaeuropass.service.TemplateStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class BasicTemplateStrategy implements TemplateStrategy {

    final static private String TEMPLATE_NAME = "basic";
    private final List<FillerUtil> fillerList;

    private final PdfGeneratorService pdfGeneratorService;
    private final TemplateConfiguration templateConfiguration;

    @Override
    public boolean accept(final String templateName) {
        return TEMPLATE_NAME.equals(templateName);
    }

    @Override
    public byte[] execute(ResumeDTO resumeDTO) {
        Map<String, Object> resumeMap = this.preGenerate(resumeDTO);
        byte[] bytes = this.generate(resumeMap);
        this.postGenerate(resumeMap);
        return bytes;
    }

    private Map<String, Object> preGenerate(ResumeDTO resumeDTO) {
        Map<String, Object> result = new HashMap<>();
        this.fillerList.stream()
                .filter(filler -> filler.accept(resumeDTO))
                .forEach(filler -> fillUp(resumeDTO, filler, result));
        return result;
    }

    private static void fillUp(ResumeDTO resumeDTO, FillerUtil filler, Map<String, Object> result) {
        try {
            filler.fillUp(resumeDTO, result);
        } catch (ApplicationException e) {
            log.error(e.getMessage());
        }
    }

    private byte[] generate(Map<String, Object> resumeMap) {
        return this.pdfGeneratorService.generatePDF(templateConfiguration.getBasicTemplateDirectory(), templateConfiguration.getBasicTemplateName(), resumeMap);
    }

    private void postGenerate(Map<String, Object> resumeMap) {
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
