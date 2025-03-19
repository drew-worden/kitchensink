// Package
package org.jboss.as.quickstarts.kitchensink;

// Imports
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class of the application.
 */
@OpenAPIDefinition
(
    info = @io.swagger.v3.oas.annotations.info.Info(title = "Kitchen Sink API", version = "v1", description = "The Kitchen Sink API"),
        servers = {
            @io.swagger.v3.oas.annotations.servers.Server(url = "http://localhost:8080", description = "Local server"),
            @io.swagger.v3.oas.annotations.servers.Server(url = "https://kitchensink-spring.drew.zip", description = "Deployed server")
        }
)
@SpringBootApplication
public class KitchenSinkApp {
    /**
     * The main method of the application.
     * @param args The arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(KitchenSinkApp.class, args);
    }
}
