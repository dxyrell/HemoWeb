package com.example.hemoweb.controller;

import com.example.hemoweb.model.Bolsa;
import com.example.hemoweb.service.BolsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bolsas")
@CrossOrigin(origins = "*")
public class BolsaController {

    @Autowired
    private BolsaService bolsaService;

    // Buscar bolsa pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Bolsa> buscarBolsa(@PathVariable Integer id) {
        Optional<Bolsa> bolsa = bolsaService.buscarPorId(id);
        return bolsa.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar uma nova bolsa
    @PostMapping
    public ResponseEntity<Bolsa> criarBolsa(@RequestBody Bolsa bolsa) {
        Bolsa novaBolsa = bolsaService.criarBolsa(bolsa);
        return ResponseEntity.ok(novaBolsa);
    }

    // Deletar uma bolsa pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBolsa(@PathVariable Integer id) {
        bolsaService.deletarBolsa(id);
        return ResponseEntity.noContent().build();
    }

    // Editar dados de uma bolsa
    @PutMapping("/{id}")
    public ResponseEntity<Bolsa> editarBolsa(@PathVariable Integer id, @RequestBody Bolsa bolsa) {
        Bolsa bolsaEditada = bolsaService.editarBolsa(id, bolsa);
        return ResponseEntity.ok(bolsaEditada);
    }
}
