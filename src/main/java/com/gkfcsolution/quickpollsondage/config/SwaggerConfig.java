package com.gkfcsolution.quickpollsondage.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class SwaggerConfig {

/*
    // Configuration OpenAPI pour la version v1 avec un groupe spécifique
    @Bean
    @Qualifier("v1")
    @Primary
    public OpenAPI customOpenAPIv1() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de gestion des sondages - Version 1")
                        .version("1.0")
                        .description("Documentation de l'API des sondages pour la version 1 avec Spring Boot 3 et OpenAPI")
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
                        new Server().url("http://localhost:8080/v1").description("Local Server v1"),
                        new Server().url("https://api.frankguekeng.com/v1").description("Preproduction Server v1"),
                        new Server().url("https://api.gkfcsolution.com/v1").description("Production Server v1")
                ));
    }

    // Configuration OpenAPI pour la version v2 avec un groupe spécifique
    @Bean
    @Qualifier("v2")
    public OpenAPI customOpenAPIv2() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de gestion des sondages - Version 2")
                        .version("2.0")
                        .description("Documentation de l'API des sondages pour la version 2 avec Spring Boot 3 et OpenAPI")
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
                        new Server().url("http://localhost:8080/v2").description("Local Server v2"),
                        new Server().url("https://api.frankguekeng.com/v2").description("Preproduction Server v2"),
                        new Server().url("https://api.gkfcsolution.com/v2").description("Production Server v2")
                ));
    }*/

/*    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }*/

 /*   @Bean
    public GroupedOpenApi apiV1() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/v1/**")
                .addOperationCustomizer((operation, handlerMethod) -> operation.summary("API Version 1"))
                .build();
    }

    @Bean
    public GroupedOpenApi apiV2() {
        return GroupedOpenApi.builder()
                .group("v2")
                .pathsToMatch("/v2/**")
                .addOperationCustomizer((operation, handlerMethod) -> operation.summary("API Version 2"))
                .build();
    }

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
    }*/


    @Bean
    public GroupedOpenApi v1Api() {
        return GroupedOpenApi.builder()
                .group("v1") // Le nom du groupe pour cette version
                .pathsToMatch("/v1/**") // Regroupe toutes les routes correspondant à /v1/*
                .addOpenApiCustomizer(openApi -> openApi
                        .info(new Info()
                                .title("API de gestion des sondages - Version 1")
                                .version("1.0")
                                .description("Documentation de l'API des sondages pour la version 1 avec Spring Boot 3 et OpenAPI")
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
                                new Server().url("http://localhost:8080/v1").description("Local Server v1"),
                                new Server().url("https://api.frankguekeng.com/v1").description("Preproduction Server v1"),
                                new Server().url("https://api.gkfcsolution.com/v1").description("Production Server v1")
                        ))
                )
                .build();
    }

    @Bean
    public GroupedOpenApi v2Api() {
        return GroupedOpenApi.builder()
                .group("v2") // Le nom du groupe pour cette version
                .pathsToMatch("/v2/**") // Regroupe toutes les routes correspondant à /v2/*
                .addOpenApiCustomizer(openApi -> openApi
                        .info(new Info()
                                .title("API de gestion des sondages - Version 2")
                                .version("2.0")
                                .description("Documentation de l'API des sondages pour la version 2 avec Spring Boot 3 et OpenAPI")
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
                                new Server().url("http://localhost:8080/v2").description("Local Server v2"),
                                new Server().url("https://api.frankguekeng.com/v2").description("Preproduction Server v2"),
                                new Server().url("https://api.gkfcsolution.com/v2").description("Production Server v2")
                        ))
                )
                .build();
    }

    @Bean
    public GroupedOpenApi v3Api() {
        return GroupedOpenApi.builder()
                .group("v3") // Le nom du groupe pour cette version
                .pathsToMatch("/v3/**") // Regroupe toutes les routes correspondant à /v2/*
                .addOpenApiCustomizer(openApi -> openApi
                        .info(new Info()
                                .title("API de gestion des sondages - Version 3")
                                .version("3.0")
                                .description("Documentation de l'API des sondages pour la version 3 avec Spring Boot 3 et OpenAPI")
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
                                new Server().url("http://localhost:8080/v3").description("Local Server v3"),
                                new Server().url("https://api.frankguekeng.com/v3").description("Preproduction Server v3"),
                                new Server().url("https://api.gkfcsolution.com/v3").description("Production Server v3")
                        ))
                )
                .build();
    }

}
