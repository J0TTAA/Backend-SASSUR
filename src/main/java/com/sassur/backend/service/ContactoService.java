package com.sassur.backend.service;

import com.sassur.backend.model.Contacto;
import com.sassur.backend.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    // Método para crear un nuevo contacto
    public Contacto saveContacto(Contacto contacto) {
        // Si el contacto es nuevo (ID no existe en la base de datos)
        if (contacto.getId() == null || !contactoRepository.existsById(contacto.getId())) {
            contacto.setFechaCreacion(LocalDateTime.now());
        }
        return contactoRepository.save(contacto);
    }

    // Método para actualizar un contacto existente
    public Contacto updateContacto(String id, Contacto contactoActualizado) {
        Optional<Contacto> contactoExistente = contactoRepository.findById(id);

        if (contactoExistente.isPresent()) {
            Contacto contacto = contactoExistente.get();

            // **No modificar la fecha de creación**
            contactoActualizado.setFechaCreacion(contacto.getFechaCreacion());

            // **Actualizar los demás campos**
            contacto.setNombre(contactoActualizado.getNombre());
            contacto.setApellido(contactoActualizado.getApellido());
            contacto.setCorreo(contactoActualizado.getCorreo());
          
            contacto.setDireccion(contactoActualizado.getDireccion());
            contacto.setEstablecimiento(contactoActualizado.getEstablecimiento()); // Nuevo campo
            contacto.setCargo(contactoActualizado.getCargo()); // Nuevo campo

            return contactoRepository.save(contacto);
        }
        return null; // Si no encuentra el contacto, retorna null
    }

    // Obtener todos los contactos
    public List<Contacto> getAllContactos() {
        return contactoRepository.findAll();
    }

    // Obtener un contacto por ID
    public Optional<Contacto> getContactoById(String id) {
        return contactoRepository.findById(id);
    }

    // Eliminar un contacto
    public boolean deleteContacto(String id) {
        if (contactoRepository.existsById(id)) {
            contactoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar un contacto por nombre y apellido
    public Optional<Contacto> getContactoByNombreAndApellido(String nombre, String apellido) {
        return contactoRepository.findByNombreAndApellido(nombre, apellido);
    }
}
