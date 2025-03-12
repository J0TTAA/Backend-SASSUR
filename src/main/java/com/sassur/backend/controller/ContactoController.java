package com.sassur.backend.controller;

import com.sassur.backend.model.Contacto;
import com.sassur.backend.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;

    // Crear un nuevo contacto
    @PostMapping
    public ResponseEntity<Contacto> createContacto(@RequestBody Contacto contacto) {
        // Verificar si ya existe un contacto con el mismo ID
        if (contacto.getId() != null && contactoService.getContactoById(contacto.getId()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna 400 si el contacto ya existe
        }
        Contacto contactoGuardado = contactoService.saveContacto(contacto);
        return new ResponseEntity<>(contactoGuardado, HttpStatus.CREATED);
    }

    // Actualizar un contacto existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Contacto> updateContacto(@PathVariable String id, @RequestBody Contacto contacto) {
        Contacto contactoActualizado = contactoService.updateContacto(id, contacto);

        if (contactoActualizado != null) {
            return new ResponseEntity<>(contactoActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener todos los contactos
    @GetMapping
    public ResponseEntity<List<Contacto>> getAllContactos() {
        List<Contacto> contactos = contactoService.getAllContactos();
        return new ResponseEntity<>(contactos, HttpStatus.OK);
    }

    // Obtener un contacto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contacto> getContactoById(@PathVariable String id) {
        return contactoService.getContactoById(id)
                .map(contacto -> new ResponseEntity<>(contacto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar un contacto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContacto(@PathVariable String id) {
        if (contactoService.deleteContacto(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Buscar un contacto por nombre y apellido
    @GetMapping("/buscar")
    public ResponseEntity<Contacto> getContactoByNombreAndApellido(@RequestParam String nombre, @RequestParam String apellido) {
        return contactoService.getContactoByNombreAndApellido(nombre, apellido)
                .map(contacto -> new ResponseEntity<>(contacto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
