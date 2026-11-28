package com.mislbd.sprint.service;

import com.mislbd.sprint.dto.ApiDtos;

public interface AuthService {
    public ApiDtos.LoginResponse login(ApiDtos.LoginRequest r);

    public String register(ApiDtos.RegisterRequest request);
}
