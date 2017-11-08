package com.project.test.database.controllers;

import com.project.test.database.Entities.Place;
import com.project.test.database.Entities.Post;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by Zoran on 24.10.2017..
 */

public class PlaceController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    public PlaceController() {
    }

    public Place addNewPlace(String name, Post post){
        Place place = new Place(name,post,CurrentDate,CurrentDate);
place.save();
    return place;
}

    public List<Place> GetAllRecordsFromTable(){

        return SQLite.select().from(Place.class).queryList();


    }
    public void DeleteAllRecordsInTable(){

        final List<Place> gndPlan = GetAllRecordsFromTable();
        for(int i = 0; i < gndPlan.size(); i++){

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
