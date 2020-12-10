package ru.foodtechlab.callcenter.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Конфигурация документации
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket authApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("auth-api");
    }
}
