package com.andreidodu.minervaeuropass.util;

import com.andreidodu.minervaeuropass.constants.ImageConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class FileUtil {
    private final ImageConfiguration imageConfiguration;

    public  String saveImage(String base64string) throws IOException {
        String base64Image = base64string.split(",")[1];
        byte[] imageByte = Base64.getDecoder().decode(base64Image);
        Date dateTime = new Date();
        String filename = dateTime.getTime() + ".png";
        String path = imageConfiguration.getImagePath() + "/" + filename;
        Files.write(new File(path).toPath(), imageByte);
        return path;
    }
}
