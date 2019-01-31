package com.company.employeeMicroservice.security;

/*
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    public TokenAuthenticationFilter(){
        super("/**");
        setAuthenticationSuccessHandler((request, response, authentication)->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            request.getRequestDispatcher(request.getServletPath() + request.getPathInfo())
                    .forward(request, response);
        });
        setAuthenticationFailureHandler((request, response, authenticationException)->
        {
            response.getOutputStream().print(authenticationException.getMessage());
        });

    }



    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(TokenData.TOKEN.getValue());
        if (token == null)
            token = request.getParameter(TokenData.TOKEN.getValue());
        if (token == null) {
            TokenAuthentication authentication = new TokenAuthentication(null);
            authentication.setAuthenticated(false);
            return authentication;
        }
        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        Authentication authentication = getAuthenticationManager().authenticate(tokenAuthentication);
        return authentication;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
    }
}*/