package com.dux.pruebatecnica.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.error(authentication.isAuthenticated()+"aaa");


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
