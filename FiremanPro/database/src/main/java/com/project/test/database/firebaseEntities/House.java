package com.project.test.database.firebaseEntities;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.FirebaseFirestore;
import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.House_photos_Table;
import com.project.test.database.Entities.Photos;
import com.project.test.database.MainDatabase;
import com.project.test.database.imageSaver.ImageSaver;
import com.project.test.database.interfaces.IPhoto;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zoran on 23.10.2017..
 */

@IgnoreExtraProperties
public class House {
    private String name_owner;
    private String surname_owner;
    private int number_of_tenants;
    private int number_of_floors;
    private String list_of_floors;
    private int number_of_children;
    private String year_children;
    private int number_of_adults;
    private String years_adults;
    private int number_of_powerless_and_elders;
    private String years_powerless_elders;
    private boolean disability_person;
    private String power_supply; //type of connection
    private boolean gas_connection;
    private String type_of_heating;
    private int number_of_gas_bottle;
    private String type_of_roof;
    private int hydrant_distance;
    private boolean high_risk_object;
    private String HRO_type_of_roof;
    private boolean HRO_power_supply;
    private String HRO_content;
    private boolean HRO_animals;
    private String telNumber;
    private String mobNumber;
    private com.project.test.database.firebaseEntities.Address address;
    private String profilPicUrl;
    private String profilPicStorageLocation;
    private String id;
    private List<com.project.test.database.firebaseEntities.Photos> gndPhoto;

    public House() {
    }

    public House(String name_owner, String surname_owner, int number_of_tenants, int number_of_floors, String list_of_floors, int number_of_children, String year_children, int number_of_adults, String years_adults, int number_of_powerless_and_elders, String years_powerless_elders, boolean disability_person, String power_supply, boolean gas_connection, String type_of_heating, int number_of_gas_bottle, String type_of_roof, int hydrant_distance, boolean high_risk_object, String HRO_type_of_roof, boolean HRO_power_supply, String HRO_content, boolean HRO_animals, String telNumber, String mobNumber, com.project.test.database.firebaseEntities.Address address, String profilPicUrl) {
        this.name_owner = name_owner;
        this.surname_owner = surname_owner;
        this.number_of_tenants = number_of_tenants;
        this.number_of_floors = number_of_floors;
        this.list_of_floors = list_of_floors;
        this.number_of_children = number_of_children;
        this.year_children = year_children;
        this.number_of_adults = number_of_adults;
        this.years_adults = years_adults;
        this.number_of_powerless_and_elders = number_of_powerless_and_elders;
        this.years_powerless_elders = years_powerless_elders;
        this.disability_person = disability_person;
        this.power_supply = power_supply;
        this.gas_connection = gas_connection;
        this.type_of_heating = type_of_heating;
        this.number_of_gas_bottle = number_of_gas_bottle;
        this.type_of_roof = type_of_roof;
        this.hydrant_distance = hydrant_distance;
        this.high_risk_object = high_risk_object;
        this.HRO_type_of_roof = HRO_type_of_roof;
        this.HRO_power_supply = HRO_power_supply;
        this.HRO_content = HRO_content;
        this.HRO_animals = HRO_animals;
        this.telNumber = telNumber;
        this.mobNumber = mobNumber;
        this.address = address;
        this.profilPicUrl = profilPicUrl;

    }

    public House(String name_owner, String surname_owner, int number_of_tenants, int number_of_floors, String list_of_floors, int number_of_children, String year_children, int number_of_adults, String years_adults, int number_of_powerless_and_elders, String years_powerless_elders, boolean disability_person, String power_supply, boolean gas_connection, String type_of_heating, int number_of_gas_bottle, String type_of_roof, int hydrant_distance, boolean high_risk_object, String HRO_type_of_roof, boolean HRO_power_supply, String HRO_content, boolean HRO_animals, String telNumber, String mobNumber, com.project.test.database.firebaseEntities.Address address, String profilPicUrl, String profilPicStorageLocation) {
        this.name_owner = name_owner;
        this.surname_owner = surname_owner;
        this.number_of_tenants = number_of_tenants;
        this.number_of_floors = number_of_floors;
        this.list_of_floors = list_of_floors;
        this.number_of_children = number_of_children;
        this.year_children = year_children;
        this.number_of_adults = number_of_adults;
        this.years_adults = years_adults;
        this.number_of_powerless_and_elders = number_of_powerless_and_elders;
        this.years_powerless_elders = years_powerless_elders;
        this.disability_person = disability_person;
        this.power_supply = power_supply;
        this.gas_connection = gas_connection;
        this.type_of_heating = type_of_heating;
        this.number_of_gas_bottle = number_of_gas_bottle;
        this.type_of_roof = type_of_roof;
        this.hydrant_distance = hydrant_distance;
        this.high_risk_object = high_risk_object;
        this.HRO_type_of_roof = HRO_type_of_roof;
        this.HRO_power_supply = HRO_power_supply;
        this.HRO_content = HRO_content;
        this.HRO_animals = HRO_animals;
        this.telNumber = telNumber;
        this.mobNumber = mobNumber;
        this.address = address;
        this.profilPicUrl = profilPicUrl;
        this.profilPicStorageLocation = profilPicStorageLocation;

    }

