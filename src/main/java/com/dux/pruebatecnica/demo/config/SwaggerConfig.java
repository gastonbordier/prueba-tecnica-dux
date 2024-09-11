package com.dux.pruebatecnica.demo.config;

import com.dux.pruebatecnica.demo.utils.AppProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private final AppProperties properties;

    @Bean
    GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .displayName(properties.getAppName())
                .group(properties.getApiGroup())
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title(properties.getAppName()).
                        description(properties.getApiDescription()).
                        version(properties.getAppVersion()))
                .openapi("3.0.3")
                .servers(List.of(new Server().description(properties.getApiLocalServerName())
                        .url(properties.getApiLocalServerUrl())));

    }
}
