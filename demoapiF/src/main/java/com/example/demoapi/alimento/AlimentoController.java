package com.example.demoapi.alimento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

   
    
    @Autowired
    private AlimentoService alimentoService;

    @PostMapping("/addSeveral")
    public ResponseEntity<String> addSeveralAlimentos(@RequestBody List<Alimento> alimentos) {
        alimentoService.addSeveralAlimentos(alimentos);
        return new ResponseEntity<>("Alimentos agregados correctamente.", HttpStatus.CREATED);
    }

    @PostMapping("/addOne")
    public ResponseEntity<Alimento> addOneAlimento(@RequestBody Alimento alimento) {
        Alimento savedAlimento = alimentoService.addAlimento(alimento);
        return new ResponseEntity<>(savedAlimento, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Alimento>> getAllAlimentos() {
        List<Alimento> alimentos = alimentoService.getAllAlimentos();
        return new ResponseEntity<>(alimentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> getAlimentoById(@PathVariable Long id) {
        Optional<Alimento> alimento = alimentoService.getAlimentoById(id);
        return alimento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    }
    