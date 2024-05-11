package com.eske.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {


    private SecretKey key = Keys.hmacShaKeyFor(JwtConst.SEACRET_KEY.getBytes());


    public String generateToken(Authentication auth)
    {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        return Jwts.builder().setIssuedAt(new Date())
                .setExpiration((new Date(new Date().getTime()+8400000)))
                .claim("email",auth.getName())
                .claim("authorities",roles)
                .signWith(key)
                .compact();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> auths = new HashSet<>();

        for(GrantedAuthority authority: authorities)
        {
            auths.add(authority.getAuthority());
        }

        return String.join(",",auths);
    }



    public String getEmailFromJWTTOken(String t)
    {
        t = t.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(t).getBody();


        return String.valueOf(claims.get("email"));
    }

}
