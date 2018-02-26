package com.project.test.database.firebaseEntities.report;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.project.test.database.Entities.Reports;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zoran on 23.10.2017..
 */

@IgnoreExtraProperties
public class Consumption {

    private com.project.test.database.firebaseEntities.Address address_location;
    private String id;

    private String emailTo;

    private String callerPerson;

    private boolean completed_intervention;

    private DocumentReference house;


    private Reports reports;


    public Consumption() {
    }

    public Consumption(com.project.test.database.firebaseEntities.Address address_location, String id, String emailTo, String callerPerson, boolean completed_intervention, DocumentReference house, Reports reports) {
        this.address_location = address_location;
        this.id = id;
        this.emailTo = emailTo;
        this.callerPerson = callerPerson;
        this.completed_intervention = completed_intervention;
        this.house = house;
        this.reports = reports;
    }

    @Exclude
    public Map<String, Object> toMap() {


        HashMap<String, Object> result = new HashMap<>();
        result.put("address_location", address_location.toMap());
        result.put("emailTo", emailTo);
        result.put("callerPerson", callerPerson);
        result.put("completed_intervention", completed_intervention);
        result.put("house", house);
        result.put("reports", reports);
        return result;

    }


}