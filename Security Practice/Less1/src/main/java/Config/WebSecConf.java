package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecConf {

    @Bean
    public UserDetailsService userDetailsService()
    {
        var uds = new inMemoryUserDetailManager();
        return null;
    }

}
