package com.example.redditClone.controller;

import com.example.redditClone.dto.AuthenticationResponse;
import com.example.redditClone.dto.LoginRequest;
import com.example.redditClone.dto.RefreshTokenRequest;
import com.example.redditClone.dto.RegisterRequest;
import com.example.redditClone.service.AuthService;
import com.example.redditClone.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User registration successful", HttpStatus.OK);
    }
    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("User verified successfully", HttpStatus.OK);
    }
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }
    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body("Refresh Token Deleted Successfully!!");
    }
}
