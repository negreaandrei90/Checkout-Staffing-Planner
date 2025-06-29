package com.negrea.csf.service;

import com.negrea.csf.dto.user.request.LoginRequest;
import com.negrea.csf.dto.user.request.UserDtoRequest;
import com.negrea.csf.dto.user.response.UserDtoResponse;
import com.negrea.csf.mapper.user.UserMapper;
import com.negrea.csf.model.user.User;
import com.negrea.csf.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserDtoResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if(!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return UserDtoResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .build();
    }

    public UserDtoResponse register(UserDtoRequest request) {
        User user = userRepository.save(userMapper.toEntity(request));

        return userMapper.toDto(user);
    }
}
