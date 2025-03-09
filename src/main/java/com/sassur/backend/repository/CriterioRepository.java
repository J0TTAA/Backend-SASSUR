package com.sassur.backend.repository;

import com.sassur.backend.model.CriterioDerivacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriterioRepository extends MongoRepository<CriterioDerivacion, String> {
    // Consultas personalizadas (por ejemplo, encontrar criterios por especialidad)
    List<CriterioDerivacion> findByEspecialidad(String especialidad);

    
    List<CriterioDerivacion> findByPatologia(String patologia);
}
