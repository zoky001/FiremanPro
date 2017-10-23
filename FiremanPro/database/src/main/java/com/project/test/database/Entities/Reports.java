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



}
