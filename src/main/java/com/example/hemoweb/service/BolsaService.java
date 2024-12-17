package com.example.hemoweb.service;

import com.example.hemoweb.model.Bolsa;
import com.example.hemoweb.repository.BolsaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BolsaService {

    @Autowired
    private BolsaRepository bolsaRepository;

    // Salvar bolsa
    public Bolsa criarBolsa(Bolsa bolsa) {
        return bolsaRepository.save(bolsa);
    }

    // Buscar por ID
    public Optional<Bolsa> buscarPorId(Integer id) {
        return bolsaRepository.findById(id);
    }

    // Editar bolsa
    public Bolsa editarBolsa(Integer id, Bolsa bolsa) {
        return bolsaRepository.findById(id).map(bolsaExistente -> {
            bolsaExistente.setStatus(bolsa.getStatus());
            bolsaExistente.setData(bolsa.getData());
            bolsaExistente.setVolume(bolsa.getVolume());
            bolsaExistente.setLocal(bolsa.getLocal());
            bolsaExistente.setCpfDoador(bolsa.getCpfDoador());
            return bolsaRepository.save(bolsaExistente);
        }).orElseThrow(() -> new RuntimeException("Bolsa com ID " + id + " não encontrada"));
    }

    // Deletar bolsa
    public void deletarBolsa(Integer id) {
        if (bolsaRepository.existsById(id)) {
            bolsaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Bolsa com ID " + id + " não encontrada");
        }
    }

    // Listar bolsas paginadas
    public Page<Bolsa> listarBolsasPaginadas(Pageable pageable) {
        return bolsaRepository.findAll(pageable);
    }
}
