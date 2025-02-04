package com.springSec.security.service;

import com.springSec.security.model.Users;
import com.springSec.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JWTService jwtService;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public List<Users> getUsers() {
        return userRepo.findAll();
    }

    public Users addUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
             return  jwtService.generateToken(user.getUsername());
        return "failure";
    }
}
