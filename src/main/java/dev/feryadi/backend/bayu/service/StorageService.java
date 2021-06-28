package dev.feryadi.backend.bayu.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    void save(MultipartFile multipartFile);

    Resource load(String filename);

    void deleteAll();

    Stream<Path> loadAll();
}
