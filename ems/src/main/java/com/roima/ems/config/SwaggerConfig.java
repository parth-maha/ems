package com.roima.ems.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI baseOpenAPI(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("EMS");


        Info info = new Info()
                .title("EMS")
                .version("1.0.0")
                .description("Employee Management System");

        return new OpenAPI().info(info).servers(List.of(server));
    }

}
