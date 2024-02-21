package com.example.demoapi.comida;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoapi.comida.Comida;
import com.example.demoapi.comida.ComidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ComidaService {
      @Autowired
    private ComidaRepository comidaRepository;

    public Comida saveComida(Comida comida) {
        return comidaRepository.save(comida);
    }

}
