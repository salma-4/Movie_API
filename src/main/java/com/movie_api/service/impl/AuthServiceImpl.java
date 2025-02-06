package com.movie_api.service.impl;

import com.movie_api.Mapper.UserMapper;
import com.movie_api.dto.request.UserLoginRequestDto;
import com.movie_api.dto.request.UserRegistrationRequestDto;
import com.movie_api.dto.response.UserResponseDto;
import com.movie_api.entity.Token;
import com.movie_api.entity.User;
import com.movie_api.exception.ConflictException;
import com.movie_api.exception.RecordNotFoundException;
import com.movie_api.repository.TokenRepository;
import com.movie_api.repository.UserRepository;
import com.movie_api.security.JwtService;
import com.movie_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    @Transactional
    public UserResponseDto signup(UserRegistrationRequestDto newUser) {
        String userEmail = newUser.getEmail();
        Optional<User> existingUser  = userRepository.findByEmail(userEmail);
        if(existingUser.isPresent())
        {
            log.error("User enter existing email "+ userEmail);
            throw new ConflictException("Email already exist");
        }
        User user  = userMapper.toUserEntity(newUser);
        userRepository.save(user);

        String jwt = jwtService.generateToken(user);
        Token token = userMapper.toEntity(user,jwt);
        tokenRepository.save(token);

        return userMapper.toUserResponseDto(token);
    }

    @Override
    @Transactional
    public UserResponseDto login(UserLoginRequestDto userLoginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getEmail(),
                        userLoginRequest.getPassword())
        );

        User user = userRepository.findByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new RecordNotFoundException("Invalid email or password"));

        String jwt = jwtService.generateToken(user);
        Token token = userMapper.toEntity(user, jwt);
        tokenRepository.save(token);

        log.info("User logged in successfully: {}", userLoginRequest.getEmail());
        return userMapper.toUserResponseDto(token);

    }
}
