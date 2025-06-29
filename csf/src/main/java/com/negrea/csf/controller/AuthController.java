package com.negrea.csf.controller;

import com.negrea.csf.dto.user.request.LoginRequest;
import com.negrea.csf.dto.user.request.UserDtoRequest;
import com.negrea.csf.dto.user.response.UserDtoResponse;
import com.negrea.csf.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/csf/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<UserDtoResponse> login(@RequestBody LoginRequest request) {
        UserDtoResponse response = service.login(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDtoResponse> register(@RequestBody UserDtoRequest request) {
        UserDtoResponse response = service.register(request);

        return ResponseEntity.ok(response);
    }
}
