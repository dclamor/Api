package com.example.demoapi.alimento;

import com.example.demoapi.comida.Comida;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Alimento")
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    @Column(unique = true)
    private String nombre;
    private int valorCalorico;
    
    
}
