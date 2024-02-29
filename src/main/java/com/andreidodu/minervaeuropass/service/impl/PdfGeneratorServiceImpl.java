package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.PdfGeneratorService;
import com.andreidodu.minervaeuropass.service.XMLService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Service;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PdfGeneratorServiceImpl implements PdfGeneratorService {
    private final TemplateConfiguration templateConfiguration;
    private final XMLService xmlService;

    @Override
    public byte[] generatePDF(final String templateDirectory, final String templateName, Map<String, Object> dataMap) {
        try (ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream()) {
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            FOUserAgent userAgent = fopFactory.newFOUserAgent();
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, pdfOutput);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            final String template = templateConfiguration.getTemplatesDirectory() + "/" + templateDirectory + "/" + templateName;
            File templateFile = new File(template);
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(templateFile));

            Result result = new SAXResult(fop.getDefaultHandler());

            Source src = new StreamSource(xmlService.createXMLFromMap(dataMap));
            transformer.transform(src, result);
            return pdfOutput.toByteArray();
        } catch (Exception e) {
            log.error("Fatal error while trying to generate the pdf: " + e.getMessage());
            throw new ApplicationException("Something went wrong: failed to generate PDF. " + e.getMessage());
        }
    }

}
