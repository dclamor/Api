package com.example.demoapi.tipoComida;

import org.springframework.web.bind.annotation.RestController;

import com.example.demoapi.comida.Comida;
import com.example.demoapi.comida.ComidaRepository;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/TipoComida")
@RequiredArgsConstructor

public class TipoComidaController {
    @Autowired
    private TipoComidaService tipoComidaService;
    @Autowired
    private TipoComidaRepository tipoComidaRepository;

    @Autowired
    private ComidaRepository comidaRepository;

    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TipoComida> createTipoComida(@RequestBody TipoComida tipoComida) {
       
        
         // Verificar si el TipoComida ya existe en la base de datos
        Optional<TipoComida> existingTipoComida = tipoComidaRepository.findById(tipoComida.getId());
        if (existingTipoComida.isPresent()) {
            TipoComida updatedTipoComida = existingTipoComida.get();
            // Actualizar los campos relevantes del TipoComida
            updatedTipoComida.setNombre(tipoComida.getNombre());
            // Otros campos que deseas actualizar

            // Actualizar las comidas asociadas
            for (Comida comida : tipoComida.getListaComidas()) {
                // Verificar si la comida ya existe
                Optional<Comida> existingComida = comidaRepository.findById(comida.getId());
                if (existingComida.isPresent()) {
                    // Actualizar los campos relevantes de la comida
                    Comida updatedComida = existingComida.get();
                    updatedComida.setNombre(comida.getNombre());
                    // Otros campos que deseas actualizar

                    // Guardar la comida actualizada
                    comidaRepository.save(updatedComida);
                } else {
                    // Si la comida no existe, agregarla al TipoComida actualizado
                    comida.setTipoComida(updatedTipoComida);
                    updatedTipoComida.getListaComidas().add(comida);
                }
            }
            // Guardar el TipoComida actualizado
            return new ResponseEntity<>(updatedTipoComida, HttpStatus.CREATED);

        } else {
        // Log para verificar el TipoComida recibido       
        tipoComida.getListaComidas().forEach(comida -> comida.setTipoComida(tipoComida));
        TipoComida savedTipoComida = tipoComidaService.saveTipoComida(tipoComida);
        return new ResponseEntity<>(savedTipoComida, HttpStatus.CREATED);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<TipoComida> getTipoComidaById(@PathVariable int id) {
        Optional<TipoComida> tipoComida = tipoComidaService.getTipoComidaById(id);
        return tipoComida.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
