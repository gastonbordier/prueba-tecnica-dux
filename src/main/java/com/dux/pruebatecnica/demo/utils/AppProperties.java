package com.dux.pruebatecnica.demo.utils;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppProperties {
    @Value("${spring.application.name}")
    private String appName;
    @Value("${app.version:1.0.0}")
    private String appVersion;
    @Value("${api.group}")
    private String apiGroup;
    @Value("${api.description}")
    private String apiDescription;
    @Value("${api.local.server.name}")
    private String apiLocalServerName;
    @Value("${api.local.server.url}")
    private String apiLocalServerUrl;

}
