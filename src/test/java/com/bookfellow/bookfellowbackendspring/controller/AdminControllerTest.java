package com.bookfellow.bookfellowbackendspring.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminControllerTest {

  @Autowired
  private AdminController adminController;

  @Test
  void contextLoads() throws Exception {
    assertThat(adminController).isNotNull();
  }
}
