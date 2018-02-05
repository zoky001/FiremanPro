package com.project.test.database.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.test.database.Entities.Post;
import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zoran on 23.10.2017..
 */

@IgnoreExtraProperties
public class Address {


    public String place;
    public String streetName;
    public String streetNumber;
    public double longitude;
    public double latitude;
    private int postalCode;

    public Address() {
    }


    public Address(String place, String streetName, String streetNumber, double longitude, double latitude, int postalCode) {
        this.place = place;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.postalCode = postalCode;
    }

    @Exclude
    public Map<String, Object> toMap() {

         FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference post = db.collection("posts").document(String.valueOf(postalCode));

        HashMap<String, Object> result = new HashMap<>();
        result.put("streetName", streetName);
        result.put("streetNumber" , streetNumber);
        result.put("place" , place);
        result.put("longitude", longitude);
        result.put("latitude", latitude);
        result.put("postRef", post);
        return result;

    }
}
