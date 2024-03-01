package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.FileService;
import com.andreidodu.minervaeuropass.service.ResumeService;
import com.andreidodu.minervaeuropass.service.TemplateStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final List<TemplateStrategy> templateStrategyList;
    private final FileService fileService;
    private final TemplateConfiguration templateConfiguration;

    @Override
    public byte[] generateBytes(ResumeDTO resumeDTO, final String templateName) {
        return templateStrategyList
                .stream()
                .filter(strategy -> strategy.accept(templateName))
                .findFirst()
                .map(templateStrategy -> templateStrategy.execute(resumeDTO))
                .map(this::storeACopyIfNecessary)
                .orElseThrow(() -> new ApplicationException(String.format("Strategy [%s] not found", templateName)));
    }

    private byte[] storeACopyIfNecessary(byte[] bytes) {
        if (templateConfiguration.getEnableSavePDF()) {
            fileService.storeACopy(bytes);
        }
        return bytes;
    }

}
