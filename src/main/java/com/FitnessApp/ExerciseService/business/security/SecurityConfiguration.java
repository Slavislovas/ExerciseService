package com.FitnessApp.ExerciseService.business.security;

import com.FitnessApp.ExerciseService.business.security.filter.JWTTokenValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(new JWTTokenValidationFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .antMatchers("/exercise/find/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/exercise/delete/**", "/exercise/edit/**", "/exercise/create").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()
                .httpBasic()
                .and()
                .build();
    }
}
