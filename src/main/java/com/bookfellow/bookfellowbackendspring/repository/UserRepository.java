package com.bookfellow.bookfellowbackendspring.repository;

import com.bookfellow.bookfellowbackendspring.entity.Role;
import com.bookfellow.bookfellowbackendspring.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);

  User findByRole(Role role);
}
