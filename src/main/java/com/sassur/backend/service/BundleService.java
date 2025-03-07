package com.sassur.backend.service;

import com.sassur.backend.model.CriterioDerivacion;
import com.sassur.backend.repository.CriterioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BundleService {

    @Autowired
    private CriterioRepository criterioRepository;

    // Método para cargar los criterios desde un bundle (JSON)
    public void cargarCriteriosDesdeBundle(List<CriterioDerivacion> criterios) {
        if (criterios != null && !criterios.isEmpty()) {
            criterioRepository.saveAll(criterios); // Guarda todos los criterios en la base de datos
        } else {
            throw new IllegalArgumentException("La lista de criterios está vacía o nula. Asegúrese de enviar datos válidos.");
        }
    }
}
