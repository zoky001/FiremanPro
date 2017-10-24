package com.project.test.database.Entities;

import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.sql.Date;
import java.util.List;

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

    @Column java.util.Date updated_at;
    @Column java.util.Date created_at;


    @Column
    @ForeignKey(tableClass = Places.class)
    Places place;

    public House() {
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public House(String name_owner, String surname_owner, int IDplace, String address, int number_of_tenants, int number_of_floors, String list_of_floors, int number_of_children, String year_children, int number_of_adults, String years_adults, int number_of_powerless_and_elders, String years_powerless_elders, boolean disability_person, String power_supply, boolean gas_connection, String type_of_heating, boolean gas_bottle, int number_of_gas_bottle, String type_of_roof, int hydrant_distance, boolean high_risk_object, String HRO_type_of_roof, boolean HRO_power_supply, String HRO_content, boolean HRO_animals, long longitude, long latitude, String house_image, java.util.Date updated_at, java.util.Date created_at) {

        this.name_owner = name_owner;
        this.surname_owner = surname_owner;
        this.place_id = IDplace;
        this.address = address;
        this.number_of_tenants = number_of_tenants;
        this.number_of_floors = number_of_floors;
        this.list_of_floors = list_of_floors;
        this.number_of_children = number_of_children;
        this.year_children = year_children;
        this.number_of_adults = number_of_adults;
        this.years_adults = years_adults;
        this.number_of_powerless_and_elders = number_of_powerless_and_elders;
        this.years_powerless_elders = years_powerless_elders;
        this.disability_person = disability_person;
        this.power_supply = power_supply;
        this.gas_connection = gas_connection;
        this.type_of_heating = type_of_heating;
        this.gas_bottle = gas_bottle;
        this.number_of_gas_bottle = number_of_gas_bottle;
        this.type_of_roof = type_of_roof;
        this.hydrant_distance = hydrant_distance;
        this.high_risk_object = high_risk_object;
        this.HRO_type_of_roof = HRO_type_of_roof;
        this.HRO_power_supply = HRO_power_supply;
        this.HRO_content = HRO_content;
        this.HRO_animals = HRO_animals;
        this.longitude = longitude;
        this.latitude = latitude;
        this.house_image = house_image;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public String getName_owner() {
        return name_owner;
    }

    public int getPlace_id() {
        return place_id;
    }

    public int getId_house() {
        return id_house;
    }

    public String getPlaceName(){


        List<Places> places2 = SQLite.select()
                .from(Places.class)
                .where(Places_Table.id_place.is(getPlace_id()))

                .queryList();
System.out.println("rezultat upita: "+ places2.size());

        return places2.get(0).getName();
    }

    public void setName_owner(String name_owner) {

        this.name_owner = name_owner;
    }

    public String getHouse_image() {
        return house_image;
    }

    public java.util.Date getCreated_at() {
        return created_at;
    }
}
