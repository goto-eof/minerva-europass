package com.andreidodu.minervaeuropass.service;

import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;

import java.io.IOException;
import java.util.Map;

public interface ResumeService {
    byte[] generateBytes(ResumeDTO resumeDTO, String templateName) throws IOException;

    Map<String, Object> processResumeAndReturnMap(ResumeDTO resumeDTO) throws IOException;
}