    @Exclude
    public Map<String, Object> toMap() {


        HashMap<String, Object> result = new HashMap<>();
        result.put("name_owner", name_owner);
        result.put("surname_owner", surname_owner);
        result.put("number_of_floors", number_of_floors);
        result.put("list_of_floors", list_of_floors);
        result.put("number_of_children", number_of_children);

        result.put("year_children", year_children);
        result.put("number_of_adults", number_of_adults);
        result.put("years_adults", years_adults);
        result.put("number_of_powerless_and_elders", number_of_powerless_and_elders);
        result.put("years_powerless_elders", years_powerless_elders);

        result.put("disability_person", disability_person);
        result.put("power_supply", power_supply);
        result.put("gas_connection", gas_connection);
        result.put("type_of_heating", type_of_heating);
        result.put("number_of_gas_bottle", number_of_gas_bottle);

        result.put("type_of_roof", type_of_roof);
        result.put("hydrant_distance", hydrant_distance);
        result.put("high_risk_object", high_risk_object);
        result.put("HRO_type_of_roof", HRO_type_of_roof);
        result.put("HRO_power_supply", HRO_power_supply);

        result.put("HRO_content", HRO_content);
        result.put("HRO_animals", HRO_animals);
        result.put("telNumber", telNumber);
        result.put("mobNumber", mobNumber);

        result.put("profilPicUrl", profilPicUrl);

        result.put("address", address.toMap());
        return result;

    }


    public Uri getProfilPhotos() {
        return Uri.parse(profilPicUrl);
    }


    public List<com.project.test.database.firebaseEntities.Photos> getGroundPlanPhotos() {

        return gndPhoto;
    }

    public void setSurname_owner(String surname_owner) {
        this.surname_owner = surname_owner;
    }

    public void setNumber_of_tenants(int number_of_tenants) {
        this.number_of_tenants = number_of_tenants;
    }

    public void setNumber_of_floors(int number_of_floors) {
        this.number_of_floors = number_of_floors;
    }

    public void setList_of_floors(String list_of_floors) {
        this.list_of_floors = list_of_floors;
    }

    public void setNumber_of_children(int number_of_children) {
        this.number_of_children = number_of_children;
    }

    public void setYear_children(String year_children) {
        this.year_children = year_children;
    }

    public void setNumber_of_adults(int number_of_adults) {
        this.number_of_adults = number_of_adults;
    }

    public void setYears_adults(String years_adults) {
        this.years_adults = years_adults;
    }

    public void setNumber_of_powerless_and_elders(int number_of_powerless_and_elders) {
        this.number_of_powerless_and_elders = number_of_powerless_and_elders;
    }

    public void setYears_powerless_elders(String years_powerless_elders) {
        this.years_powerless_elders = years_powerless_elders;
    }

    public void setDisability_person(boolean disability_person) {
        this.disability_person = disability_person;
    }

    public void setPower_supply(String power_supply) {
        this.power_supply = power_supply;
    }

    public void setGas_connection(boolean gas_connection) {
        this.gas_connection = gas_connection;
    }

    public void setType_of_heating(String type_of_heating) {
        this.type_of_heating = type_of_heating;
    }

    public void setNumber_of_gas_bottle(int number_of_gas_bottle) {
        this.number_of_gas_bottle = number_of_gas_bottle;
    }

    public void setType_of_roof(String type_of_roof) {
        this.type_of_roof = type_of_roof;
    }

