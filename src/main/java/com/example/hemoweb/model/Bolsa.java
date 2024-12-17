package com.example.hemoweb.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bolsas")
public class Bolsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id; // ID único da bolsa

    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @Column(name = "data", nullable = false, length = 10)
    private String data; // Formato esperado: "yyyy-MM-dd"

    @Column(name = "volume", nullable = false)
    private Float volume; // Volume em mililitros (ml)

    @Column(name = "local", nullable = false, length = 100)
    private String local;

    @Column(name = "cpf_doador", nullable = false)
    private Integer cpfDoador; // CPF do doador (chave estrangeira)

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getCpfDoador() {
        return cpfDoador;
    }

    public void setCpfDoador(Integer cpfDoador) {
        this.cpfDoador = cpfDoador;
    }

    @Override
    public String toString() {
        return "Bolsa{" +
                "id=" + id +
                ", status=" + status +
                ", data='" + data + '\'' +
                ", volume=" + volume +
                ", local='" + local + '\'' +
                ", cpfDoador=" + cpfDoador +
                '}';
    }
}
