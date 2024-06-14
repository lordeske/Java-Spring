package com.eske.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.Security;
import java.util.List;

public class JwtTokenVaalidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String jwt = request.getHeader(JwtConst.HEADER_NAME);


        if (jwt!= null)
        {
            jwt = jwt.substring(7);



            try {

                SecretKey key= Keys.hmacShaKeyFor(JwtConst.SEACRET_KEY.getBytes());
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                        .parseClaimsJws(jwt).getBody();

                String email = String.valueOf(claims.get("email"));
                String authors = String.valueOf(claims.get("authorities"));


                //DOBIJAMO ROLOVE, ADMIN, ROLEOWNER itd, i onda ih prevatamo u list

                List<GrantedAuthority> aut = AuthorityUtils.commaSeparatedStringToAuthorityList(authors);

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,aut);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception e)
            {
                throw  new BadCredentialsException("Netacan token");
            }

            filterChain.doFilter(request, response);


        }




    }
}
