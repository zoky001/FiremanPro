package com.kizo.ground_plan.Tab.model;

import android.graphics.Bitmap;

public class ImageBean {

    private Integer imagePath;
private Bitmap imgBitmap;
    public void setImagePath(Integer imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getImagePath() {
        return imagePath;
    }

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }

    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }
}