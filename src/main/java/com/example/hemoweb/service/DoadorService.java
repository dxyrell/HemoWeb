package com.example.hemoweb.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hemoweb.model.Doador;
import com.example.hemoweb.repository.DoadorRepository;

@Service
public class DoadorService {

    private final DoadorRepository doadorRepository;

    public DoadorService(DoadorRepository doadorRepository) {
        this.doadorRepository = doadorRepository;
    }

    @Transactional
    public Doador salvar(Doador doador) {
        return doadorRepository.save(doador);
    }

    @Transactional
    public Doador alterar(Doador doador) {
        Optional<Doador> doadorExistente = doadorRepository.findById(doador.getCpf());
        if (doadorExistente.isEmpty()) {
            throw new IllegalArgumentException("Doador não encontrado para alteração.");
        }
        return doadorRepository.save(doador);
    }

    @Transactional
    public void remover(String cpf) {
        doadorRepository.deleteById(cpf);
    }

    public Optional<Doador> buscarPorCpf(String cpf) {
        return doadorRepository.findById(cpf);
    }
}
