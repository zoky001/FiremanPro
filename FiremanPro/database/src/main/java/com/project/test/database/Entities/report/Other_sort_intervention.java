package com.project.test.database.Entities.report;

import com.project.test.database.Entities.report.Intervention_Type;
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
public class Other_sort_intervention extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int ID;


    @Column
    Date updated_at;
    @Column
    Date created_at;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Intervention_Type intervention_type;

    public Other_sort_intervention() {
    }

    public Other_sort_intervention(Date updated_at, Date created_at, Intervention_Type intervention_type) {
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.intervention_type = intervention_type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public Intervention_Type getIntervention_type() {
        return intervention_type;
    }

    public void setIntervention_type(Intervention_Type intervention_type) {
        this.intervention_type = intervention_type;
    }
}
