package com.example.servicio_producto.controller;

import com.example.servicio_producto.model.Producto;
import com.example.servicio_producto.service.ImageService;
import com.example.servicio_producto.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final ImageService imageService;

    public ProductoController(ProductoService productoService, ImageService imageService) {

        this.productoService = productoService;
        this.imageService = imageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(
            @RequestPart("producto") Producto producto, // Recibe el JSON
            @RequestPart("file") MultipartFile file     // Recibe la Imagen
    ) {
        String urlImagen = imageService.subirImagen(file);

        producto.setImagen(urlImagen);

        return productoService.guardar(producto);
    }

    @GetMapping
    public List<Producto> listarProductos() {

        return productoService.listar();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity <Producto> actualizar(
            @PathVariable Long id,
            @RequestPart("producto") Producto producto,
            @RequestPart("file") MultipartFile file
    ){
        if(file != null && !file.isEmpty()){
            String urlImagen = imageService.subirImagen(file);
            producto.setImagen(urlImagen);
        }
        Producto productoActualizado = productoService.actualizar(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);

        // Creamos un mapa para devolver un JSON simple
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Producto eliminado correctamente");

        // Devolvemos status 200 OK con el mensaje
        return ResponseEntity.ok(respuesta);
    }
}
