package com.project.test.database.controllers.report;

import android.content.Context;
import android.graphics.Bitmap;

import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.House_Table;
import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.controllers.House_photosController;
import com.project.test.database.imageSaver.ImageSaver;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zoran on 24.10.2017..
 */

public class InterventionController {
    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

    public InterventionController() {
    }

    public Intervention_track addNewIntervention_atHouse(House house){
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
        Intervention_track intervention_track = new Intervention_track(false,house,CurrentDate,CurrentDate);
                intervention_track.save();
        return intervention_track;
    }





    public List<Intervention_track> GetAllRecordsFromTable_Intervention_track() {

        return SQLite.select().from(Intervention_track.class).queryList();
    }

    public List<Reports> GetAllRecordsFromTable_Reports() {

        return SQLite.select().from(Reports.class).queryList();
    }

    public void DeleteAllRecordsInTable_Intervention_track() {

        final List<Intervention_track> gndPlan = GetAllRecordsFromTable_Intervention_track();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }

    public void DeleteAllRecordsInTable_Reports() {

        final List<Reports> gndPlan = GetAllRecordsFromTable_Reports();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
