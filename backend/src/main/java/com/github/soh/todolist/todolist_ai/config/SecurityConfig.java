package com.github.soh.todolist.todolist_ai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean @Deprecated
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests().requestMatchers("/swagger-ui/**","/v3/api-docs/**", "/tasks/**").permitAll().anyRequest().authenticated().and().httpBasic();

        return http.build();
    }

}
