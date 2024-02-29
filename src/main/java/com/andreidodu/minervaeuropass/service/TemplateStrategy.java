package com.andreidodu.minervaeuropass.service;

import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;

import java.util.Map;

public interface TemplateStrategy {
    boolean accept(String templateName);

    byte[] execute(ResumeDTO resumeDTO);

}
