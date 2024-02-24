package com.andreidodu.minervaeuropass.service;

import com.andreidodu.minervaeuropass.dto.ResumeDTO;

import java.util.Map;

public interface ResumeService {
    Map<String, Object> processResumeAndReturnMap(ResumeDTO resumeDTO);
}
