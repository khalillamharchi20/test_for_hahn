package com.example.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() 
            .authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET,"/api/public/search").hasAnyRole("ADMINISTRATOR","HR")  
            .requestMatchers(HttpMethod.GET,"/api/public/filter").hasAnyRole("ADMINISTRATOR","HR")  
            .requestMatchers(HttpMethod.GET,"/api/public/department").hasRole("MANAGER") 
                .requestMatchers(HttpMethod.GET,"/api/public").hasAnyRole("ADMINISTRATOR","HR")  
                .requestMatchers(HttpMethod.POST,"/api/public").hasAnyRole("ADMINISTRATOR","HR") 
                .requestMatchers(HttpMethod.DELETE,"/api/public/{id}").hasAnyRole("ADMINISTRATOR","HR")
                .requestMatchers(HttpMethod.GET,"/api/public/{id}").hasAnyRole("ADMINISTRATOR","HR")
                
                .requestMatchers(HttpMethod.PUT,"/api/public/{id}").hasAnyRole("ADMINISTRATOR","HR","MANAGER")   
                .requestMatchers("/api/admin/**").hasRole("ADMINISTRATOR") 
                .anyRequest().authenticated() 
            .and()
            .httpBasic();
        
        return http.build();
    }
  
}