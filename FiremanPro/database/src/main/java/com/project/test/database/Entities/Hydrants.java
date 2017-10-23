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

    @Column Date updated_at;
    @Column Date created_at;

    @Column
    @ForeignKey(tableClass = Places.class)
    Places place;



}
