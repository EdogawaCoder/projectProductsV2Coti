package com.edo.configurations;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI productApiOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("productApi")
                .description("API's product management")
                .version("v1")
                .contact(new Contact().name("Edogawa Team").email("edev.coderx@gmail.com")))
            .servers(List.of(
                new Server().url("http://localhost:8082").description("localhost"),
                new Server().url("https://api.edogawa.com").description("Prod")
            ));
    }

    // Opcional: agrupar endpoints (por path)
    @Bean
    public GroupedOpenApi productGroup() {
        return GroupedOpenApi.builder()
            .group("products")
            .pathsToMatch("api/v1/products/**")
            .build();
    }
    
    @Bean
    public GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
            .group("all")
            .pathsToMatch("/**")
            .build();
    }
}
