package com.example.hemoweb.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "doadores")
public class Doador {

    @Id
    @Column(name = "cpf", nullable = false, unique = true)
    private Integer cpf; // CPF como chave primária

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "telefone", nullable = false, length = 15)
    private String telefone;

    @Column(name = "endereco", length = 150)
    private String endereco;

    @Column(name = "nascimento", nullable = false)
    private LocalDate nascimento;

    @Column(name = "tipo_sanguineo", nullable = false, length = 3)
    private String tipoSanguineo;

    // Getters e Setters

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    @Override
    public String toString() {
        return "Doador{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", nascimento=" + nascimento +
                ", tipoSanguineo='" + tipoSanguineo + '\'' +
                '}';
    }
}
