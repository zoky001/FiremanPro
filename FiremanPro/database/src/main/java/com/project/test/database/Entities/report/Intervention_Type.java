package com.project.test.database.Entities.report;

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
public class Intervention_Type extends BaseModel {

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
            Sort_of_intervention sort_of_intervention;


    public Intervention_Type(String name, Date updated_at, Date created_at, Sort_of_intervention sort_of_intervention) {
        this.name = name;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.sort_of_intervention = sort_of_intervention;
    }

    public Intervention_Type(Integer ID, String name, Date updated_at, Date created_at, Sort_of_intervention sort_of_intervention) {
       this.id = ID;
        this.name = name;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.sort_of_intervention = sort_of_intervention;
        this.save();
    }

    public Intervention_Type() {
    }

    public Sort_of_intervention getSort_of_intervention() {
        return sort_of_intervention;
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


    public static Intervention_Type getRandomType(){

        List<Intervention_Type> house = SQLite.select().from(Intervention_Type.class).queryList();


        return house.get(0);
    }
}
