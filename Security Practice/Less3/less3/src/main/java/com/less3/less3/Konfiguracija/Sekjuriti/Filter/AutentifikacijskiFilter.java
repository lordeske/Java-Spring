package com.less3.less3.Konfiguracija.Sekjuriti.Filter;

import com.less3.less3.Konfiguracija.Sekjuriti.Menadzers.CustomAuthMenadzer;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@AllArgsConstructor
@Component
public class AutentifikacijskiFilter extends OncePerRequestFilter {

    private final CustomAuthMenadzer customAuthMenadzer;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var a = customAuthMenadzer.authenticate(null);


        if(a.isAuthenticated())
        {
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request, response); /// Samo ako radi
        }




    }
}
