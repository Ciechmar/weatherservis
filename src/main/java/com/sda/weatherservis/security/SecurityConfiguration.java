package com.sda.weatherservis.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    //         /localization                  -> /localization
    //         /localization/{id}             -> /localization/*
    //                                        -> /localization/**
    //         /localization/{id}/forecast    -> /localization/*/forecast
    //                                        -> /localization/**

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/localization").permitAll()
//                .antMatchers(HttpMethod.POST, "/localization").hasAuthority("ROLE_ADMIN") // .hasRole("ADMIN")
                .anyRequest().permitAll() // authenticated()
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable()
                .httpBasic()
                .and()
                .userDetailsService(userDetailsService);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
