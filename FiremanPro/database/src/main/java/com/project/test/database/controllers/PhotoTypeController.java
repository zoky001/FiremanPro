package com.project.test.database.controllers;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Photos;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by Zoran on 24.10.2017..
 */

public class PhotoTypeController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

    PhotoType profil;
    PhotoType groundPlan;




    public PhotoTypeController() {
check();

    }
private void check(){
    List<PhotoType> type = SQLite.select().from(PhotoType.class).queryList();

    if (type.size() < 1){ // provjera dali postoje upisani tipovi
        this.groundPlan = CreateGroundPlanType();
        this.profil = CreateProfilType();
       // System.out.print("NOVI ZAPISI: + "+getProfil().getType());
      //  System.out.print("NOVI ZAPISI: + "+getGroundPlan().getType());

    }else {
      //  System.out.print("POSTOJ ZAPISI: + ");

        for (PhotoType typ: type
                ) {
            System.out.println("FOREACHHHHH ");
            System.out.println("FOREACHHHHH : "+typ.getType() + "\n");
            if (typ.getType().equals("PROFIL")){
                System.out.print("IF PRVI: + ");
                this.profil = typ;
            }
            if (typ.getType().equals("GND_PLAN")){
                System.out.print("IF DRUGI: + ");
                this.groundPlan = typ;
            }
        }

      //  System.out.println("POSTOJE ZAPISI: + "+getProfil().getType());
       // System.out.println("pOSTOJE ZAPISI: + "+getGroundPlan().getType());
    }
}
    public PhotoType getProfil() {
//        System.out.print("getprofil: + " + profil.getType());
        return profil;
    }

    public PhotoType getGroundPlan() {
    //    System.out.print("getGND: + " + groundPlan.getType());
        return groundPlan;
    }

    public List<PhotoType> GetAllRecordsFromTable(){
check();
        System.out.print("GET ALL RECORDS PHOTO TIPE: " + SQLite.select().from(PhotoType.class).queryList().size());
        return SQLite.select().from(PhotoType.class).queryList();


    }
    public void DeleteAllRecordsInTable(){

        final List<PhotoType> gndPlan = GetAllRecordsFromTable();
        for(int i = 0; i < gndPlan.size(); i++){

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }

    private PhotoType CreateProfilType(){
    PhotoType profilTyp = new PhotoType(100,"PROFIL","Slika profila kuće",CurrentDate,CurrentDate);
    profilTyp.save();
    return profilTyp;
}

private PhotoType CreateGroundPlanType(){
    PhotoType groundPlanTyp = new PhotoType(101,"GND_PLAN","Slika sadrži tlocrt kuće",CurrentDate,CurrentDate);
    groundPlanTyp.save();
    return groundPlanTyp;
}



}
