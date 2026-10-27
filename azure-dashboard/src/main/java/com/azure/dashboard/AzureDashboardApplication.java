package com.azure.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Main entry point for the Azure Management Dashboard application.
 * Extends SpringBootServletInitializer for Azure Web App (WAR) deployment.
 */
@SpringBootApplication
public class AzureDashboardApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AzureDashboardApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(AzureDashboardApplication.class, args);
    }
}
