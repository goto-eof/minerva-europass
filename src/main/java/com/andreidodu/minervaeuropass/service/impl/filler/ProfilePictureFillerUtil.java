package com.andreidodu.minervaeuropass.service.impl.filler;

import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.dto.resume.ResumeDTO;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.impl.FillerUtil;
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

    public void fillUp(ResumeDTO resumeDTO, Map<String, Object> result) {
        try {
            String path = fileUtil.saveImage(resumeDTO.getImage());
            result.put(ResumeConst.FIELD_PROFILE_PICTURE_PATH, path);
        } catch (IOException e) {
            throw new ApplicationException(String.format("Unable to save image: %s", e.getMessage()));
        }

    }
}
