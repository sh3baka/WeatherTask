package com.practice.weatherapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 1, message = "UserName should be atleast 1 char long")
    private String userName;
    @Size(min = 1, message = "Name should be atleast 1 char long")
    private String name;
    @Size(min = 1, message = "Surname should be atleast 1 char long")
    private String surname;
    @OneToOne
    private Location location;

    public User(Long id, String userName, String name, String surname, Location location) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.location = location;
    }

    public User() {
        //JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
