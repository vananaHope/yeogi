package com.vanana.yeogi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.vanana.yeogi.base")
@EnableJpaRepositories(basePackages = "com.vanana.yeogi.base")
@SpringBootApplication(scanBasePackages = {"com.vanana.yeogi"})
public class GuestApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuestApplication.class, args);
    }
}