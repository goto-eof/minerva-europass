package com.andreidodu.iteuropass.service;

import com.andreidodu.iteuropass.dto.ResumeDTO;

import java.util.Map;

public interface ResumeService {
    Map<String, Object> processResumeAndReturnMap(ResumeDTO resumeDTO);
}
