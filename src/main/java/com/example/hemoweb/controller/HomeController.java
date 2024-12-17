package com.example.hemoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.hemoweb.model.Doador;
import com.example.hemoweb.model.Usuario;
import com.example.hemoweb.model.Bolsa;
import com.example.hemoweb.service.DoadorService;
import com.example.hemoweb.service.UsuarioService;
import com.example.hemoweb.service.BolsaService;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DoadorService doadorService;

    @Autowired
    private BolsaService bolsaService;

    // Página inicial
    @GetMapping("/")
    public String retornarPaginaInicial() {
        return "index";
    }

    // Página de doadores
    @GetMapping("/pages/doadores")
    public String retornarPaginaDoadores() {
        return "doadores/doadores";
    }

    // Lista de doadores com paginação
    @GetMapping("/pages/fragment/lista-doadores")
    public String listarDoadoresHTML(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("cpf")));

        Page<Doador> pageDoadores = doadorService.listarDoadoresPaginados(pageable);

        model.addAttribute("previousPageUrl",
                "/pages/fragment/lista-doadores?page=" + (pageDoadores.getNumber() - 1) + "&size=" + pageDoadores.getSize());
        model.addAttribute("nextPageUrl",
                "/pages/fragment/lista-doadores?page=" + (pageDoadores.getNumber() + 1) + "&size=" + pageDoadores.getSize());
        model.addAttribute("pageDoadores", pageDoadores);
        model.addAttribute("doadores", pageDoadores.getContent());
        return "fragment/lista-doadores";
    }

    // Página de usuários
    @GetMapping("/pages/usuarios")
    public String retornarPaginaUsuarios() {
        return "usuarios/usuarios";
    }

    // Lista de usuários com paginação
    @GetMapping("/pages/fragment/lista-usuarios")
    public String listarUsuariosHTML(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("cpf")));

        Page<Usuario> pageUsuarios = usuarioService.listarUsuariosPaginados(pageable);

        model.addAttribute("previousPageUrl",
                "/pages/fragment/lista-usuarios?page=" + (pageUsuarios.getNumber() - 1) + "&size=" + pageUsuarios.getSize());
        model.addAttribute("nextPageUrl",
                "/pages/fragment/lista-usuarios?page=" + (pageUsuarios.getNumber() + 1) + "&size=" + pageUsuarios.getSize());
        model.addAttribute("pageUsuarios", pageUsuarios);
        model.addAttribute("usuarios", pageUsuarios.getContent());
        return "fragment/lista-usuarios";
    }

    // Página de bolsas
    @GetMapping("/pages/bolsas")
    public String retornarPaginaBolsas() {
        return "bolsas/bolsas";
    }

    // Lista de bolsas com paginação
    @GetMapping("/pages/fragment/lista-bolsas")
    public String listarBolsasHTML(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));

        Page<Bolsa> pageBolsas = bolsaService.listarBolsasPaginadas(pageable);

        model.addAttribute("previousPageUrl",
                "/pages/fragment/lista-bolsas?page=" + (pageBolsas.getNumber() - 1) + "&size=" + pageBolsas.getSize());
        model.addAttribute("nextPageUrl",
                "/pages/fragment/lista-bolsas?page=" + (pageBolsas.getNumber() + 1) + "&size=" + pageBolsas.getSize());
        model.addAttribute("pageBolsas", pageBolsas);
        model.addAttribute("bolsas", pageBolsas.getContent());
        return "fragment/lista-bolsas";
    }
}
