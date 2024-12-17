package com.example.hemoweb.service;

import com.example.hemoweb.model.Usuario;
import com.example.hemoweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Salvar usuário
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Buscar por CPF
    public Optional<Usuario> buscarUsuarioPorCpf(Integer cpf) {
        return usuarioRepository.findById(cpf);
    }

    // Editar usuário
    public Usuario editarUsuario(Integer cpf, Usuario usuario) {
        return usuarioRepository.findById(cpf).map(usuarioExistente -> {
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setSenha(usuario.getSenha());
            usuarioExistente.setCargo(usuario.getCargo());
            return usuarioRepository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuário com CPF " + cpf + " não encontrado"));
    }

    // Deletar usuário
    public void deletarUsuario(Integer cpf) {
        if (usuarioRepository.existsById(cpf)) {
            usuarioRepository.deleteById(cpf);
        } else {
            throw new RuntimeException("Usuário com CPF " + cpf + " não encontrado");
        }
    }

    // Listar usuários paginados
    public Page<Usuario> listarUsuariosPaginados(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
}
