package com.project.test.database.Entities.report;

import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Consumption extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int id;

    @Column
    double apsorbent;
    @Column
    double automatic_ladder;

    @Column
    double co2;

    @Column
    double command_vehicle;

    @Column
    double fire_extinguisher;

    @Column
    double fire_fighter;

    @Column
    double foam;

   // @Column
   // double id2;

    @Column
    double insurance;

    @Column
    double navalVehicle;

    @Column
    double powerPumpClock;

    @Column
    double roadTankers;

    @Column
    double specialVehicle;

    @Column
    double tehnicalVehicle;

    @Column
    double transportationVehicle;


    @Column
    Date updated_at;
    @Column
    Date created_at;

    public Consumption() {
    }

    public Consumption(double apsorbent, double automatic_ladder, double co2, double command_vehicle, double fire_extinguisher, double fire_fighter, double foam,
                      // double id2,
                       double insurance, double navalVehicle, double powerPumpClock, double roadTankers, double specialVehicle, double tehnicalVehicle, double transportationVehicle, Date updated_at, Date created_at) {
        this.apsorbent = apsorbent;
        this.automatic_ladder = automatic_ladder;
        this.co2 = co2;
        this.command_vehicle = command_vehicle;
        this.fire_extinguisher = fire_extinguisher;
        this.fire_fighter = fire_fighter;
        this.foam = foam;
    //    this.id2 = id2;
        this.insurance = insurance;
        this.navalVehicle = navalVehicle;
        this.powerPumpClock = powerPumpClock;
        this.roadTankers = roadTankers;
        this.specialVehicle = specialVehicle;
        this.tehnicalVehicle = tehnicalVehicle;
        this.transportationVehicle = transportationVehicle;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public double getApsorbent() {
        return apsorbent;
    }

    public void setApsorbent(double apsorbent) {
        this.apsorbent = apsorbent;
    }

    public double getAutomatic_ladder() {
        return automatic_ladder;
    }

    public void setAutomatic_ladder(double automatic_ladder) {
        this.automatic_ladder = automatic_ladder;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getCommand_vehicle() {
        return command_vehicle;
    }

    public void setCommand_vehicle(double command_vehicle) {
        this.command_vehicle = command_vehicle;
    }

    public double getFire_extinguisher() {
        return fire_extinguisher;
    }

    public void setFire_extinguisher(double fire_extinguisher) {
        this.fire_extinguisher = fire_extinguisher;
    }

    public double getFire_fighter() {
        return fire_fighter;
    }

    public void setFire_fighter(double fire_fighter) {
        this.fire_fighter = fire_fighter;
    }

    public double getFoam() {
        return foam;
    }

    public void setFoam(double foam) {
        this.foam = foam;
    }

 //  public double getId2() { return id2; }

 //   public void setId2(double id2) { this.id2 = id2;}


    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public double getNavalVehicle() {
        return navalVehicle;
    }

    public void setNavalVehicle(double navalVehicle) {
        this.navalVehicle = navalVehicle;
    }

    public double getPowerPumpClock() {
        return powerPumpClock;
    }

    public void setPowerPumpClock(double powerPumpClock) {
        this.powerPumpClock = powerPumpClock;
    }

    public double getRoadTankers() {
        return roadTankers;
    }

    public void setRoadTankers(double roadTankers) {
        this.roadTankers = roadTankers;
    }

    public double getSpecialVehicle() {
        return specialVehicle;
    }

    public void setSpecialVehicle(double specialVehicle) {
        this.specialVehicle = specialVehicle;
    }

    public double getTehnicalVehicle() {
        return tehnicalVehicle;
    }

    public void setTehnicalVehicle(double tehnicalVehicle) {
        this.tehnicalVehicle = tehnicalVehicle;
    }

    public double getTransportationVehicle() {
        return transportationVehicle;
    }

    public void setTransportationVehicle(double transportationVehicle) {
        this.transportationVehicle = transportationVehicle;
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
