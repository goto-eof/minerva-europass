package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.ResumeService;
import com.andreidodu.minervaeuropass.service.TemplateStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final List<TemplateStrategy> templateStrategyList;
    private final List<FillerUtil> fillerList;

    @Override
    public byte[] generateBytes(ResumeDTO resumeDTO, String templateName) throws IOException {
        Map<String, Object> resumeMap = this.processResumeAndReturnMap(resumeDTO);

        byte[] pdfBytes = templateStrategyList
                .stream()
                .filter(strategy -> strategy.accept(templateName))
                .findFirst()
                .map(templateStrategy -> templateStrategy.generate(resumeMap))
                .orElseThrow(() -> new ApplicationException(String.format("Strategy [%s] not found", templateName)));

        String imagePathAndFilename = (String) resumeMap.get(ResumeConst.FIELD_PROFILE_PICTURE_PATH);
        Files.delete(new File(imagePathAndFilename).toPath());
        return pdfBytes;
    }

    @Override
    public Map<String, Object> processResumeAndReturnMap(ResumeDTO resumeDTO) throws IOException {
        Map<String, Object> result = new HashMap<>();
        this.fillerList.forEach(filler -> fillUp(resumeDTO, filler, result));
        return result;
    }

    private static void fillUp(ResumeDTO resumeDTO, FillerUtil filler, Map<String, Object> result) {
        try {
            filler.fillUp(resumeDTO, result);
        } catch (ApplicationException e) {
            log.error(e.getMessage());
        }
    }


}
