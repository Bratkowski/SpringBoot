package com.bratkowski.booklibary.domain;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String Name;


    @ManyToOne
    @JoinColumn (name = "userID")
    private User user;

    public Role() {
    }

    public Role(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public User getUser(User user) {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
