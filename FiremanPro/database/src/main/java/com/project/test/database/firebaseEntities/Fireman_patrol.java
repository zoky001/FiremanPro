package com.project.test.database.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.author;
import static android.R.attr.name;
import static android.R.attr.type;

/**
 * Created by Zoran on 26.1.2018..
 */

@IgnoreExtraProperties
public class Fireman_patrol {

    public String Type;
    public String Name;
    public Cost cost;
   // public Integer id;

    public Fireman_patrol() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Fireman_patrol(String type, String name, Cost cost1) {
        Type = type;
        Name = name;
        this.cost = cost1;

    }
    @Exclude
    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("Type", Type);
        result.put("Name" , Name);
        result.put("Cost", cost.toMap());

        return result;

    }

}