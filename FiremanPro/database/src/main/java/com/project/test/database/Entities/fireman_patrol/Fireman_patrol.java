package com.project.test.database.Entities.fireman_patrol;

import android.widget.TableRow;

import com.project.test.database.Entities.Post;
import com.project.test.database.Entities.fire_intervention.Time_spread;
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
public class Fireman_patrol extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int ID;

    @Column
    String name;


    @Column
    Date updated_at;
    @Column
    Date created_at;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Type_of_unit type_of_unit;


    public Fireman_patrol() {
    }

    public Fireman_patrol(String name, Date updated_at, Date created_at, Type_of_unit type_of_unit) {
        this.name = name;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.type_of_unit = type_of_unit;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Type_of_unit getType_of_unit() {
        return type_of_unit;
    }

    public void setType_of_unit(Type_of_unit type_of_unit) {
        this.type_of_unit = type_of_unit;
    }


    public Fireman addFiremenToPatrol(String name, String surname, String active) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        Fireman fireman = new Fireman(name, surname, active, CurrentDate, CurrentDate, this);
        fireman.save();
        return fireman;

    }

    public Truck add_SPECIAL_TruckToPatrol(String name) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        Types_all_Controller types_all_controller = new Types_all_Controller();
        Truck truck = new Truck(name, CurrentDate, CurrentDate, types_all_controller.get_SPECIAL_vehicle_type_of_truck(), this);
        truck.save();

        return truck;

    }

    public Truck add_NAVAL_TruckToPatrol(String name) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        Types_all_Controller types_all_controller = new Types_all_Controller();
        Truck truck = new Truck(name, CurrentDate, CurrentDate, types_all_controller.get_naval_vehicle_type_of_truck(), this);
        truck.save();

        return truck;

    }

    public Truck add_TRANSPORT_TruckToPatrol(String name) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        Types_all_Controller types_all_controller = new Types_all_Controller();
        Truck truck = new Truck(name, CurrentDate, CurrentDate, types_all_controller.get_transportation_vehicle_type_of_truck(), this);
        truck.save();

        return truck;

    }


    public void addNewCosts(double apsorbent, double automatic_Ladder, double co2, double command_Vehicle, double fire_extinguisher, double fire_fighter, double foam, double insurance, double naval_vehicle, double power_pump_clock, double road_tankers, double special_vehicle, double tehnical_vehicle, double transportation_vehicle) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        Costs costs = new Costs(apsorbent, automatic_Ladder, co2, command_Vehicle, fire_extinguisher, fire_fighter, foam
                , insurance, naval_vehicle, power_pump_clock, road_tankers, special_vehicle, tehnical_vehicle, transportation_vehicle, CurrentDate, CurrentDate);
        costs.save();

        Cost_fireman_patrol cost_fireman_patrol = new Cost_fireman_patrol(CurrentDate, costs, this, CurrentDate, CurrentDate);
        cost_fireman_patrol.save();

    }

    public static Fireman_patrol getRandomPatrol() {

        List<Fireman_patrol> house = SQLite.select().from(Fireman_patrol.class).queryList();


        return house.get(0);
    }


    public List<Truck> getAllTrucks() {
        List<Truck> house = SQLite.select().from(Truck.class).where(Truck_Table.fireman_patrol_ID.is(this.getID())).queryList();


        return house;

    }

    public static Fireman_patrol getPatrolByName(String name) {

        return SQLite.select().from(Fireman_patrol.class).where(Fireman_patrol_Table.name.is(name)).querySingle();
    }

    public Truck getTruckByName(String name) {
        List<Truck> trucks = getAllTrucks();

        for (Truck t :
                trucks) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;

    }
}
