package com.jjara.blog.oauth.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
@EnableConfigurationProperties(KeycloakServerProperties.class)
public class AuthorizationServerApp {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationServerApp.class);

    public static void main(final String[] args) {
        SpringApplication.run(AuthorizationServerApp.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> onApplicationReadyEventListener(final ServerProperties serverProperties, final KeycloakServerProperties keycloakServerProperties) {
        return (evt) -> {
            var port = serverProperties.getPort();
            var contextPath = keycloakServerProperties.getContextPath();
            LOG.info("Embedded Keycloak started: http://localhost:{}{} to use keycloak", port, contextPath);
        };
    }

}