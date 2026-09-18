package com.pi.estoquemadeireira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EstoqueMadeireiraApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstoqueMadeireiraApplication.class, args);
    }

}
