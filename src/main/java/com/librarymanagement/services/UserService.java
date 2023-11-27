package com.librarymanagement.services;

import com.librarymanagement.models.requests.UserRegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> registerUser(UserRegisterRequest registerRequest);
    ResponseEntity<Object> retrieveUser(String username);
}
