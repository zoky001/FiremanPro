package com.kizo.web_services.responses.WS_entities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostW {

    @SerializedName("postal_code")
    @Expose
    private Integer postalCode;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}