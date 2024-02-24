package com.andreidodu.minervaeuropass.service;

import java.util.Map;

public interface PdfGeneratorService {
    byte[] generatePDF(String templateName, Map<String, Object> dataMap);
}
