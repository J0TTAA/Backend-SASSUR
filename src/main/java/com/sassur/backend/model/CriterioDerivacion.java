package com.sassur.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "criterios_derivacion")  // Define la colección en MongoDB
public class CriterioDerivacion {

    @Id
    private String id;  // ID único generado por MongoDB

    private String especialidad;  // Especialidad médica
    private String patologia;     // Patología para derivación
    private String criteriosDerivacion; // Criterios que definen la derivación
    private String examenesPrevios; // Exámenes previos necesarios
    private String observaciones;  // Observaciones adicionales
    private List<ExamenLink> examenesLinks; // Lista de exámenes opcionales con enlaces

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getPatologia() { return patologia; }
    public void setPatologia(String patologia) { this.patologia = patologia; }

    public String getCriteriosDerivacion() { return criteriosDerivacion; }
    public void setCriteriosDerivacion(String criteriosDerivacion) { this.criteriosDerivacion = criteriosDerivacion; }

    public String getExamenesPrevios() { return examenesPrevios; }
    public void setExamenesPrevios(String examenesPrevios) { this.examenesPrevios = examenesPrevios; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public List<ExamenLink> getExamenesLinks() { return examenesLinks; }
    public void setExamenesLinks(List<ExamenLink> examenesLinks) { this.examenesLinks = examenesLinks; }
}
