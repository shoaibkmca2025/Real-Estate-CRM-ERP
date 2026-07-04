package com.bcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Application entry point.
 *
 * Upgraded from Spring Boot 1.5 (WAR + SpringBootServletInitializer) to Spring
 * Boot 3 — now an executable JAR with embedded Tomcat (run via
 * {@code java -jar} or {@code mvn spring-boot:run}).
 */
@SpringBootApplication
@EnableScheduling
public class BuilderAppDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuilderAppDemoApplication.class, args);
	}
}
