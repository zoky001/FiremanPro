package com.project.test.database.Entities;

import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Address extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int ID;

    @Column
    String streetName;

    @Column
    String streetNumber;


    @Column
    double longitude;
    @Column
    double latitude;

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

    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Post post;


    public Address() {
    }

    public Address(String streetName, String place, String streetNumber, Post post, double longitude, double latitude, Date updated_at, Date created_at) {
        this.streetName = streetName + ";" + place;
        this.streetNumber = streetNumber;
        this.post = post;
        this.longitude = longitude;
        this.latitude = latitude;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public Address(Integer ID, String streetName, String streetNumber, double longitude, double latitude, Date updated_at, Date created_at, Post post) {
        this.ID = ID;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.longitude = longitude;
        this.latitude = latitude;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStreetNameIfExist() {
        //zapis u polju "streetName" je oblika "ime_ulice;ime_sela"
        String s1 = this.streetName;
        String[] street = s1.split(";");

        if (street.length == 2 && street[0].length() > 0 && !street[0].contains("null"))
            return street[0];
        else
            return "";
    }

    public String getPlaceNameIfExist() {

        //zapis u polju "streetName" je oblika "ime_ulice;ime_sela"
        String s1 = this.streetName;
        String[] street = s1.split(";");

        if (street.length == 2 && street[1].length() > 0 && !street[1].contains("null"))
            return street[1];
        else
            return "";
    }

    public void setStreetName(String streetName) {


        this.streetName = streetName + ";" + this.getPlaceNameIfExist();
    }

    public void setPlaceName(String placeName) {

        this.streetName = this.getStreetNameIfExist() + ";" + placeName;
    }


    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }


    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
