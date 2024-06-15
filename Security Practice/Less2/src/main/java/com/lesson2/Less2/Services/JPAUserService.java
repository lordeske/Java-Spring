package com.lesson2.Less2.Services;

import com.lesson2.Less2.Repo.UserRepo;
import com.lesson2.Less2.Secu.SecuUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class JPAUserService implements UserDetailsService {



    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepo.findUserByUsername(username);

       return user.map(SecuUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Korisnik nije pronadjen: " + username ));



    }
}
