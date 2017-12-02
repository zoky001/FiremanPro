package com.project.test.database.Entities.report;

import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Photos;
import com.project.test.database.Entities.Reports;
import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Intervention_track extends BaseModel {


    @PrimaryKey(autoincrement = true)
    @Column
    int id_intervention_track;

    @Column
    String emailTo;

    @Column
    String callerPerson;


    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            House house;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
    Address location;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
    Reports reports;



    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    public Intervention_track() {
    }

    public Intervention_track(String emailTo, String callerPerson, House house, Address location, Reports reports, Date updated_at, Date created_at) {
        this.emailTo = emailTo;
        this.callerPerson = callerPerson;
        this.house = house;
        this.location = location;
        this.reports = reports;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }


    public int getId_intervention_track() {
        return id_intervention_track;
    }

    public void setId_intervention_track(int id_intervention_track) {
        this.id_intervention_track = id_intervention_track;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getCallerPerson() {
        return callerPerson;
    }

    public void setCallerPerson(String callerPerson) {
        this.callerPerson = callerPerson;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public Reports getReports() {
        return reports;
    }

    public void setReports(Reports reports) {
        this.reports = reports;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
