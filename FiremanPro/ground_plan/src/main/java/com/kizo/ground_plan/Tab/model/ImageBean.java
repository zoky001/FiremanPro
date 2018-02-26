package com.kizo.ground_plan.Tab.model;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.project.test.database.firebaseEntities.Photos;

public class ImageBean {

    private Integer imagePath;
    private Bitmap imgBitmap;
    private String url;

    public void setImagePath(Integer imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getImagePath() {
        return imagePath;
    }

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }


}