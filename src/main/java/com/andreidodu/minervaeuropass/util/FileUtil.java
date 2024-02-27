package com.andreidodu.minervaeuropass.util;

import com.andreidodu.minervaeuropass.constants.ImageConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class FileUtil {
    public static final String BASE_64_SEPARATOR = ",";
    public static final String FILE_EXTENSION = ".bin";
    public static final String SLASH = "/";
    private final ImageConfiguration imageConfiguration;

    public String saveImage(String base64string) throws IOException {
        byte[] imageByte = base64ToByte(base64string);
        Date dateTime = new Date();
        String filename = dateTime.getTime() + FILE_EXTENSION;
        String fullPath = imageConfiguration.getImagePath() + SLASH + filename;
        Path path = new File(fullPath).toPath();
        Files.write(path, imageByte);
        return fullPath;
    }

    private static byte[] base64ToByte(String base64string) {
        String base64Image = base64string.split(BASE_64_SEPARATOR)[1];
        return Base64.getDecoder().decode(base64Image);
    }
}
