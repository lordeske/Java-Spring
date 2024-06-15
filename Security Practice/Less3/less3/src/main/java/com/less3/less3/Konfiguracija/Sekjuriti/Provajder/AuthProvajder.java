package com.less3.less3.Konfiguracija.Sekjuriti.Provajder;

import com.less3.less3.Konfiguracija.Sekjuriti.Auth.CustomAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AuthProvajder implements AuthenticationProvider {

    @Value("${nas.tajni.kljuc}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuth ca = (CustomAuth) authentication;

            String headerKey = ca.getKljuc();


            if(key.equals(headerKey))
            {
                return new CustomAuth(true,null);
            }

            else throw new BadCredentialsException("Ne valja podaci");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuth.class.equals(authentication);
    }
}
