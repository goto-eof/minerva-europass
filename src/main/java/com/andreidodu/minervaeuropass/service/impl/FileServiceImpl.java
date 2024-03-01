package com.andreidodu.minervaeuropass.service.impl;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.constants.TemplateConfiguration;
import com.andreidodu.minervaeuropass.repository.FileRepository;
import com.andreidodu.minervaeuropass.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    final private FileRepository fileRepository;
    final private TemplateConfiguration templateConfiguration;

    @Override
    public void storeACopy(byte[] data) {
        final String filename = ResumeConst.FILE_NAME_PREFIX + (new Date()).getTime() + ResumeConst.FILE_EXTENSION;
        this.fileRepository.savePDF(templateConfiguration.getPdfPath(), filename, data);
    }
}
