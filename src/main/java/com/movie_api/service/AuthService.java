package com.movie_api.service;

import com.movie_api.dto.request.UserLoginRequestDto;
import com.movie_api.dto.request.UserRegistrationRequestDto;
import com.movie_api.dto.response.UserResponseDto;

public interface AuthService {

    UserResponseDto signup(UserRegistrationRequestDto newUser);
    UserResponseDto login(UserLoginRequestDto userLoginRequest);
}
