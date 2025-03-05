package com.gkfcsolution.quickpollsondage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

/*    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }*/

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de gestion des sondages")
                        .version("1.0")
                        .description("Documentation de l'API des sondages avec avec Spring Boot 3 et OpenAPI")
                        .contact(new Contact()
                                .name("GKFCSolution - Frank GUEKENG")
                                .email("gkfcsolution@gmail.com")
                                .url("https://www.gkfcsolution.com")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server"),
                        new Server().url("https://api.frankguekeng.com").description("preproduction Server"),
                        new Server().url("https://api.gkfcsolution.com").description("Production Server")
                ));
    }

}
