package com.andreidodu.minervaeuropass.service;

import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;

public interface TemplateStrategy {

    boolean accept(String templateName);

    byte[] execute(ResumeDTO resumeDTO);

}
