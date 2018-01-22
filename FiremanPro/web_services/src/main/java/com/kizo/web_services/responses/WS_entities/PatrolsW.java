package com.kizo.web_services.responses.WS_entities;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatrolsW {

    @SerializedName("PATROL_ID")
    @Expose
    private Integer pATROLID;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("type_of_units_id")
    @Expose
    private Integer typeOfUnitsId;
    @SerializedName("fireman_patrol_id")
    @Expose
    private Object firemanPatrolId;
    @SerializedName("apsorbent")
    @Expose
    private Object apsorbent;
    @SerializedName("automatic_Ladder")
    @Expose
    private Object automaticLadder;
    @SerializedName("co2")
    @Expose
    private Object co2;
    @SerializedName("command_Vehicle")
    @Expose
    private Object commandVehicle;
    @SerializedName("fire_extinguisher")
    @Expose
    private Object fireExtinguisher;
    @SerializedName("fire_fighter")
    @Expose
    private Object fireFighter;
    @SerializedName("foam")
    @Expose
    private Object foam;
    @SerializedName("insurance")
    @Expose
    private Object insurance;
    @SerializedName("naval_vehicle")
    @Expose
    private Object navalVehicle;
    @SerializedName("power_pump_clock")
    @Expose
    private Object powerPumpClock;
    @SerializedName("road_tankers")
    @Expose
    private Object roadTankers;
    @SerializedName("special_vehicle")
    @Expose
    private Object specialVehicle;
    @SerializedName("tehnical_vehicle")
    @Expose
    private Object tehnicalVehicle;
    @SerializedName("transportation_vehicle")
    @Expose
    private Object transportationVehicle;
    @SerializedName("firemans")
    @Expose
    private List<Fireman> firemans = null;
    @SerializedName("trucks")
    @Expose
    private List<Truck> trucks = null;

    public Integer getPATROLID() {
        return pATROLID;
    }

    public void setPATROLID(Integer pATROLID) {
        this.pATROLID = pATROLID;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getTypeOfUnitsId() {
        return typeOfUnitsId;
    }

    public void setTypeOfUnitsId(Integer typeOfUnitsId) {
        this.typeOfUnitsId = typeOfUnitsId;
    }

    public Object getFiremanPatrolId() {
        return firemanPatrolId;
    }

    public void setFiremanPatrolId(Object firemanPatrolId) {
        this.firemanPatrolId = firemanPatrolId;
    }

    public Object getApsorbent() {
        return apsorbent;
    }

    public void setApsorbent(Object apsorbent) {
        this.apsorbent = apsorbent;
    }

    public Object getAutomaticLadder() {
        return automaticLadder;
    }

    public void setAutomaticLadder(Object automaticLadder) {
        this.automaticLadder = automaticLadder;
    }

    public Object getCo2() {
        return co2;
    }

    public void setCo2(Object co2) {
        this.co2 = co2;
    }

    public Object getCommandVehicle() {
        return commandVehicle;
    }

    public void setCommandVehicle(Object commandVehicle) {
        this.commandVehicle = commandVehicle;
    }

    public Object getFireExtinguisher() {
        return fireExtinguisher;
    }

    public void setFireExtinguisher(Object fireExtinguisher) {
        this.fireExtinguisher = fireExtinguisher;
    }

    public Object getFireFighter() {
        return fireFighter;
    }

    public void setFireFighter(Object fireFighter) {
        this.fireFighter = fireFighter;
    }

    public Object getFoam() {
        return foam;
    }

    public void setFoam(Object foam) {
        this.foam = foam;
    }

    public Object getInsurance() {
        return insurance;
    }

    public void setInsurance(Object insurance) {
        this.insurance = insurance;
    }

    public Object getNavalVehicle() {
        return navalVehicle;
    }

    public void setNavalVehicle(Object navalVehicle) {
        this.navalVehicle = navalVehicle;
    }

    public Object getPowerPumpClock() {
        return powerPumpClock;
    }

    public void setPowerPumpClock(Object powerPumpClock) {
        this.powerPumpClock = powerPumpClock;
    }

    public Object getRoadTankers() {
        return roadTankers;
    }

    public void setRoadTankers(Object roadTankers) {
        this.roadTankers = roadTankers;
    }

    public Object getSpecialVehicle() {
        return specialVehicle;
    }

    public void setSpecialVehicle(Object specialVehicle) {
        this.specialVehicle = specialVehicle;
    }

    public Object getTehnicalVehicle() {
        return tehnicalVehicle;
    }

    public void setTehnicalVehicle(Object tehnicalVehicle) {
        this.tehnicalVehicle = tehnicalVehicle;
    }

    public Object getTransportationVehicle() {
        return transportationVehicle;
    }

    public void setTransportationVehicle(Object transportationVehicle) {
        this.transportationVehicle = transportationVehicle;
    }

    public List<Fireman> getFiremans() {
        return firemans;
    }

    public void setFiremans(List<Fireman> firemans) {
        this.firemans = firemans;
    }

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<Truck> trucks) {
        this.trucks = trucks;
    }

}