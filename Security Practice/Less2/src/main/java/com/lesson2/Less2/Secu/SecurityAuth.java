package com.lesson2.Less2.Secu;

import com.lesson2.Less2.Tabele.Authority;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@AllArgsConstructor
public class SecurityAuth implements GrantedAuthority {



    private final Authority authority;
    @Override
    public String getAuthority() {

        return authority.getImeprava();

    }
}
