package com.bcs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 6 configuration — stateless, JWT-based.
 *
 * Replaces the legacy setup where {@code security.basic.enabled=false} disabled
 * security entirely and auth state lived in the HTTP session. Public endpoints
 * (login / forgot password / swagger) are permitted; everything else requires a
 * valid bearer token validated by {@link JwtAuthenticationFilter}.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	/** Endpoints reachable without a JWT (the legacy unauthenticated entry points). */
	private static final String[] PUBLIC_ENDPOINTS = {
			"/loginWebService",
			"/forgotPassword",
			"/addLoginDetails",
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/swagger-ui.html"
	};

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// CORS handled by WebMvcConfigurer (DBConfig); CSRF not needed for stateless JWT APIs.
				.csrf(AbstractHttpConfigurer::disable)
				.cors(cors -> {})
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
						.requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
						.anyRequest().authenticated())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	/**
	 * BCrypt encoder bean (existing password hashes are BCrypt, so they keep working).
	 * Named {@code bCryptPasswordEncoder} to avoid clashing with the legacy
	 * {@code com.bcs.utility.PasswordEncoder} @Component (bean name "passwordEncoder").
	 */
	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
