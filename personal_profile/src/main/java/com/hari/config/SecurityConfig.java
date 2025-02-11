package com.hari.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	@SneakyThrows
	SecurityFilterChain securityFilterChain(HttpSecurity security) {
		return security.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						req -> req.requestMatchers("/profile/**").permitAll().anyRequest().authenticated())
				.formLogin(Customizer.withDefaults())
				.build();
	}

}
