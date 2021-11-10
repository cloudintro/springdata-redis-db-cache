package io.github.smallintro.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	// localhost:8081/swagger-ui/index.html
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("io.github.smallintro.redis.controller"))
				.paths(PathSelectors.any()).build();
	}

	private  ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Small Into to Spring Boot Redis as Database, Cache and Message broker")
				.description("Demo Spring Boot Application's API")
				.version("v0.0.1")
				.contact(new Contact("Sushil Prasad","https://smallintro.github.io",""))
				.license("License")
				.licenseUrl("https://github.com/smallintro/springdata-redis-db-cache/LICENSE")
				.build();
	}

}