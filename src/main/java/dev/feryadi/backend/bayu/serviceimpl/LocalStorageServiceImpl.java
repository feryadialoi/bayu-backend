package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.service.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class LocalStorageServiceImpl implements StorageService {

    private final Path rootPath = Paths.get("uploads");

    public LocalStorageServiceImpl() {
        init();
    }

    @Override
    public void init() {
        try {
            if (!Files.exists(rootPath)) {
                Files.createDirectory(rootPath);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("could not initialize folder for upload");
        }
    }

    @Override
    public void save(MultipartFile multipartFile) {
        try {
            String pattern = "yyyy_MM_dd_HH_mm_ss.SSS";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String format = simpleDateFormat.format(new Date());

            Files.copy(multipartFile.getInputStream(), this.rootPath.resolve(format + "_" + multipartFile.getOriginalFilename()));
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("could not store the file. Error: " + exception.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path filePath = rootPath.resolve(filename);
            Resource resource = null;
            resource = new UrlResource(filePath.toUri());
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

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootPath.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(rootPath, 1).filter(path -> !path.equals(rootPath)).map(rootPath::relativize);
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new RuntimeException("could not load the files");
        }
    }
}
