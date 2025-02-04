package com.springboot.eCommerce.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI eCommerceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("eCommerce Application")
                        .description("Backend APIs for eCommerce app")
                        .version("v1.0.0")
                        .contact(new Contact().name("Dominic Le").email("ngocha2k.ln@gmail.com"))
                        .license(new License().name("License").url("/"))
                );
    }
}
