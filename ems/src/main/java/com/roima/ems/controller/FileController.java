package com.roima.ems.controller;

import com.roima.ems.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class FileController {

    private final FileService fileService;

    @PostMapping("/{id}")
    public ResponseEntity<?> uploadProfileImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try{
            String filePath = fileService.saveProfileImage(file, id);
            return ResponseEntity.ok().body("Image uploaded");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileImage(@PathVariable Long id) throws MalformedURLException {
        try{
            return fileService.getProfileImage(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch profile");
        }
    }
}
