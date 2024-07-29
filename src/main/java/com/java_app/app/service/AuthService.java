package com.java_app.app.service;

import com.java_app.app.dto.LoginDto;
import com.java_app.app.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
