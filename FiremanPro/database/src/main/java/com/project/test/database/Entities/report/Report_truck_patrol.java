package com.project.test.database.Entities.report;

import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Truck;
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
public class Report_truck_patrol extends BaseModel {


    @PrimaryKey(autoincrement = true)
    @Column
    int id_report_truck_patrol;

    @Column
    double km;

    @Column
    double hours;

    @Column
    int numberOfFireman;

    @Column
    double water;

    @Column
    double foam;

    @Column
    double powder;

    @Column
    double co2;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Truck truck;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Fireman_patrol fireman_patrol;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Reports reports;

    @Column
    Date updated_at;
    @Column
    Date created_at;


    public Report_truck_patrol() {
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getFoam() {
        return foam;
    }

    public void setFoam(double foam) {
        this.foam = foam;
    }

    public double getPowder() {
        return powder;
    }

    public void setPowder(double powder) {
        this.powder = powder;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public Report_truck_patrol(int numberFireman, double water, double foam, double powder, double co2, double km, double hours, Truck truck, Fireman_patrol fireman_patrol, Reports reports, Date updated_at, Date created_at) {
        this.numberOfFireman = numberFireman;
        this.water = water;
        this.foam = foam;
        this.powder = powder;
        this.co2 = co2;
        this.km = km;
        this.hours = hours;
        this.truck = truck;
        this.fireman_patrol = fireman_patrol;
        this.reports = reports;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public int getNumberOfFireman() {
        return numberOfFireman;
    }

    public void setNumberOfFireman(int numberOfFireman) {
        this.numberOfFireman = numberOfFireman;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Fireman_patrol getFireman_patrol() {
        return fireman_patrol;
    }

    public void setFireman_patrol(Fireman_patrol fireman_patrol) {
        this.fireman_patrol = fireman_patrol;
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
