package com.project.test.database.Entities;

import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.w3c.dom.Text;

import java.sql.Date;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Hydrants extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int id_hydrant;

    @Column
    String type_of_hydrant;

    @Column
    String description;


    //address
    @ForeignKey(saveForeignKeyModel = true)
    Address address;

    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    public Hydrants() {
    }

    public Hydrants(String type_of_hydrant, String description, Address address, java.util.Date updated_at, java.util.Date created_at) {
        this.type_of_hydrant = type_of_hydrant;
        this.description = description;
        this.address = address;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }


    public String getType_of_hydrant() {
        return type_of_hydrant;
    }

    public void setType_of_hydrant(String type_of_hydrant) {
        this.type_of_hydrant = type_of_hydrant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public java.util.Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(java.util.Date updated_at) {
        this.updated_at = updated_at;
    }

    public java.util.Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(java.util.Date created_at) {
        this.created_at = created_at;
    }
}
