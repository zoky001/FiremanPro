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
 * Kontrolira dodavanje novih hidranata u bazu podataka
 *
 * @see Hydrants
 * <p>
 * Created by Zoran on 24.10.2017..
 * </p>
 * @author Zoran Hrnčić
 */

public class HydrantsController {
    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    public HydrantsController() {
    }


    /**
     * Dodavanje novog hidranta u bazu podataka.
     *
     * Svi podatci o lokaciji i vrsti hidranta.
     *
     * @param type_of_hydrant
     * @param description
     * @param streetName
     * @param place
     * @param streentNumber
     * @param post
     * @param lonngitude
     * @param latitude
     */
    public void addNewHydrant(String type_of_hydrant, String description, String streetName, String place, String streentNumber, Post post, Double lonngitude, Double latitude) {


        Address address = new Address(streetName, place, streentNumber, post, lonngitude, latitude, CurrentDate, CurrentDate);
        address.save();

        Hydrants hydrants = new Hydrants(type_of_hydrant, description, address, CurrentDate, CurrentDate);
        hydrants.save();

    }


    /**
     *
     * @return svi zapisi iz tablice Hydrants
     */
    public List<Hydrants> GetAllRecordsFromTableHydrants() {

        return SQLite.select().from(Hydrants.class).queryList();
    }


    /**
     * brisanje svih zapisa u tablici Hydrants
     */
    public void DeleteAllRecordsInTableHydrants() {

        final List<Hydrants> gndPlan = GetAllRecordsFromTableHydrants();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
