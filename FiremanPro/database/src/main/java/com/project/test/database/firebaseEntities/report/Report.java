package com.project.test.database.firebaseEntities.report;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.fire_intervention.Tehnical_intervention;
import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.report.Consumption;
import com.project.test.database.Entities.report.Other_sort_intervention;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.firebaseEntities.Photos;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;

/**
 * Created by Zoran on 23.10.2017..
 */

@IgnoreExtraProperties
public class Report {



   private java.util.Date time_call_received;


    private java.util.Date time_intervention_start;


    private java.util.Date time_arrival_intervention;


    private java.util.Date time_intervention_ended;



    private double water_m3;


    private double foam_l;


    private double powden_kg;


    private double co2_kg;


    private String description;


    private double surface_m2;


    private double superficies_ha;


    private String help;



    private double mehanization_type;


    private  double mehanization_hour;

    private   DocumentReference sort_of_interventionRef;

    private String sort_of_intervention_ID;

    private com.project.test.database.firebaseEntities.report.Consumption consumption;

    private DocumentReference signed_fireman;

    private Fireman signed;



    public Report() {
    }



    @Exclude
    public Map<String, Object> toMap() {


        HashMap<String, Object> result = new HashMap<>();
       // result.put("name_owner", name_owner);

        return result;

    }


}