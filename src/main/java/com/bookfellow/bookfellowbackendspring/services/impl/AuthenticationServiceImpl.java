package com.bookfellow.bookfellowbackendspring.services.impl;

import com.bookfellow.bookfellowbackendspring.dto.JwtAuthenticationResponse;
import com.bookfellow.bookfellowbackendspring.dto.RefreshTokenRequest;
import com.bookfellow.bookfellowbackendspring.dto.SigninRequest;
import com.bookfellow.bookfellowbackendspring.dto.SignupRequest;
import com.bookfellow.bookfellowbackendspring.entity.Role;
import com.bookfellow.bookfellowbackendspring.entity.User;
import com.bookfellow.bookfellowbackendspring.repository.UserRepository;
import com.bookfellow.bookfellowbackendspring.services.AuthenticationService;
import com.bookfellow.bookfellowbackendspring.services.JWTService;
import java.time.Instant;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JWTService jwtService;

  public User signup(SignupRequest signUpRequest) {
    User user = new User();
    user.setFirstName(signUpRequest.getFirstName());
    user.setLastName(signUpRequest.getLastName());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setRole(Role.USER);
    Instant now = java.time.Instant.now();
    user.setCreatedAt(now);
    user.setUpdatedAt(now);

    return userRepository.save(user);
  }

  public JwtAuthenticationResponse signin(SigninRequest signInRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
            signInRequest.getPassword()));

    User user = userRepository.findByEmail(signInRequest.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    String jwt = jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

    JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
    jwtAuthenticationResponse.setToken(jwt);
    jwtAuthenticationResponse.setRefreshToken(refreshToken);
    return jwtAuthenticationResponse;
  }

  public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
    String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
    User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
      String jwt = jwtService.generateToken(user);

      JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
      jwtAuthenticationResponse.setToken(jwt);
      jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
      return jwtAuthenticationResponse;
    } else {
      throw new IllegalArgumentException("Invalid token");
    }
  }
}
