package com.mislbd.sprint.serviceImpl;

import com.mislbd.sprint.entity.UserEntity;
import com.mislbd.sprint.repository.UserRepository;
import com.mislbd.sprint.security.JwtService;
import com.mislbd.sprint.dto.ApiDtos;
import com.mislbd.sprint.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.*;

@Component
public class AuthServiceImpl implements AuthService {
    public final UserRepository userRepository;
    private final AuthenticationManager manager;
    private final JwtService jwt;
    private final PasswordEncoder passwordEncoder;



    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager m, JwtService j, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        manager = m;
        jwt = j;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ApiDtos.LoginResponse login(ApiDtos.LoginRequest r) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(r.userName(), r.password()));
        return new ApiDtos.LoginResponse(jwt.generate(r.userName()), r.userName());
    }
    @Override
    public String register(ApiDtos.RegisterRequest request) {

        if (userRepository.existsByUserName(request.userName())) {
            throw new RuntimeException("Username already exists");
        }

        UserEntity user = new UserEntity();
        user.setUserName(request.userName());

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);

        return "User registered successfully";
    }

}
