package com.sassur.backend.repository;

import com.sassur.backend.model.Contacto;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ContactoRepository extends MongoRepository<Contacto, String> {

    Optional<Contacto> findByCorreo(String correo);  // Buscar por correo

    Optional<Contacto> findByNombreAndApellido(String nombre, String apellido);  // Buscar por nombre y apellido

 
}
