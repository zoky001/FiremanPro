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

    @Column Date updated_at;
    @Column Date created_at;

    public int getId_place() {
        return id_place;
    }

    public void setId_place(int id_place) {
        this.id_place = id_place;
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
}
