package com.project.test.database.controllers;

import android.content.Context;

import com.project.test.database.Entities.House;

import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Photos;
import com.project.test.database.Entities.Places;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zoran on 24.10.2017..
 */

public class PhotosController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    public PhotosController() {
    }

    public Photos addNewPhotoName(String photoname, String locationName){
        Photos photo = new Photos(photoname,locationName,CurrentDate,CurrentDate);
        photo.save();



    return photo;
}

    public int getPhotoResourceIDbyContext (Context contextItem, Photos photo) {

        int imageresource = contextItem.getResources().getIdentifier("@drawable/"+photo.getFileName(), "drawable", contextItem.getPackageName());

        return imageresource;
    }

    public List<Photos> GetAllRecordsFromTable(){

        return SQLite.select().from(Photos.class).queryList();


    }
    public void DeleteAllRecordsInTable(){

        final List<Photos> gndPlan = GetAllRecordsFromTable();
        for(int i = 0; i < gndPlan.size(); i++){

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
