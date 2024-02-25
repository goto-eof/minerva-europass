package com.andreidodu.minervaeuropass.service;

import com.andreidodu.minervaeuropass.dto.ResumeDTO;

import java.io.IOException;
import java.util.Map;

public interface ResumeService {
    Map<String, Object> processResumeAndReturnMap(ResumeDTO resumeDTO) throws IOException;
}
