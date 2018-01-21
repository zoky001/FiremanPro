package com.project.test.database.Entities;

import com.project.test.database.Entities.fire_intervention.Fire_intervention;
import com.project.test.database.Entities.fire_intervention.Fire_intervention_Table;
import com.project.test.database.Entities.fire_intervention.Spatial_spread;
import com.project.test.database.Entities.fire_intervention.Size_of_fire;
import com.project.test.database.Entities.fire_intervention.Spreading_smoke;
import com.project.test.database.Entities.fire_intervention.Tehnical_intervention;
import com.project.test.database.Entities.fire_intervention.Time_spread;
import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.fireman_patrol.Fireman_Table;
import com.project.test.database.Entities.fire_intervention.Tehnical_intervention;
import com.project.test.database.Entities.fire_intervention.Tehnical_intervention_Table;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Truck;
import com.project.test.database.Entities.report.Consumption;
import com.project.test.database.Entities.report.Intervention_Type;
import com.project.test.database.Entities.report.Other_sort_intervention;
import com.project.test.database.Entities.report.Outdoor_type;
import com.project.test.database.Entities.report.Report_fireman;
import com.project.test.database.Entities.report.Report_fireman_Table;
import com.project.test.database.Entities.report.Report_truck_patrol;
import com.project.test.database.Entities.report.Report_truck_patrol_Table;
import com.project.test.database.Entities.report.Other_sort_intervention_Table;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.MainDatabase;
import com.project.test.database.controllers.report.Types_all_Controller;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

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

    Fireman signed;


    Tehnical_intervention NEWtehnical_intervention;
    Other_sort_intervention NEWother_sort_intervention;

    public Reports() {
    }


    public Reports(java.util.Date time_call_received, java.util.Date time_intervention_start, java.util.Date time_arrival_intervention, java.util.Date time_intervention_ended, double water_m3, double foam_l, double powden_kg, double co2_kg, String description, double surface_m2, double superficies_ha, String help, double mehanization_type, double mehanization_hour, java.util.Date updated_at, java.util.Date created_at, Consumption consumption, Sort_of_intervention sort_of_intervention, Fireman signed) {
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

        this.signed = signed;
    }

    public Sort_of_intervention getSort_of_intervention() {
        return sort_of_intervention;
    }

    public void setSort_of_intervention(Sort_of_intervention sort_of_intervention) {
        this.sort_of_intervention = sort_of_intervention;
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


    public void addConsumption(double apsorbent, double automatic_ladder, double co2, double command_vehicle, double fire_extinguisher,
                               double fire_fighter, double foam,// double id2,
                               double insurance, double navalVehicle, double powerPumpClock,
                               double roadTankers, double specialVehicle, double tehnicalVehicle, double transportationVehicle) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


        consumption = new Consumption( apsorbent,  automatic_ladder, co2, command_vehicle,  fire_extinguisher,fire_fighter,  foam,  // id2,
                  insurance,  navalVehicle,  powerPumpClock,  roadTankers,  specialVehicle,  tehnicalVehicle,  transportationVehicle, CurrentDate, CurrentDate);


       // consumption.save();

        this.consumption = consumption;
    }


    public void addFireIntervention(Intervention_Type intervention_type){

        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
        Fire_intervention fire_intervention = new Fire_intervention(intervention_type, this, CurrentDate, CurrentDate);
        fire_intervention.save();
    }

    public void addFireInterventionDetails(Date localization, Date fireExtinguished, int destroyed_space, boolean repeated, Spreading_smoke spreading_smoke, Spatial_spread spatial_spread, Time_spread time_spread, Outdoor_type outdoor_type, Size_of_fire size_of_fire, Intervention_Type intervention_type) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        Fire_intervention fire_intervention = new Fire_intervention(localization,fireExtinguished,destroyed_space,repeated,CurrentDate,CurrentDate,spreading_smoke, spatial_spread,time_spread,outdoor_type,size_of_fire,intervention_type,this);
        // fire_intervention.save();

    }

    public void addFireInterventionDetails(Date localization, Date fireExtinguished, int destroyed_space, boolean repeated, Spreading_smoke spreading_smoke, Spatial_spread spatial_spread, Time_spread time_spread, Outdoor_type outdoor_type, Size_of_fire size_of_fire) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
        this.getFireInterventionDetails().addDetails(localization,fireExtinguished,destroyed_space,repeated,spreading_smoke, spatial_spread,time_spread,outdoor_type,size_of_fire);
    }

    public void saveFireInterventionDetails(){

        this.getFireInterventionDetails().save();
    }

    public void addTehnicalInterventionDetails(Intervention_Type intervention_type) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        NEWtehnical_intervention = new Tehnical_intervention(CurrentDate,CurrentDate,intervention_type,this);
    }


    public void saveTehnicalInterventionDetails(){
        NEWtehnical_intervention.save();
    }

    public void addOtherInterventionDetails(Intervention_Type intervention_type) {

        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());


        NEWother_sort_intervention = new Other_sort_intervention(CurrentDate,CurrentDate,intervention_type,this);
    }


    public void saveOtherInterventionDetails(){
        NEWother_sort_intervention.save();
    }

    public void addFiremanToIntervention(Fireman fireman) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        Report_fireman report_fireman = new Report_fireman(fireman, this, CurrentDate, CurrentDate);
        report_fireman.save();
    }

    public void addFiremanPatrolandTruck(int numberOfFireman, double water, double foam, double powder, double co2, double km, double hours, Truck truck, Fireman_patrol fireman_patrol) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
        Report_truck_patrol report_truck_patrol = new Report_truck_patrol(numberOfFireman, water, foam, powder, co2, km, hours, truck, fireman_patrol, this, CurrentDate, CurrentDate);
        report_truck_patrol.save();
    }

    public void addFiremanSignedToIntervention(Fireman fireman) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        this.signed = fireman;
        this.save();
    }

    public Fire_intervention getFireInterventionDetails() {

        List<Fire_intervention> house = SQLite.select().from(Fire_intervention.class).where(Fire_intervention_Table.report_id.is(this.id)).queryList();


        return house.get(0);
    }


    public Tehnical_intervention getTehnicalInterventionDetails(){
        Types_all_Controller types_all_controller = new Types_all_Controller();

        try{
            if (getSort_of_intervention().getId() == types_all_controller.get_TRHNICAL_Sort_of_intervention().getId())
            {
                return  SQLite.select().from(Tehnical_intervention.class).where(Tehnical_intervention_Table.report_id.is(this.id)).queryList().get(0);
            }
            else {
                return null;
            }


        }catch (Exception e){

            System.out.println("EXCEPTION: " + e.getMessage());
            return null;

        }



    }

    public Other_sort_intervention getOtherInterventionDetails(){
        Types_all_Controller types_all_controller = new Types_all_Controller();

        try{
            if (getSort_of_intervention().getId() == types_all_controller.get_OTHER_Sort_of_intervention().getId())
            {
                return  SQLite.select().from(Other_sort_intervention.class).where(Other_sort_intervention_Table.report_id.is(this.id)).queryList().get(0);
            }
            else {
                return null;
            }


        }catch (Exception e){

            System.out.println("EXCEPTION: " + e.getMessage());
            return null;

        }

    }

    public List<Report_truck_patrol> getTrucksAndPatrols(){


        List<Report_truck_patrol> house = SQLite.select().from(Report_truck_patrol.class).where(Report_truck_patrol_Table.reports_id.is(this.id)).queryList();


        return house;
    }

    public List<Report_fireman> getFiremans() {

        List<Report_fireman> house = SQLite.select().from(Report_fireman.class).where(Report_fireman_Table.reports_id.is(this.id)).queryList();


        return house;
    }


}
