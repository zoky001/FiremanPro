package com.kizo.web_services.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirWebServiceResponse {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("houses")
    @Expose
    private String houses;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("photoPhotoTypeHouses")
    @Expose
    private String photoPhotoTypeHouses;
    @SerializedName("photoType")
    @Expose
    private String photoType;
    @SerializedName("post")
    @Expose
    private String post;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouses() {
        return houses;
    }

    public void setHouses(String houses) {
        this.houses = houses;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhotoPhotoTypeHouses() {
        return photoPhotoTypeHouses;
    }

    public void setPhotoPhotoTypeHouses(String photoPhotoTypeHouses) {
        this.photoPhotoTypeHouses = photoPhotoTypeHouses;
    }

    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}


