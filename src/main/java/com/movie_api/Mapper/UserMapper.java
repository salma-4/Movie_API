package com.movie_api.Mapper;

import com.movie_api.dto.request.UserRegistrationRequestDto;
import com.movie_api.dto.response.UserResponseDto;
import com.movie_api.entity.Token;
import com.movie_api.entity.User;

public interface UserMapper {
    User toUserEntity(UserRegistrationRequestDto user);
    UserResponseDto toUserResponseDto(Token token);

    Token toEntity(User user,String jwt);
}
