package com.sit.jbc.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileUploader {
    @Value("${upload.path}")
    private String path;

    public String upload(MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return null;
        }
        else {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            InputStream is = file.getInputStream();

            Files.copy(is, Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + fileName;
        }
    }
}