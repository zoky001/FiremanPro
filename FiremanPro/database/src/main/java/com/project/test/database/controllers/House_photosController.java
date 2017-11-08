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
        System.out.print("CONSTRUCTORRR: "+photoTypeController.getProfil().getType());
     check();


    }


    public void addNewProfilPhotoToHouse(String photosName, String photosLocationName, House house){
        check();
        Photos photos = addNewPhotoName(photosName,photosLocationName);
        House_photos noviZapis = new House_photos(photos,profil,house,CurrentDate,CurrentDate);
        noviZapis.save();
        System.out.println("TIPOVI NAKON SPREMANJA__ADD PROFIL: "+profil.getType()+
                "\n" + profil.getID());
    }


    public void addNewGrouondPlanPhotoToHouse(String photosName, String photosLocationName, House house){
        check();
        Photos photos = addNewPhotoName(photosName,photosLocationName);
        House_photos noviZapis = new House_photos(photos,groundPlan,house,CurrentDate,CurrentDate);
        noviZapis.save();
        System.out.println("TIPOVI NAKON SPREMANJA__ADD GND: "+groundPlan.getType()+
        "\n" + groundPlan.getID());
    }

    private void check(){
        this.profil = photoTypeController.getProfil();
        this.groundPlan = photoTypeController.getGroundPlan();
    }






private Photos addNewPhotoName(String name, String locationname){

    return photosController.addNewPhotoName(name,locationname);
}

    public List<House_photos> GetAllRecordsFromTable(){

        return SQLite.select().from(House_photos.class).queryList();


    }
    public void DeleteAllRecordsInTable(){

        final List<House_photos> gndPlan = GetAllRecordsFromTable();
        for(int i = 0; i < gndPlan.size(); i++){

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }
}
