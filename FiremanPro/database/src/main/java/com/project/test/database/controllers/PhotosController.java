package com.project.test.database.controllers;

import android.content.Context;


import com.project.test.database.Entities.Photos;


import com.raizlabs.android.dbflow.sql.language.SQLite;


import java.util.List;

/**
 * Kontrolira dodavanje novih podataka o fotografijama u bazu podataka.
 * <p>
 * <p>
 * Created by Zoran on 24.10.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */

public class PhotosController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    public PhotosController() {
    }


    /**
     * Dodavanje novih podataka o fotografiji u bazu podataka.
     *
     * @param photoname    naziv fotografije npr (prvi kat kuce)
     * @param locationName naziv datoteke fotografije npr. (prvi_kat.png)
     * @param url          link za preuzimanje fotografije (WebServis)
     * @return novokrirani objekt/zapis
     */
    public Photos addNewPhotoName(String photoname, String locationName, String url) {
        Photos photo = new Photos(photoname, locationName, url, CurrentDate, CurrentDate);
        photo.save();
        return photo;
    }

    public int getPhotoResourceIDbyContext(Context contextItem, Photos photo) {

        int imageresource = contextItem.getResources().getIdentifier("@drawable/" + photo.getFileName(), "drawable", contextItem.getPackageName());

        return imageresource;
    }


    /**
     * @return svi zapisi iz tablise Photos
     */
    public List<Photos> GetAllRecordsFromTable() {

        return SQLite.select().from(Photos.class).queryList();


    }


    /**
     * brisanje svih zapisa iz tablice Photos
     *
     * @see Photos
     */
    public void DeleteAllRecordsInTable() {

        final List<Photos> gndPlan = GetAllRecordsFromTable();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
