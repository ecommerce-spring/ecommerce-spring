package com.example.servicio_producto.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageService {

    private final Cloudinary cloudinary;

    // Inyección por constructor (Best Practice)
    public ImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String subirImagen(MultipartFile file) {
        try {
            // 1. Subir el archivo a Cloudinary
            // ObjectUtils.emptyMap() indica que no enviamos parámetros extra (como transformaciones)
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", "productos"));

            // 2. Obtener y retornar la URL segura (https)
            return uploadResult.get("secure_url").toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Si falla, retornamos null (o podrías lanzar una excepción)
        }
    }
}