package com.project.air.firemanpro.loaders;


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
import com.project.test.database.Entities.fire_intervention.Time_spread;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Type_of_truck;
import com.project.test.database.Entities.fireman_patrol.Type_of_unit;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.Entities.report.Sort_of_intervention_Table;
import com.project.test.database.FirebasePatrolController;
import com.project.test.database.controllers.AddressController;
import com.project.test.database.controllers.FiremanPatrolController;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.HydrantsController;
import com.project.test.database.controllers.PostController;
import com.project.test.database.controllers.report.Types_all_Controller;
import com.project.test.database.helper.MockData;
import com.project.test.database.imageSaver.SaveResourceImage;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import retrofit.Response;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Klasa se koristi za dohvaćanje podataka putem Web Servisa.Sadrži metode za pokretanje dohvaćanja podataka putem WS,
 * te po primitku, obrađenih podataka, iste pomoću različitih metoda pohranjuje u bazu podataka.
 * <p>
 * <p>
 * <p>
 * Created by Zoran Hrnčić on 25.11.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */
public class WsDataLoader extends DataLoader implements AirWebServiceHandler {

    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


    private Types_all_Controller types_all_controller = new Types_all_Controller();

    public WsDataLoader() {

    }

    /**
     * Metoda instancira  AirWebServiceCaller, te pokreće dohvaćanje podataka.
     *
     * @param dataLoadedListener "Implementacija" interface-a DataLoadedListener
     * @author Zoran Hrnčić
     */
    @Override
    public void loadData(DataLoadedListener dataLoadedListener) {
        super.loadData(dataLoadedListener);

        System.out.println("serviceLaravel: WSdata.loadDAta0");
        AirWebServiceCaller allEntries = new AirWebServiceCaller(this);


        allEntries.getAll();


    }

    /**
     * Po primitku strukturiranih podataka, pristiglih od Web Servisa, prvo se obriši svi prethodni zapisi u mobilnoj bazi podataka.
     * Nakon brisanja se pohranjuju svi novi zapisi u bazu.
     *
     * @author Zoran Hrnčić
     */
    @Override
    public void onDataArrived(List<Post> post, List<PhotoType> photoTypes, List<HousesW> housesWs, Response<AirWebServiceResponse> response) {
        MockData mockData = new MockData();
        mockData.deleteAllWhenNewDataArrived(); // delete all old data in database
        System.out.println("serviceLaravel: WSdata.loadDAta1");

        savePosts(post);

        savePhotoTypes(photoTypes);

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

        setDataArrival();

    }

