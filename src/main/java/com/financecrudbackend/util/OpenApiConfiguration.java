package com.financecrudbackend.util;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "financeCrudApi")
    public OpenAPI financeCrudApi() {
        return new OpenAPI().components(new Components()).info(new Info().title("Finance Crud API")
                .description("Finance Crud API implemented with Spring Boot RESTful service and documented using springdoc-openapi"));
    }
}