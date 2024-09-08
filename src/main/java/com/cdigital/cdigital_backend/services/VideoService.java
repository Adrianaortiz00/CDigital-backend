package com.cdigital.cdigital_backend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class VideoService {

    private final Cloudinary cloudinary;

    public VideoService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadVideo(byte[] videoBytes) throws IOException {
        // Asegúrate de manejar correctamente el tipo de retorno
        @SuppressWarnings("unchecked")
        Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(videoBytes,
                ObjectUtils.asMap("resource_type", "video"));
        return (String) uploadResult.get("url");
    }
}
