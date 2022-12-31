package com.jss.inventory.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@TestConfiguration
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/").permitAll()
                .anyExchange().authenticated()
                .and().httpBasic()
                .and().formLogin()
                .disable()
                .build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("username")
                .password("{noop}password")
                .roles("USER")
                .build();

        return new MapReactiveUserDetailsService(user);
    }
}
