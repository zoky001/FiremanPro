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
public class House_photo_gallery extends BaseModel{

    @PrimaryKey(autoincrement = true)
    @Column
    int id_house_photo_gallery;

    @Column int houseID; //foreign key to places
    @Column String image_name;
    @Column String image_location;


    @Column Date updated_at;
    @Column Date created_at;

    @Column
    @ForeignKey(tableClass = House.class)
    House house;

    public int getId_house_photo_gallery() {
        return id_house_photo_gallery;
    }

    public void setId_house_photo_gallery(int id_house_photo_gallery) {
        this.id_house_photo_gallery = id_house_photo_gallery;
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getImage_location() {
        return image_location;
    }

    public void setImage_location(String image_location) {
        this.image_location = image_location;
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

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
