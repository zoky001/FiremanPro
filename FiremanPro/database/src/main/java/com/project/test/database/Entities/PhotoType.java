package com.project.test.database.Entities;

import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class PhotoType extends BaseModel {

    @PrimaryKey(autoincrement = false)
    @Column
    int ID;

    @Column
    String Type;

    @Column
    String Description;

    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    public PhotoType() {
    }

    public PhotoType(int ID, String type, String description, Date updated_at, Date created_at) {
        this.ID = ID;
        Type = type;
        Description = description;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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
