package com.project.test.database.Entities;

import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.data.Blob;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.w3c.dom.Text;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Reports extends BaseModel{

    @PrimaryKey(autoincrement = true)
    @Column
    int id_report;

    @Column int houseID; //foreign key to places
    @Column String description;

    @Column Date date;
    @Column String send_email;






    @Column Date updated_at;
    @Column Date created_at;

    @Column
    @ForeignKey(tableClass = House.class)
    House house;

    public int getId_report() {
        return id_report;
    }

    public void setId_report(int id_report) {
        this.id_report = id_report;
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSend_email() {
        return send_email;
    }

    public void setSend_email(String send_email) {
        this.send_email = send_email;
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

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
