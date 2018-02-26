package com.project.test.database.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.Exclude;

/**
 * Created by Zoran on 26.1.2018..
 */

@IgnoreExtraProperties
public class Fireman {

    @Exclude
    private String id;

    private String name;
    private String surname;
    private String active;

    public Fireman() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Fireman(String name, String surname, String active) {
        this.name = name;
        this.surname = surname;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}