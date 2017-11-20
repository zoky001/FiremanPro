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
        16.127483,
        46.368682
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
                16.127473,
                46.368447

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
                16.127398,
                46.368343
        );

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
                16.130054,
               46.363101
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

        //next house
        nova = addressController.addNewAddress(
                "Ulica Antuna Mihanovića",
                "Križovljan Radovečki",
                "7",
                cesticaPost,
                16.127299,
                46.368049

        );

        house = houseController.AddNewHouse(
                "Anamarija",
                "Bananković",
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

        houseController.AddProfilPicToHouse("profil_krizovljan_radovecki_antuna_mihanovica_7",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_7",house);


        //next house
        nova = addressController.addNewAddress(
                "Ulica Antuna Mihanovića",
                "Križovljan Radovečki",
                "11",
                cesticaPost,
                16.127333,
                46.367909


        );

        house = houseController.AddNewHouse(
                "Anton",
                "Barić",
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

        houseController.AddProfilPicToHouse("profil_krizovljan_radovecki_antuna_mihanovica_11",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_11",house);

        //next house
        nova = addressController.addNewAddress(
                "Ulica Antuna Mihanovića",
                "Križovljan Radovečki",
                "13",
                cesticaPost,
                16.127033,
                46.367540



        );

        house = houseController.AddNewHouse(
                "Željko",
                "Hrnčić",
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

        houseController.AddProfilPicToHouse("profil_krizovljan_radovecki_antuna_mihanovica_13",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_13",house);

        //next house
        nova = addressController.addNewAddress(
                "Ulica Antuna Mihanovića",
                "Križovljan Radovečki",
                "15",
                cesticaPost,
                16.126830,
                46.367522




        );

        house = houseController.AddNewHouse(
                "Slavica",
                "Hrnčić",
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

        houseController.AddProfilPicToHouse("profil_krizovljan_radovecki_antuna_mihanovica_15",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_15",house);

        //next house
        nova = addressController.addNewAddress(
                "Varaždinska Ulica",
                "Cestica",
                "25",
                cesticaPost,
                16.127715,
                46.368558





        );

        house = houseController.AddNewHouse(
                "Ivica",
                "Milec",
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

        houseController.AddProfilPicToHouse("profil_cestica_varazdinska_ulica_25",house);
        houseController.AddGroundPlanPicToHouse("gnd_cestica_varazdinska_ulica_25",house);

        //next house
        nova = addressController.addNewAddress(
                "Varaždinska Ulica",
                "Cestica",
                "21",
                cesticaPost,
                16.128423,
                46.368452

                );

        house = houseController.AddNewHouse(
                "Neven",
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

        houseController.AddProfilPicToHouse("profil_cestica_varazdinska_ulica_21",house);
        houseController.AddGroundPlanPicToHouse("gnd_cestica_varazdinska_ulica_21",house);


        //next house
        nova = addressController.addNewAddress(
                "Ulica Ljudevita Gaja",
                "Cestica",
                "26",
                cesticaPost,
                16.133096,
                46.374510

        );

        house = houseController.AddNewHouse(
                "Maria",
                "Vnuk",
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

        houseController.AddProfilPicToHouse("profil_cestica_ulica_ljudevita_gaja_26",house);
        houseController.AddGroundPlanPicToHouse("gnd_cestica_ulica_ljudevita_gaja_26",house);

        //next house
        nova = addressController.addNewAddress(
                "Ulica Antuna Mihanovića",
                "Križovljan Radovečki",
                "6",
                cesticaPost,
                16.126637,
                46.367875


        );

        house = houseController.AddNewHouse(
                "Mario",
                "Majhen",
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

        houseController.AddProfilPicToHouse("profil_krizovljan_radovecki_antuna_mihanovica_6",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_6",house);

/*
        //second house za testt samo
        nova = addressController.addNewAddress(
                "null",
                "Samo Place",
                "5",
                cesticaPost,
                16.127398,
                46.368343

        );

        house = houseController.AddNewHouse(
                "Ime SAmo",
                "Place",
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
                "Ulica Samo Ulica",
                "null",
                "5",
                cesticaPost,
                16.127398,
                46.368343


        );

        house = houseController.AddNewHouse(
                "Vlasnik_Samo",
                "Prezime SAmo",
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
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_5",house);*/




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
