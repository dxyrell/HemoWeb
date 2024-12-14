package com.example.hemoweb.repository.queries.doador;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.hemoweb.filter.DoadorFilter;
import com.example.hemoweb.model.Doador;
import com.example.hemoweb.repository.pagination.PaginacaoUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class DoadorQueriesImpl implements DoadorQueries {

    private static final Logger logger = LoggerFactory.getLogger(DoadorQueriesImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Doador> pesquisar(DoadorFilter filtro, Pageable pageable) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Doador> criteriaQuery = builder.createQuery(Doador.class);
        Root<Doador> d = criteriaQuery.from(Doador.class);
        TypedQuery<Doador> typedQuery;

        List<Predicate> predicateList = new ArrayList<>();
        Predicate[] predArray;

        // Filtros baseados no DoadorFilter
        if (StringUtils.hasText(filtro.getNome())) {
            predicateList.add(builder.like(builder.lower(d.get("nome")), "%" + filtro.getNome().toLowerCase() + "%"));
        }
        if (StringUtils.hasText(filtro.getTipoSanguineo())) {
            predicateList.add(builder.equal(d.get("tipoSanguineo"), filtro.getTipoSanguineo()));
        }
        if (filtro.getCpf() != null) {
            predicateList.add(builder.equal(d.get("cpf"), filtro.getCpf()));
        }

        // Configurando a query para os resultados paginados
        predArray = new Predicate[predicateList.size()];
        predicateList.toArray(predArray);
        criteriaQuery.select(d).where(predArray);

        // Ordenação e paginação
        PaginacaoUtil.prepararOrdem(d, criteriaQuery, builder, pageable);
        typedQuery = em.createQuery(criteriaQuery);
        PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
        List<Doador> doadores = typedQuery.getResultList();

        // Calculando o total de registros com os filtros aplicados
        logger.info("Calculando o total de registros que o filtro retornará.");
        CriteriaQuery<Long> criteriaQueryTotal = builder.createQuery(Long.class);
        Root<Doador> dTotal = criteriaQueryTotal.from(Doador.class);
        criteriaQueryTotal.select(builder.count(dTotal)).where(predArray);
        TypedQuery<Long> typedQueryTotal = em.createQuery(criteriaQueryTotal);
        long totalDoadores = typedQueryTotal.getSingleResult();

        logger.info("O filtro retornará {} registros.", totalDoadores);

        return new PageImpl<>(doadores, pageable, totalDoadores);
    }
}
