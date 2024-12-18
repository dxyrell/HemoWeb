package com.example.hemoweb.repository;

import com.example.hemoweb.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<Doador, Integer> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}
