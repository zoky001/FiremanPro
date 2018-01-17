package com.project.test.database.imageSaver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Photos;

import com.project.test.database.controllers.PhotosController;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.List;

/**
 * Created by Zoran on 1.11.2017..
 */

public class SaveResourceImage {

    private PhotosController photosController = new PhotosController();

    private List<House> houses;
    private List<Photos> photos;
    private Context context;

    public SimpleImageLoadingListener simpleImageLoadingListener;

    public SaveResourceImage(Context context, SimpleImageLoadingListener simpleImageLoadingListener) {
        this.photos = photosController.GetAllRecordsFromTable();
this.simpleImageLoadingListener = simpleImageLoadingListener;
        this.context = context;
    }

    public  void SaveAllPhotoFromUrlToInternalStorage()
    {
        for (Photos h : photos
                ) {

            SaveImageFromUrlToInternalStorage(h.getUrl());
        }

    }

    private String SaveImageFromUrlToInternalStorage(String url) {
       final String name = sha256(url);

System.out.println("URL_SLIKE: preuzimanje___:"+url);


        //imageloader
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();

        ImageLoader.getInstance().init(config);
        ImageLoader imageLoader = ImageLoader.getInstance(); // Get singleton instance
        imageLoader.loadImage(url,
                simpleImageLoadingListener

        );


        return name;
    }


    public static  String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();//.substring(0,10);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }



}
