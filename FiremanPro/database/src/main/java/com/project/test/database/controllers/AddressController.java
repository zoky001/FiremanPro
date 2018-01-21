package com.project.test.database.controllers;

import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.Photos;

import com.project.test.database.Entities.Post;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Date;
import java.util.List;

/**
 * Konrolira dodavanje novih zapisa adresa u tablicu "Adderess"
 * <p>
 * <p>
 * Created by Zoran on 24.10.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 * @see Address
 */

public class AddressController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    public AddressController() {
    }


    /**
     * Kriranje novog zapisa u tablici
     * @param streetName
     * @param place
     * @param streetNumber
     * @param post Objekt pošte kojemu pripada adresa
     * @param longitude
     * @param latitude
     *
     * @return novokreirani objek/zapis adrese
     */
    public Address addNewAddress(String streetName, String place, String streetNumber, Post post, double longitude, double latitude) {

        Address address = new Address(streetName, place, streetNumber, post, longitude, latitude, CurrentDate, CurrentDate);
        address.save();

        return address;
    }

    /**
     * popis svih zapisa iz tablice Address
     * @return lista svih zapisa iz tablice Address

     */
    public static List<Address> GetAllRecordsFromTable() {

        return SQLite.select().from(Address.class).queryList();


    }

    /**
     * brisanje svih zapisa u tablici Address
     *
     * @see Address
     */
    public void DeleteAllRecordsInTable() {

        final List<Address> gndPlan = GetAllRecordsFromTable();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
