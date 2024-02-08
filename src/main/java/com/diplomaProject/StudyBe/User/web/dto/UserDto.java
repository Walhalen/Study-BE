package com.diplomaProject.StudyBe.User.web.dto;

import com.diplomaProject.StudyBe.Subject.Subject;

import java.util.Collection;
import java.util.List;

public class UserDto {

    private String username;

    private String email;
    private String description;

    private Collection<Subject> tags;

    private Collection<FavoriteUserDto> favorites;

    private double rating;

    public UserDto(String username, String email, Collection<Subject> tags,  String description, Collection<FavoriteUserDto> favorites, double rating) {
        this.username = username;
        this.email = email;
        this.tags = tags;
        this.description = description;
        this.rating = rating;
        this.favorites = favorites;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Collection<Subject> getTags() {
        return tags;
    }

    public void setTags(Collection<Subject> tags) {
        this.tags = tags;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Collection<FavoriteUserDto> getFavorites() {
        return favorites;
    }

    public void setFavorites(Collection<FavoriteUserDto> favorites) {
        this.favorites = favorites;
    }
}
