package com.bcs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration.
 *
 * Changes from the Spring Boot 1.5 version:
 *  - {@code WebMvcConfigurerAdapter} (removed in Spring 6) -> {@code WebMvcConfigurer}.
 *  - CORS is locked down to configured SPA origins instead of {@code allowedOrigins("*")}.
 *
 * The legacy DAOs obtain their Hibernate {@code SessionFactory} by unwrapping the
 * JPA {@code EntityManagerFactory} in a {@code @PostConstruct} (see each *DaoImpl),
 * which avoids declaring a {@code SessionFactory} bean — that would form a cycle
 * with Spring Boot's JPA auto-configuration.
 */
@Configuration
public class DBConfig implements WebMvcConfigurer {

	@Value("${app.cors.allowed-origins}")
	private String[] allowedOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(allowedOrigins)
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(true);
	}
}
