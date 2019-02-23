package service.interaction.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import service.interaction.security.TokenAuthentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;

public class TokenAuthenticationManager implements AuthenticationManager {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (authentication instanceof TokenAuthentication) {
            TokenAuthentication readyTokenAuthentication = processAuthentication((TokenAuthentication) authentication);
            return readyTokenAuthentication;
        } else {
            authentication.setAuthenticated(false);
            return authentication;
        }

    }

    private TokenAuthentication processAuthentication(TokenAuthentication authentication) throws AuthenticationException {
        String token = authentication.getToken();
        String key = "key123";
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
            return buildFullTokenAuthentication(authentication, claims);
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }
        //if (claims.get(TokenData.EXPIRATION_DATE.getValue(), String.class) == null)
         //   throw new AuthenticationServiceException("Invalid token");
        //Date expiredDate = new Date(claims.get("TOKEN_EXPIRATION_DATE", Long.class));
        //if (expiredDate.after(new Date()))
        //    return new TokenAuthentication(authentication.getToken(),  true, (TokenUser)authentication.getPrincipal());
        //else
        //    throw new AuthenticationServiceException("Token expired date error");
    }

    private TokenAuthentication buildFullTokenAuthentication(TokenAuthentication authentication, DefaultClaims claims) {
        TokenUser tokenUser = new TokenUser(
                claims.get(TokenData.USER_NAME.getValue(), String.class),
                claims.get(TokenData.DEPARTMENT_ID.getValue(), String.class),
                claims.get(TokenData.PERMISSION.getValue(), String.class)
        );
        TokenAuthentication fullTokenAuthentication =
                new TokenAuthentication(authentication.getToken(), true, tokenUser);
        return fullTokenAuthentication;

    }
}

