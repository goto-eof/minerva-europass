package com.andreidodu.iteuropass.service;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public interface PdfGeneratorService {
    byte[] generatePDF(String templateName, Map<String, Object> dataMap);
}
