package com.project.test.database.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Zoran on 26.1.2018..
 */

@IgnoreExtraProperties
public class Truck {


    public String Name;
    public String Type_of_trucks;

    public Truck() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Truck(String name, String type_of_trucks) {
        this.Name = name;
        this.Type_of_trucks = type_of_trucks;
    }
}