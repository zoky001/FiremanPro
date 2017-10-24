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


    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    @Column
    @ForeignKey(tableClass = House.class)
    House house;



}
