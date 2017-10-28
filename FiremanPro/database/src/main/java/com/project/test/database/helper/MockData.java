package com.project.test.database.helper;

import com.project.test.database.Entities.Ground_plan;
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
        final List<Ground_plan> gndPlan = SQLite.select().from(Ground_plan.class).queryList();
        for(int i = 0; i < gndPlan.size(); i++){

            gndPlan.get(i).delete();
            //delete all item in table House
        }
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
                "042725091",
                "0995982910",
                date,
                date);
        house.save();

        Ground_plan g5 = new Ground_plan(house.getId_house(),"Prvi kat","gnd_2",date,date);
        g5.save();

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
                "house2",
                "042725091",
                "0995982910",
                date,
                date);
        house1.save();

        Ground_plan g1 = new Ground_plan(house1.getId_house(),"Prvi kat","gnd_1",date,date);
        g1.save();

        Ground_plan g2 = new Ground_plan(house1.getId_house(),"Drugi kat","gnd_2",date,date);
        g2.save();

        Ground_plan g3 = new Ground_plan(house1.getId_house(),"Treci kat","gnd_3",date,date);
        g3.save();

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
                "house3",
                "042725091",
                "0995982910",
                date,
                date);

        house2.save(); // <-- available from BaseModel super class
        Ground_plan g4 = new Ground_plan(house2.getId_house(),"Prvi kat","gnd_1",date,date);
        g4.save();
        House house3 = new House(
                "Ana",
                "Banana",
                place.getId_place(),
                "Varaždinska 35 42208 Cestica",
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
                "house4",
                "042725091",
                "0995982910",
                date,
                date);
        house3.save();

        Ground_plan g6 = new Ground_plan(house3.getId_house(),"Prvi kat","gnd_1",date,date);
        g6.save();

        Ground_plan g7 = new Ground_plan(house3.getId_house(),"Drugi kat","gnd_2",date,date);
        g7.save();



    }

}
