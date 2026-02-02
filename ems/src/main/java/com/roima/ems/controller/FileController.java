package com.roima.ems.controller;

import com.roima.ems.service.FileService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class FileController {

    private final FileService fileService;

    @PostMapping("/{id}")
    public ResponseEntity<?> uploadProfileImage(@PathVariable Long id, @RequestParam("file") MultipartFile file, HttpServletResponse response) {
        try{
            String fileName = fileService.saveProfileImage(file, id);
            Cookie cookie = new Cookie("FILE_REF_",fileName);
            cookie.setMaxAge(3600);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setSecure(false);

            response.addCookie(cookie);
            return ResponseEntity.ok().body("Image uploaded");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileImage(@PathVariable Long id, HttpServletRequest request,HttpServletResponse response) throws MalformedURLException {
        try{
            Resource resource = fileService.getProfileImage(id);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Cache-Control", "public, max-age=3600");
                return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch profile");
        }
    }
}
