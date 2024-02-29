package com.andreidodu.minervaeuropass.repository.impl;

import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.repository.FileRepository;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Repository
public class FileRepositoryImpl implements FileRepository {
    @Override
    public void savePDF(String path, String filename, byte[] data) {
        try (FileOutputStream stream = new FileOutputStream(path + "/" + filename)) {
            stream.write(data);
        } catch (IOException e) {
            throw new ApplicationException(e.getMessage());
        }
    }
}
