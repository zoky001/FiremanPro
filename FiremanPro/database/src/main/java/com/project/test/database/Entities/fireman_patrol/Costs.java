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
}
