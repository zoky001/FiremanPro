package com.project.test.database.Entities.fireman_patrol;

import com.project.test.database.Entities.House;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Photos;
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
public class Cost_fireman_patrol extends BaseModel {


    @PrimaryKey(autoincrement = true)
    @Column
    int cost_fireman_patrol;

    @Column
    int year;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
          Costs costs;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Fireman_patrol fireman_patrol;


    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;



    public Cost_fireman_patrol() {
    }


    public Cost_fireman_patrol(int year, Costs costs, Fireman_patrol fireman_patrol, Date updated_at, Date created_at) {
        this.year = year;
        this.costs = costs;
        this.fireman_patrol = fireman_patrol;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public int getCost_fireman_patrol() {
        return cost_fireman_patrol;
    }

    public void setCost_fireman_patrol(int cost_fireman_patrol) {
        this.cost_fireman_patrol = cost_fireman_patrol;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Costs getCosts() {
        return costs;
    }

    public void setCosts(Costs costs) {
        this.costs = costs;
    }

    public Fireman_patrol getFireman_patrol() {
        return fireman_patrol;
    }

    public void setFireman_patrol(Fireman_patrol fireman_patrol) {
        this.fireman_patrol = fireman_patrol;
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
