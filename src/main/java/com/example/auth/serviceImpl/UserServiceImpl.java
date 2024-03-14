package com.example.auth.serviceImpl;

import com.example.auth.Repositories.UserRepository;
import com.example.auth.entities.User;
import com.example.auth.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserRepository userRepository ;
    private static final long TOKEN_EXPIRATION_TIME = 3600000;

    @Value("${security.jwt.secret}")
    String jwtSecret ;

    @Override
    public User SignUp(User user) {
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setPassword((user.getPassword()));
        userRepository.save(user1);

        return user1;
    }

    @Override
    public User SignIn(User user) {
      User user1 = userRepository.findUserByEmail(user.getEmail());
      if(user1== null){
          throw new IllegalArgumentException("user not exists");
      }
      if (!user1.getPassword().equals(user.getPassword())){
          throw new IllegalArgumentException("password not valid ");
      }
          String token = generateToken(user1.getId());

          user1.setToken(token);

          userRepository.save(user1);

        return user1;
    }


    private String generateToken(Long userId ) {
        Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

}
