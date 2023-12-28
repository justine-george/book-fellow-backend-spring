package com.bookfellow.bookfellowbackendspring.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
  UserDetailsService userDetailsService();
}
