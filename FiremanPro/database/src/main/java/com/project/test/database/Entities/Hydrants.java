package com.project.test.database.Entities;

import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.sql.Date;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Hydrants extends BaseModel{

    @PrimaryKey(autoincrement = true)
    @Column
    int id_hydrant;

    @Column int placeID; //foreign key to places
    @Column String address;
    @Column String type_of_hydrant;


    @Column long longitude;
    @Column long latitude;

    @Column String hydrant_image;

    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    @Column
    @ForeignKey(tableClass = Places.class)
    Places place;

    public int getId_hydrant() {
        return id_hydrant;
    }

    public void setId_hydrant(int id_hydrant) {
        this.id_hydrant = id_hydrant;
    }

    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType_of_hydrant() {
        return type_of_hydrant;
    }

    public void setType_of_hydrant(String type_of_hydrant) {
        this.type_of_hydrant = type_of_hydrant;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public String getHydrant_image() {
        return hydrant_image;
    }

    public void setHydrant_image(String hydrant_image) {
        this.hydrant_image = hydrant_image;
    }



    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }



    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }
}
