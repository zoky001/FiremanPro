package com.project.test.database.helper;

import com.project.test.database.Entities.Address;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Photos;

import com.project.test.database.Entities.Post;
import com.project.test.database.controllers.AddressController;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.House_photosController;
import com.project.test.database.controllers.PhotoTypeController;
import com.project.test.database.controllers.PhotosController;
import com.project.test.database.controllers.PostController;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Zoran on 23.10.2017..
 */

public class MockData {

    AddressController addressController = new AddressController();
    House_photosController house_photosController = new House_photosController();
    HouseController houseController = new HouseController();
    PhotosController photosController = new PhotosController();
    PhotoTypeController photoTypeController = new PhotoTypeController();

    PostController postController = new PostController();

    public  void  deleteAll(){

        addressController.DeleteAllRecordsInTable();
        house_photosController.DeleteAllRecordsInTable();
        houseController.DeleteAllRecordsInTable();
        photosController.DeleteAllRecordsInTable();
        photoTypeController.DeleteAllRecordsInTable();
        postController.DeleteAllRecordsInTable();


    }
    public void writeAll(){

        deleteAll();
        //delete all


        java.util.Date date = new java.util.Date(System.currentTimeMillis());

        Post cesticaPost = postController.addNewPost(42208,"Cestica");

        /*

        SELA:
       radovec
         radovec_polje
        cestica
         krizovljan_radovecki
*/






Address nova = addressController.addNewAddress(
        "Ulica Antuna Mihanovića",
        "Križovljan Radovečki",
        "1",
        cesticaPost,
        (long)46.368682,
        (long)16.127483
);


House house = houseController.AddNewHouse(
        "ana",
        "Antolić",
        5,
        3,
        "prvi kat, drugi kat",
        2,
        "1998,2000",
        2,
        "1966,1970",
        0,
        "",
        FALSE,
        "Nadzemni",
        TRUE,
        "plin",
        2,
        "Lim",
        20,
        FALSE,
        "nema",
        FALSE,
        "ništa",
        FALSE,
        "042725091",
        "0995982910",
        nova);

        houseController.AddProfilPicToHouse("profil_krizovljan_radovecki_antuna_mihanovica_1",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_1",house);

        //second house
        nova = addressController.addNewAddress(
                "Ulica Antuna Mihanovića",
        "Križovljan Radovečki",
                "3",
                cesticaPost,
                (long)46.368447,
                (long)16.127473
        );

        house = houseController.AddNewHouse("Marinki",
                "kokot",
                5,
                3,
                "prvi kat, drugi kat",
                2,
                "1998,2000",
                2,
                "1966,1970",
                0,
                "",
                FALSE,
                "Nadzemni",
                TRUE,
                "plin",
                2,
                "Lim",
                20,
                FALSE,
                "nema",
                FALSE,
                "ništa",
                FALSE,
                "042725091",
                "0995982910",
                nova);

        houseController.AddProfilPicToHouse("profil_krizovljan_radovecki_antuna_mihanovica_3",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_3",house);

        //second house
        nova = addressController.addNewAddress(
                "Ulica Antuna Mihanovića",
                "Križovljan Radovečki",
                "5",
                cesticaPost,
                (long)46.368343,
                (long)16.127398);

        house = houseController.AddNewHouse(
                "Jadranko",
                "Kokot",
                5,
                3,
                "prvi kat, drugi kat",
                2,
                "1998,2000",
                2,
                "1966,1970",
                0,
                "",
                FALSE,
                "Nadzemni",
                TRUE,
                "plin",
                2,
                "Lim",
                20,
                FALSE,
                "nema",
                FALSE,
                "ništa",
                FALSE,
                "042725091",
                "0995982910",
                nova);

        houseController.AddProfilPicToHouse("profil_krizovljan_radovecki_antuna_mihanovica_5",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_5",house);

        //second house
        nova = addressController.addNewAddress(
                "Ulica Antuna Mihanovica",
                "Radovec",
                "23A",
                cesticaPost,
                (long)46.363101,
                (long)16.130054
        );

        house = houseController.AddNewHouse(
                "Goran",
                "Darabuš",
                5,
                3,
                "prvi kat, drugi kat",
                2,
                "1998,2000",
                2,
                "1966,1970",
                0,
                "",
                FALSE,
                "Nadzemni",
                TRUE,
                "plin",
                2,
                "Lim",
                20,
                FALSE,
                "nema",
                FALSE,
                "ništa",
                FALSE,
                "042725091",
                "0995982910",
                nova);

        houseController.AddProfilPicToHouse("profil_radovec_ulica_antuna_mihanovica_23a",house);
        houseController.AddGroundPlanPicToHouse("gnd_radovec_ulica_antuna_mihanovica_23a",house);

        /*
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
        house11.saveGndPlans(gnd);*/
    }

    public  void  printAll(){
        final List<House> house = houseController.GetAllRecordsFromTable();
        final List<Photos> photos = photosController.GetAllRecordsFromTable();
        final List<Address> address = addressController.GetAllRecordsFromTable();
        final List<PhotoType> photoTypes = photoTypeController.GetAllRecordsFromTable();
        final List<House_photos> house_photoses = house_photosController.GetAllRecordsFromTable();
        final List<Post> posts = postController.GetAllRecordsFromTable();




        System.out.println(" HOUSE ID " +
                " OWNER " +
                " street " +
                " num"  +

                " pošta"  +
                " broj"  +
                " profil " +
                " gnd " +
                "\n");
        for(int i = 0; i < house.size(); i++){


            System.out.print("-----------------------------------------------" + "\n");

            System.out.print(house.get(i).getId_house()+" | " +
                    house.get(i).getName_owner()+" | " +
                    house.get(i).getAddress().getStreetNameIfExist() + " | " +
                    house.get(i).getAddress().getStreetNumber()+ " | " +

                    house.get(i).getAddress().getPost().getName()+ " | " +
                    house.get(i).getAddress().getPost().getPostal_code()+ " | " +
                    house.get(i).getProfilPhotos().getImageName()+ " | " +
                    house.get(i).getGroundPlanPhotos().get(0).getPhoto().getImageName()+ " | " +

                    "\n");


        }

        System.out.print(" ADDRESS ID " +
                " ulica" +
                "\n");

        for(int i = 0; i < address.size(); i++){



            System.out.print("-----------------------------------------------" + "\n");

            System.out.print(address.get(i).getID()+" | " +
                    address.get(i).getStreetNameIfExist()+ "\n");

        }



        System.out.print(" POSTAL CODE " +
                " NAZIV" +
                "\n");

        for(int i = 0; i < posts.size(); i++){



            System.out.print("-----------------------------------------------" + "\n");

            System.out.print(posts.get(i).getPostal_code()+" | " +
                    posts.get(i).getName() + "\n");

        }

        System.out.print(" photo_ID " +
                " NAME " +
                 "\n");
        for (int i = 0; i < photos.size();i++){
            System.out.print("-----------------------------------------------" + "\n");

            System.out.print(photos.get(i).getID()+" | " +

                    photos.get(i).getImageName()+" | " + "\n");
        }

        System.out.print(" photo_ID " +
                " TYPE" +
                "\n");
        for (int i = 0; i < photoTypes.size();i++){
            System.out.print("-----------------------------------------------" + "\n");

            System.out.print(photoTypes.get(i).getID()+" | " +

                    photoTypes.get(i).getType()+" | " + "\n");
        }
        System.out.print(" photo_ID " +
                " TYPE_ID" +
                " HOUSE_ID" +
                "\n");

        for (int i = 0; i < house_photoses.size();i++){
            System.out.print("-----------------------------------------------" + "\n");

            //System.out.print("house_photoses_size_: "+ house_photoses.size()+"\n");


            System.out.print(house_photoses.get(i).getPhoto().getID()+" | " +

                   house_photoses.get(i).getPhotoType().getID()+" | " +

                    house_photoses.get(i).getHouse().getId_house()+" | " + "\n");

        }
    }
}
