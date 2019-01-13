package com.company.authorisationservice;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetTokenService {

    public static final int TOKEN_DAYS_ALIVE = 15;

    public String getToken(User user){
        final Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("permission", user.getPermission());
        tokenData.put("userName", user.getUserName());
        tokenData.put("departmentId", user.getDepartmentId());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, TOKEN_DAYS_ALIVE);
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "key";
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
        return token;
    }

}
