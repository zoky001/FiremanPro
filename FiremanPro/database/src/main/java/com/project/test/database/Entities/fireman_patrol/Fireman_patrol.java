package com.project.test.database.Entities.fireman_patrol;

import com.project.test.database.Entities.Post;
import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Fireman_patrol extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int ID;

    @Column
    String name;




    @Column
    Date updated_at;
    @Column
    Date created_at;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
           Type_of_unit type_of_unit;


    public Fireman_patrol() {
    }

    public Fireman_patrol(String name, Date updated_at, Date created_at, Type_of_unit type_of_unit) {
        this.name = name;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.type_of_unit = type_of_unit;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Type_of_unit getType_of_unit() {
        return type_of_unit;
    }

    public void setType_of_unit(Type_of_unit type_of_unit) {
        this.type_of_unit = type_of_unit;
    }
}
