package com.example.hemoweb.controller;

import com.example.hemoweb.model.Doador;
import com.example.hemoweb.service.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doadores")
@CrossOrigin(origins = "*")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    // Buscar doador pelo CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Doador> buscarDoador(@PathVariable Integer cpf) {
        Optional<Doador> doador = doadorService.buscarPorCpf(cpf);
        return doador.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar um novo doador
    @PostMapping
    public ResponseEntity<Doador> criarDoador(@RequestBody Doador doador) {
        Doador novoDoador = doadorService.criarDoador(doador);
        return ResponseEntity.ok(novoDoador);
    }

    // Deletar um doador pelo CPF
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarDoador(@PathVariable Integer cpf) {
        doadorService.deletarDoador(cpf);
        return ResponseEntity.noContent().build();
    }

    // Editar dados de um doador
    @PutMapping("/{cpf}")
    public ResponseEntity<Doador> editarDoador(@PathVariable Integer cpf, @RequestBody Doador doador) {
        Doador doadorEditado = doadorService.editarDoador(cpf, doador);
        return ResponseEntity.ok(doadorEditado);
    }
}
