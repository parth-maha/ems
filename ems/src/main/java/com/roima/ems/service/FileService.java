package com.roima.ems.service;

import com.roima.ems.entity.Employees;
import com.roima.ems.repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${image.path}")
    private String imagePath;
    private final EmployeeRepo employeeRepo;

    public String saveProfileImage(MultipartFile file, Long id) throws IOException {
        Employees emp = employeeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee Not found"));

        Path uploadPath = Paths.get(imagePath);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = "profile_" + id + ".png";
        Path filePath = uploadPath.resolve(fileName);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    public Resource getProfileImage(Long id) throws IOException {
        Employees emp = employeeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee Not found"));

        String fileName = "profile_" + id + ".png";

        Path filePath = Paths.get(imagePath).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());

        if(resource.exists() && resource.isReadable()){
            return  resource;
        }else{
            throw new FileNotFoundException("File not found");
        }
    }
}
