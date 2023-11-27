package com.librarymanagement.repositories;

import com.librarymanagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}
