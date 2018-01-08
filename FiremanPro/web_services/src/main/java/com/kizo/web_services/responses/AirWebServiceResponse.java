package com.kizo.web_services.responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kizo.web_services.responses.WS_entities.HousesW;
import com.kizo.web_services.responses.WS_entities.Hydrantw;
import com.kizo.web_services.responses.WS_entities.InterventionTypeW;
import com.kizo.web_services.responses.WS_entities.OutdoorTypeW;
import com.kizo.web_services.responses.WS_entities.PatrolsW;
import com.kizo.web_services.responses.WS_entities.PhotoTypeW;
import com.kizo.web_services.responses.WS_entities.PostW;
import com.kizo.web_services.responses.WS_entities.SizeOfFireW;
import com.kizo.web_services.responses.WS_entities.SortOfInterventionW;
import com.kizo.web_services.responses.WS_entities.SpatialSpreadW;
import com.kizo.web_services.responses.WS_entities.SpreadingSmokeW;
import com.kizo.web_services.responses.WS_entities.TimeSpreadW;
import com.kizo.web_services.responses.WS_entities.TypeOfTruckW;
import com.kizo.web_services.responses.WS_entities.TypeOfUnitW;

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
    @SerializedName("sort_of_interventionWS")
    @Expose
    private List<SortOfInterventionW> sortOfInterventionWS = null;
    @SerializedName("intervention_typeWS")
    @Expose
    private List<InterventionTypeW> interventionTypeWS = null;
    @SerializedName("outdoor_typeWS")
    @Expose
    private List<OutdoorTypeW> outdoorTypeWS = null;
    @SerializedName("size_of_fireWS")
    @Expose
    private List<SizeOfFireW> sizeOfFireWS = null;
    @SerializedName("spatial_spreadWS")
    @Expose
    private List<SpatialSpreadW> spatialSpreadWS = null;
    @SerializedName("spreading_smokeWS")
    @Expose
    private List<SpreadingSmokeW> spreadingSmokeWS = null;
    @SerializedName("time_spreadWS")
    @Expose
    private List<TimeSpreadW> timeSpreadWS = null;
    @SerializedName("type_of_truckWS")
    @Expose
    private List<TypeOfTruckW> typeOfTruckWS = null;
    @SerializedName("type_of_unitWS")
    @Expose
    private List<TypeOfUnitW> typeOfUnitWS = null;
    @SerializedName("hydrants")
    @Expose
    private List<Hydrantw> hydrants = null;
    @SerializedName("patrolsWS")
    @Expose
    private List<PatrolsW> patrolsWS = null;

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

    public List<SortOfInterventionW> getSortOfInterventionWS() {
        return sortOfInterventionWS;
    }

    public void setSortOfInterventionWS(List<SortOfInterventionW> sortOfInterventionWS) {
        this.sortOfInterventionWS = sortOfInterventionWS;
    }

    public List<InterventionTypeW> getInterventionTypeWS() {
        return interventionTypeWS;
    }

    public void setInterventionTypeWS(List<InterventionTypeW> interventionTypeWS) {
        this.interventionTypeWS = interventionTypeWS;
    }

    public List<OutdoorTypeW> getOutdoorTypeWS() {
        return outdoorTypeWS;
    }

    public void setOutdoorTypeWS(List<OutdoorTypeW> outdoorTypeWS) {
        this.outdoorTypeWS = outdoorTypeWS;
    }

    public List<SizeOfFireW> getSizeOfFireWS() {
        return sizeOfFireWS;
    }

    public void setSizeOfFireWS(List<SizeOfFireW> sizeOfFireWS) {
        this.sizeOfFireWS = sizeOfFireWS;
    }

    public List<SpatialSpreadW> getSpatialSpreadWS() {
        return spatialSpreadWS;
    }

    public void setSpatialSpreadWS(List<SpatialSpreadW> spatialSpreadWS) {
        this.spatialSpreadWS = spatialSpreadWS;
    }

    public List<SpreadingSmokeW> getSpreadingSmokeWS() {
        return spreadingSmokeWS;
    }

    public void setSpreadingSmokeWS(List<SpreadingSmokeW> spreadingSmokeWS) {
        this.spreadingSmokeWS = spreadingSmokeWS;
    }

    public List<TimeSpreadW> getTimeSpreadWS() {
        return timeSpreadWS;
    }

    public void setTimeSpreadWS(List<TimeSpreadW> timeSpreadWS) {
        this.timeSpreadWS = timeSpreadWS;
    }

    public List<TypeOfTruckW> getTypeOfTruckWS() {
        return typeOfTruckWS;
    }

    public void setTypeOfTruckWS(List<TypeOfTruckW> typeOfTruckWS) {
        this.typeOfTruckWS = typeOfTruckWS;
    }

    public List<TypeOfUnitW> getTypeOfUnitWS() {
        return typeOfUnitWS;
    }

    public void setTypeOfUnitWS(List<TypeOfUnitW> typeOfUnitWS) {
        this.typeOfUnitWS = typeOfUnitWS;
    }

    public List<Hydrantw> getHydrants() {
        return hydrants;
    }

    public void setHydrants(List<Hydrantw> hydrants) {
        this.hydrants = hydrants;
    }

    public List<PatrolsW> getPatrolsWS() {
        return patrolsWS;
    }

    public void setPatrolsWS(List<PatrolsW> patrolsWS) {
        this.patrolsWS = patrolsWS;
    }

}