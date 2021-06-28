package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.FileInfo;
import dev.feryadi.backend.bayu.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class FileController {

    private final StorageService storageService;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            storageService.save(file);
            return new ResponseEntity<>("Uploaded the file successfully: " + file.getOriginalFilename(), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>("Could not upload the file: " + file.getOriginalFilename(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/files")
    public ResponseEntity<List<FileInfo>> getListFile() {
        List<FileInfo> fileInfos = storageService.loadAll()
                .map(path -> {
                    String filename = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder
                            .fromMethodName(FileController.class, "getFile", path.getFileName().toString())
                            .build().toString();
                    return new FileInfo(filename, url);
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(fileInfos, HttpStatus.OK);
    }

    @GetMapping(value = "/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
