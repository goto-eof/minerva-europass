package com.andreidodu.minervaeuropass.repository;

public interface FileRepository {
    void savePDF(String path, String filename, byte[] data);
}
