package com.diplomaProject.StudyBe.User.web.dto;

import java.util.Collection;
import java.util.List;

public class UserDto {

    private String username;

    private String email;

    private String description;

    private Collection tags;

    private Collection favorites;
    private double rating;

    public UserDto(String username, String email, Collection tags, Collection favorites, String description, double rating) {
        this.username = username;
        this.email = email;
        this.tags = tags;
        this.favorites = favorites;
        this.description = description;
        this.rating = rating;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection getTags() {
        return tags;
    }

    public void setTags(Collection tags) {
        this.tags = tags;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Collection getFavorites() {
        return favorites;
    }

    public void setFavorites(Collection favorites) {
        this.favorites = favorites;
    }
}
