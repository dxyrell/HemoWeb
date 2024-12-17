package com.example.hemoweb.service;

import com.example.hemoweb.model.Doador;
import com.example.hemoweb.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoadorService {

    @Autowired
    private DoadorRepository doadorRepository;

    // Salvar doador
    public Doador criarDoador(Doador doador) {
        return doadorRepository.save(doador);
    }

    // Buscar por CPF
    public Optional<Doador> buscarPorCpf(Integer cpf) {
        return doadorRepository.findById(cpf);
    }

    // Editar doador
    public Doador editarDoador(Integer cpf, Doador doador) {
        return doadorRepository.findById(cpf).map(doadorExistente -> {
            doadorExistente.setNome(doador.getNome());
            doadorExistente.setEmail(doador.getEmail());
            doadorExistente.setTelefone(doador.getTelefone());
            doadorExistente.setEndereco(doador.getEndereco());
            doadorExistente.setNascimento(doador.getNascimento());
            doadorExistente.setTipoSanguineo(doador.getTipoSanguineo());
            return doadorRepository.save(doadorExistente);
        }).orElseThrow(() -> new RuntimeException("Doador com CPF " + cpf + " não encontrado"));
    }

    // Deletar doador
    public void deletarDoador(Integer cpf) {
        if (doadorRepository.existsById(cpf)) {
            doadorRepository.deleteById(cpf);
        } else {
            throw new RuntimeException("Doador com CPF " + cpf + " não encontrado");
        }
    }

    // Método para listar doadores paginados
    public Page<Doador> listarDoadoresPaginados(Pageable pageable) {
        return doadorRepository.findAll(pageable);
    }
}
