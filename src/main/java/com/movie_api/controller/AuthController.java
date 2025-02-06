package com.movie_api.controller;

import com.movie_api.dto.request.UserLoginRequestDto;
import com.movie_api.dto.request.UserRegistrationRequestDto;
import com.movie_api.dto.response.UserResponseDto;
import com.movie_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard-api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRegistrationRequestDto newUser){
        UserResponseDto response = authService.signup(newUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequest){
        UserResponseDto response = authService.login(userLoginRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
