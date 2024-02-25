package com.andreidodu.minervaeuropass.service;

import java.util.Map;

public interface TemplateStrategy {
    boolean accept(String templateName);

    byte[] generate(Map<String, Object> resumeMap);
}
