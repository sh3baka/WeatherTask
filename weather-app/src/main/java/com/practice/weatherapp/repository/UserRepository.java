package com.practice.weatherapp.repository;

import com.practice.weatherapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByUserName(String username);
    void deleteByName(String name);
}
