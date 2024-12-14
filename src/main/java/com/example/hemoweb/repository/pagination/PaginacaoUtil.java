package com.example.hemoweb.repository.pagination;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;

public class PaginacaoUtil {

    private static final Logger logger = LoggerFactory.getLogger(PaginacaoUtil.class);

    /**
     * Prepara o intervalo de registros que será retornado com base na página e na quantidade de registros por página.
     *
     * @param typedQuery Consulta tipada a ser configurada.
     * @param pageable   Informações de paginação (número da página e tamanho).
     */
    public static void prepararIntervalo(TypedQuery<?> typedQuery, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
        logger.debug("Filtrando a página {}, registros entre {} e {}", paginaAtual, primeiroRegistro, primeiroRegistro + totalRegistrosPorPagina);
        typedQuery.setFirstResult(primeiroRegistro);
        typedQuery.setMaxResults(totalRegistrosPorPagina);
    }

    /**
     * Prepara a ordenação da consulta com base nos atributos definidos no Pageable.
     *
     * @param root          Raiz da consulta (entidade principal).
     * @param criteriaQuery Consulta de critérios do JPA.
     * @param builder       Construtor de critérios do JPA.
     * @param pageable      Informações de paginação e ordenação.
     */
    public static void prepararOrdem(Root<?> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder, Pageable pageable) {
        String atributo;
        Sort sort = pageable.getSort();
        List<Order> ordenacoes = new ArrayList<>();
        if (sort != null && !sort.isEmpty()) {
            for (Sort.Order o : sort) {
                logger.debug("Ordenando o resultado da pesquisa por {}, {}", o.getProperty(), o.getDirection());
                atributo = o.getProperty();
                Order order = o.isAscending() ? builder.asc(root.get(atributo)) : builder.desc(root.get(atributo));
                ordenacoes.add(order);
            }
        }
        criteriaQuery.orderBy(ordenacoes);
    }
}
