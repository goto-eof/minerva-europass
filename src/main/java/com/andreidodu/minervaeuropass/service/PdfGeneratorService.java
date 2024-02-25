package com.andreidodu.minervaeuropass.service;

import java.util.Map;

public interface PdfGeneratorService {
    byte[] generatePDF(String templateDirectory, String templateName, Map<String, Object> dataMap);
}
