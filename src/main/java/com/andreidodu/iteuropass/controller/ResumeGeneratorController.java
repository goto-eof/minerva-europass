package com.andreidodu.iteuropass.controller;

import com.andreidodu.iteuropass.constants.TemplateConfiguration;
import com.andreidodu.iteuropass.dto.ResumeDTO;
import com.andreidodu.iteuropass.service.PdfGeneratorService;
import com.andreidodu.iteuropass.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/resume")
@RequiredArgsConstructor
public class ResumeGeneratorController {

    final private ResumeService resumeService;
    final private PdfGeneratorService pdfGeneratorService;
    final private TemplateConfiguration templateConfiguration;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(@RequestBody ResumeDTO resumeDTO) {
        Map<String, Object> resumeMap = resumeService.processResumeAndReturnMap(resumeDTO);
        byte[] pdfBytes = this.pdfGeneratorService.generatePDF(templateConfiguration.getResumeTemplateName(), resumeMap);
        HttpHeaders headers = prepareHeadersForPDFDownload(pdfBytes);
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    private static HttpHeaders prepareHeadersForPDFDownload(byte[] bytes) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(bytes.length);
        headers.setContentType(MediaType.APPLICATION_PDF);
        return headers;
    }

}
