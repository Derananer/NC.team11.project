package com.company.employeeMicroservice.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class TokenAuthentication implements Authentication {

    private String token;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAuthenticated;
    private TokenUser principal;


    public TokenAuthentication(String token) {
        this.token = token;
    }

    public TokenAuthentication(
            String token,
            boolean isAuthenticated,
            TokenUser principal
    ) {
        this.token = token;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(principal.getDepartmentId()));
        this.isAuthenticated = isAuthenticated;
        this.principal = principal;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.isAuthenticated = b;
    }

    @Override
    public String getName() {
        if (principal != null)
            return principal.getUserName();
        else
            return null;
    }
}