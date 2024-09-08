package com.cdigital.cdigital_backend.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.cdigital.cdigital_backend.services.VideoService;

import java.io.IOException;

@RestController
@RequestMapping("/api/videos")
public class VideoController {


    private VideoService videoService;

    @PostMapping("/upload")
    public String uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        return videoService.uploadVideo(file.getBytes());
    }
}
