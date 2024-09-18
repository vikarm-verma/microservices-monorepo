package com.example.configclient.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  // Enable dynamic refresh for configuration properties
public class ConfigClientController {

    @Value("${info.environment:default-env}")
    private String environment;

    @Value("${configclient.message:default-message}")
    private String message;

    @Value("${configclient.database.url:default-url}")
    private String dbUrl;

    @Value("${configclient.database.username:default-username}")
    private String dbUsername;

    @Value("${configclient.database.password:default:password}")
    private String dbPassword;

    @GetMapping("/info")
    public String getInfo() {
        return String.format(" checking Environment: %s, Message: %s", environment, message);
    }

    @GetMapping("/dbinfo")
    public String getDatabaseInfo() {
        return String.format("Database URL: %s, Username: %s, Password: %s", dbUrl, dbUsername, dbPassword);
    }
}