    public void setHydrant_distance(int hydrant_distance) {
        this.hydrant_distance = hydrant_distance;
    }

    public void setHigh_risk_object(boolean high_risk_object) {
        this.high_risk_object = high_risk_object;
    }

    public void setHRO_type_of_roof(String HRO_type_of_roof) {
        this.HRO_type_of_roof = HRO_type_of_roof;
    }

    public void setHRO_power_supply(boolean HRO_power_supply) {
        this.HRO_power_supply = HRO_power_supply;
    }

    public void setHRO_content(String HRO_content) {
        this.HRO_content = HRO_content;
    }

    public void setHRO_animals(boolean HRO_animals) {
        this.HRO_animals = HRO_animals;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }

    public void setAddress(com.project.test.database.firebaseEntities.Address address) {
        this.address = address;
    }

    public String getProfilPicUrl() {
        return profilPicUrl;
    }

    public void setProfilPicUrl(String profilPicUrl) {
        this.profilPicUrl = profilPicUrl;
    }

    public String getProfilPicStorageLocation() {
        return profilPicStorageLocation;
    }

    public void setProfilPicStorageLocation(String profilPicStorageLocation) {
        this.profilPicStorageLocation = profilPicStorageLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<com.project.test.database.firebaseEntities.Photos> getGndPhoto() {
        return gndPhoto;
    }

    public void setGndPhoto(List<com.project.test.database.firebaseEntities.Photos> gndPhoto) {
        this.gndPhoto = gndPhoto;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public String getName_owner() {
        return name_owner;
    }


    public String getId_house() {
        return id;
    }

    public String getPlaceName() {
        return getAddress().getPlaceNameIfExist();

    }

    public void setName_owner(String name_owner) {

        this.name_owner = name_owner;
    }

    public String getSurname_owner() {
        return surname_owner;
    }


    public int getNumber_of_tenants() {
        return number_of_tenants;
    }

    public int getNumber_of_floors() {
        return number_of_floors;
    }

    public String getList_of_floors() {
        return list_of_floors;
    }

    public int getNumber_of_children() {
        return number_of_children;
    }

    public String getYear_children() {
        return year_children;
    }

    public int getNumber_of_adults() {
        return number_of_adults;
    }

    public String getYears_adults() {
        return years_adults;
    }

    public int getNumber_of_powerless_and_elders() {
        return number_of_powerless_and_elders;
    }

    public String getYears_powerless_elders() {
        return years_powerless_elders;
    }

    public boolean isDisability_person() {
        return disability_person;
    }

    public String getPower_supply() {
        return power_supply;
    }

    public boolean isGas_connection() {
        return gas_connection;
    }

    public String getType_of_heating() {
        return type_of_heating;
    }


    public int getNumber_of_gas_bottle() {
        return number_of_gas_bottle;
    }

    public String getType_of_roof() {
        return type_of_roof;
    }

    public int getHydrant_distance() {
        return hydrant_distance;
    }

    public boolean isHigh_risk_object() {
        return high_risk_object;
    }

    public String getHRO_type_of_roof() {
        return HRO_type_of_roof;
    }

    public boolean isHRO_power_supply() {
        return HRO_power_supply;
    }

    public String getHRO_content() {
        return HRO_content;
    }

    public boolean isHRO_animals() {
        return HRO_animals;
    }

    /*
        public Bitmap getProfilImageBitmapbyContext(Context contextItem) {

            Bitmap bitmap; =new ImageSaver(contextItem).
                    setFileName(getProfilPhotos().getFileName() + ".png").
                    setDirectoryName("Images").
                    load();


            return bitmap;
        }*/


    public void getProfilImageBitmapFromCloudStorage(IPhoto iPhoto) {
        com.project.test.database.firebaseEntities.Photos photos = new com.project.test.database.firebaseEntities.Photos(profilPicStorageLocation,"",profilPicUrl);
        photos.getImageBitmapFromCloudStorage(iPhoto);
    }

    public com.project.test.database.firebaseEntities.Address getAddress() {
        return address;
    }

    public String getAddressStreet() {
        return getAddress().getStreetNameIfExist();
    }

    public boolean isGas_bottle() {
        if (number_of_gas_bottle > 0) {
            return true;
        } else {
            return false;
        }
    }
}