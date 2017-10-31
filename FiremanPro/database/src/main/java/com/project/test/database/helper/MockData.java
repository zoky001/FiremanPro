package com.project.test.database.helper;

import com.project.test.database.Entities.Ground_plan;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Places;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        Places radovec_polje = new Places("Radovec Polje",date ,date);
        radovec_polje.save();

        Places cestica = new Places("Cestica",date ,date);
        cestica.save();

        Places krizovljan_radovecki = new Places("Križovljan Radovečki",date ,date);
        krizovljan_radovecki.save();

        Places radovec = new Places("Radovec",date ,date);
        radovec.save();




        ArrayList<String> gnd = new ArrayList<String>();
        gnd.add("gnd_krizovljan_radovecki_antuna_mihanovica_1");
        House house = new House(
                "Ana",
                "Cegles",
                krizovljan_radovecki.getId_place(),
                "Ulica Antuna Mihanovića 1 42208 Cestica",
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
                (long)46.368682,
                (long)16.127483,
                "profil_krizovljan_radovecki_antuna_mihanovica_1",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);

        house.save();
        house.saveGndPlans(gnd);


        gnd = new ArrayList<String>();
        gnd.add("gnd_krizovljan_radovecki_antuna_mihanovica_3");
        House house1 = new House(
                "Marinko",
                "Kokot",
                krizovljan_radovecki.getId_place(),
                "Ulica Antuna Mihanovića 3 42208 Cestica",
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
                (long)46.368447,
                (long)16.127473,
                "profil_krizovljan_radovecki_antuna_mihanovica_3",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house1.save();
        house1.saveGndPlans(gnd);


        gnd = new ArrayList<String>();
        gnd.add("gnd_krizovljan_radovecki_antuna_mihanovica_5");
        House house2 = new House(
                "Jadranko",
                "Kokot",
                krizovljan_radovecki.getId_place(),
                "Ulica Antuna Mihanovića 5 42208 Cestica",
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
                (long)46.368343,
                (long)16.127398,
                "profil_krizovljan_radovecki_antuna_mihanovica_5",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);

        house2.save(); // <-- available from BaseModel super class
        house2.saveGndPlans(gnd);


        gnd = new ArrayList<String>();
        gnd.add("gnd_krizovljan_radovecki_antuna_mihanovica_7");
        House house3 = new House(
                "Ana",
                "Banana",
                krizovljan_radovecki.getId_place(),
                "Ulica Antuna Mihanovića 7 42208 Cestica",
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
                (long)46.368049,
                (long) 16.127299,
                "profil_krizovljan_radovecki_antuna_mihanovica_7",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house3.save();
        house3.saveGndPlans(gnd);

        gnd = new ArrayList<String>();
        gnd.add("gnd_krizovljan_radovecki_antuna_mihanovica_11");
        House house4 = new House(
                "Anton",
                "Barić",
                krizovljan_radovecki.getId_place(),
                "Ulica Antuna Mihanovića 11 42208 Cestica",
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
                (long)46.367909,
                (long)16.127333,
                "profil_krizovljan_radovecki_antuna_mihanovica_11",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house4.save();
        house4.saveGndPlans(gnd);

        gnd = new ArrayList<String>();
        gnd.add("gnd_krizovljan_radovecki_antuna_mihanovica_13");
        House house5 = new House(
                "Željko",
                "Hrnčić",
                krizovljan_radovecki.getId_place(),
                "Ulica Antuna Mihanovića 13 42208 Cestica",
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
                (long)46.367540,
                (long)16.127033,
                "profil_krizovljan_radovecki_antuna_mihanovica_13",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house5.save();
        house5.saveGndPlans(gnd);

        gnd = new ArrayList<String>();
        gnd.add("gnd_krizovljan_radovecki_antuna_mihanovica_15");
        House house6 = new House(
                "Slavica",
                "Hrnčić",
                krizovljan_radovecki.getId_place(),
                "Ulica Antuna Mihanovića 15 42208 Cestica",
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
                (long)46.367522,
                (long)16.126830,
                "profil_krizovljan_radovecki_antuna_mihanovica_15",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house6.save();
        house6.saveGndPlans(gnd);

        gnd = new ArrayList<String>();
        gnd.add("gnd_cestica_varazdinska_ulica_25");
        House house7 = new House(
                "Ivica",
                "Milec",
                cestica.getId_place(),
                "Varaždinska Ulica 25 42208 Cestica",
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
                (long)46.368558,
                (long)16.127715,
                "profil_cestica_varazdinska_ulica_25",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house7.save();
        house7.saveGndPlans(gnd);

        gnd = new ArrayList<String>();
        gnd.add("gnd_cestica_varazdinska_ulica_21");
        House house8 = new House(
                "Neven",
                "Kokot",
                cestica.getId_place(),
                "Varaždinska Ulica 21 42208 Cestica",
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
                (long)46.368196,
                (long)16.128744,
                "profil_cestica_varazdinska_ulica_21",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house8.save();
        house8.saveGndPlans(gnd);

        gnd = new ArrayList<String>();
        gnd.add("gnd_radovec_ulica_antuna_mihanovica_23a");
        House house9 = new House(
                "Goran",
                "Darabuš",
                radovec.getId_place(),
                "Ulica Antuna Mihanovica 23A 42208 Cestica",
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
                (long)46.363101,
                (long)16.130054,
                "profil_radovec_ulica_antuna_mihanovica_23a",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house9.save();
        house9.saveGndPlans(gnd);

        gnd = new ArrayList<String>();
        gnd.add("gnd_cestica_ulica_ljudevita_gaja_26");
        House house10 = new House(
                "Maria",
                "Vnuk",
                cestica.getId_place(),
                "Ulica Ljudevita Gaja 26 42208 Cestica",
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
                (long)46.374547,
                (long)16.132721,
                "profil_cestica_ulica_ljudevita_gaja_26",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house10.save();
        house10.saveGndPlans(gnd);

        gnd = new ArrayList<String>();
        gnd.add("gnd_krizovljan_radovecki_antuna_mihanovica_6");
        House house11 = new House(
                "Mario",
                "Majhen",
                krizovljan_radovecki.getId_place(),
                "Ulica Antuna Mihanovića 6 42208 Cestica",
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
                (long)46.367875,
                (long)16.126637,
                "profil_krizovljan_radovecki_antuna_mihanovica_6",
                "042725091",
                "0995982910",
                gnd,
                date,
                date);
        house11.save();
        house11.saveGndPlans(gnd);
    }

    public static void  printAll(){
        final List<House> house = SQLite.select().from(House.class).queryList();
        final List<Places> places = SQLite.select().from(Places.class).queryList();
        final List<Ground_plan> gndPlan = SQLite.select().from(Ground_plan.class).queryList();

        System.out.print(" HOUSE ID " +
                " IMAGE NAME " +
                " IMAGE ADRESS " + "\n");
        for(int i = 0; i < gndPlan.size(); i++){


            System.out.print("-----------------------------------------------" + "\n");

            System.out.print(gndPlan.get(i).getHouseID()+" | " +
                    gndPlan.get(i).getImgName()+" | " +
                    gndPlan.get(i).getImgAdress()+" | " + "\n");


        }

        System.out.print(" HOUSE ID " +
                " OWNER NAME " +
                " OWNER SURNAME" +
                " PLACE " +
                " PROFIL " +
                " ADDRESS" + "\n");

        for(int i = 0; i < house.size(); i++){



            System.out.print("-----------------------------------------------" + "\n");

            System.out.print(house.get(i).getId_house()+" | " +
                    house.get(i).getName_owner()+" | " +
                    house.get(i).getSurname_owner()+" | " +
                    house.get(i).getPlaceName()+" | " +
                    house.get(i).getHouse_image()+" | " +
                    house.get(i).getAddress()+" | " + "\n");

        }

        System.out.print(" PLACE ID " +
                " NAME " +
                 "\n");
        for (int i = 0; i < places.size();i++){
            System.out.print("-----------------------------------------------" + "\n");

            System.out.print(places.get(i).getId_place()+" | " +

                    places.get(i).getName()+" | " + "\n");
        }
    }
}
