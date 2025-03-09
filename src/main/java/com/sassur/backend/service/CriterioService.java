package com.sassur.backend.service;

import com.sassur.backend.model.CriterioDerivacion;
import com.sassur.backend.repository.CriterioRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriterioService {

    @Autowired
    private CriterioRepository criterioRepository;

    // Obtener todos los criterios
    public List<CriterioDerivacion> getAllCriterios() {
        return criterioRepository.findAll();
    }

   // Obtener un criterio de derivación por ID
    public CriterioDerivacion getCriterioById(String id) {
        // Verificar si el ID es un ObjectId válido
        if (!ObjectId.isValid(id)) {
            // El ID no es válido
            return null;
        }

        // Buscar el CriterioDerivacion en la base de datos usando el ID
        Optional<CriterioDerivacion> criterio = criterioRepository.findById(id);
        return criterio.orElse(null);  // Si no lo encuentra, retorna null
    }

    // Crear un nuevo criterio
    public CriterioDerivacion createCriterio(CriterioDerivacion criterio) {
        return criterioRepository.save(criterio);
    }

    // Actualizar un criterio existente
    public CriterioDerivacion updateCriterio(String id, CriterioDerivacion criterio) {
        if (criterioRepository.existsById(id)) {
            criterio.setId(id);  // Asigna el ID para actualizar el documento existente
            return criterioRepository.save(criterio);
        }
        return null;  // Si no existe, retorna null
    }

    // Eliminar un criterio por ID
    public boolean deleteCriterio(String id) {
        if (criterioRepository.existsById(id)) {
            criterioRepository.deleteById(id);
            return true;
        }
        return false;  // Retorna false si no lo encuentra
    }
}
