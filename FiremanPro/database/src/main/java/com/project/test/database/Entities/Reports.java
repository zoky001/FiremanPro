package com.project.test.database.Entities;

import com.project.test.database.Entities.fire_intervention.Fire_intervention;
import com.project.test.database.Entities.fire_intervention.Tehnical_intervention;
import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.report.Consumption;
import com.project.test.database.Entities.report.Other_sort_intervention;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.w3c.dom.Text;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Reports extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int id;


    @Column
    java.util.Date time_call_received;

    @Column
    java.util.Date time_intervention_start;

    @Column
    java.util.Date time_arrival_intervention;

    @Column
    java.util.Date time_intervention_ended;


    @Column
    double water_m3;

    @Column
    double foam_l;

    @Column
    double powden_kg;

    @Column
    double co2_kg;

    @Column
    String description;

    @Column
    double surface_m2;

    @Column
    double superficies_ha;

    @Column
    String help;


    @Column
    double mehanization_type;

    @Column
    double mehanization_hour;





    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    //address
    @ForeignKey(saveForeignKeyModel = true)
    Consumption consumption;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Sort_of_intervention sort_of_intervention;


    @ForeignKey(saveForeignKeyModel = true) //on update cascade
    Fire_intervention fire_intervention;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Other_sort_intervention other_sort_intervention;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Tehnical_intervention tehnical_intervention;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
    Fireman signed;




    public Reports() {
    }


    public Reports(java.util.Date time_call_received, java.util.Date time_intervention_start, java.util.Date time_arrival_intervention, java.util.Date time_intervention_ended, double water_m3, double foam_l, double powden_kg, double co2_kg, String description, double surface_m2, double superficies_ha, String help, double mehanization_type, double mehanization_hour, java.util.Date updated_at, java.util.Date created_at, Consumption consumption, Sort_of_intervention sort_of_intervention, Fire_intervention fire_intervention, Other_sort_intervention other_sort_intervention, Tehnical_intervention tehnical_intervention, Fireman signed) {
        this.time_call_received = time_call_received;
        this.time_intervention_start = time_intervention_start;
        this.time_arrival_intervention = time_arrival_intervention;
        this.time_intervention_ended = time_intervention_ended;
        this.water_m3 = water_m3;
        this.foam_l = foam_l;
        this.powden_kg = powden_kg;
        this.co2_kg = co2_kg;
        this.description = description;
        this.surface_m2 = surface_m2;
        this.superficies_ha = superficies_ha;
        this.help = help;
        this.mehanization_type = mehanization_type;
        this.mehanization_hour = mehanization_hour;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.consumption = consumption;
        this.sort_of_intervention = sort_of_intervention;
        this.fire_intervention = fire_intervention;
        this.other_sort_intervention = other_sort_intervention;
        this.tehnical_intervention = tehnical_intervention;
        this.signed = signed;
    }

    public Sort_of_intervention getSort_of_intervention() {
        return sort_of_intervention;
    }

    public void setSort_of_intervention(Sort_of_intervention sort_of_intervention) {
        this.sort_of_intervention = sort_of_intervention;
    }

    public Fire_intervention getFire_intervention() {
        return fire_intervention;
    }

    public void setFire_intervention(Fire_intervention fire_intervention) {
        this.fire_intervention = fire_intervention;
    }

    public Other_sort_intervention getOther_sort_intervention() {
        return other_sort_intervention;
    }

    public void setOther_sort_intervention(Other_sort_intervention other_sort_intervention) {
        this.other_sort_intervention = other_sort_intervention;
    }

    public Tehnical_intervention getTehnical_intervention() {
        return tehnical_intervention;
    }

    public void setTehnical_intervention(Tehnical_intervention tehnical_intervention) {
        this.tehnical_intervention = tehnical_intervention;
    }

    public Fireman getSigned() {
        return signed;
    }

    public void setSigned(Fireman signed) {
        this.signed = signed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.util.Date getTime_call_received() {
        return time_call_received;
    }

    public void setTime_call_received(java.util.Date time_call_received) {
        this.time_call_received = time_call_received;
    }

    public java.util.Date getTime_intervention_start() {
        return time_intervention_start;
    }

    public void setTime_intervention_start(java.util.Date time_intervention_start) {
        this.time_intervention_start = time_intervention_start;
    }

    public java.util.Date getTime_arrival_intervention() {
        return time_arrival_intervention;
    }

    public void setTime_arrival_intervention(java.util.Date time_arrival_intervention) {
        this.time_arrival_intervention = time_arrival_intervention;
    }

    public java.util.Date getTime_intervention_ended() {
        return time_intervention_ended;
    }

    public void setTime_intervention_ended(java.util.Date time_intervention_ended) {
        this.time_intervention_ended = time_intervention_ended;
    }

    public double getWater_m3() {
        return water_m3;
    }

    public void setWater_m3(double water_m3) {
        this.water_m3 = water_m3;
    }

    public double getFoam_l() {
        return foam_l;
    }

    public void setFoam_l(double foam_l) {
        this.foam_l = foam_l;
    }

    public double getPowden_kg() {
        return powden_kg;
    }

    public void setPowden_kg(double powden_kg) {
        this.powden_kg = powden_kg;
    }

    public double getCo2_kg() {
        return co2_kg;
    }

    public void setCo2_kg(double co2_kg) {
        this.co2_kg = co2_kg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSurface_m2() {
        return surface_m2;
    }

    public void setSurface_m2(double surface_m2) {
        this.surface_m2 = surface_m2;
    }

    public double getSuperficies_ha() {
        return superficies_ha;
    }

    public void setSuperficies_ha(double superficies_ha) {
        this.superficies_ha = superficies_ha;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public double getMehanization_type() {
        return mehanization_type;
    }

    public void setMehanization_type(double mehanization_type) {
        this.mehanization_type = mehanization_type;
    }

    public double getMehanization_hour() {
        return mehanization_hour;
    }

    public void setMehanization_hour(double mehanization_hour) {
        this.mehanization_hour = mehanization_hour;
    }

    public java.util.Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(java.util.Date updated_at) {
        this.updated_at = updated_at;
    }

    public java.util.Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(java.util.Date created_at) {
        this.created_at = created_at;
    }

    public Consumption getConsumption() {
        return consumption;
    }

    public void setConsumption(Consumption consumption) {
        this.consumption = consumption;
    }
}
