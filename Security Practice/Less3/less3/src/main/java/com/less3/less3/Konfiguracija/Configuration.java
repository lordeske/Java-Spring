package com.less3.less3.Konfiguracija;

import com.less3.less3.Konfiguracija.Sekjuriti.Filter.AutentifikacijskiFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@org.springframework.context.annotation.Configuration
@AllArgsConstructor
public class Configuration {

    private final AutentifikacijskiFilter autentifikacijskiFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .addFilterAt(autentifikacijskiFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .build();
    }



}
