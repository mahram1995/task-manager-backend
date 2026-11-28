package com.mislbd.sprint.controller;

import com.mislbd.sprint.service.AuthService;
import com.mislbd.sprint.serviceImpl.AuthServiceImpl;
import com.mislbd.sprint.dto.ApiDtos;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService s;

    public AuthController(AuthServiceImpl s) {
        this.s = s;
    }
    @Operation(
            summary = "User Login",
            security = {}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Login successful"),
            @ApiResponse(responseCode = "401",
                    description = "Invalid username or password")
    })
    @PostMapping("/login")
    public ApiDtos.LoginResponse login(@Valid @RequestBody ApiDtos.LoginRequest r) {
        System.out.println("hello login");
        return s.login(r);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody ApiDtos.RegisterRequest request) {

        return ResponseEntity.ok(s.register(request));
    }
}