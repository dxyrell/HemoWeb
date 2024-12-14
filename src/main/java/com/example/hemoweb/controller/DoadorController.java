package com.example.hemoweb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hemoweb.filter.DoadorFilter;
import com.example.hemoweb.model.Doador;
import com.example.hemoweb.pagination.PageWrapper;
import com.example.hemoweb.repository.DoadorRepository;
import com.example.hemoweb.service.DoadorService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/doadores")
public class DoadorController {

    private static final Logger logger = LoggerFactory.getLogger(DoadorController.class);

    private final DoadorRepository doadorRepository;
    private final DoadorService doadorService;

    public DoadorController(DoadorRepository doadorRepository, DoadorService doadorService) {
        this.doadorRepository = doadorRepository;
        this.doadorService = doadorService;
    }

    @GetMapping("/todos")
    public String mostrarTodosDoadores(Model model) {
        List<Doador> doadores = doadorRepository.findAll();
        logger.info("Doadores buscados: {}", doadores);
        model.addAttribute("doadores", doadores);
        return "doadores/todos";
    }

    @GetMapping("/cadastrar")
    public String abrirPaginaCadastro(Doador doador) {
        return "doadores/cadastro";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Doador doador) {
        doadorService.salvar(doador);
        return "redirect:/doadores/sucesso";
    }

    @GetMapping("/sucesso")
    public String abrirSucesso(Model model) {
        model.addAttribute("mensagem", "Cadastro de Doador Efetuado com Sucesso");
        return "mensagem";
    }

    @GetMapping("/pesquisar")
    public String pesquisar(DoadorFilter filtro, Model model,
            @PageableDefault(size = 7) @SortDefault(sort = "cpf", direction = Sort.Direction.ASC) Pageable pageable,
            HttpServletRequest request) {
        Page<Doador> pagina = doadorRepository.pesquisar(filtro, pageable);
        logger.info("Doadores pesquisados: {}", pagina.getContent());
        PageWrapper<Doador> paginaWrapper = new PageWrapper<>(pagina, request);
        model.addAttribute("pagina", paginaWrapper);
        return "doadores/doadores";
    }

    @PostMapping("/abriralterar")
    public String abrirAlterar(Doador doador, Model model) {
        model.addAttribute("doador", doador);
        return "doadores/alterar";
    }

    @PostMapping("/alterar")
    public String alterar(Doador doador) {
        doadorService.alterar(doador);
        return "redirect:/doadores/sucesso2";
    }

    @GetMapping("/sucesso2")
    public String abrirSucesso2(Model model) {
        model.addAttribute("mensagem", "Alteração de Doador Efetuada com Sucesso");
        return "mensagem";
    }

    @PostMapping("/confirmarremocao")
    public String confirmarRemocao(Doador doador, Model model) {
        model.addAttribute("doador", doador);
        return "doadores/confirmarremocao";
    }

    @PostMapping("/remover")
    public String remover(Doador doador) {
        doadorService.remover(doador.getCpf());
        return "redirect:/doadores/sucesso3";
    }

    @GetMapping("/sucesso3")
    public String abrirSucesso3(Model model) {
        model.addAttribute("mensagem", "Remoção de Doador Efetuada com Sucesso");
        return "mensagem";
    }
}
