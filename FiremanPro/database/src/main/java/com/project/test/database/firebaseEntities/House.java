package com.project.test.database.firebaseEntities;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.House_photos_Table;
import com.project.test.database.Entities.Photos;
import com.project.test.database.MainDatabase;
import com.project.test.database.imageSaver.ImageSaver;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zoran on 23.10.2017..
 */

@IgnoreExtraProperties
public class House {
    public String name_owner;
    public String surname_owner;
    public int number_of_tenants;
    public int number_of_floors;
    public String list_of_floors;
    public int number_of_children;
    public String year_children;
    public int number_of_adults;
    public String years_adults;
    public int number_of_powerless_and_elders;
    public String years_powerless_elders;
    public boolean disability_person;
    public String power_supply; //type of connection
    public boolean gas_connection;
    public String type_of_heating;
    public int number_of_gas_bottle;
    public String type_of_roof;
    public int hydrant_distance;
    public boolean high_risk_object;
    public String HRO_type_of_roof;
    public boolean HRO_power_supply;
    public String HRO_content;
    public boolean HRO_animals;
    public String telNumber;
    public String mobNumber;
    private com.project.test.database.firebaseEntities.Address address;
    public String profilPicUrl;

    public House() {
    }

    public House(String name_owner, String surname_owner, int number_of_tenants, int number_of_floors, String list_of_floors, int number_of_children, String year_children, int number_of_adults, String years_adults, int number_of_powerless_and_elders, String years_powerless_elders, boolean disability_person, String power_supply, boolean gas_connection, String type_of_heating, int number_of_gas_bottle, String type_of_roof, int hydrant_distance, boolean high_risk_object, String HRO_type_of_roof, boolean HRO_power_supply, String HRO_content, boolean HRO_animals, String telNumber, String mobNumber, com.project.test.database.firebaseEntities.Address address, String profilPicUrl) {
        this.name_owner = name_owner;
        this.surname_owner = surname_owner;
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
        this.number_of_gas_bottle = number_of_gas_bottle;
        this.type_of_roof = type_of_roof;
        this.hydrant_distance = hydrant_distance;
        this.high_risk_object = high_risk_object;
        this.HRO_type_of_roof = HRO_type_of_roof;
        this.HRO_power_supply = HRO_power_supply;
        this.HRO_content = HRO_content;
        this.HRO_animals = HRO_animals;
        this.telNumber = telNumber;
        this.mobNumber = mobNumber;
        this.address = address;
        this.profilPicUrl = profilPicUrl;
    }

    @Exclude
    public Map<String, Object> toMap() {


        HashMap<String, Object> result = new HashMap<>();
        result.put("name_owner", name_owner);
        result.put("surname_owner", surname_owner);
        result.put("number_of_floors", number_of_floors);
        result.put("list_of_floors", list_of_floors);
        result.put("number_of_children", number_of_children);

        result.put("year_children", year_children);
        result.put("number_of_adults", number_of_adults);
        result.put("years_adults", years_adults);
        result.put("number_of_powerless_and_elders", number_of_powerless_and_elders);
        result.put("years_powerless_elders", years_powerless_elders);

        result.put("disability_person", disability_person);
        result.put("power_supply", power_supply);
        result.put("gas_connection", gas_connection);
        result.put("type_of_heating", type_of_heating);
        result.put("number_of_gas_bottle", number_of_gas_bottle);

        result.put("type_of_roof", type_of_roof);
        result.put("hydrant_distance", hydrant_distance);
        result.put("high_risk_object", high_risk_object);
        result.put("HRO_type_of_roof", HRO_type_of_roof);
        result.put("HRO_power_supply", HRO_power_supply);

        result.put("HRO_content", HRO_content);
        result.put("HRO_animals", HRO_animals);
        result.put("telNumber", telNumber);
        result.put("mobNumber", mobNumber);

        result.put("profilPicUrl", profilPicUrl);

        result.put("address", address.toMap());
        return result;

    }
}