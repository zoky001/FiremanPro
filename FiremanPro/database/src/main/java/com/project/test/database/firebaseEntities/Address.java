package com.project.test.database.firebaseEntities;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zoran on 23.10.2017..
 */

@IgnoreExtraProperties
public class Address {


    private String place;
    private String streetName;
    private String streetNumber;
    private double longitude;
    private double latitude;
    private int postalCode;
    private com.project.test.database.firebaseEntities.Post post;
    private DocumentReference postRef;
    private String ID;

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStreetName() {
        return streetName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public DocumentReference getPostRef() {
        return postRef;
    }

    public void setPostRef(DocumentReference postRef) {
        this.postRef = postRef;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }


    public String getStreetNameIfExist() {
      return streetName;
    }

    public String getPlaceNameIfExist() {
return place;
    }

    public void setStreetName(String streetName) {


        this.streetName = streetName;
    }

    public void setPlaceName(String placeName) {

        this.place = placeName;
    }


    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }


}
