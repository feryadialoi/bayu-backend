package dev.feryadi.backend.bayu.utils;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UserPhotoStorageUtil {
    void save(MultipartFile multipartFile);

    Resource load(String filename);
}
