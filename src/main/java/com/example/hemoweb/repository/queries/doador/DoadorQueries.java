package com.example.hemoweb.repository.queries.doador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hemoweb.filter.DoadorFilter;
import com.example.hemoweb.model.Doador;

public interface DoadorQueries {

    Page<Doador> pesquisar(DoadorFilter filtro, Pageable pageable);

}
