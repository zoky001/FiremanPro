package com.kizo.web_services.responses.WS_entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Truck {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type_of_trucks_id")
    @Expose
    private Integer typeOfTrucksId;
    @SerializedName("fireman_patrols_id")
    @Expose
    private Integer firemanPatrolsId;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeOfTrucksId() {
        return typeOfTrucksId;
    }

    public void setTypeOfTrucksId(Integer typeOfTrucksId) {
        this.typeOfTrucksId = typeOfTrucksId;
    }

    public Integer getFiremanPatrolsId() {
        return firemanPatrolsId;
    }

    public void setFiremanPatrolsId(Integer firemanPatrolsId) {
        this.firemanPatrolsId = firemanPatrolsId;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

}