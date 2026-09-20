package com.pi.estoquemadeireira.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI estoqueMadeireiraOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Estoque Madeireira API")
                        .description("API REST para gestao de estoque e vendas de uma madeireira. "
                                + "Modulos: Produtos (controle de estoque), Vendas (baixa automatica "
                                + "de estoque em transacao atomica), Clientes e consulta de endereco "
                                + "via integracao externa com a API ViaCEP (Spring Cloud OpenFeign).")
                        .version("1.0.0")
                        .contact(new Contact().name("Projeto Final - Back-end Spring Boot")));
    }
}
