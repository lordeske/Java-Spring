package com.eske.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.HttpSecurityDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.lang.reflect.Array;
import java.security.Security;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class Config {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {


        http.sessionManagement(menagment -> menagment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(Authorize -> Authorize
                        .requestMatchers("/api/admin/**")
                        .hasAnyRole("ROLE_FASTFOOD_OWNER","ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                .addFilterBefore(new JwtTokenVaalidator(), BasicAuthenticationFilter.class)
                .csrf(cstf-> cstf.disable())
                .cors(cors-> cors.configurationSource(corsConfigurationSource));


        return null ;
    }


    private CorsConfigurationSource corsConfigurationSource()
    {


        return  new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();

                cfg.setAllowedOrigins(List.of(
                        "http://localhost:3000"
                ));


                cfg.setAllowedMethods(
                        Collections.singletonList("*")
                );

                cfg.setAllowCredentials(true);

                cfg.setAllowedHeaders(Collections.singletonList("*"));

                cfg.setExposedHeaders(Arrays.asList("Authorization"));

                cfg.setMaxAge(3600L);
                return  cfg;
            }
        };
    }


    @Bean
    PasswordEncoder passwordEncoder()  ///for pass code
    {
        return  new BCryptPasswordEncoder();
    }



}