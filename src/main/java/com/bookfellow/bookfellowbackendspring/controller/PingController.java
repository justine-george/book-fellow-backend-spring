package com.bookfellow.bookfellowbackendspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ping")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PingController {
  @GetMapping
  public ResponseEntity<String> ping() {
    return ResponseEntity.ok("Pong");
  }
}
