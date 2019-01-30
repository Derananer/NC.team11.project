package com.company.authorisationservice;



import com.company.authorisationservice.security.TokenData;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    public static final int TOKEN_DAYS_ALIVE = 15;

    @Autowired
    private UserRepository userRepository;

    public User add(UserCreation userCreation) throws Exception {
        if(userRepository.findByEmail(userCreation.email) != null) throw new Exception("Email exists");
        if(userRepository.findByUserName(userCreation.userName) != null) throw new Exception("username exists");
        User user = new User(
                userCreation.firstName,
                userCreation.lastName,
                userCreation.secondName,
                userCreation.departmentId,
                userCreation.email,
                userCreation.userName,
                userCreation.password
        );
        user.setPermission(Permission.USER.getValue());
        return userRepository.save(user);

    }

    public String logIn(String loginString, String password) throws Exception {
        User user = userRepository.findByEmail(loginString);
        if(user == null) user = userRepository.findByUserName(loginString);
        if(user != null && user.getPassword().equals(password)) return getToken(user);
        else return null;

    }

    public String getToken(User user) {

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(TokenData.USER_NAME.getValue(), user.getUserName());
        tokenData.put(TokenData.DEPARTMENT_ID.getValue(), user.getDepartmentId());
        tokenData.put(TokenData.PERMISSION.getValue(), user.getPermission());
        //tokenData.put(TokenData.PERMISSION.getValue(), user.getPermission());
        //Calendar calendar = Calendar.getInstance();
        //calendar.add(Calendar.YEAR, 100);
        //tokenData.put("token_expiration_date", calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        //jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "aaaa123456789aaaa123456789aaaa123456789aaaa123456789aaaa123456789";
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS256, key).compact();
        return token;

    }
}
