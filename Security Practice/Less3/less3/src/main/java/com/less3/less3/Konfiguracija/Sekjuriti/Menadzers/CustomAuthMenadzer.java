package com.less3.less3.Konfiguracija.Sekjuriti.Menadzers;

import com.less3.less3.Konfiguracija.Sekjuriti.Provajder.AuthProvajder;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomAuthMenadzer implements AuthenticationManager {

    private final AuthProvajder authProvajder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authProvajder.supports(authentication))
        {
            return authProvajder.authenticate(authentication);
        }
    }
}
