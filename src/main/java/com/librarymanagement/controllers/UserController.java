package com.librarymanagement.controllers;

import com.librarymanagement.models.requests.UserRegisterRequest;
import com.librarymanagement.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/users")
public class UserController {
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegisterRequest userRegisterRequest){
        return userService.registerUser(userRegisterRequest);
    }

    @GetMapping("/")
    public ResponseEntity<Object> retrieveUser(@RequestParam String username){
        return userService.retrieveUser(username);
    }
}
