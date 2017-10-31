package com.project.test.database.Entities;

import android.content.Context;

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
public class Ground_plan extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int id_ground_plan;

    @Column
    int houseID; //foreign key to places
    @Column
    String imgName;
    @Column
    String imgAdress;


    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    @Column
    @ForeignKey(tableClass = House.class)
    House house;

    public int getId_ground_plan() {
        return id_ground_plan;
    }

    public void setId_ground_plan(int id_ground_plan) {
        this.id_ground_plan = id_ground_plan;
    }

    public int getHouseID() {
        return houseID;
    }

    public Ground_plan() {

    }

    public Ground_plan(int houseID, String imgName, String imgAdress, java.util.Date updated_at, java.util.Date created_at) {
        this.houseID = houseID;
        this.imgName = imgName;
        this.imgAdress = imgAdress;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }


    public String getImgAdress() {
        return imgAdress;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getImgName() {
        return imgName;
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

    public int getImageResourceIDbyContext(Context contextItem) {

        int imageresource = contextItem.getResources().getIdentifier("@drawable/" + getImgAdress(), "drawable", contextItem.getPackageName());

        return imageresource;
    }
}
