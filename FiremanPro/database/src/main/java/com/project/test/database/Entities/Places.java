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
public class Places extends BaseModel{

    @PrimaryKey(autoincrement = true)
    @Column
    int id_place;

  @Column String name;

    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;


    public Places(String name, java.util.Date updated_at, java.util.Date created_at) {
        this.name = name;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public Places() {
    }

    public int getId_place() {
        return id_place;
    }

    public String getName() {
        return name;
    }

    public java.util.Date getUpdated_at() {
        return updated_at;
    }

    public java.util.Date getCreated_at() {
        return created_at;
    }
}
