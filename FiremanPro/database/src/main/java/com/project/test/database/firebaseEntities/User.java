package com.project.test.database.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Zoran on 26.1.2018..
 */

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
   // public Integer id;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(Integer id, String username, String email) {
        this.username = username;
        this.email = email;
       // this.id = id;
    }

}