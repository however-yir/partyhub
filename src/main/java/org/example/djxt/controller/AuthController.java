package org.example.djxt.controller;

import jakarta.validation.Valid;
import org.example.djxt.common.ApiResponse;
import org.example.djxt.config.JwtProperties;
import org.example.djxt.dto.auth.LoginRequest;
import org.example.djxt.dto.auth.LoginResponse;
import org.example.djxt.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          JwtProperties jwtProperties) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(request.getUsername());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setTokenType("Bearer");
        response.setExpiresIn(jwtProperties.getExpirationSeconds());
        response.setUsername(request.getUsername());

        return ApiResponse.ok("login success", response);
    }

    @GetMapping("/me")
    public ApiResponse<Map<String, Object>> currentUser(Authentication authentication) {
        Map<String, Object> profile = new LinkedHashMap<>();
        profile.put("username", authentication == null ? null : authentication.getName());
        profile.put("authenticated", authentication != null && authentication.isAuthenticated());
        return ApiResponse.ok(profile);
    }
}
