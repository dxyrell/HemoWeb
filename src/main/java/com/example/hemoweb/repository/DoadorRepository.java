package com.example.hemoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hemoweb.model.Doador;
import com.example.hemoweb.repository.queries.doador.DoadorQueries;

public interface DoadorRepository extends JpaRepository<Doador, String>, DoadorQueries {
    // O segundo parâmetro (String) é o tipo do identificador (CPF)
}
