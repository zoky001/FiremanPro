package com.project.test.database.controllers.report;

import android.content.Context;
import android.graphics.Bitmap;

import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.House_Table;
import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.Entities.report.Intervention_track_Table;
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

    public boolean checkIfExistUnfinishedInterventionAtHouse( House house){

        List<Intervention_track> intervention_tracks =  SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.house_id_house.is(house.getId_house())).and(Intervention_track_Table.completed_intervention.is(Boolean.FALSE)).queryList();

        if (intervention_tracks.size() > 0)
            return true;
        else return false;
    }

    public Intervention_track getUnfinishedInterventionAtHouse( House house){

        Intervention_track intervention_tracks =  SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.house_id_house.is(house.getId_house())).and(Intervention_track_Table.completed_intervention.is(Boolean.FALSE)).querySingle();

        return intervention_tracks;
    }



    public Intervention_track addNewIntervention_atHouse(House house){
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
        Intervention_track intervention_track = new Intervention_track(false,house,CurrentDate,CurrentDate);
                intervention_track.save();
        return intervention_track;
    }


    public static List<Intervention_track> getAllIntervention() {

        return SQLite.select().from(Intervention_track.class).queryList();
    }

    public static  Intervention_track getInterventionByID ( int id){

        return SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.id_intervention_track.is(id)).querySingle();
    }

 public static  List<Intervention_track> getCompletedIntervention (){

        return SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.completed_intervention.is(true)).queryList();
    }

    public static  List<Intervention_track> getUnfinishedIntervention (){

        return SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.completed_intervention.is(false)).queryList();
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
