package com.andreidodu.minervaeuropass.util;

import com.andreidodu.minervaeuropass.constants.ImageConfiguration;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConfigurationCheckerUtil {

    private final TemplateConfiguration templateConfiguration;
    private final ImageConfiguration imageConfiguration;
    private final FileUtil fileUtil;

    @PostConstruct
    private void checkConfigurationAndWarn() {
        checkPDFPath();
        checkImagePath();
    }

    private void checkImagePath() {
        if (fileUtil.isPathExists(imageConfiguration.getImagePath())) {
            log.info("\n\nImage path configured correctly: {}\n\n", imageConfiguration.getImagePath());
            return;
        }
        log.warn("\n\nImage path does not exists: {}\n\n", imageConfiguration.getImagePath());
    }

    private void checkPDFPath() {
        if (fileUtil.isPathExists(templateConfiguration.getPdfPath())) {
            log.info("\n\nPDF path configured correctly: {}\n\n", templateConfiguration.getPdfPath());
            return;
        }
        log.warn("\n\nPDF path does not exists: {}\n\n", templateConfiguration.getPdfPath());
    }


}
