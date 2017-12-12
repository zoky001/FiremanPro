package com.project.air.firemanpro.loaders;


import android.content.Context;

import com.kizo.core_module.DataLoadedListener;
import com.kizo.core_module.DataLoader;
import com.kizo.web_services.AirWebServiceCaller;
import com.kizo.web_services.AirWebServiceHandler;
import com.kizo.web_services.responses.WS_entities.HousesW;
import com.kizo.web_services.responses.WS_entities.SlikePlanova;
import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Post;
import com.project.test.database.controllers.AddressController;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.PostController;
import com.project.test.database.imageSaver.ImageSaver;
import com.project.test.database.imageSaver.SaveResourceImage;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Zoran on 25.11.2017..
 */

public class WsDataLoader extends DataLoader {
    private boolean storesArrived = false;
    private boolean discountsArrived = false;

    private SaveResourceImage  saveResourceImage;
private Context context;
    public WsDataLoader(Context context) {
        this.context = context;
        saveResourceImage = new SaveResourceImage(context);

    }

    @Override
    public void loadData(DataLoadedListener dataLoadedListener) {
        super.loadData(dataLoadedListener);

        System.out.println("serviceLaravel: WSdata.loadDAta");
        AirWebServiceCaller storesWs = new AirWebServiceCaller(storesHandler);


        storesWs.getAll("getAll", House.class);


    }

    //TODO: As an exercise, change the architecture so that you have only one AirWebServiceHandler

    AirWebServiceHandler storesHandler = new AirWebServiceHandler() {
        @Override
        public void onDataArrived(List<Post> post, List<PhotoType> photoTypes, List<HousesW> housesWs, boolean ok) {
            System.out.println("serviceLaravel: WSdata.on data arived    sada ide ispis pristiglih podataka");
            System.out.println("onDATAArrived");

            if(ok){
                List<Post> posts = post;
                for(Post post1 : posts){
                    System.out.println("serviceLaravel: " + post1.getName());
                    post1.save();
                }

                storesArrived = true;

                List<PhotoType> photoTypes1=  photoTypes;
                for(PhotoType photoType : photoTypes1){
                    System.out.println("serviceLaravel: " + photoType.getType());
                    photoType.save();
                }
                storesArrived = true;


                AddressController addressController = new AddressController();
                HouseController houseController = new HouseController();
                Address address;
                House house;

                for(HousesW housesW : housesWs){
                    address = addressController.addNewAddress(
                            housesW.getStreetName(),
                            housesW.getPlace(),
                            housesW.getStreetNumber(),
                            PostController.getPostByPostalCode(housesW.getPostId()),
                            housesW.getLongitude(),
                            housesW.getLatitude()
                    );

                    house = houseController.AddNewHouse(
                            housesW.getNameOwner(),
                            housesW.getSurnameOwner(),
                            housesW.getNumberOfTenants(),
                            housesW.getNumberOfFloors(),
                            housesW.getListOfFloors(),
                            housesW.getNumberOfChildren(),
                            housesW.getYearChildren(),
                            housesW.getNumberOfAdults(),
                            housesW.getYearsAdults(),
                            housesW.getNumberOfPowerlessAndElders(),
                            housesW.getYearsPowerlessElders(),
                            housesW.getDisabilityPerson()==1?TRUE:FALSE,
                            housesW.getPowerSupply(),
                            housesW.getGasConnection()==1?TRUE:FALSE,
                            housesW.getTypeOfHeating(),
                            housesW.getNumberOfGasBottle(),
                            housesW.getTypeOfRoof(),
                            housesW.getHydrantDistance(),
                            housesW.getHighRiskObject()==1?TRUE:FALSE,
                            housesW.getHROTypeOfRoof(),
                            housesW.getHROPowerSupply()==1?TRUE:FALSE,
                            housesW.getHROContent(),
                            housesW.getHROAnimals()==1?TRUE:FALSE,
                            housesW.getTelNumber(),
                            housesW.getMobNumber(),
                            address);
/*
                    if (housesW.getProfilPocture() != null)
                        houseController.AddProfilPicToHouse(housesW.getProfilPocture().getFileName(),house);

                    if (housesW.getSlikePlanova() != null)
                    for (SlikePlanova slika:
                         housesW.getSlikePlanova()) {
                        houseController.AddGroundPlanPicToHouse(slika.getFileName(),house);

                    }*/

                    if (housesW.getProfilPocture() != null) {

                        String name= saveResourceImage.SaveImageFromUrlToInternalStorage(housesW.getProfilPocture().getUrl());

                        houseController.AddProfilPicToHouse(name, house);
                    }
                    if (housesW.getSlikePlanova() != null)
                        for (SlikePlanova slika:
                                housesW.getSlikePlanova()) {

                            String name= saveResourceImage.SaveImageFromUrlToInternalStorage(slika.getUrl());

                            houseController.AddGroundPlanPicToHouse(name,house);

                        }


                }
                storesArrived = true;


                checkDataArrival();
            }
        }
    };



    private void checkDataArrival(){
        if(storesArrived){
            mDataLoadedListener.onDataLoaded((ArrayList<House>) HouseController.getAllHouseRecords());
        }
    }
}