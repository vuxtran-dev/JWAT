package com.example.demo.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/auth/login").permitAll()
				.requestMatchers("/auth/register").permitAll()
				.requestMatchers("/api/tasks").permitAll()
				.requestMatchers("/api/tasks/{id}").permitAll()
				.requestMatchers("/api/tasks/take/{taskId}").permitAll()
				.requestMatchers("/error").permitAll()
				.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(request -> {
					CorsConfiguration corsConfig = new CorsConfiguration();
					corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
					corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
					corsConfig.setAllowedHeaders(Arrays.asList("*"));
					corsConfig.setAllowCredentials(true);
					return corsConfig;
				}));

		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailService);
		return provider;

	}

}
