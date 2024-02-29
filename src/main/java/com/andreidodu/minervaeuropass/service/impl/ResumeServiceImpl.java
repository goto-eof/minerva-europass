package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.ResumeService;
import com.andreidodu.minervaeuropass.service.TemplateStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final List<TemplateStrategy> templateStrategyList;

    @Override
    public byte[] generateBytes(ResumeDTO resumeDTO, String templateName) throws IOException {
        return templateStrategyList
                .stream()
                .filter(strategy -> strategy.accept(templateName))
                .findFirst()
                .map(templateStrategy -> templateStrategy.execute(resumeDTO))
                .orElseThrow(() -> new ApplicationException(String.format("Strategy [%s] not found", templateName)));
    }

}
