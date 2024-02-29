package com.andreidodu.minervaeuropass.service.templatestrategies.basic.filler;

import com.andreidodu.minervaeuropass.constants.ImageConfiguration;
import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.FillerUtil;
import com.andreidodu.minervaeuropass.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Order(15)
@Component
@RequiredArgsConstructor
public class ProfilePictureFillerUtil implements FillerUtil {

    private final FileUtil fileUtil;
    private final ImageConfiguration imageConfiguration;

    public boolean accept(ResumeDTO resumeDTO) {
        return resumeDTO.getImage() != null;
    }

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        try {
            result.put(ResumeConst.FIELD_ENABLE_PROFILE_PICTURE, ResumeConst.VALUE_TRUE);
            fileUtil.createDirectoryRecursively(imageConfiguration.getImagePath());
            String path = fileUtil.saveImage(imageConfiguration.getImagePath(), resumeDTO.getImage());
            result.put(ResumeConst.FIELD_PROFILE_PICTURE_PATH, path);
        } catch (IOException e) {
            throw new ApplicationException(String.format("Unable to save image: %s", e.getMessage()));
        }

    }
}
