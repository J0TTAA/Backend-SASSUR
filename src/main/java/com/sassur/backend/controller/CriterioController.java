package com.sassur.backend.controller;

import com.sassur.backend.model.CriterioDerivacion;
import com.sassur.backend.service.CriterioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/criterios")
@Validated  // Para validar las anotaciones en los modelos
public class CriterioController {

    @Autowired
    private CriterioService criterioService;

    // Obtener todos los criterios de derivación
    @GetMapping
    public List<CriterioDerivacion> getAllCriterios() {
        return criterioService.getAllCriterios();
    }

    // Obtener un criterio de derivación por ID
    @GetMapping("/{id}")
    public ResponseEntity<CriterioDerivacion> getCriterioById(@PathVariable String id) {
        CriterioDerivacion criterio = criterioService.getCriterioById(id);
        if (criterio != null) {
            return new ResponseEntity<>(criterio, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Si no lo encuentra, devuelve 404
    }

    // Crear un nuevo criterio de derivación
    @PostMapping
    public ResponseEntity<CriterioDerivacion> createCriterio(@Valid @RequestBody CriterioDerivacion criterio) {
        CriterioDerivacion creado = criterioService.createCriterio(criterio);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);  // Devuelve 201 creado
    }

    // Actualizar un criterio de derivación
    @PutMapping("/{id}")
    public ResponseEntity<CriterioDerivacion> updateCriterio(@PathVariable String id, @Valid @RequestBody CriterioDerivacion criterio) {
        CriterioDerivacion actualizado = criterioService.updateCriterio(id, criterio);
        if (actualizado != null) {
            return new ResponseEntity<>(actualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Si no lo encuentra, devuelve 404
    }

    // Eliminar un criterio de derivación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCriterio(@PathVariable String id) {
        if (criterioService.deleteCriterio(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Devuelve 204 si la eliminación fue exitosa
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Si no lo encuentra, devuelve 404
    }
}
