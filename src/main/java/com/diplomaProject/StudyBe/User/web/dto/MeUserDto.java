package com.diplomaProject.StudyBe.User.web.dto;

import com.diplomaProject.StudyBe.Subject.Subject;

import java.util.Collection;

public class MeUserDto {

    private String username;

    private String email;

    private String description;

    private Collection<Subject> tags;

    private Collection<FavoriteUserDto> favorites;
    private double rating;

    public MeUserDto(String username, String email, Collection<Subject> tags, Collection<FavoriteUserDto> favorites, String description, double rating) {
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

    public Collection<Subject> getTags() {
        return tags;
    }

    public void setTags(Collection<Subject> tags) {
        this.tags = tags;
    }

    public Collection<FavoriteUserDto> getFavorites() {
        return favorites;
    }

    public void setFavorites(Collection<FavoriteUserDto> favorites) {
        this.favorites = favorites;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
