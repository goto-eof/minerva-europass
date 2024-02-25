package com.andreidodu.minervaeuropass.controller;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.dto.ResumeDTO;
import com.andreidodu.minervaeuropass.service.PdfGeneratorService;
import com.andreidodu.minervaeuropass.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/resume")
@RequiredArgsConstructor
public class ResumeGeneratorController {

    final private ResumeService resumeService;

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generate(@RequestBody ResumeDTO resumeDTO) throws IOException {
        byte[] pdfBytes = resumeService.generateBytes(resumeDTO);
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
