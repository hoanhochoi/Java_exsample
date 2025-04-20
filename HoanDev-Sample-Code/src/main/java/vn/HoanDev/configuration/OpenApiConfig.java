package vn.HoanDev.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    private static final Logger log = LoggerFactory.getLogger(OpenApiConfig.class);

    @Bean
    public OpenAPI openAPI(@Value("${open.api.title}") String title,
                           @Value("${open.api.description}") String description,
                           @Value("${open.api.version}") String version,
                           @Value("${open.api.serverUrl}") String serverUrl,
                           @Value("${open.api.serverName}") String serverName
 ){
     return new OpenAPI().info(new Info().title(title).
             version(version).description(description).
             license(new License().name("api license").url("http://domain.vn/license")))
             .servers(List.of(new Server().url(serverUrl).description(serverName)));
//             . components (
//                     new Components()
//                             .addSecuritySchemes(
//                                      "bearerAuth",
//                                        new SecurityScheme()
//                                                .type(SecurityScheme. Type. HTTP)
//                                    . scheme ("bearer")
//                    .bearerFormat("JWT")))
//                        .security(List.of(new SecurityRequirement().addList(  "bearerAuth")));
 }

    @Bean
    public GroupedOpenApi groupedOpenApi(){
     return GroupedOpenApi.builder()
             .group("api-server")
             .packagesToScan("vn.HoanDev.controller")
             .build();

    }
}
