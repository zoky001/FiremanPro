package com.project.test.database.Entities;

import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

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
    String place;

    @Column
    String postalCode;

    @Column
    long longitude;
    @Column
    long latitude;


    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;


}
