package com.project.test.database.Entities.fire_intervention;

import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.report.Intervention_Type;
import com.project.test.database.Entities.report.Other_sort_intervention;
import com.project.test.database.Entities.report.Outdoor_type;
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
public class Fire_intervention extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int id;

    @Column
    Date localization;

    @Column
    Date fireExtinguished;

    @Column
    int destroyed_space;

    @Column
    boolean repeated;


    @Column
    Date updated_at;
    @Column
    Date created_at;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
           Spreading_smoke spreading_smoke;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Sepatial_spread sepatial_spread;
    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Time_spread time_spread;
    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Outdoor_type outdoor_type;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Size_of_fire size_of_fire;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Intervention_Type intervention_type;

    @ForeignKey(saveForeignKeyModel = true) //on update cascade
    Reports report;








    public Fire_intervention() {


    }

    public Fire_intervention(Date localization, Date fireExtinguished, int destroyed_space, boolean repeated, Date updated_at, Date created_at, Spreading_smoke spreading_smoke, Sepatial_spread sepatial_spread, Time_spread time_spread, Outdoor_type outdoor_type, Size_of_fire size_of_fire, Intervention_Type intervention_type, Reports report) {
        this.localization = localization;
        this.fireExtinguished = fireExtinguished;
        this.destroyed_space = destroyed_space;
        this.repeated = repeated;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.spreading_smoke = spreading_smoke;
        this.sepatial_spread = sepatial_spread;
        this.time_spread = time_spread;
        this.outdoor_type = outdoor_type;
        this.size_of_fire = size_of_fire;
        this.intervention_type = intervention_type;
        this.report = report;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLocalization() {
        return localization;
    }

    public void setLocalization(Date localization) {
        this.localization = localization;
    }

    public Date getFireExtinguished() {
        return fireExtinguished;
    }

    public void setFireExtinguished(Date fireExtinguished) {
        this.fireExtinguished = fireExtinguished;
    }

    public int getDestroyed_space() {
        return destroyed_space;
    }

    public void setDestroyed_space(int destroyed_space) {
        this.destroyed_space = destroyed_space;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
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

    public Spreading_smoke getSpreading_smoke() {
        return spreading_smoke;
    }

    public void setSpreading_smoke(Spreading_smoke spreading_smoke) {
        this.spreading_smoke = spreading_smoke;
    }

    public Sepatial_spread getSepatial_spread() {
        return sepatial_spread;
    }

    public void setSepatial_spread(Sepatial_spread sepatial_spread) {
        this.sepatial_spread = sepatial_spread;
    }

    public Time_spread getTime_spread() {
        return time_spread;
    }

    public void setTime_spread(Time_spread time_spread) {
        this.time_spread = time_spread;
    }

    public Outdoor_type getOutdoor_type() {
        return outdoor_type;
    }

    public void setOutdoor_type(Outdoor_type outdoor_type) {
        this.outdoor_type = outdoor_type;
    }

    public Size_of_fire getSize_of_fire() {
        return size_of_fire;
    }

    public void setSize_of_fire(Size_of_fire size_of_fire) {
        this.size_of_fire = size_of_fire;
    }

    public Intervention_Type getIntervention_type() {
        return intervention_type;
    }

    public void setIntervention_type(Intervention_Type intervention_type) {
        this.intervention_type = intervention_type;
    }
}
