package com.andreidodu.minervaeuropass.controller;

import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resume")
@RequiredArgsConstructor
public class ResumeGeneratorController {

    final private ResumeService resumeService;

    @PostMapping("/generate/templateName/{templateName}/locale/{locale}")
    public ResponseEntity<byte[]> retrieveResumePDF(@RequestBody ResumeDTO resumeDTO, @PathVariable String templateName) {
        byte[] pdfBytes = resumeService.generateBytes(resumeDTO, templateName);
        HttpHeaders headers = prepareHeadersForPDFDownload(pdfBytes);
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    private static HttpHeaders prepareHeadersForPDFDownload(byte[] bytes) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(bytes.length);
        headers.setContentType(MediaType.APPLICATION_PDF);
        return headers;
    }

}
