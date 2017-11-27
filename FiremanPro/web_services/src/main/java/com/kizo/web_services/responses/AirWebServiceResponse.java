package com.kizo.web_services.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kizo.web_services.responses.WS_entities.HousesW;
import com.kizo.web_services.responses.WS_entities.PhotoTypeW;
import com.kizo.web_services.responses.WS_entities.PostW;

public class AirWebServiceResponse {

    @SerializedName("housesWS")
    @Expose
    private List<HousesW> housesWS = null;
    @SerializedName("photoTypeWS")
    @Expose
    private List<PhotoTypeW> photoTypeWS = null;
    @SerializedName("postWS")
    @Expose
    private List<PostW> postWS = null;

    public List<HousesW> getHousesWS() {
        return housesWS;
    }

    public void setHousesWS(List<HousesW> housesWS) {
        this.housesWS = housesWS;
    }

    public List<PhotoTypeW> getPhotoTypeWS() {
        return photoTypeWS;
    }

    public void setPhotoTypeWS(List<PhotoTypeW> photoTypeWS) {
        this.photoTypeWS = photoTypeWS;
    }

    public List<PostW> getPostWS() {
        return postWS;
    }

    public void setPostWS(List<PostW> postWS) {
        this.postWS = postWS;
    }

}

