package com.movie_api.Mapper.impl;

import com.movie_api.Mapper.UserMapper;
import com.movie_api.dto.request.UserRegistrationRequestDto;
import com.movie_api.dto.response.UserResponseDto;
import com.movie_api.entity.Token;
import com.movie_api.entity.User;
import com.movie_api.entity.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public User toUserEntity(UserRegistrationRequestDto user) {
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(Role.valueOf(user.getRole().toUpperCase()))
                .build();
    }

    @Override
    public UserResponseDto toUserResponseDto(Token token) {
        return UserResponseDto.builder()
                .jwt(token.getJwt())
                .expiredAt(token.getExpiredAt())
                .build();
    }

    @Override
    public Token toEntity(User user, String jwt) {
        return Token.builder()
                .jwt(jwt)
                .createdAt(ZonedDateTime.now())
                .expiredAt(ZonedDateTime.now().plusHours(1))
                .user(user)
                .build();
    }
}
