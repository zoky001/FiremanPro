package com.project.test.database.Entities.fire_intervention;

import android.support.annotation.Size;

import com.project.test.database.Entities.report.Outdoor_type;
import com.project.test.database.Entities.report.Outdoor_type_Table;
import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Size_of_fire extends BaseModel {

    @PrimaryKey(autoincrement = false)
    @Column
    int id;

    @Column
    String name;

    @Column
    String description;



    @Column
    Date updated_at;
    @Column
    Date created_at;


    public Size_of_fire() {
    }

    public Size_of_fire(int id, String name, String description, Date updated_at, Date created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.updated_at = updated_at;
        this.created_at = created_at;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public static Size_of_fire getRandomType(){

        List<Size_of_fire> house = SQLite.select().from(Size_of_fire.class).queryList();


        return house.get(0);
    }
    public static Size_of_fire getByName(String name){

        Size_of_fire spreading_smoke = SQLite.select().from(Size_of_fire.class).where(Size_of_fire_Table.name.is(name)).querySingle();


        return spreading_smoke;
    }
}
