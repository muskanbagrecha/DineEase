package com.mb.DineEase.config;

import com.mb.DineEase.constants.ApplicationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public void filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/api/admin/**").hasRole(ApplicationConstants.RESTAURANT_MANAGER)
                                .requestMatchers("/api/**").authenticated()
                                .anyRequest().permitAll()); //for login and signup endpoint
        http.build();
    }

}
