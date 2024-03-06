package com.andreidodu.minervaeuropass.util;

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


    public String saveImage(final String imagePath, String base64string) throws IOException {
        byte[] imageByte = base64ToByte(base64string);
        String filename = calculateFilename();
        String fullPath = imagePath + SLASH + filename;
        Path path = new File(fullPath).toPath();
        Files.write(path, imageByte);
        return fullPath;
    }

    private static String calculateFilename() {
        Date dateTime = new Date();
        return dateTime.getTime() + FILE_EXTENSION;
    }

    private static byte[] base64ToByte(String base64string) {
        String base64Image = base64string.split(BASE_64_SEPARATOR)[1];
        return Base64.getDecoder().decode(base64Image);
    }

    public boolean createDirectoryRecursively(String path) {
        if (isPathExists(path)) {
            return false;
        }
        return new File(path).mkdirs();
    }

    public boolean isPathExists(final String path) {
        return new File(path).exists();
    }
}
