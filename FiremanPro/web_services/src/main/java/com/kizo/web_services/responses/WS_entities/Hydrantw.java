package com.kizo.web_services.responses.WS_entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hydrantw {

    @SerializedName("hydrantID")
    @Expose
    private Integer hydrantID;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("type_of_hydrant")
    @Expose
    private String typeOfHydrant;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("streetName")
    @Expose
    private String streetName;
    @SerializedName("streetNumber")
    @Expose
    private String streetNumber;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("post_id")
    @Expose
    private Integer postId;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;

    public Integer getHydrantID() {
        return hydrantID;
    }

    public void setHydrantID(Integer hydrantID) {
        this.hydrantID = hydrantID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getTypeOfHydrant() {
        return typeOfHydrant;
    }

    public void setTypeOfHydrant(String typeOfHydrant) {
        this.typeOfHydrant = typeOfHydrant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}