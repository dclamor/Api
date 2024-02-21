package com.example.demoapi.comida;

import java.util.List;

// ComidaRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Integer> {
}
