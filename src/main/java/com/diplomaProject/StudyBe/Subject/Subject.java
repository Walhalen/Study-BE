package com.diplomaProject.StudyBe.Subject;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", unique = true )
    private String name;

    @Column(name = "color")
    private String color = "grey";

    public Subject() {
    }

    public Subject(String name, String color) {
        this.name = name;
        if(color != null && !color.equals(""))
        {
            this.color = color;
        }

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
