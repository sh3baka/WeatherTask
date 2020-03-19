package com.practice.weatherapp.controller;

import com.practice.weatherapp.model.User;
import com.practice.weatherapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{name}")
    public User getByUsername(@PathVariable String name) {
        return userRepository.findByName(name);
    }

    @PostMapping("/user")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
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