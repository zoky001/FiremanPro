package com.project.air.firemanpro;

/**
 * Created by Nikol on 21.10.2017..
 */

public class DbResult {

    private String Name;
    private String SurName;
    private String Adress;

    public DbResult(String name, String surName, String adress) {
        Name = name;
        SurName = surName;
        Adress = adress;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurName() {
        return SurName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }
}
