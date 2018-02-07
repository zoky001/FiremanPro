package com.project.test.database.firebaseEntities;

import android.graphics.Bitmap;

import com.google.firebase.database.IgnoreExtraProperties;
import com.project.test.database.Entities.firebase.Firestore;
import com.project.test.database.FirebaseStorageController;

import io.reactivex.Single;

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




    public Single<Bitmap> getImageBitmapFromCloudStorage() {
        return FirebaseStorageController.getImageBitmapFromCloudStorage(getStorageLocation());
    }



}
