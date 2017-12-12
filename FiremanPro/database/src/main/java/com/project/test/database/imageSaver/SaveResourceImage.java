package com.project.test.database.imageSaver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Photos;

import com.project.test.database.controllers.PhotosController;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.io.File;
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

    public SaveResourceImage(Context context) {
        this.photos = photosController.GetAllRecordsFromTable();

        this.context = context;
    }

    public void SaveImageFromResourceToInternalStorage() {

        File mydir = context.getDir("Images", Context.MODE_PRIVATE);
        File lister = mydir.getAbsoluteFile();


        if (lister.list().length < photos.size())
            SaveAllImageToInternalStorage();


    }

    public String SaveImageFromUrlToInternalStorage(String url) {
       final String name = sha256(url);



        Picasso.with(context)
                .load(url)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded (final Bitmap bitmap, Picasso.LoadedFrom from){
            /* Save the bitmap or do something with it here */
                        Bitmap scal = Bitmap.createScaledBitmap(bitmap, 270, 200, false);

                        new ImageSaver(context).
                                setFileName(name + ".png").
                                setDirectoryName("Images").
                                save(scal);




                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

        return name;
    }

    public String sha256(String base) {
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

    public void SaveAllImageToInternalStorage() {
        Bitmap scal;

        for (Photos h : photos
                ) {

            if (h.getFileName().contains("gnd")) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), photosController.getPhotoResourceIDbyContext(context, h));
                scal = Bitmap.createScaledBitmap(bitmap, 800, 600, false);

            } else {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), photosController.getPhotoResourceIDbyContext(context, h));
                scal = Bitmap.createScaledBitmap(bitmap, 270, 200, false);

            }


            new ImageSaver(context).
                    setFileName(h.getFileName() + ".png").
                    setDirectoryName("Images").
                    save(scal);

        }
    }

}
