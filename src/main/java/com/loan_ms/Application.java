package com.loan_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAware")
@OpenAPIDefinition(
			info = @Info(
						title = "Loans microservice REST API Documentation",
						description = "EazyBank Loans microservice REST API Documentation",
						version = "v1",
						contact = @Contact(
									name = "Avinash Verma",
									email = "avinash@gmail.com",
									url = "https://www.google.com"
								),
						license = @License(
									name = "Apache 2.0",
									url = "https://www.youtube.com"
								)
					),
			externalDocs = @ExternalDocumentation(
						description = "EazyBank Loans microservice REST API Documentation",
						url = "https://www.eazybytes.com/swagger-ui.html"
					)
		)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
