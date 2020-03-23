package com.practice.weatherapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Location {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull(message = "Country cannot be empty")
    private String country;
    @NotNull(message = "City cannot be empty")
    private String city;

    public Location(Long id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    public Location() {
        //JPA
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
