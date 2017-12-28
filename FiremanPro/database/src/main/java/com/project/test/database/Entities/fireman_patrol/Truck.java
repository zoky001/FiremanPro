package com.project.test.database.Entities.fireman_patrol;

import com.project.test.database.Entities.fire_intervention.Time_spread;
import com.project.test.database.MainDatabase;
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
public class Truck extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int id;

    @Column
    String name;


    @Column
    Date updated_at;
    @Column
    Date created_at;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
           Type_of_truck type_of_truck;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
          Fireman_patrol fireman_patrol;


    public Truck() {
    }

    public Truck(String name, Date updated_at, Date created_at, Type_of_truck type_of_truck, Fireman_patrol fireman_patrol) {
        this.name = name;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.type_of_truck = type_of_truck;
        this.fireman_patrol = fireman_patrol;
    }


    public Type_of_truck getType_of_truck() {
        return type_of_truck;
    }

    public void setType_of_truck(Type_of_truck type_of_truck) {
        this.type_of_truck = type_of_truck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Fireman_patrol getFireman_patrol() {
        return fireman_patrol;
    }

    public void setFireman_patrol(Fireman_patrol fireman_patrol) {
        this.fireman_patrol = fireman_patrol;
    }

    public static Truck getRandomTruck(){

        List<Truck> house = SQLite.select().from(Truck.class).queryList();


        return house.get(0);
    }



}
