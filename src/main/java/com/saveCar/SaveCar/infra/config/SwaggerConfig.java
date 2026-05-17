package com.saveCar.SaveCar.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenApi() {

        //Contact contact = new Contact();
        //contact.name("Felipe");
        //contact.email("felipehgresele@gmail.com");

        Info info = new Info();
        info.title("SaveCar");
        info.version("v1");
        info.description("Aplicação para gerenciamento de veículos");

        return new OpenAPI().info(info);
    };

}
