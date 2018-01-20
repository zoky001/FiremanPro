package com.project.test.database.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;


import com.project.test.database.Entities.House_Table;
import com.project.test.database.Entities.Address_Table;

import com.project.test.database.imageSaver.ImageSaver;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Kontrolira dodavanje nove kuće u bazu, te sve aktivnosti vezane za objekt {@link House}
 * <p>
 * <p>
 * Created by Zoran on 24.10.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */

public class HouseController {
    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
    House_photosController house_photosController = new House_photosController();

    public HouseController() {
    }

    /**
     * Dodavanje nove kuće u bazu podataka.
     *
     * @param name_owner
     * @param surname_owner
     * @param number_of_tenants
     * @param number_of_floors
     * @param list_of_floors
     * @param number_of_children
     * @param year_children
     * @param number_of_adults
     * @param years_adults
     * @param number_of_powerless_and_elders
     * @param years_powerless_elders
     * @param disability_person
     * @param power_supply
     * @param gas_connection
     * @param type_of_heating
     * @param number_of_gas_bottle
     * @param type_of_roof
     * @param hydrant_distance
     * @param high_risk_object
     * @param HRO_type_of_roof
     * @param HRO_power_supply
     * @param HRO_content
     * @param HRO_animals
     * @param telNumber
     * @param mobNumber
     * @param address
     * @return novokreirani objekt/zapis kuće
     */
    public House AddNewHouse(String name_owner, String surname_owner, int number_of_tenants, int number_of_floors, String list_of_floors, int number_of_children, String year_children, int number_of_adults, String years_adults, int number_of_powerless_and_elders, String years_powerless_elders, boolean disability_person, String power_supply, boolean gas_connection, String type_of_heating, int number_of_gas_bottle, String type_of_roof, int hydrant_distance, boolean high_risk_object, String HRO_type_of_roof, boolean HRO_power_supply, String HRO_content, boolean HRO_animals, String telNumber, String mobNumber, Address address) {

        House house = new House(name_owner,
                surname_owner,
                number_of_tenants,
                number_of_floors,
                list_of_floors,
                number_of_children,
                year_children,
                number_of_adults,
                years_adults,
                number_of_powerless_and_elders,
                years_powerless_elders,
                disability_person,
                power_supply,
                gas_connection,
                type_of_heating,
                number_of_gas_bottle,
                type_of_roof,
                hydrant_distance,
                high_risk_object,
                HRO_type_of_roof,
                HRO_power_supply,
                HRO_content,
                HRO_animals,
                telNumber,
                mobNumber,
                CurrentDate,
                CurrentDate,
                address);
        house.save();

        return house;
    }

    /**
     * Dodavanje profilne slike kući.
     *
     * @param pic   - naziv slike
     * @param url   - link za preuzimanje slike
     * @param house - kuća kojoj se dodaje slika
     */
    public void AddProfilPicToHouse(String pic, String url, House house) {
        house_photosController.addNewProfilPhotoToHouse(pic, pic, url, house);
    }

    /**
     * Dodavanje slike tlocrta kući.
     *
     * @param pic   - naziv slike
     * @param url   - link za preuzimanje slike
     * @param house - kuća kojoj se dodaje slika
     */
    public void AddGroundPlanPicToHouse(String pic, String url, House house) {
        house_photosController.addNewGrouondPlanPhotoToHouse(pic, pic, url, house);
    }


    /**
     * Dohvaćanje objkta kuće za prosljeđeni ID.
     *
     * @param idHouse - id kuće
     * @return objekt kuće ili NULL ako ne postoji
     */
    public static House getHouse(int idHouse) {

        try {
            List<House> house = SQLite.select().from(House.class).where(House_Table.id_house.is(idHouse)).queryList();
            return house.get(0);

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            return null;
        }

    }

    /**
     * TEST method
     *
     * @return
     */
    public static House getFirstHouse() {
        try {
            List<House> house = SQLite.select().from(House.class).queryList();
            return house.get(0);

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            return null;
        }

    }

    /**
     * @return svi zapisi iz tablice House
     */
    public static List<House> getAllHouseRecords() {

        List<House> house = SQLite.select().from(House.class).queryList();

        return house;
    }


