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
        Path uploadPath = Paths.get(imagePath);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Employees emp = employeeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee Not found"));
        String fileName = "profile_" + id + ".png";
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        emp.setProfilePath(filePath.toString());
        employeeRepo.save(emp);
        return filePath.toString();
    }

    public ResponseEntity<Resource> getProfileImage(Long Id) throws MalformedURLException {
        Employees emp = employeeRepo.findById(Id).orElseThrow(() -> new IllegalArgumentException("Employee Not found"));
        String fileName = "profile_" + emp.getId() + ".png";
        Path filePath = Paths.get(imagePath).resolve(fileName);
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            HttpHeaders headers = new HttpHeaders();
            // Use set() to replace any existing Cache-Control header
            headers.set("Cache-Control", "public, max-age=3600");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.IMAGE_PNG)
//                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS).cachePublic())
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
