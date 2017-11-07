package com.project.test.database.controllers;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Photos;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by Zoran on 24.10.2017..
 */

public class House_photosController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
PhotoTypeController photoTypeController = new PhotoTypeController();
    PhotosController photosController = new PhotosController();



    PhotoType profil ;
    PhotoType groundPlan;




    public House_photosController() {
this.profil = photoTypeController.getProfil();
        this.groundPlan = photoTypeController.getGroundPlan();


    }


    public void addNewProfilPhotoToHouse(String photosName, String photosLocationName, House house){
        Photos photos = addNewPhotoName(photosName,photosLocationName);
        House_photos noviZapis = new House_photos(photos,profil,house,CurrentDate,CurrentDate);
        noviZapis.save();
    }


    public void addNewGrouondPlanPhotoToHouse(String photosName, String photosLocationName, House house){
        Photos photos = addNewPhotoName(photosName,photosLocationName);
        House_photos noviZapis = new House_photos(photos,groundPlan,house,CurrentDate,CurrentDate);
        noviZapis.save();
    }






private Photos addNewPhotoName(String name, String locationname){

    return photosController.addNewPhotoName(name,locationname);
}


}