    /**
     * Traženje kuće prema imenu  i prezimenu vlasnika
     *
     * @param text ime i prezime vlasnika kuće
     * @return lista kuća čiji se vlasnici podudaraju sa traženim unosom
     */
    public static List<House> serachByNameAndSurnameQuery(String text) {

        //Fetching all houses from database and empty list (type house)
        List<House> allHouses = SQLite.select().from(House.class).queryList();
        List<House> house = new ArrayList<>(allHouses);
        house.removeAll(allHouses);

        //Checking if string doesn't contain space (single word search)
        if (!text.contains(" ")) {

            for (int i = 0; i < allHouses.size(); i++) {

                String stringAddressWithName = allHouses.get(i).getName_owner().toLowerCase() + " " + allHouses.get(i).getSurname_owner().toLowerCase() + " " + allHouses.get(i).getAddress().getStreetNameIfExist().toLowerCase() +
                        " " + allHouses.get(i).getAddress().getStreetNumber().toLowerCase() + " " + allHouses.get(i).getAddress().getPlaceNameIfExist().toLowerCase() + " " + Integer.toString(allHouses.get(i).getAddress().getPost().getPostal_code()).toLowerCase() + " " + allHouses.get(i).getAddress().getPost().getName().toLowerCase();

                //Checking if specific house is already inside houses list and if not, then adding it to list
                if (stringAddressWithName.contains(text.toLowerCase()) && !house.contains(allHouses.get(i))) {
                    house.add(allHouses.get(i));

                }
            }
        }
        //Checking if string contains space for split (for searching by name and surname and/or address without needing to type commas)
        if (text.contains(" ")) {

            for (int i = 0; i < allHouses.size(); i++) {

                String stringAddressWithName = allHouses.get(i).getName_owner().toLowerCase() + " " + allHouses.get(i).getSurname_owner().toLowerCase() + " " + allHouses.get(i).getAddress().getStreetNameIfExist().toLowerCase() +
                        " " + allHouses.get(i).getAddress().getStreetNumber().toLowerCase() + " " + allHouses.get(i).getAddress().getPlaceNameIfExist().toLowerCase() + " " + Integer.toString(allHouses.get(i).getAddress().getPost().getPostal_code()).toLowerCase() + " " + allHouses.get(i).getAddress().getPost().getName().toLowerCase();
                List<String> splittedText = Arrays.asList(text.split(" "));

                boolean contains = true;
                for (String string : splittedText
                        ) {
                    if (!stringAddressWithName.contains((string.toLowerCase()))) {
                        contains = false;
                        break;
                    }
                }
                //Checking if specific house is already inside houses list and if not, then adding it to list
                if (contains && !house.contains(allHouses.get(i))) {
                    house.add(allHouses.get(i));
                }
            }
        }
        //If text contains commas
        if (text.contains(",")) {

            List<String> splitedStrings = Arrays.asList(text.split(", "));
            if (splitedStrings.size() == 3) {

                for (int i = 0; i < allHouses.size(); i++) {

                    if (splitedStrings.get(0).toLowerCase().contains(allHouses.get(i).getAddress().getStreetNameIfExist().toLowerCase() + " " + allHouses.get(i).getAddress().getStreetNumber().toLowerCase())
                            && splitedStrings.get(1).toLowerCase().contains(allHouses.get(i).getAddress().getPlaceNameIfExist().toLowerCase())
                            && splitedStrings.get(2).toLowerCase().contains((Integer.toString(allHouses.get(i).getAddress().getPost().getPostal_code()) + " " + allHouses.get(i).getAddress().getPost().getName()).toLowerCase())) {

                        //Checking if specific house is already inside houses list and if not, then adding it to list
                        if (!house.contains(allHouses.get(i))) {
                            house.add(allHouses.get(i));
                        }
                    }
                }
            }

            if (splitedStrings.size() == 2) {

                for (int i = 0; i < allHouses.size(); i++) {
                    if (allHouses.get(i).getAddress().getPlaceNameIfExist() == "" && splitedStrings.get(0).toLowerCase().contains(allHouses.get(i).getAddress().getStreetNameIfExist().toLowerCase() + " " + allHouses.get(i).getAddress().getStreetNumber().toLowerCase())
                            && splitedStrings.get(1).toLowerCase().contains((Integer.toString(allHouses.get(i).getAddress().getPost().getPostal_code()) + " " + allHouses.get(i).getAddress().getPost().getName()).toLowerCase())
                            ) {
                        //Checking if specific house is already inside houses list and if not, then adding it to list
                        if (!house.contains(allHouses.get(i))) {
                            house.add(allHouses.get(i));
                        }
                    }
                    if (allHouses.get(i).getAddress().getStreetNameIfExist() == "" && splitedStrings.get(0).toLowerCase().contains(allHouses.get(i).getAddress().getPlaceNameIfExist().toLowerCase() + " " + allHouses.get(i).getAddress().getStreetNumber().toLowerCase())
                            && splitedStrings.get(1).toLowerCase().contains((Integer.toString(allHouses.get(i).getAddress().getPost().getPostal_code()) + " " + allHouses.get(i).getAddress().getPost().getName()).toLowerCase())) {
                        //Checking if specific house is already inside houses list and if not, then adding it to list
                        if (!house.contains(allHouses.get(i))) {
                            house.add(allHouses.get(i));
                        }
                    }
                }

            }

        }
        return house;
    }

    /**
     * @return svi zapisi iz tablice House
     */
    public List<House> GetAllRecordsFromTable() {

        return SQLite.select().from(House.class).queryList();
    }

    /**
     * brisanje svih zapisa u tablici House
     *
     * @see House
     */
    public void DeleteAllRecordsInTable() {

        final List<House> gndPlan = GetAllRecordsFromTable();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
