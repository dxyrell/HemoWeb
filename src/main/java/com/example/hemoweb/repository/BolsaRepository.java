package com.example.hemoweb.repository;

import com.example.hemoweb.model.Bolsa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BolsaRepository extends JpaRepository<Bolsa, Integer> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}
