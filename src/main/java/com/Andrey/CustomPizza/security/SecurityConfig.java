package com.Andrey.CustomPizza.security;

import com.Andrey.CustomPizza.model.UserDetails.Usr;
import com.Andrey.CustomPizza.repository.Users.UsrRepository;
import com.Andrey.CustomPizza.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.Principal;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()
                .antMatchers("/aaa").authenticated()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UsrRepository usrRepository){
        return map -> {
            String id = (String)map.get("sub");

            Usr usr = usrRepository.findById(id).orElseGet(()->{
                Usr newUsr = new Usr();

                newUsr.setEmail((String)map.get("email"));
                newUsr.setName((String)map.get("name"));
                newUsr.setId(id);
                return newUsr;
            });
            return usrRepository.save(usr);
        };
    }
}
