package com.sassur.backend.controller;

import com.sassur.backend.service.BundleService;
import com.sassur.backend.service.ExcelService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.sassur.backend.model.CriterioDerivacion;

@RestController
@RequestMapping("/api/criterios/upload")
public class CriterioUploadController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private BundleService bundleService;

    // Endpoint para cargar el archivo Excel
@PostMapping("/excel")
public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
    // Verifica si el archivo es válido
    if (file.isEmpty()) {
        return ResponseEntity.badRequest().body("El archivo está vacío.");
    }

    try {
        // Llama al servicio de Excel para procesar y cargar los datos
        excelService.cargarCriteriosDesdeExcel(file);

        // Respuesta de éxito
        return ResponseEntity.ok("Datos cargados correctamente desde el archivo Excel.");

    } catch (Exception e) {
        // Manejo de excepciones y evitar loop
        return ResponseEntity.status(500).body("Error al cargar los datos desde el archivo Excel: " + e.getMessage());
    }
}


    // Endpoint para cargar el bundle (JSON)
    @PostMapping("/bundle")
    public ResponseEntity<String> cargarBundle(@RequestBody List<CriterioDerivacion> criterios) {
        try {
            bundleService.cargarCriteriosDesdeBundle(criterios);
            return ResponseEntity.ok("Criterios cargados correctamente desde el bundle JSON.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al cargar los datos desde el bundle JSON: " + e.getMessage());
        }
    }
}
