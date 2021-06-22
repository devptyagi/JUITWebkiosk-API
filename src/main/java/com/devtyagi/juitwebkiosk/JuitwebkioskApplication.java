package com.devtyagi.juitwebkiosk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class JuitwebkioskApplication {

	public static void main(String[] args) {
		SpringApplication.run(JuitwebkioskApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.paths(PathSelectors.ant("/api/*"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfoBuilder()
				.title("JUIT WebKiosk API")
				.description("API for accessing JUIT Webkiosk Student Data")
				.version("1.0")
				.contact(new Contact("Dev Tyagi", "https://www.linkedin.com/in/devtyagi",  "devptyagi01@gmail.com"))
				.build();
	}

}
