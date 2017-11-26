package com.project.air.firemanpro.loaders;

import com.kizo.core.DataLoadedListener;
import com.kizo.core.DataLoader;
import com.kizo.web_services.AirWebServiceCaller;
import com.kizo.web_services.AirWebServiceHandler;
import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Post;
import com.project.test.database.controllers.HouseController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoran on 25.11.2017..
 */

public class WsDataLoader extends DataLoader {
    private boolean storesArrived = false;
    private boolean discountsArrived = false;

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
        public void onDataArrived(Object post, Object address, boolean ok) {
            System.out.println("serviceLaravel: WSdata.on data arived    sada ide ispis pristiglih podataka");
            System.out.print("onDATAArrived");

            if(ok){
                List<Post> stores = (List<Post>) post;
                for(Post store : stores){
                    System.out.println("serviceLaravel: " + store.getName());
                    store.save();
                }
                storesArrived = true;
                List<Address> addresses= (List<Address>) address;
                for(Address adr : addresses){
                    System.out.println("serviceLaravel: " + adr.getStreetNumber());
                    adr.save();
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