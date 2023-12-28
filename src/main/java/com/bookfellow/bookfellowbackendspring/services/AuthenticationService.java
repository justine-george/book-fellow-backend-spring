package com.bookfellow.bookfellowbackendspring.services;

import com.bookfellow.bookfellowbackendspring.dto.JwtAuthenticationResponse;
import com.bookfellow.bookfellowbackendspring.dto.RefreshTokenRequest;
import com.bookfellow.bookfellowbackendspring.dto.SigninRequest;
import com.bookfellow.bookfellowbackendspring.dto.SignupRequest;
import com.bookfellow.bookfellowbackendspring.entity.User;

public interface AuthenticationService {
  User signup(SignupRequest signUpRequest);

  JwtAuthenticationResponse signin(SigninRequest signInRequest);

  JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
