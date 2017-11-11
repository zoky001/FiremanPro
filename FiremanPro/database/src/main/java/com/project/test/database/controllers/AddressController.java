package com.project.test.database.controllers;

import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.Photos;

import com.project.test.database.Entities.Post;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;
import java.util.List;

/**
 * Created by Zoran on 24.10.2017..
 */

public class AddressController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    public AddressController() {
    }

    public Address addNewAddress(String streetName, String streetNumber, Post post, long longitude, long latitude){

Address address = new Address(streetName,streetNumber,post,longitude,latitude,CurrentDate,CurrentDate);
address.save();

    return address;
}

    public List<Address> GetAllRecordsFromTable(){

        return SQLite.select().from(Address.class).queryList();


    }
    public void DeleteAllRecordsInTable(){

        final List<Address> gndPlan = GetAllRecordsFromTable();
        for(int i = 0; i < gndPlan.size(); i++){

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
