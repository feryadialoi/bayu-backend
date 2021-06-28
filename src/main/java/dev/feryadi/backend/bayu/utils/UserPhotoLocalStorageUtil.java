package dev.feryadi.backend.bayu.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserPhotoLocalStorageUtil implements UserPhotoStorageUtil {

    private final Environment environment;


    @Override
    public void save(MultipartFile multipartFile) {
        Path rootPath = Paths.get(Objects.requireNonNull(environment.getProperty("user-photos.path")));

        try {
            Files.copy(
                    multipartFile.getInputStream(),
                    rootPath.resolve(Objects.requireNonNull(multipartFile.getOriginalFilename())),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("could not store the file. Error: " + exception.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        Path rootPath = Paths.get(Objects.requireNonNull(environment.getProperty("user-photos.path")));

        try {
            Path filePath = rootPath.resolve(filename);
            Resource resource = null;
            resource = new UrlResource(filePath.toUri());
            System.out.println(resource);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("could not read the file");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
