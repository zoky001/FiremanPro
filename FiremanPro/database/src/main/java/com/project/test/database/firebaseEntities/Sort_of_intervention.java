package com.project.test.database.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zoran on 26.1.2018..
 */

@IgnoreExtraProperties
public class Sort_of_intervention {

    public String Description;
    public String Name;

   // public Integer id;


    public Sort_of_intervention() {
    }

    public Sort_of_intervention(String description, String name) {
        Description = description;
        Name = name;

    }
}