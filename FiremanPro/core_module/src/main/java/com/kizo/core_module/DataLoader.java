package com.kizo.core_module;

import com.project.test.database.Entities.House;

import java.util.ArrayList;

/**
 * Created by Zoran on 25.11.2017..
 */

public abstract class DataLoader {

    public ArrayList<House> houses;


    protected DataLoadedListener mDataLoadedListener;

    public void loadData(DataLoadedListener dataLoadedListener){
        this.mDataLoadedListener = dataLoadedListener;
    }

    public boolean dataLoaded(){
        if(houses == null){
            return false;
        }
        else{
            return true;
        }
    }

}