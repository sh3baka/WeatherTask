package com.practice.weatherapp.controller;

import com.practice.weatherapp.exception.UserNotFoundException;
import com.practice.weatherapp.model.User;
import com.practice.weatherapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{name}")
    public User getByUsername(@PathVariable String name) {
        User user = userRepository.findByName(name);
        if (user == null)
            throw new UserNotFoundException("User " + name + " not found");

        return user;

    }

    @PostMapping("/user")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
        userRepository.save(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(user.getName()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/user/{name}")
    @Transactional
    public void deleteUsername(@PathVariable String name) {
        userRepository.deleteByName(name);
    }
}
