package com.kizo.core;

import com.project.test.database.Entities.House;

import java.util.ArrayList;

/**
 * Created by Zoran on 25.11.2017..
 */

public interface DataLoadedListener {
    void onDataLoaded(ArrayList<House> houses);
}