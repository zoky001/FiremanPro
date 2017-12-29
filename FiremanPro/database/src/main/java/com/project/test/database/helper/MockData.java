package com.project.test.database.helper;

import com.project.test.database.Entities.Address;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Photos;

import com.project.test.database.Entities.Post;
import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.fire_intervention.Spatial_spread;
import com.project.test.database.Entities.fire_intervention.Size_of_fire;
import com.project.test.database.Entities.fire_intervention.Spreading_smoke;
import com.project.test.database.Entities.fire_intervention.Time_spread;
import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Truck;
import com.project.test.database.Entities.report.Intervention_Type;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.Entities.report.Outdoor_type;
import com.project.test.database.controllers.AddressController;
import com.project.test.database.controllers.FiremanPatrolController;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.House_photosController;
import com.project.test.database.controllers.PhotoTypeController;
import com.project.test.database.controllers.PhotosController;
import com.project.test.database.controllers.PostController;
import com.project.test.database.controllers.report.InterventionController;
import com.project.test.database.controllers.report.Types_all_Controller;

import java.util.ArrayList;
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
    FiremanPatrolController firemanPatrolController = new FiremanPatrolController();
    InterventionController interventionController = new InterventionController();
Types_all_Controller types_all_controller = new Types_all_Controller();


    public  void  deleteAll(){

        addressController.DeleteAllRecordsInTable();
        house_photosController.DeleteAllRecordsInTable();
        houseController.DeleteAllRecordsInTable();
        photosController.DeleteAllRecordsInTable();
        photoTypeController.DeleteAllRecordsInTable();
        postController.DeleteAllRecordsInTable();

        firemanPatrolController.DeleteAllRecordsInTable();
        interventionController.DeleteAllRecordsInTable_Intervention_track();
        interventionController.DeleteAllRecordsInTable_Reports();

       types_all_controller.DeleteAllRecordsTable_Intervention_type();


    }

    public void createMockIntervention(){
        Intervention_track intervencija = interventionController.addNewIntervention_atHouse(HouseController.getFirstHouse());


        intervencija.add_FIRE_ReportToIntervention();

        intervencija.callReceived();
        intervencija.intervetionStarted();
        intervencija.intervetionArrival();
        intervencija.intervetionEnded();



        java.util.Date localzationTime = new java.util.Date(System.currentTimeMillis());
        java.util.Date fire_extinguished_time= new java.util.Date(System.currentTimeMillis());

        intervencija.getReports().addFireInterventionDetails(localzationTime,fire_extinguished_time,1,false, Spreading_smoke.getRandomType(), Spatial_spread.getRandomType(), Time_spread.getRandomType(), Outdoor_type.getRandomType(), Size_of_fire.getRandomType(),Intervention_Type.getRandomType());

        intervencija.getReports().addFiremanPatrolandTruck(12,0.5,15,15,21,21,12, Truck.getRandomTruck(),Fireman_patrol.getRandomPatrol());
        intervencija.getReports().addFiremanPatrolandTruck(12,12,12,26,0.845,15,15, Truck.getRandomTruck(),Fireman_patrol.getRandomPatrol());

        intervencija.addDescriptionOfIntervention("opis intervencije jedan završena");

        intervencija.addObjectSurface_m2(55);

        intervencija.addObjectSuperficies_ha(2.0);

intervencija.addHelpers("Nitko nije sudjelovao");
        intervencija.getReports().addConsumption(21,15,12,15,15,12,12,12,54,87,54,65,40,0,0);

        intervencija.getReports().addFiremanToIntervention(Fireman.getRandomType());
        intervencija.getReports().addFiremanToIntervention(Fireman.getRandomType());
        intervencija.getReports().addFiremanSignedToIntervention(Fireman.getRandomType());

        intervencija.completeInterventionTrack();

//druga intervencija
        intervencija = interventionController.addNewIntervention_atHouse(HouseController.getFirstHouse());


        intervencija.add_FIRE_ReportToIntervention();

        intervencija.callReceived();
        intervencija.intervetionStarted();
        intervencija.intervetionArrival();
        intervencija.intervetionEnded();




        localzationTime = new java.util.Date(System.currentTimeMillis());
       fire_extinguished_time= new java.util.Date(System.currentTimeMillis());

        intervencija.getReports().addFireInterventionDetails(localzationTime,fire_extinguished_time,1,false, Spreading_smoke.getRandomType(), Spatial_spread.getRandomType(), Time_spread.getRandomType(), Outdoor_type.getRandomType(), Size_of_fire.getRandomType(),Intervention_Type.getRandomType());

        intervencija.getReports().addFiremanPatrolandTruck(12,0.5,15,15,21,21,12, Truck.getRandomTruck(),Fireman_patrol.getRandomPatrol());
        intervencija.getReports().addFiremanPatrolandTruck(12,12,12,26,0.845,15,15, Truck.getRandomTruck(),Fireman_patrol.getRandomPatrol());

        intervencija.addDescriptionOfIntervention("opis intervencije dvaaaaaa_ završena");

        intervencija.addObjectSurface_m2(55);

        intervencija.addObjectSuperficies_ha(2.0);

        intervencija.addHelpers("Nitko nije sudjelovao");
        intervencija.getReports().addConsumption(21,15,12,15,15,12,12,12,54,87,54,65,40,0,0);

        intervencija.getReports().addFiremanToIntervention(Fireman.getRandomType());
        intervencija.getReports().addFiremanToIntervention(Fireman.getRandomType());
        intervencija.getReports().addFiremanSignedToIntervention(Fireman.getRandomType());

        intervencija.completeInterventionTrack();


        //treca intervencija
        //druga intervencija
        intervencija = interventionController.addNewIntervention_atHouse(HouseController.getFirstHouse());


        intervencija.add_FIRE_ReportToIntervention();

        intervencija.callReceived();
        intervencija.intervetionStarted();
        intervencija.intervetionArrival();
        intervencija.intervetionEnded();




        localzationTime = new java.util.Date(System.currentTimeMillis());
        fire_extinguished_time= new java.util.Date(System.currentTimeMillis());

        intervencija.getReports().addFireInterventionDetails(localzationTime,fire_extinguished_time,1,false, Spreading_smoke.getRandomType(), Spatial_spread.getRandomType(), Time_spread.getRandomType(), Outdoor_type.getRandomType(), Size_of_fire.getRandomType(),Intervention_Type.getRandomType());

        intervencija.getReports().addFiremanPatrolandTruck(12,0.5,15,15,21,21,12, Truck.getRandomTruck(),Fireman_patrol.getRandomPatrol());
        intervencija.getReports().addFiremanPatrolandTruck(12,12,12,26,0.845,15,15, Truck.getRandomTruck(),Fireman_patrol.getRandomPatrol());

        intervencija.addDescriptionOfIntervention("opis intervencije dvaaaaaa_ NEDOVRŠENA");

        intervencija.addObjectSurface_m2(55);

        intervencija.addObjectSuperficies_ha(2.0);

        intervencija.addHelpers("Nitko nije sudjelovao");
        intervencija.getReports().addConsumption(21,15,12,15,15,12,12,12,54,87,54,65,40,0,0);

        intervencija.getReports().addFiremanToIntervention(Fireman.getRandomType());
        intervencija.getReports().addFiremanToIntervention(Fireman.getRandomType());
        intervencija.getReports().addFiremanSignedToIntervention(Fireman.getRandomType());

       // unfinished intervention intervencija.completeInterventionTrack();
    }

    public void writeCodeBook(){

        types_all_controller.addNewSize_of_fire_Type(100,"mali požar","jedan aparat za gašenje požara");
        types_all_controller.addNewSize_of_fire_Type(101,"mali požar - 1  \" C \" mlaz","1  \" C \" mlaz");
        types_all_controller.addNewSize_of_fire_Type(102,"srednji požar","2-3  \" C \" mlaz");
        types_all_controller.addNewSize_of_fire_Type(103,"veliki požar","više od 3  \" C \" mlaza");


        types_all_controller.addNewSpatial_spread_Type(100,"bez širenja iz prostora u kojem je nastao","");
        types_all_controller.addNewSpatial_spread_Type(101,"na prostoriju/okno","");
        types_all_controller.addNewSpatial_spread_Type(102,"na grupu prostorija","");
        types_all_controller.addNewSpatial_spread_Type(103,"na kat-etažu kao odsječak","");





        types_all_controller.addNewSpatial_spread_Type(104,"dio kata-etaže","");
        types_all_controller.addNewSpatial_spread_Type(105,"stubište","");
        types_all_controller.addNewSpatial_spread_Type(106,"dio stubišta","");
        types_all_controller.addNewSpatial_spread_Type(107,"odsječak s više katova","");
        types_all_controller.addNewSpatial_spread_Type(108,"kat s više odsječaka","");

        types_all_controller.addNewSpatial_spread_Type(109,"zgradu kao odsječak","");
        types_all_controller.addNewSpatial_spread_Type(110,"zgradu s više odsječaka","");
        types_all_controller.addNewSpatial_spread_Type(111,"zgradu s kojom se graniči","");
        types_all_controller.addNewSpatial_spread_Type(112,"susjednu zgradu ili građevinu","");
        types_all_controller.addNewSpatial_spread_Type(113,"pročelje građevine (fasadu)","");
        types_all_controller.addNewSpatial_spread_Type(114,"cijelu građevinu","");





        types_all_controller.addNewTime_spread_Type(100,"normalan tijek požara","");
        types_all_controller.addNewTime_spread_Type(101,"flashover","");

        types_all_controller.addNewTime_spread_Type(102,"praskanje","");
        types_all_controller.addNewTime_spread_Type(103,"eksplozija","");
        types_all_controller.addNewTime_spread_Type(104,"detonacija","");
        types_all_controller.addNewTime_spread_Type(105,"raspadanje","");


        types_all_controller.addNewSpreading_smoke_Type(100,"bez širenja dima","");
        types_all_controller.addNewSpreading_smoke_Type(101,"na prostoriju/okno","");
        types_all_controller.addNewSpreading_smoke_Type(102,"na grupu prostorija","");
        types_all_controller.addNewSpreading_smoke_Type(103,"na kat-etažu kao odsječak","");
        types_all_controller.addNewSpreading_smoke_Type(104,"dio kata-etaže","");
        types_all_controller.addNewSpreading_smoke_Type(105,"stubište","");
        types_all_controller.addNewSpreading_smoke_Type(106,"dio stubišta","");
        types_all_controller.addNewSpreading_smoke_Type(107,"odsječak s više  katova","");
        types_all_controller.addNewSpreading_smoke_Type(108,"kat s više  odsječaka","");
        types_all_controller.addNewSpreading_smoke_Type(109,"zgradu kao odsječak","");
        types_all_controller.addNewSpreading_smoke_Type(110,"zgradu s više odsječaka","");
        types_all_controller.addNewSpreading_smoke_Type(111,"zgradu s kojom se graniči","");
        types_all_controller.addNewSpreading_smoke_Type(112,"susjednu zgradu ili građevinu","");
        types_all_controller.addNewSpreading_smoke_Type(113," cijelu građevinu","");

        types_all_controller.addNewOutdoor_type_Type(100,"niski površinski","");
        types_all_controller.addNewOutdoor_type_Type(101,"visoki (požar krošnji)","");
        types_all_controller.addNewOutdoor_type_Type(102,"podzemni","");
        types_all_controller.addNewOutdoor_type_Type(103,"kombinirani","");

        ArrayList<String> strings = new ArrayList<String>();

        strings.add("Pomoćni objekt u domaćinstvu");
        strings.add("Stambena zgrada");
        strings.add("Turistički objekti i vikend-kuće");

        types_all_controller.get_FIRE_Sort_of_intervention().addTypes_of_intervention(strings);

        ArrayList<String> strings1 = new ArrayList<String>();

        strings1.add("PRIJEVOZ VODE po nalogu)");
        strings1.add("LAŽNA DOJAVA");
        strings1.add("IZVIDI");

        types_all_controller.get_OTHER_Sort_of_intervention().addTypes_of_intervention(strings1);

        ArrayList<String> strings2 = new ArrayList<String>();

        strings2.add("Spašvavanje unesrećene osobe");
        strings2.add("Uklanjanje vozila s prometnice");
        strings2.add("Čišćenje i održavanje prometnice");

        types_all_controller.get_TRHNICAL_Sort_of_intervention().addTypes_of_intervention(strings2);




    }
    public void writePatrol(){
        Fireman_patrol dvd_Cestica = firemanPatrolController.addNew_DVD_FiremanPatrol("DVD Križovljan Cestica");
        dvd_Cestica.add_NAVAL_TruckToPatrol("Stari Tamić");
        dvd_Cestica.add_TRANSPORT_TruckToPatrol("VW Transporter");
        dvd_Cestica.addFiremenToPatrol("Dario","Hrnčić","");
        dvd_Cestica.addFiremenToPatrol("Mario","Jeger","");
        dvd_Cestica.addFiremenToPatrol("Schany ", "Vočanec","");

        dvd_Cestica.addNewCosts(12,13,14,15,16,17,18,15,99,12,54,15,54,58);


        Fireman_patrol dvd_Babinec = firemanPatrolController.addNew_DVD_FiremanPatrol("DVD Babinec");
        dvd_Babinec.add_NAVAL_TruckToPatrol("Stari Tamić");
        dvd_Babinec.add_TRANSPORT_TruckToPatrol("VW Transporter");
        dvd_Babinec.addFiremenToPatrol("Dario-Babinec","Hrnčić","");
        dvd_Babinec.addFiremenToPatrol("Mario-Babinec","Jeger","");
        dvd_Babinec.addFiremenToPatrol("Schany-babinec ", "Vočanec","");

        dvd_Babinec.addNewCosts(12,13,14,15,16,17,18,15,99,12,54,15,54,58);


    }
    public void writeAll(){

        deleteAll();
        //delete all
writePatrol();
        writeCodeBook();

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
        "Ana",
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

        house = houseController.AddNewHouse("Marinko",
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

        houseController.AddProfilPicToHouse("profil_krizovljan_radovecki_antuna_mihanovica_3",house);

        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_3",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_5",house);

        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_3",house);
        houseController.AddGroundPlanPicToHouse("gnd_krizovljan_radovecki_antuna_mihanovica_5",house);

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
                "Varaždinska ulica",
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
                "Varaždinska ulica",
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



        createMockIntervention();
    }

    public  void  printAll(){
        final List<House> house = houseController.GetAllRecordsFromTable();
        final List<Photos> photos = photosController.GetAllRecordsFromTable();
        final List<Address> address = addressController.GetAllRecordsFromTable();
        final List<PhotoType> photoTypes = photoTypeController.GetAllRecordsFromTable();
        final List<House_photos> house_photoses = house_photosController.GetAllRecordsFromTable();
        final List<Post> posts = postController.GetAllRecordsFromTable();
        final List<Intervention_track> intervention_tracks = interventionController.GetAllRecordsFromTable_Intervention_track();
        final List<Reports> reportses = interventionController.GetAllRecordsFromTable_Reports();




        System.out.println("Obavijest primljena" +
                " Iizlazak na intervenciju" +
                " Dolazak na intervenciju " +
                " Lokalizirano"  +
                " Ugašen"  +
                " Završtek intervencije"  +
                " Vrsta intervencije" +
                " Tip intervencije " +

                " Veličina požara"  +
                " Broj građevina"  +
                " Ponovio se požar" +
                " Prostorno širenje požara " +

                " Vremensko širenje požara"  +
                " Širenje dima"  +
                " Vrsta požara na otvorenom" +

                " Općina "  +
                " Mjesto "  +
                " Ulica" +

                " Naziv Postrojbe "  +
                " Vozilo"  +
                " Broj" +
                " Općina "  +
                " KM "  +
                " Sati" +
                " Broj Vatrogasca "  +
                " Voda "  +
                " Pjenilo " +
                " Prah "  +
                " CO2 "  +
                " Opis" +

                " Vlasnik "  +
                " Površina objekta "  +
                " Vanjsi prostor " +
                " Helper " +

                " Sat navalnog vozila  "  +
                " Vatrogasac" +
                " potpisao " +

                "\n");
        for(int i = 0; i < intervention_tracks.size(); i++){


            System.out.print("-----------------------------------------------" + "\n");

            System.out.println(
                    intervention_tracks.get(i).getReports().getTime_call_received()+" | " +
                    intervention_tracks.get(i).getReports().getTime_intervention_start()+" | " +
                    intervention_tracks.get(i).getReports().getTime_arrival_intervention() + " | " +
                    intervention_tracks.get(i).getReports().getFireInterventionDetails().getLocalization()+ " | " +

                    intervention_tracks.get(i).getReports().getFireInterventionDetails().getFireExtinguished()+ " | " +
                    intervention_tracks.get(i).getReports().getSort_of_intervention().getName()+ " | " +
                    intervention_tracks.get(i).getReports().getFireInterventionDetails().getIntervention_type().getName()+ " | " +

                    intervention_tracks.get(i).getReports().getFireInterventionDetails().getDestroyed_space()+ " | " +
                    intervention_tracks.get(i).getReports().getFireInterventionDetails().isRepeated()+ " | " +
                    intervention_tracks.get(i).getReports().getFireInterventionDetails().getSpatial_spread().getName()+ " | " +
                    intervention_tracks.get(i).getReports().getFireInterventionDetails().getTime_spread().getName()+ " | " +
                    intervention_tracks.get(i).getReports().getFireInterventionDetails().getSpreading_smoke().getName()+ " | " +
                    intervention_tracks.get(i).getReports().getFireInterventionDetails().getOutdoor_type().getName()+ " | " +

                    intervention_tracks.get(i).getLocation().getPost().getName()+ " | " +
                    intervention_tracks.get(i).getLocation().getPlaceNameIfExist()+ " | " +
                    intervention_tracks.get(i).getLocation().getStreetNameIfExist()+ " | " +

                            intervention_tracks.get(i).getReports().getTrucksAndPatrols().get(0).getFireman_patrol().getName()+ " | " +
                            intervention_tracks.get(i).getReports().getTrucksAndPatrols().get(0).getTruck().getName()+ " | " + "   |  "+
                            intervention_tracks.get(i).getReports().getTrucksAndPatrols().get(0).getKm()+ " | " +

                            intervention_tracks.get(i).getReports().getTrucksAndPatrols().get(0).getHours()+ " | " +
                            intervention_tracks.get(i).getReports().getTrucksAndPatrols().get(0).getNumberOfFireman()+ " | " + "   |  "+
                            intervention_tracks.get(i).getReports().getTrucksAndPatrols().get(0).getWater()+ " | " +

                            intervention_tracks.get(i).getReports().getTrucksAndPatrols().get(0).getFoam()+ " | " +
                            intervention_tracks.get(i).getReports().getTrucksAndPatrols().get(0).getPowder()+ " | " +
                            intervention_tracks.get(i).getReports().getTrucksAndPatrols().get(0).getCo2()+ " | " + "   |  "+

                            intervention_tracks.get(i).getReports().getDescription() + " | " +

                            intervention_tracks.get(i).getHouse().getName_owner()+ " | " +
                            intervention_tracks.get(i).getReports().getSurface_m2()+ " | " + "   |  "+

                            intervention_tracks.get(i).getReports().getSuperficies_ha() + " | " +

                            intervention_tracks.get(i).getReports().getHelp() + " | " +


                            intervention_tracks.get(i).getReports().getConsumption().getNavalVehicle()+ " | " + "   |  "+

                            intervention_tracks.get(i).getReports().getFiremans().get(0).getFireman().getName()+ " | " +

                            intervention_tracks.get(i).getReports().getSigned().getSurname() + " | " +

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
