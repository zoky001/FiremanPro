package com.project.test.database.helper;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Places;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Zoran on 23.10.2017..
 */

public class MockData {

    public static void  deleteAll(){
        final List<House> house = SQLite.select().from(House.class).queryList();
        final List<Places> places = SQLite.select().from(Places.class).queryList();

        for(int i = 0; i < house.size(); i++){

            house.get(i).delete();
            //delete all item in table House
        }
        for (int i = 0; i < places.size();i++){

            places.get(i).delete();
//delete all item in table Places
        }
    }
    public static void writeAll(){

        deleteAll();
        //delete all


        java.util.Date date = new java.util.Date(System.currentTimeMillis());

        Places place = new Places("Radovec Polje",date ,date);
        place.save();

        House house = new House(
                "Ana",
                "Cegles",
                place.getId_place(),
                "Radovečka 32 42208 Cestica",
                2,
                2,
                "prvi kat, drugi kat",
                0,
                "",
                5,
                "1995,1975,1978,1985",
                0,
                "",
                FALSE,
                "Nadzemni priključak",
                FALSE,
                "DRVA",
                TRUE,
                1,
                "Limeni pokrov",
                30,
                TRUE,
                "Crijep",
                TRUE,
                "Sijeno",
                FALSE,
                (long)49.1512,
                (long)16.1654,
                "house1",
                date,
                date);
        house.save();

        House house1 = new House(
                "Marinko",
                "Kokot",
                place.getId_place(),
                "Radovečka 32 42208 Cestica",
                2,
                2,
                "prvi kat, drugi kat",
                0,
                "",
                5,
                "1995,1975,1978,1985",
                0,
                "",
                FALSE,
                "Nadzemni priključak",
                FALSE,
                "DRVA",
                TRUE,
                1,
                "Limeni pokrov",
                30,
                TRUE,
                "Crijep",
                TRUE,
                "Sijeno",
                FALSE,
                (long)49.1512,
                (long)16.1654,
                "house1",
                date,
                date);
        house1.save();


        House house2 = new House(
                "Jadranko",
                "Kokot",
                place.getId_place(),
                "Radovečka 32 42208 Cestica",
                2,
                2,
                "prvi kat, drugi kat",
                0,
                "",
                5,
                "1995,1975,1978,1985",
                0,
                "",
                FALSE,
                "Nadzemni priključak",
                FALSE,
                "DRVA",
                TRUE,
                1,
                "Limeni pokrov",
                30,
                TRUE,
                "Crijep",
                TRUE,
                "Sijeno",
                FALSE,
                (long)49.1512,
                (long)16.1654,
                "house1",
                date,
                date);

        house2.save(); // <-- available from BaseModel super class


    }

}
