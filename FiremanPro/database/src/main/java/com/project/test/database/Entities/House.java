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

    public int getId_house() {
        return id_house;
    }

    public void setId_house(int id_house) {
        this.id_house = id_house;
    }

    public String getName_owner() {
        return name_owner;
    }

    public void setName_owner(String name_owner) {
        this.name_owner = name_owner;
    }

    public String getSurname_owner() {
        return surname_owner;
    }

    public void setSurname_owner(String surname_owner) {
        this.surname_owner = surname_owner;
    }

    public int getPlace_id() {
        return place_id;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber_of_tenants() {
        return number_of_tenants;
    }

    public void setNumber_of_tenants(int number_of_tenants) {
        this.number_of_tenants = number_of_tenants;
    }

    public int getNumber_of_floors() {
        return number_of_floors;
    }

    public void setNumber_of_floors(int number_of_floors) {
        this.number_of_floors = number_of_floors;
    }

    public String getList_of_floors() {
        return list_of_floors;
    }

    public void setList_of_floors(String list_of_floors) {
        this.list_of_floors = list_of_floors;
    }

    public int getNumber_of_children() {
        return number_of_children;
    }

    public void setNumber_of_children(int number_of_children) {
        this.number_of_children = number_of_children;
    }

    public String getYear_children() {
        return year_children;
    }

    public void setYear_children(String year_children) {
        this.year_children = year_children;
    }

    public int getNumber_of_adults() {
        return number_of_adults;
    }

    public void setNumber_of_adults(int number_of_adults) {
        this.number_of_adults = number_of_adults;
    }

    public String getYears_adults() {
        return years_adults;
    }

    public void setYears_adults(String years_adults) {
        this.years_adults = years_adults;
    }

    public int getNumber_of_powerless_and_elders() {
        return number_of_powerless_and_elders;
    }

    public void setNumber_of_powerless_and_elders(int number_of_powerless_and_elders) {
        this.number_of_powerless_and_elders = number_of_powerless_and_elders;
    }

    public String getYears_powerless_elders() {
        return years_powerless_elders;
    }

    public void setYears_powerless_elders(String years_powerless_elders) {
        this.years_powerless_elders = years_powerless_elders;
    }

    public boolean isDisability_person() {
        return disability_person;
    }

    public void setDisability_person(boolean disability_person) {
        this.disability_person = disability_person;
    }

    public String getPower_supply() {
        return power_supply;
    }

    public void setPower_supply(String power_supply) {
        this.power_supply = power_supply;
    }

    public boolean isGas_connection() {
        return gas_connection;
    }

    public void setGas_connection(boolean gas_connection) {
        this.gas_connection = gas_connection;
    }

    public String getType_of_heating() {
        return type_of_heating;
    }

    public void setType_of_heating(String type_of_heating) {
        this.type_of_heating = type_of_heating;
    }

    public boolean isGas_bottle() {
        return gas_bottle;
    }

    public void setGas_bottle(boolean gas_bottle) {
        this.gas_bottle = gas_bottle;
    }

    public int getNumber_of_gas_bottle() {
        return number_of_gas_bottle;
    }

    public void setNumber_of_gas_bottle(int number_of_gas_bottle) {
        this.number_of_gas_bottle = number_of_gas_bottle;
    }

    public String getType_of_roof() {
        return type_of_roof;
    }

    public void setType_of_roof(String type_of_roof) {
        this.type_of_roof = type_of_roof;
    }

    public int getHydrant_distance() {
        return hydrant_distance;
    }

    public void setHydrant_distance(int hydrant_distance) {
        this.hydrant_distance = hydrant_distance;
    }

    public boolean isHigh_risk_object() {
        return high_risk_object;
    }

    public void setHigh_risk_object(boolean high_risk_object) {
        this.high_risk_object = high_risk_object;
    }

    public String getHRO_type_of_roof() {
        return HRO_type_of_roof;
    }

    public void setHRO_type_of_roof(String HRO_type_of_roof) {
        this.HRO_type_of_roof = HRO_type_of_roof;
    }

    public boolean isHRO_power_supply() {
        return HRO_power_supply;
    }

    public void setHRO_power_supply(boolean HRO_power_supply) {
        this.HRO_power_supply = HRO_power_supply;
    }

    public String getHRO_content() {
        return HRO_content;
    }

    public void setHRO_content(String HRO_content) {
        this.HRO_content = HRO_content;
    }

    public boolean isHRO_animals() {
        return HRO_animals;
    }

    public void setHRO_animals(boolean HRO_animals) {
        this.HRO_animals = HRO_animals;
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

    public String getHouse_image() {
        return house_image;
    }

    public void setHouse_image(String house_image) {
        this.house_image = house_image;
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

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }
}
