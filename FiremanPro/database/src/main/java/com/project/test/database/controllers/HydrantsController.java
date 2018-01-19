package com.project.test.database.controllers;

import android.content.Context;
import android.graphics.Bitmap;

import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;

import com.project.test.database.Entities.Hydrants;
import com.project.test.database.Entities.Post;
import com.project.test.database.imageSaver.ImageSaver;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zoran on 24.10.2017..
 */

public class HydrantsController {
    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    public HydrantsController() {
    }

    public void addNewHydrant(String type_of_hydrant, String description, String streetName, String place, String streentNumber, Post post, Double lonngitude, Double latitude){


       Address address = new Address(streetName,place,streentNumber,post,lonngitude,latitude,CurrentDate,CurrentDate);
        address.save();

        Hydrants hydrants = new Hydrants(type_of_hydrant,description,address,CurrentDate,CurrentDate);
        hydrants.save();

    }


    public static List<Hydrants> GetAllRecordsFromTableHydrants() {

        return SQLite.select().from(Hydrants.class).queryList();
    }

    public void DeleteAllRecordsInTableHydrants() {

        final List<Hydrants> gndPlan = GetAllRecordsFromTableHydrants();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