    /**
     * Pohranjuje sve podatke o vatrogasnim postrojbama u  bazu podataka.
     *
     * @param patrolsWS popis vatrogasnih postrojba dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
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

    /**
     * Pohranjuje sve podatke o hidrantima u  bazu podataka.
     *
     * @param hydrants popis hidranata sa svim podatcima dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
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


    /**
     * Pohranjuje tipove vremenskih širenja požara u  bazu podataka.
     *
     * @param timeSpreadWS tipovi vremenskih širenja požara dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveTimeSpread(List<TimeSpreadW> timeSpreadWS) {

        for (TimeSpreadW t :
                timeSpreadWS) {

            Time_spread timespread = new Time_spread(t.getId(), t.getName(), t.getDescription(), CurrentDate, CurrentDate);
            timespread.save();

        }
    }

    /**
     * Pohranjuje vrste vatrogasnih postrojbi (DVD, JVP..) u  bazu podataka.
     *
     * @param typeOfUnitWS vrste vatrogasnih postrojbi dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveTypeOfUnits(List<TypeOfUnitW> typeOfUnitWS) {
        for (TypeOfUnitW t :
                typeOfUnitWS) {
            Type_of_unit type_of_unit = new Type_of_unit(t.getId(), t.getName(), CurrentDate, CurrentDate);
            type_of_unit.save();
        }
    }

    /**
     * Pohranjuje vrste vatrogasnih vozila (Transportno, navalno..) u  bazu podataka.
     *
     * @param typeOfTruckWS vrste vatrogasnih vozila dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveTypeOfTrucks(List<TypeOfTruckW> typeOfTruckWS) {

        for (TypeOfTruckW t :
                typeOfTruckWS) {
            Type_of_truck type_of_truck = new Type_of_truck(t.getId(), t.getTypeName(), CurrentDate, CurrentDate);
            type_of_truck.save();
        }

    }

    /**
     * Pohranjuje vrste širenja dima kod požara (Bez širenja,na prostoriju...) u  bazu podataka.
     *
     * @param spreadingSmokeWS vrste širenja dima dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveSpreadingSmokes(List<SpreadingSmokeW> spreadingSmokeWS) {
        for (SpreadingSmokeW s :
                spreadingSmokeWS) {
            types_all_controller.addNewSpreading_smoke_Type(s.getId(), s.getName(), s.getDescription());

        }
    }

    /**
     * Pohranjuje vrste prostornog širenja požara (Bez širenja,na prostoriju...)u  bazu podataka.
     *
     * @param spatialSpreadWS vrste prostornog širnja požara dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveSpatiaSpreads(List<SpatialSpreadW> spatialSpreadWS) {
        for (SpatialSpreadW s :
                spatialSpreadWS) {
            types_all_controller.addNewSpatial_spread_Type(s.getId(), s.getName(), s.getDescription());

        }
    }

    /**
     * Pohranjuje vrste veličine požara u  bazu podataka.
     *
     * @param sizeOfFireWS vrste veličine požara dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveSizeOfFire(List<SizeOfFireW> sizeOfFireWS) {
        for (SizeOfFireW s :
                sizeOfFireWS) {
            types_all_controller.addNewSize_of_fire_Type(s.getId(), s.getName(), s.getDescription());

        }

    }

    /**
     * Pohranjuje vrste požara na otvorenom u  bazu podataka.
     *
     * @param outdoorTypeWS vrste požara na otvorenom dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveOutdoorTypes(List<OutdoorTypeW> outdoorTypeWS) {

        for (OutdoorTypeW o :
                outdoorTypeWS) {
            types_all_controller.addNewOutdoor_type_Type(o.getId(), o.getName(), o.getDescription());
        }


    }

    /**
     * Pohranjuje vrste intervencija u  bazu podataka.
     *
     * @param interventionTypeWS vrste intervencija dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveinterventionType(List<InterventionTypeW> interventionTypeWS) {

        for (InterventionTypeW intp :
                interventionTypeWS) {

            Sort_of_intervention sort_of_intervention = SQLite.select().from(Sort_of_intervention.class).where(Sort_of_intervention_Table.id.is(intp.getInterventionId())).querySingle();

            if (sort_of_intervention != null) {
                sort_of_intervention.addType_of_intervention(intp.getId(), intp.getName());
            }

        }


    }

    /**
     * Pohranjuje vrste vatrogasnih intervencija (požarna, tehnička, ostala) u  bazu podataka.
     *
     * @param sortOfInterventionWS vrste vatrogasnih intervencija dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveSortOfIntrevention(List<SortOfInterventionW> sortOfInterventionWS) {

        for (SortOfInterventionW s :
                sortOfInterventionWS) {

            Sort_of_intervention sort_of_intervention = new Sort_of_intervention(s.getId(), s.getName(), "", CurrentDate, CurrentDate);
            sort_of_intervention.save();
            FirebasePatrolController.saveFireInterventionType(sort_of_intervention);

        }
    }

    /**
     * Pohranjuje sve kuće i  sve podatke (adresa, podatci o kučanstvu, slike tlocrta...) o istima u  bazu podataka.
     *
     * @param housesWs popis kučanstva sa svim podatcima dohvaćeni putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void saveHouses(List<HousesW> housesWs) {

        AddressController addressController = new AddressController();
        final HouseController houseController = new HouseController();
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


/*
               = SaveResourceImage.sha256(housesW.getProfilPocture().getUrl());
                FirebaseStorageController.storeProfilPicFromURI(housesW.getProfilPocture().getUrl(),name,t);
             */

                String name = SaveResourceImage.sha256(housesW.getProfilPocture().getUrl());
                houseController.AddProfilPicToHouse(name, housesW.getProfilPocture().getUrl(), house);


            }
            if (housesW.getSlikePlanova() != null)
                for (SlikePlanova slika :
                        housesW.getSlikePlanova()) {
                    String name = SaveResourceImage.sha256(slika.getUrl());
                    houseController.AddGroundPlanPicToHouse(name, slika.getUrl(), house);

                }


        }
    }

    /**
     * Pohranjuje tipove fotografija  (Profilna, tlocrt...) u  bazu podataka.
     *
     * @param photoTypes tipovi fotografija dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void savePhotoTypes(List<PhotoType> photoTypes) {

        List<PhotoType> photoTypes1 = photoTypes;
        for (PhotoType photoType : photoTypes1) {
            photoType.save();
        }
    }

    /**
     * Pohranjuje poštanske urede u  bazu podataka.
     *
     * @param post popis pošta dohvaćenih putem Web Servisa
     * @author Zoran Hrnčić
     */
    private void savePosts(List<Post> post) {

        List<Post> posts = post;
        for (Post post1 : posts) {
            post1.save();
        }

    }

    /**
     * Po završetku uspješnog popunjavanja baze podataka sa pristiglim podatcima ova metoda poziva metodu iz interface-a.
     * Daje se "signal" da je završeno dohvačanje i upisivanje podataka u bazu.
     *
     * @author Zoran Hrnčić
     */
    private void setDataArrival() {

        mDataLoadedListener.onDataLoaded();

    }


}