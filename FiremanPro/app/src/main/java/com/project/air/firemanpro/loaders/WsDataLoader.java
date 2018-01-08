package com.project.air.firemanpro.loaders;


import android.content.Context;

import com.kizo.core_module.DataLoadedListener;
import com.kizo.core_module.DataLoader;
import com.kizo.web_services.AirWebServiceCaller;
import com.kizo.web_services.AirWebServiceHandler;
import com.kizo.web_services.responses.AirWebServiceResponse;
import com.kizo.web_services.responses.WS_entities.Fireman;
import com.kizo.web_services.responses.WS_entities.HousesW;
import com.kizo.web_services.responses.WS_entities.Hydrantw;
import com.kizo.web_services.responses.WS_entities.InterventionTypeW;
import com.kizo.web_services.responses.WS_entities.OutdoorTypeW;
import com.kizo.web_services.responses.WS_entities.PatrolsW;
import com.kizo.web_services.responses.WS_entities.SizeOfFireW;
import com.kizo.web_services.responses.WS_entities.SlikePlanova;
import com.kizo.web_services.responses.WS_entities.SortOfInterventionW;
import com.kizo.web_services.responses.WS_entities.SpatialSpreadW;
import com.kizo.web_services.responses.WS_entities.SpreadingSmokeW;
import com.kizo.web_services.responses.WS_entities.TimeSpreadW;
import com.kizo.web_services.responses.WS_entities.Truck;
import com.kizo.web_services.responses.WS_entities.TypeOfTruckW;
import com.kizo.web_services.responses.WS_entities.TypeOfUnitW;
import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Post;
import com.project.test.database.Entities.fire_intervention.Size_of_fire;
import com.project.test.database.Entities.fire_intervention.Time_spread;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Type_of_truck;
import com.project.test.database.Entities.fireman_patrol.Type_of_unit;
import com.project.test.database.Entities.fireman_patrol.Type_of_unit_Table;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.Entities.report.Sort_of_intervention_Table;
import com.project.test.database.controllers.AddressController;
import com.project.test.database.controllers.FiremanPatrolController;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.HydrantsController;
import com.project.test.database.controllers.PostController;
import com.project.test.database.controllers.report.Types_all_Controller;
import com.project.test.database.helper.MockData;
import com.project.test.database.imageSaver.ImageSaver;
import com.project.test.database.imageSaver.SaveResourceImage;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import retrofit.Response;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Zoran on 25.11.2017..
 */

public class WsDataLoader extends DataLoader {
    private boolean storesArrived = false;
    private boolean discountsArrived = false;
    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
    //  private SaveResourceImage  saveResourceImage;
    private Context context;
    private Types_all_Controller types_all_controller = new Types_all_Controller();

    public WsDataLoader(Context context) {
        this.context = context;
        // saveResourceImage = new SaveResourceImage(context);

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
        public void onDataArrived(List<Post> post, List<PhotoType> photoTypes, List<HousesW> housesWs, Response<AirWebServiceResponse> response, boolean ok) {
            System.out.println("serviceLaravel: WSdata.on data arived    sada ide ispis pristiglih podataka");
            System.out.println("onDATAArrived");

            MockData mockData = new MockData();
            mockData.deleteAllWhenNewDataArrived(); // delete all old data in database

            if (ok) {
                savePosts(post);
                    /*
                    List<Post> posts = post;
                    for(Post post1 : posts){
                        System.out.println("serviceLaravel: " + post1.getName());
                        post1.save();
                    }*/

                storesArrived = true;
                savePhotoTypes(photoTypes);
                /*
                List<PhotoType> photoTypes1 = photoTypes;
                for (PhotoType photoType : photoTypes1) {
                    System.out.println("serviceLaravel: " + photoType.getType());
                    photoType.save();
                }*/
                saveHouses(housesWs);


                saveSortOfIntrevention(response.body().getSortOfInterventionWS());
                saveinterventionType(response.body().getInterventionTypeWS());

                saveOutdoorTypes(response.body().getOutdoorTypeWS());
                saveSizeOfFire(response.body().getSizeOfFireWS());

                saveSpatiaSpreads(response.body().getSpatialSpreadWS());
                saveSpreadingSmokes(response.body().getSpreadingSmokeWS());
                saveTimeSpread(response.body().getTimeSpreadWS());
                saveTypeOfTrucks(response.body().getTypeOfTruckWS());
                saveTypeOfUnits(response.body().getTypeOfUnitWS());

                saveHydrants(response.body().getHydrants());

                saveFiremanPatrol(response.body().getPatrolsWS());

                checkDataArrival();
            }
        }
    };

