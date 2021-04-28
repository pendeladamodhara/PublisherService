package com.pkglobel.app.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-26T14:41:51.027Z")
@Configuration
public class SwaggerDocumentationConfig {

	ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Customer Publisher Service")
				.description(
						"This API is used to post customer data into kafka server")
				.license("").licenseUrl("http://unlicense.org")
				.termsOfServiceUrl("").version("1.0.0")
				.contact(new Contact("", "", "pdamodara@pkglobal.com")).build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("io.swagger.api"))
				.build()
				.directModelSubstitute(org.threeten.bp.LocalDate.class,
						java.sql.Date.class)
				.directModelSubstitute(org.threeten.bp.OffsetDateTime.class,
						java.util.Date.class).apiInfo(apiInfo());
	}

}
