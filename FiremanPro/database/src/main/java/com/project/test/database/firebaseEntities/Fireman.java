package com.project.test.database.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Zoran on 26.1.2018..
 */

@IgnoreExtraProperties
public class Fireman {

    public String name;
    public String surname;
    public String active;

    public Fireman() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Fireman(String name, String surname, String active) {
        this.name = name;
        this.surname = surname;
        this.active = active;
    }
}