    private void saveFiremanPatrol(List<PatrolsW> patrolsWS) {
        FiremanPatrolController firemanPatrolController = new FiremanPatrolController();
        for (PatrolsW p :
                patrolsWS) {


            Fireman_patrol dvd_Cestica = firemanPatrolController.addNew_DVD_FiremanPatrol(p.getName());
            for (Truck t : p.getTrucks()
                    ) {
                if (t.getTypeOfTrucksId() == 100) {
                    dvd_Cestica.add_NAVAL_TruckToPatrol(t.getName());
                } else if (t.getTypeOfTrucksId() == 101) {
                    dvd_Cestica.add_TRANSPORT_TruckToPatrol(t.getName());
                } else if (t.getTypeOfTrucksId() == 102) {
                    dvd_Cestica.add_SPECIAL_TruckToPatrol(t.getName());
                }
            }
            for (Fireman f :
                    p.getFiremans()) {
                dvd_Cestica.addFiremenToPatrol(f.getName(), f.getSurname(), f.getActive());
            }

            System.out.println("apsorbent:" + p.getApsorbent());
            dvd_Cestica.addNewCosts(


                   Double.valueOf(p.getApsorbent().toString()),
                    Double.valueOf(p.getAutomaticLadder().toString()),
                    Double.valueOf(p.getCo2().toString()),
                    Double.valueOf(p.getCommandVehicle().toString()),
                    Double.valueOf(p.getFireExtinguisher().toString()),
                    Double.valueOf(p.getFireFighter().toString()),
                    Double.valueOf(p.getFoam().toString()),
                    Double.valueOf(p.getInsurance().toString()),
                    Double.valueOf(p.getNavalVehicle().toString()),
                    Double.valueOf(p.getPowerPumpClock().toString()),
                    Double.valueOf(p.getRoadTankers().toString()),
                    Double.valueOf(p.getSpecialVehicle().toString()),
                    Double.valueOf(p.getTehnicalVehicle().toString()),
                    Double.valueOf(p.getTransportationVehicle().toString())

            );


        }


    }

    private void saveHydrants(List<Hydrantw> hydrants) {
        HydrantsController hydrantsController = new HydrantsController();

        for (Hydrantw h :
                hydrants) {
            hydrantsController.addNewHydrant(
                    h.getTypeOfHydrant(),
                    h.getDescription(),
                    h.getStreetName(),
                    h.getPlace(),
                    h.getStreetNumber(),
                    Post.getPostById(h.getPostId()),
                    h.getLongitude(),
                    h.getLatitude()
            );
        }
    }


    private void saveTimeSpread(List<TimeSpreadW> timeSpreadWS) {

        for (TimeSpreadW t :
                timeSpreadWS) {

            Time_spread timespread = new Time_spread(t.getId(), t.getName(), t.getDescription(), CurrentDate, CurrentDate);
            timespread.save();

        }
    }

    private void saveTypeOfUnits(List<TypeOfUnitW> typeOfUnitWS) {
        for (TypeOfUnitW t :
                typeOfUnitWS) {
            Type_of_unit type_of_unit = new Type_of_unit(t.getId(), t.getName(), CurrentDate, CurrentDate);
            type_of_unit.save();
        }
    }

    private void saveTypeOfTrucks(List<TypeOfTruckW> typeOfTruckWS) {

        for (TypeOfTruckW t :
                typeOfTruckWS) {
            Type_of_truck type_of_truck = new Type_of_truck(t.getId(), t.getTypeName(), CurrentDate, CurrentDate);
            type_of_truck.save();
        }

    }

    private void saveSpreadingSmokes(List<SpreadingSmokeW> spreadingSmokeWS) {
        for (SpreadingSmokeW s :
                spreadingSmokeWS) {
            types_all_controller.addNewSpreading_smoke_Type(s.getId(), s.getName(), s.getDescription());

        }
    }

