package com.sassur.backend.service;

import com.sassur.backend.model.CriterioDerivacion;
import com.sassur.backend.model.ExamenLink;
import com.sassur.backend.repository.CriterioRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private CriterioRepository criterioRepository;

    public void cargarCriteriosDesdeExcel(MultipartFile file) {
    try {
        // Leer el archivo Excel
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);  // Asumimos que estamos trabajando con la primera hoja

        // Procesar las filas
        List<CriterioDerivacion> criterios = new ArrayList<>();
        
        for (Row row : sheet) {
            // Verifica si la fila está vacía (si la primera celda es vacía, puede indicar una fila vacía)
            if (row.getCell(0) != null && !row.getCell(0).toString().isEmpty()) {
                CriterioDerivacion criterio = new CriterioDerivacion();
                
                // Asegúrate de que las celdas tengan datos antes de asignarlos
                criterio.setEspecialidad(getStringCell(row.getCell(0)));
                criterio.setPatologia(getStringCell(row.getCell(1)));
                criterio.setCriteriosDerivacion(getStringCell(row.getCell(2)));
                criterio.setExamenesPrevios(getStringCell(row.getCell(3)));
                criterio.setObservaciones(getStringCell(row.getCell(4)));

                // Agregar el criterio a la lista solo si tiene datos
                criterios.add(criterio);
            }
        }

        // Guardar los criterios solo si hay datos
        if (!criterios.isEmpty()) {
            criterioRepository.saveAll(criterios);
        }

        workbook.close();  // Cierra el archivo de Excel después de procesarlo

    } catch (Exception e) {
        throw new RuntimeException("Error al procesar el archivo Excel: " + e.getMessage());
    }
}

// Método auxiliar para obtener los valores de las celdas
private String getStringCell(Cell cell) {
    if (cell == null) {
        return "";
    }
    return cell.getStringCellValue();
}

}
