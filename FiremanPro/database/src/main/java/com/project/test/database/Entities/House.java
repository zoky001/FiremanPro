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
public class House extends BaseModel{

    @PrimaryKey(autoincrement = true)
    @Column
    int id_house;
    @Column String name_owner;
    @Column String surname_owner;
    @Column int place_id; //foreign key to places
    @Column String address;
    @Column int number_of_tenants;
    @Column int number_of_floors;
    @Column String list_of_floors;
    @Column int number_of_children;
    @Column String year_children;
    @Column int number_of_adults;
    @Column String years_adults;
    @Column int number_of_powerless_and_elders;
    @Column String years_powerless_elders;
    @Column boolean disability_person;
    @Column String power_supply; //type of connection
    @Column boolean gas_connection;
    @Column String type_of_heating;
    @Column boolean gas_bottle;
    @Column int number_of_gas_bottle;
    @Column String type_of_roof;
    @Column int hydrant_distance;
    @Column boolean high_risk_object;
    @Column String HRO_type_of_roof;
    @Column boolean HRO_power_supply;
    @Column String HRO_content;
    @Column boolean HRO_animals;

    @Column long longitude;
    @Column long latitude;
    @Column String house_image;

    @Column Date updated_at;
    @Column Date created_at;


    @Column
    @ForeignKey(tableClass = Places.class)
    Places place;
}
