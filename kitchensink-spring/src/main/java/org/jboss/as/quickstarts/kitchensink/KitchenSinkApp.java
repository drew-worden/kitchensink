package org.jboss.as.quickstarts.kitchensink;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
(
    info = @io.swagger.v3.oas.annotations.info.Info(title = "Kitchen Sink API", version = "v1", description = "The Kitchen Sink API"),
        servers = {
            @io.swagger.v3.oas.annotations.servers.Server(url = "http://localhost:8080/rest/v1", description = "Local server"),
            @io.swagger.v3.oas.annotations.servers.Server(url = "http://kitchensink-spring.drew.zip/rest", description = "Deployed server")
        }
)
@SpringBootApplication
public class KitchenSinkApp {
    public static void main(String[] args) {
        SpringApplication.run(KitchenSinkApp.class, args);
    }
}
