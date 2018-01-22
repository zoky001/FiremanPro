package com.project.test.database.controllers;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Photos;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 *
 * Krira nove tipove fotografije (profil, tlocrt..)
 *
 * Created by Zoran on 24.10.2017..
 */

public class PhotoTypeController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

    PhotoType profil;
    PhotoType groundPlan;


    String profilType = "PROFIL";
    String gndType = "GND_PLAN";
    public PhotoTypeController() {
        check();

    }

    private void check() {
        List<PhotoType> type = SQLite.select().from(PhotoType.class).queryList();

        if (type.size() < 1) {
            this.groundPlan = CreateGroundPlanType();
            this.profil = CreateProfilType();

        } else {

            for (PhotoType typ : type
                    ) {
                if (typ.getType().equals(profilType)) {
                    this.profil = typ;
                }
                if (typ.getType().equals(gndType)) {
                    this.groundPlan = typ;
                }
            }

        }
    }

    /**
     *
     * @return objekt tipa "profilna slika"
     */
    public PhotoType getProfil() {
        return profil;
    }


    /**
     *
     * @return objekt tipa "slika Tlocrta"
     */
    public PhotoType getGroundPlan() {
        return groundPlan;
    }


    /**
     *
     * @return svi zapisi iz tablice PhotoType
     */
    public List<PhotoType> GetAllRecordsFromTable() {
        check();

        return SQLite.select().from(PhotoType.class).queryList();


    }


    public static List<PhotoType> GetAllRecordsFromTable_Static(){
        return SQLite.select().from(PhotoType.class).queryList();
    }

   

    /**
     * Brisanje svih zapisa u tablici   PhotoType
     *
     * @see PhotoType
     */
    public void DeleteAllRecordsInTable() {


        final List<PhotoType> gndPlan = GetAllRecordsFromTable();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }

    /**
     * kriranje tipa "profilna slika"
     * @return
     */
    private PhotoType CreateProfilType() {
        PhotoType profilTyp = new PhotoType(100, profilType, "Slika profila kuće", CurrentDate, CurrentDate);
        profilTyp.save();
        return profilTyp;
    }

    /**
     * kreiranje tipa "slika tlocrta"
     * @return
     */
    private PhotoType CreateGroundPlanType() {
        PhotoType groundPlanTyp = new PhotoType(101, gndType, "Slika sadrži tlocrt kuće", CurrentDate, CurrentDate);
        groundPlanTyp.save();
        return groundPlanTyp;
    }


}
