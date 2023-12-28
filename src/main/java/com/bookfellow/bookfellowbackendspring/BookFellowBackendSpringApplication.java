package com.bookfellow.bookfellowbackendspring;

import com.bookfellow.bookfellowbackendspring.entity.Role;
import com.bookfellow.bookfellowbackendspring.entity.User;
import com.bookfellow.bookfellowbackendspring.repository.UserRepository;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookFellowBackendSpringApplication implements CommandLineRunner {

  @Autowired
  private UserRepository userRepository;

  public static void main(String[] args) {
    SpringApplication.run(BookFellowBackendSpringApplication.class, args);
  }

  @Override
  public void run(String... args) {
    User adminAccount = userRepository.findByRole(Role.ADMIN);

    if (adminAccount == null) {
      User admin = new User();
      admin.setFirstName("Admin First Name");
      admin.setLastName("Admin Last Name");
      admin.setEmail("admin@test.com");
      admin.setRole(Role.ADMIN);
      admin.setPassword(new BCryptPasswordEncoder().encode("adminpassword"));
      Instant now = java.time.Instant.now();
      admin.setCreatedAt(now);
      admin.setUpdatedAt(now);

      userRepository.save(admin);
    }
  }
}
