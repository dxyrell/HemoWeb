package com.example.hemoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) // Exclui configuração automática de segurança
@EnableJpaRepositories("com.example.hemoweb.repository") // Habilita repositórios no pacote especificado
@EntityScan("com.example.hemoweb.model") // Escaneia entidades no pacote especificado
public class HemowebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HemowebApplication.class, args);
    }
}
