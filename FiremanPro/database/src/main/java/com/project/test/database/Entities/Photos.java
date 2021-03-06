package com.project.test.database.Entities;

import android.content.Context;
import android.graphics.Bitmap;

import com.project.test.database.MainDatabase;
import com.project.test.database.imageSaver.ImageSaver;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by Zoran on 23.10.2017..
 */

@Table(database = MainDatabase.class)
public class Photos extends BaseModel {

    @PrimaryKey(autoincrement = true)
    @Column
    int ID;

    @Column
    String ImageName;

    @Column
    String FileName;

    @Column
    String url;

    @Column
    java.util.Date updated_at;
    @Column
    java.util.Date created_at;


    public Photos() {
    }

    public Photos(String imageName, String fileName, String url, Date updated_at, Date created_at) {
        ImageName = imageName;
        FileName = fileName;
        this.url = url;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }

    public int getID() {
        return ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Bitmap getImageBitmapbyContext(Context contextItem) {

        Bitmap bitmap = new ImageSaver(contextItem).
                setFileName(getFileName() + ".png").
                setDirectoryName("Images").
                load();


        return bitmap;


    }

}
