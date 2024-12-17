package com.example.hemoweb.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.hemoweb.model.Usuario;
import com.example.hemoweb.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Buscar usuário pelo CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> buscarUsuarioPorCpf(@PathVariable Integer cpf) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorCpf(cpf);
        return usuario.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    // Deletar usuário pelo CPF
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer cpf) {
        usuarioService.deletarUsuario(cpf);
        return ResponseEntity.noContent().build();
    }

    // Editar usuário pelo CPF
    @PutMapping("/{cpf}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Integer cpf, @RequestBody Usuario usuario) {
        System.out.println("Recebido CPF: " + cpf);
        System.out.println("Recebido Dados: " + usuario);
        Usuario usuarioEditado = usuarioService.editarUsuario(cpf, usuario);
        return ResponseEntity.ok(usuarioEditado);
    }
    
}