    private void saveSpatiaSpreads(List<SpatialSpreadW> spatialSpreadWS) {
        for (SpatialSpreadW s :
                spatialSpreadWS) {
            types_all_controller.addNewSpatial_spread_Type(s.getId(), s.getName(), s.getDescription());

        }
    }

    private void saveSizeOfFire(List<SizeOfFireW> sizeOfFireWS) {
        for (SizeOfFireW s :
                sizeOfFireWS) {
            types_all_controller.addNewSize_of_fire_Type(s.getId(), s.getName(), s.getDescription());

        }

    }

    private void saveOutdoorTypes(List<OutdoorTypeW> outdoorTypeWS) {

        for (OutdoorTypeW o :
                outdoorTypeWS) {
            types_all_controller.addNewOutdoor_type_Type(o.getId(), o.getName(), o.getDescription());
        }


    }

    private void saveinterventionType(List<InterventionTypeW> interventionTypeWS) {

        for (InterventionTypeW intp :
                interventionTypeWS) {

            Sort_of_intervention sort_of_intervention = SQLite.select().from(Sort_of_intervention.class).where(Sort_of_intervention_Table.id.is(intp.getInterventionId())).querySingle();

            if (sort_of_intervention != null) {
                sort_of_intervention.addType_of_intervention(intp.getId(), intp.getName());
            }

        }


    }

    private void saveSortOfIntrevention(List<SortOfInterventionW> sortOfInterventionWS) {

        for (SortOfInterventionW s :
                sortOfInterventionWS) {

            Sort_of_intervention sort_of_intervention = new Sort_of_intervention(s.getId(), s.getName(), "", CurrentDate, CurrentDate);
            sort_of_intervention.save();

        }
    }

    private void saveHouses(List<HousesW> housesWs) {

        AddressController addressController = new AddressController();
        HouseController houseController = new HouseController();
        Address address;
        House house;

        for (HousesW housesW : housesWs) {
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
                    housesW.getDisabilityPerson() == 1 ? TRUE : FALSE,
                    housesW.getPowerSupply(),
                    housesW.getGasConnection() == 1 ? TRUE : FALSE,
                    housesW.getTypeOfHeating(),
                    housesW.getNumberOfGasBottle(),
                    housesW.getTypeOfRoof(),
                    housesW.getHydrantDistance(),
                    housesW.getHighRiskObject() == 1 ? TRUE : FALSE,
                    housesW.getHROTypeOfRoof(),
                    housesW.getHROPowerSupply() == 1 ? TRUE : FALSE,
                    housesW.getHROContent(),
                    housesW.getHROAnimals() == 1 ? TRUE : FALSE,
                    housesW.getTelNumber(),
                    housesW.getMobNumber(),
                    address);


            if (housesW.getProfilPocture() != null) {

                // String name= saveResourceImage.SaveImageFromUrlToInternalStorage(housesW.getProfilPocture().getUrl());
                String name = SaveResourceImage.sha256(housesW.getProfilPocture().getUrl());
                houseController.AddProfilPicToHouse(name, housesW.getProfilPocture().getUrl(), house);
            }
            if (housesW.getSlikePlanova() != null)
                for (SlikePlanova slika :
                        housesW.getSlikePlanova()) {

                    //String name= saveResourceImage.SaveImageFromUrlToInternalStorage(slika.getUrl());
                    String name = SaveResourceImage.sha256(slika.getUrl());
                    houseController.AddGroundPlanPicToHouse(name, slika.getUrl(), house);

                }


        }
    }

    private void savePhotoTypes(List<PhotoType> photoTypes) {

        List<PhotoType> photoTypes1 = photoTypes;
        for (PhotoType photoType : photoTypes1) {
            System.out.println("serviceLaravel: " + photoType.getType());
            photoType.save();
        }
    }


    private void savePosts(List<Post> post) {

        List<Post> posts = post;
        for (Post post1 : posts) {
            System.out.println("serviceLaravel: " + post1.getName());
            post1.save();
        }

    }


    private void checkDataArrival() {
        if (storesArrived) {
            mDataLoadedListener.onDataLoaded((ArrayList<House>) HouseController.getAllHouseRecords());
        }
    }
}