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
public class Fireman extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int id;

    @Column
    String name;

    @Column
    String surname;

    @Column
    String active;


    @Column
    Date updated_at;
    @Column
    Date created_at;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
           Fireman_patrol fireman_patrol;


    public Fireman() {
    }

    public Fireman(String name, String surname, String active, Date updated_at, Date created_at, Fireman_patrol fireman_patrol) {
        this.name = name;
        this.surname = surname;
        this.active = active;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.fireman_patrol = fireman_patrol;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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

    public static Fireman getRandomType(){

        List<Fireman> house = SQLite.select().from(Fireman.class).queryList();


        return house.get(0);
    }
    public static Fireman getFiremanbyID(Integer id){

        Fireman house = SQLite.select().from(Fireman.class).where(Fireman_Table.id.is(id)).querySingle();


        return house;
    }
}
