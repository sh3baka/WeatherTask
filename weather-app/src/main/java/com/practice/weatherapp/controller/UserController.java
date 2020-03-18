package com.practice.weatherapp.controller;

import com.practice.weatherapp.User;
import com.practice.weatherapp.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
