package com.bookfellow.bookfellowbackendspring.controller;

import com.bookfellow.bookfellowbackendspring.dto.JwtAuthenticationResponse;
import com.bookfellow.bookfellowbackendspring.dto.RefreshTokenRequest;
import com.bookfellow.bookfellowbackendspring.dto.SigninRequest;
import com.bookfellow.bookfellowbackendspring.dto.SignupRequest;
import com.bookfellow.bookfellowbackendspring.entity.User;
import com.bookfellow.bookfellowbackendspring.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;

  @PostMapping("/signup")
  public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest) {
    return ResponseEntity.ok(authenticationService.signup(signupRequest));
  }

  @PostMapping("/signin")
  public ResponseEntity<JwtAuthenticationResponse> signin(
      @RequestBody SigninRequest signinRequest) {
    return ResponseEntity.ok(authenticationService.signin(signinRequest));
  }

  @PostMapping("/refresh")
  public ResponseEntity<JwtAuthenticationResponse> refresh(
      @RequestBody RefreshTokenRequest refreshTokenRequest) {
    return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
  }
}
