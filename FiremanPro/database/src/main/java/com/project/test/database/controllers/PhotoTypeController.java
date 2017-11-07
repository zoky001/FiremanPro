package com.project.test.database.controllers;

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

        List<PhotoType> type = SQLite.select().from(PhotoType.class).queryList();

if (type.size() < 1){ // provjera dali postoje upisani tipovi
this.groundPlan = CreateGroundPlanType();
   this.profil = CreateProfilType();
}else {
    for (PhotoType typ: type
         ) {
        if (typ.getType() == "PROFIL"){
            this.profil = typ;
        }
        if (typ.getType() == "GND_PLAN"){
            this.profil = typ;
        }
    }
}
    }

    public PhotoType getProfil() {
        return profil;
    }

    public PhotoType getGroundPlan() {
        return groundPlan;
    }

    private PhotoType CreateProfilType(){
    PhotoType profilTyp = new PhotoType("PROFIL","Slika profila kuće",CurrentDate,CurrentDate);
    profilTyp.save();
    return profilTyp;
}

private PhotoType CreateGroundPlanType(){
    PhotoType groundPlanTyp = new PhotoType("GND_PLAN","Slika sadrži tlocrt kuće",CurrentDate,CurrentDate);
    groundPlanTyp.save();
    return groundPlanTyp;
}



}
