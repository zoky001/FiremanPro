package com.project.test.database.controllers;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Photos;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Kontrolira dodavanje novih slika i pridruživanje istih kučama.
 *
 * Profilne slike, Slike planova...
 *
 * <p>
 * Created by Zoran on 24.10.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 *
 */

public class House_photosController {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

    PhotoTypeController photoTypeController = new PhotoTypeController();

    PhotosController photosController = new PhotosController();

    PhotoType profil;
    PhotoType groundPlan;

    public House_photosController() {
        check();
    }


    /**
     * Dodavanje nove profilne slika odabranoj kući.
     *
     * @param photosName naziv slike npr. (Slika profila Marijine kuće)
     * @param photosLocationName nahiv datoteke slike npr (slika_profila.png)
     * @param url link odakle se preuzima slika (WebService)
     * @param house objekt kuće kojoj se pridružuje navedena fotografija
     */
    public void addNewProfilPhotoToHouse(String photosName, String photosLocationName, String url, House house) {
        check();
        Photos photos = addNewPhotoName(photosName, photosLocationName, url);
        House_photos noviZapis = new House_photos(photos, profil, house, CurrentDate, CurrentDate);
        noviZapis.save();
    }


    /**
     * Dodavanje nove slike tlocrta odabranoj kući.
     *
     * @param photosName naziv slike npr. (Slika tlocrta prvi kat)
     * @param photosLocationName nahiv datoteke slike npr (slika_prvi_kat.png)
     * @param url link odakle se preuzima slika (WebService)
     * @param house objekt kuće kojoj se pridružuje navedena fotografija
     */
    public void addNewGrouondPlanPhotoToHouse(String photosName, String photosLocationName, String url, House house) {
        check();
        Photos photos = addNewPhotoName(photosName, photosLocationName, url);
        House_photos noviZapis = new House_photos(photos, groundPlan, house, CurrentDate, CurrentDate);
        noviZapis.save();
    }


    private void check() {
        this.profil = photoTypeController.getProfil();
        this.groundPlan = photoTypeController.getGroundPlan();
    }


    /**
     * pohranjivanje nove fotografije/podatke za pristupanje fotografiji u bazu podataka.
     * @param name
     * @param locationname
     * @param url
     * @return
     */
    private Photos addNewPhotoName(String name, String locationname, String url) {

        return photosController.addNewPhotoName(name, locationname, url);
    }





    public static List<House_photos> GetAllRecordsFromTable(){
          return SQLite.select().from(House_photos.class).queryList();

    }

    /**
     * Vraćanje svih zapisa iz tablice  House_photos
     *
     * Ova tablica povezuje slike sa kućama.
     *
     * @return
     * @see House_photos
     */


    /**
     * Brisanj svih zapisa u tablisi House_photos
     *
     * @see House_photos
     */
    public void DeleteAllRecordsInTable() {

        final List<House_photos> gndPlan = GetAllRecordsFromTable();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }
}
