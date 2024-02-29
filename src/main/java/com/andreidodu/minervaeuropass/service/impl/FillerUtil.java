package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.exception.ApplicationException;

import java.util.Map;

public interface FillerUtil {
    boolean accept(ResumeDTO resumeDTO);

    void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) throws ApplicationException;
}
