package com.kizo.web_services.responses.WS_entities;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Fireman {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fireman_patrols_id")
    @Expose
    private Integer firemanPatrolsId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("active")
    @Expose
    private String active;
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

    public Integer getFiremanPatrolsId() {
        return firemanPatrolsId;
    }

    public void setFiremanPatrolsId(Integer firemanPatrolsId) {
        this.firemanPatrolsId = firemanPatrolsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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