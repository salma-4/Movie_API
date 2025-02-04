package com.movie_api.service;

import com.movie_api.dto.user.UserLoginRequestDto;
import com.movie_api.dto.user.UserRegistrationRequestDto;
import com.movie_api.dto.user.UserResponseDto;

public interface AuthService {

    UserResponseDto signup(UserRegistrationRequestDto newUser);
    UserResponseDto login(UserLoginRequestDto userLoginRequest);
}
