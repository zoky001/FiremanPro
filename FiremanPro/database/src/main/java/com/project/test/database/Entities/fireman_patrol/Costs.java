package com.project.test.database.Entities.fireman_patrol;

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
public class Costs extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int id;

    @Column
    double apsorbent;

    @Column
    double automatic_Ladder;

    @Column
    double co2;

    @Column
    double command_Vehicle;

    @Column
    double fire_extinguisher;

    @Column
    double fire_fighter;

    @Column
    double foam;

    @Column
    double insurance;

    @Column
    double naval_vehicle;

    @Column
    double power_pump_clock;

    @Column
    double road_tankers;

    @Column
    double special_vehicle;

    @Column
    double tehnical_vehicle;

    @Column
    double transportation_vehicle;


    @Column
    Date updated_at;
    @Column
    Date created_at;


    public Costs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Costs(double apsorbent, double automatic_Ladder, double co2, double command_Vehicle, double fire_extinguisher, double fire_fighter, double foam, double insurance, double naval_vehicle, double power_pump_clock, double road_tankers, double special_vehicle, double tehnical_vehicle, double transportation_vehicle, Date updated_at, Date created_at) {
        this.apsorbent = apsorbent;
        this.automatic_Ladder = automatic_Ladder;
        this.co2 = co2;
        this.command_Vehicle = command_Vehicle;
        this.fire_extinguisher = fire_extinguisher;
        this.fire_fighter = fire_fighter;
        this.foam = foam;
        this.insurance = insurance;
        this.naval_vehicle = naval_vehicle;
        this.power_pump_clock = power_pump_clock;
        this.road_tankers = road_tankers;
        this.special_vehicle = special_vehicle;
        this.tehnical_vehicle = tehnical_vehicle;
        this.transportation_vehicle = transportation_vehicle;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public double getApsorbent() {
        return apsorbent;
    }

    public void setApsorbent(double apsorbent) {
        this.apsorbent = apsorbent;
    }

    public double getAutomatic_Ladder() {
        return automatic_Ladder;
    }

    public void setAutomatic_Ladder(double automatic_Ladder) {
        this.automatic_Ladder = automatic_Ladder;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getCommand_Vehicle() {
        return command_Vehicle;
    }

    public void setCommand_Vehicle(double command_Vehicle) {
        this.command_Vehicle = command_Vehicle;
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

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public double getNaval_vehicle() {
        return naval_vehicle;
    }

    public void setNaval_vehicle(double naval_vehicle) {
        this.naval_vehicle = naval_vehicle;
    }

    public double getPower_pump_clock() {
        return power_pump_clock;
    }

    public void setPower_pump_clock(double power_pump_clock) {
        this.power_pump_clock = power_pump_clock;
    }

    public double getRoad_tankers() {
        return road_tankers;
    }

    public void setRoad_tankers(double road_tankers) {
        this.road_tankers = road_tankers;
    }

    public double getSpecial_vehicle() {
        return special_vehicle;
    }

    public void setSpecial_vehicle(double special_vehicle) {
        this.special_vehicle = special_vehicle;
    }

    public double getTehnical_vehicle() {
        return tehnical_vehicle;
    }

    public void setTehnical_vehicle(double tehnical_vehicle) {
        this.tehnical_vehicle = tehnical_vehicle;
    }

    public double getTransportation_vehicle() {
        return transportation_vehicle;
    }

    public void setTransportation_vehicle(double transportation_vehicle) {
        this.transportation_vehicle = transportation_vehicle;
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
