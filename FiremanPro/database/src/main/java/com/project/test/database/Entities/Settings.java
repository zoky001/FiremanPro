package com.project.test.database.Entities;

import com.project.test.database.MainDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Set;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Settings extends BaseModel {

    @PrimaryKey(autoincrement = false)
    @Column
    int id;

    @Column
    String patrolName;

    @Column
    String emailToSendReport;

    @Column
    Date lastSync;

    @Column
    String webServicesAddress;

    @Column
    Date created_at;

    @Column
    Date updated_at;

    public Settings() {


    }

    public Settings(int id, String patrolName, String emailToSendReport, Date lastSync, String webServicesAddress, Date created_at, Date updated_at) {
        this.id = id;
        this.patrolName = patrolName;
        this.emailToSendReport = emailToSendReport;
        this.lastSync = lastSync;
        this.webServicesAddress = webServicesAddress;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatrolName() {
        return patrolName;
    }

    public void setPatrolName(String patrolName) {
        this.patrolName = patrolName;
    }

    public String getEmailToSendReport() {
        return emailToSendReport;
    }

    public void setEmailToSendReport(String emailToSendReport) {
        this.emailToSendReport = emailToSendReport;
    }

    public Date getLastSync() {
        return lastSync;
    }

    public void setLastSync(Date lastSync) {
        this.lastSync = lastSync;
    }

    public String getWebServicesAddress() {
        return webServicesAddress;
    }

    public void setWebServicesAddress(String webServicesAddress) {
        this.webServicesAddress = webServicesAddress;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }


    private static Settings setDefaultSettings(){

        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
        List<Settings> settings = SQLite.select()
                .from(Settings.class)
                .queryList();//.size();

        if (settings.size() < 1) {

            Settings settings1 = new Settings(1,"DVD Križovljan Cestica", "zoky001@gmail.com",CurrentDate,"http://fireman-pro.ddns.net/FiremanPro-laravel/",CurrentDate,CurrentDate);
            settings1.save();
            return settings1;
        }
        else {
            return settings.get(0);
        }


    }

    public static Settings getSettings(){

        return setDefaultSettings();
    }
}