package com.group.libraryapp.controller.user.domain;

import com.group.libraryapp.controller.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
