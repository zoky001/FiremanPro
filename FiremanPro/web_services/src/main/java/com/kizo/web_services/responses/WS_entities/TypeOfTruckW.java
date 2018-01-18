package com.kizo.web_services.responses.WS_entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeOfTruckW {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type_name")
    @Expose
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}