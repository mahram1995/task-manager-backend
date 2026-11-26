package com.example.dailytask.controller;

import com.example.dailytask.dto.ApiDtos.*;
import com.example.dailytask.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService s;

    public AuthController(AuthService s) {
        this.s = s;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest r) {
        System.out.println("hello login");
        return s.login(r);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request) {

        return ResponseEntity.ok(s.register(request));
    }
}