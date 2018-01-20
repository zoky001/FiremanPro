package com.project.test.database.Entities;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.project.test.database.MainDatabase;
import com.project.test.database.R;
import com.project.test.database.imageSaver.ImageSaver;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.File;
import java.io.InputStream;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class House extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int id_house;
    @Column
    String name_owner;
    @Column
    String surname_owner;
    @Column
    int number_of_tenants;
    @Column
    int number_of_floors;
    @Column
    String list_of_floors;
    @Column
    int number_of_children;
    @Column
    String year_children;
    @Column
    int number_of_adults;
    @Column
    String years_adults;
    @Column
    int number_of_powerless_and_elders;
    @Column
    String years_powerless_elders;
    @Column
    boolean disability_person;
    @Column
    String power_supply; //type of connection
    @Column
    boolean gas_connection;
    @Column
    String type_of_heating;

    @Column
    int number_of_gas_bottle;
    @Column
    String type_of_roof;
    @Column
    int hydrant_distance;
    @Column
    boolean high_risk_object;
    @Column
    String HRO_type_of_roof;
    @Column
    boolean HRO_power_supply;
    @Column
    String HRO_content;
    @Column
    boolean HRO_animals;
    @Column
    String telNumber;
    @Column
    String mobNumber;
    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;

    List<House_photos> photos;

    @ForeignKey(saveForeignKeyModel = true)
    Address address;


    @OneToMany(methods = OneToMany.Method.ALL, variableName = "photos")
    public List<House_photos> oneToManyPhotos() {
        if (photos == null) {
            photos = SQLite.select()
                    .from(House_photos.class)
                    .where(House_photos_Table.house_id_house.eq(id_house))
                    .queryList();
        }
        return photos;
    }

    @Override
    public boolean save() {
        boolean res = super.save();
        if (photos != null) {
            for (House_photos s : photos) {
                s.setHouse(this);
                s.save();
            }
        }
        return res;
    }


    public House(Integer id_house, String name_owner, String surname_owner, int number_of_tenants, int number_of_floors, String list_of_floors, int number_of_children, String year_children, int number_of_adults, String years_adults, int number_of_powerless_and_elders, String years_powerless_elders, boolean disability_person, String power_supply, boolean gas_connection, String type_of_heating, int number_of_gas_bottle, String type_of_roof, int hydrant_distance, boolean high_risk_object, String HRO_type_of_roof, boolean HRO_power_supply, String HRO_content, boolean HRO_animals, String telNumber, String mobNumber, java.util.Date updated_at, java.util.Date created_at, Address address) {
        this.id_house = id_house;
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
        this.updated_at = updated_at;
        this.created_at = created_at;
        this.address = address;
    }

    public House() {
    }

    public House(String name_owner, String surname_owner, int number_of_tenants, int number_of_floors, String list_of_floors, int number_of_children, String year_children, int number_of_adults, String years_adults, int number_of_powerless_and_elders, String years_powerless_elders, boolean disability_person, String power_supply, boolean gas_connection, String type_of_heating, int number_of_gas_bottle, String type_of_roof, int hydrant_distance, boolean high_risk_object, String HRO_type_of_roof, boolean HRO_power_supply, String HRO_content, boolean HRO_animals, String telNumber, String mobNumber, java.util.Date updated_at, java.util.Date created_at, Address address) {
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
        this.updated_at = updated_at;
        this.created_at = created_at;

        this.address = address;
    }

    public Photos getProfilPhotos() {

        return SQLite.select()
                .from(House_photos.class)
                .where(House_photos_Table.house_id_house.eq(id_house))
                .and(House_photos_Table.photoType_ID.eq(100)) //id of profil
                .querySingle().getPhoto();


    }

    public List<House_photos> getGroundPlanPhotos() {

        return SQLite.select()
                .from(House_photos.class)
                .where(House_photos_Table.house_id_house.eq(id_house))
                .and(House_photos_Table.photoType_ID.eq(101)) //id of groundType
                .queryList();


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


    public int getId_house() {
        return id_house;
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


    public java.util.Date getUpdated_at() {
        return updated_at;
    }


    public java.util.Date getCreated_at() {
        return created_at;
    }


    public Bitmap getProfilImageBitmapbyContext(Context contextItem) {

        Bitmap bitmap = new ImageSaver(contextItem).
                setFileName(getProfilPhotos().getFileName() + ".png").
                setDirectoryName("Images").
                load();


        return bitmap;


    }


    public ArrayList<String> getListGroundPlansIDResource(Context contextItem) {


        List<House_photos> gnd = getGroundPlanPhotos();

        ArrayList<String> image_list = new ArrayList<>();

        String imageR;
        for (House_photos g : gnd
                ) {

            imageR = String.valueOf(
                    contextItem.getResources().getIdentifier("@drawable/" + g.getPhoto().getFileName(), "drawable", contextItem.getPackageName()));
            image_list.add(imageR);
        }

        return image_list;
//

    }


    public String getHouse_image() {
        return "test";
    }

    public Address getAddress() {
        return address;
    }

    public String getAddressStreet() {
        return getAddress().getStreetNameIfExist();
    }

    public boolean isGas_bottle() {
        return true;
    }
}
