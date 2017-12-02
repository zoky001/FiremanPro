package com.project.test.database.Entities.report;

import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Truck;
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
public class Report_fireman extends BaseModel {


    @PrimaryKey(autoincrement = true)
    @Column
    int id_report_truck_patrol;


    @ForeignKey(saveForeignKeyModel = true) //on update cascade
    Fireman fireman;


    @ForeignKey(saveForeignKeyModel = true) //on update cascade
            Reports reports;

    @Column
    Date updated_at;
    @Column
    Date created_at;



    public Report_fireman() {
    }

    public Report_fireman(int id_report_truck_patrol, Fireman fireman, Reports reports, Date updated_at, Date created_at) {
        this.id_report_truck_patrol = id_report_truck_patrol;
        this.fireman = fireman;
        this.reports = reports;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public int getId_report_truck_patrol() {
        return id_report_truck_patrol;
    }

    public void setId_report_truck_patrol(int id_report_truck_patrol) {
        this.id_report_truck_patrol = id_report_truck_patrol;
    }

    public Fireman getFireman() {
        return fireman;
    }

    public void setFireman(Fireman fireman) {
        this.fireman = fireman;
    }

    public Reports getReports() {
        return reports;
    }

    public void setReports(Reports reports) {
        this.reports = reports;
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
