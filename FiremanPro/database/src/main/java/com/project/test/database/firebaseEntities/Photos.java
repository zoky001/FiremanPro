package com.project.test.database.firebaseEntities;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.firebase.database.IgnoreExtraProperties;
import com.project.test.database.Entities.firebase.Firestore;
import com.project.test.database.FirebaseStorageController;
import com.project.test.database.MainDatabase;
import com.project.test.database.imageSaver.ImageSaver;
import com.project.test.database.interfaces.IPhoto;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by Zoran on 23.10.2017..
 */

@IgnoreExtraProperties
public class Photos extends Firestore {


    private String StorageLocation;


    private String Name;


    private String Url;

    private String id;


    public Photos() {
    }

    public Photos(String storageLocation, String name, String url) {
        StorageLocation = storageLocation;
        Name = name;
        Url = url;
    }

    public String getStorageLocation() {
        return StorageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        StorageLocation = storageLocation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void getImageBitmapFromCloudStorage(IPhoto iPhoto) {
        FirebaseStorageController.getImageBitmapFromCloudStorage(getStorageLocation(),iPhoto);
    }



}
