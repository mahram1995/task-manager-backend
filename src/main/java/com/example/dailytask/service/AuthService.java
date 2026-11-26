package com.example.dailytask.service;

import com.example.dailytask.dto.ApiDtos.*;
import com.example.dailytask.entity.User;
import com.example.dailytask.repository.UserRepository;
import com.example.dailytask.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.*;

@Service
public class AuthService {
    public final UserRepository userRepository;
    private final AuthenticationManager manager;
    private final JwtService jwt;
    private final PasswordEncoder passwordEncoder;


    public AuthService(UserRepository userRepository, AuthenticationManager m, JwtService j, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        manager = m;
        jwt = j;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest r) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(r.userName(), r.password()));
        return new LoginResponse(jwt.generate(r.userName()), r.userName());
    }
    public String register(RegisterRequest request) {

        if (userRepository.existsByUserName(request.userName())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUserName(request.userName());

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        return "User registered successfully";
    }

}
