package com.example.hemoweb.filter;

public class DoadorFilter {

    private String cpf; // Adicionado para filtro pelo CPF
    private String nome;
    private String tipoSanguineo;

    // Getter e Setter para CPF
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Getter e Setter para Nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para Tipo Sanguíneo
    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    @Override
    public String toString() {
        return "cpf: " + cpf + "\nnome: " + nome + "\ntipoSanguineo: " + tipoSanguineo;
    }
}
