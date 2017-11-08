package com.project.test.database.Entities;

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
public class Place extends BaseModel {


    @PrimaryKey(autoincrement = true)
    @Column
    int id_place;

    @Column
    String name;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Post post;



    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    public Place(String name, Post post, Date updated_at, Date created_at) {
        this.name = name;
        this.post = post;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public Place() {
    }

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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
