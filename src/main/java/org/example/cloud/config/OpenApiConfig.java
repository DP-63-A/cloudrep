package org.example.cloud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Automated Tax Filing API")
                        .version("1.0.0")
                        .description("""
                        This is the backend API for Automated Tax Filing System.

                        [Swagger UI here](/swagger-ui/index.html)
                        """)
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your.email@example.com")
                        )
                );
    }
}
