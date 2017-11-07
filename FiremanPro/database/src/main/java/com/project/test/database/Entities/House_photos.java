package com.project.test.database.Entities;

import android.content.Context;
import android.graphics.Bitmap;

import com.project.test.database.MainDatabase;
import com.project.test.database.imageSaver.ImageSaver;
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
public class House_photos extends BaseModel {



    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Photos photo;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            PhotoType photoType;

    @ForeignKey(stubbedRelationship = true)
    House house;

    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;


    public House_photos(Photos photo, PhotoType photoType, House house, java.util.Date updated_at, java.util.Date created_at) {
        this.photo = photo;
        this.photoType = photoType;
        this.house = house;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public Photos getPhoto() {
        return photo;
    }

    public void setPhoto(Photos photo) {
        this.photo = photo;
    }

    public PhotoType getPhotoType() {
        return photoType;
    }

    public void setPhotoType(PhotoType photoType) {
        this.photoType = photoType;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
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
