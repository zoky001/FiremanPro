package com.project.air.firemanpro.loaders;

import com.kizo.core.DataLoadedListener;
import com.kizo.core.DataLoader;
import com.kizo.web_services.AirWebServiceCaller;
import com.kizo.web_services.AirWebServiceHandler;
import com.project.test.database.Entities.House;
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

        AirWebServiceCaller storesWs = new AirWebServiceCaller(storesHandler);


        storesWs.getAll("getAll", House.class);


    }

    //TODO: As an exercise, change the architecture so that you have only one AirWebServiceHandler

    AirWebServiceHandler storesHandler = new AirWebServiceHandler() {
        @Override
        public void onDataArrived(Object result, boolean ok) {

            System.out.print("onDATAArrived");
            if(ok){
                List<House> stores = (List<House>) result;
                for(House store : stores){
                    store.save();
                }
                storesArrived = true;
                checkDataArrival();
            }
        }
    };



    private void checkDataArrival(){
        if(storesArrived && discountsArrived){
            mDataLoadedListener.onDataLoaded((ArrayList<House>) HouseController.getAllHouseRecords());
        }
    }
}