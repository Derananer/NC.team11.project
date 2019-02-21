package com.company.authorisationservice.security;



import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {
    @Value("${security.jwt.uri:/auth/**}")
    private String Uri;

    @Value("${security.jwt.header:token}")
    private String tokenHeader;

    @Value("${security.jwt.header:department}")
    private String departmentIdHeader;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey1232456472364862131-48238462314284823165214-2341hfdhjsfsdfsdfsagdgas342asf}")
    private String secret;

    public String getUri() {
        return Uri;
    }

    public String getTokenHeader() {
        return tokenHeader;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDepartmentIdHeader() {
        return departmentIdHeader;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }
}

