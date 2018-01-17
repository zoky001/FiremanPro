package com.kizo.web_services.responses.WS_entities;


        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class HousesW {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address_id")
    @Expose
    private Integer addressId;
    @SerializedName("name_owner")
    @Expose
    private String nameOwner;
    @SerializedName("surname_owner")
    @Expose
    private String surnameOwner;
    @SerializedName("number_of_tenants")
    @Expose
    private Integer numberOfTenants;
    @SerializedName("number_of_floors")
    @Expose
    private Integer numberOfFloors;
    @SerializedName("list_of_floors")
    @Expose
    private String listOfFloors;
    @SerializedName("number_of_children")
    @Expose
    private Integer numberOfChildren;
    @SerializedName("year_children")
    @Expose
    private String yearChildren;
    @SerializedName("number_of_adults")
    @Expose
    private Integer numberOfAdults;
    @SerializedName("years_adults")
    @Expose
    private String yearsAdults;
    @SerializedName("number_of_powerless_and_elders")
    @Expose
    private Integer numberOfPowerlessAndElders;
    @SerializedName("years_powerless_elders")
    @Expose
    private String yearsPowerlessElders;
    @SerializedName("disability_person")
    @Expose
    private Integer disabilityPerson;
    @SerializedName("power_supply")
    @Expose
    private String powerSupply;
    @SerializedName("gas_connection")
    @Expose
    private Integer gasConnection;
    @SerializedName("type_of_heating")
    @Expose
    private String typeOfHeating;
    @SerializedName("number_of_gas_bottle")
    @Expose
    private Integer numberOfGasBottle;
    @SerializedName("type_of_roof")
    @Expose
    private String typeOfRoof;
    @SerializedName("hydrant_distance")
    @Expose
    private Integer hydrantDistance;
    @SerializedName("high_risk_object")
    @Expose
    private Integer highRiskObject;
    @SerializedName("HRO_type_of_roof")
    @Expose
    private String hROTypeOfRoof;
    @SerializedName("HRO_power_supply")
    @Expose
    private Integer hROPowerSupply;
    @SerializedName("HRO_content")
    @Expose
    private String hROContent;
    @SerializedName("HRO_animals")
    @Expose
    private Integer hROAnimals;
    @SerializedName("telNumber")
    @Expose
    private String telNumber;
    @SerializedName("mobNumber")
    @Expose
    private String mobNumber;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("house_ID")
    @Expose
    private Integer houseID;
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
    @SerializedName("slike_planova")
    @Expose
    private List<SlikePlanova> slikePlanova = null;
    @SerializedName("profilPicture")
    @Expose
    private ProfilPicture profilPocture;

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

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getSurnameOwner() {
        return surnameOwner;
    }

    public void setSurnameOwner(String surnameOwner) {
        this.surnameOwner = surnameOwner;
    }

    public Integer getNumberOfTenants() {
        return numberOfTenants;
    }

    public void setNumberOfTenants(Integer numberOfTenants) {
        this.numberOfTenants = numberOfTenants;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(Integer numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String getListOfFloors() {
        return listOfFloors;
    }

    public void setListOfFloors(String listOfFloors) {
        this.listOfFloors = listOfFloors;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getYearChildren() {
        return yearChildren;
    }

    public void setYearChildren(String yearChildren) {
        this.yearChildren = yearChildren;
    }

    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public String getYearsAdults() {
        return yearsAdults;
    }

    public void setYearsAdults(String yearsAdults) {
        this.yearsAdults = yearsAdults;
    }

    public Integer getNumberOfPowerlessAndElders() {
        return numberOfPowerlessAndElders;
    }

    public void setNumberOfPowerlessAndElders(Integer numberOfPowerlessAndElders) {
        this.numberOfPowerlessAndElders = numberOfPowerlessAndElders;
    }

    public String getYearsPowerlessElders() {
        return yearsPowerlessElders;
    }

    public void setYearsPowerlessElders(String yearsPowerlessElders) {
        this.yearsPowerlessElders = yearsPowerlessElders;
    }

    public Integer getDisabilityPerson() {
        return disabilityPerson;
    }

    public void setDisabilityPerson(Integer disabilityPerson) {
        this.disabilityPerson = disabilityPerson;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    public Integer getGasConnection() {
        return gasConnection;
    }

    public void setGasConnection(Integer gasConnection) {
        this.gasConnection = gasConnection;
    }

    public String getTypeOfHeating() {
        return typeOfHeating;
    }

    public void setTypeOfHeating(String typeOfHeating) {
        this.typeOfHeating = typeOfHeating;
    }

    public Integer getNumberOfGasBottle() {
        return numberOfGasBottle;
    }

    public void setNumberOfGasBottle(Integer numberOfGasBottle) {
        this.numberOfGasBottle = numberOfGasBottle;
    }

    public String getTypeOfRoof() {
        return typeOfRoof;
    }

    public void setTypeOfRoof(String typeOfRoof) {
        this.typeOfRoof = typeOfRoof;
    }

    public Integer getHydrantDistance() {
        return hydrantDistance;
    }

    public void setHydrantDistance(Integer hydrantDistance) {
        this.hydrantDistance = hydrantDistance;
    }

    public Integer getHighRiskObject() {
        return highRiskObject;
    }

    public void setHighRiskObject(Integer highRiskObject) {
        this.highRiskObject = highRiskObject;
    }

    public String getHROTypeOfRoof() {
        return hROTypeOfRoof;
    }

    public void setHROTypeOfRoof(String hROTypeOfRoof) {
        this.hROTypeOfRoof = hROTypeOfRoof;
    }

    public Integer getHROPowerSupply() {
        return hROPowerSupply;
    }

    public void setHROPowerSupply(Integer hROPowerSupply) {
        this.hROPowerSupply = hROPowerSupply;
    }

    public String getHROContent() {
        return hROContent;
    }

    public void setHROContent(String hROContent) {
        this.hROContent = hROContent;
    }

    public Integer getHROAnimals() {
        return hROAnimals;
    }

    public void setHROAnimals(Integer hROAnimals) {
        this.hROAnimals = hROAnimals;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
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

    public Integer getHouseID() {
        return houseID;
    }

    public void setHouseID(Integer houseID) {
        this.houseID = houseID;
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

    public List<SlikePlanova> getSlikePlanova() {
        return slikePlanova;
    }

    public void setSlikePlanova(List<SlikePlanova> slikePlanova) {
        this.slikePlanova = slikePlanova;
    }

    public ProfilPicture getProfilPocture() {
        return profilPocture;
    }

    public void setProfilPocture(ProfilPicture profilPocture) {
        this.profilPocture = profilPocture;
    }

}