package com.project.test.database.imageSaver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Photos;

import com.project.test.database.controllers.PhotosController;


import java.io.File;
import java.util.List;

/**
 * Created by Zoran on 1.11.2017..
 */

public class SaveResourceImage {

    private  PhotosController photosController= new PhotosController();

   private List<House> houses;
    private  List<Photos> photos;
    private Context context;

    public SaveResourceImage(Context context) {
        this.photos = photosController.GetAllRecordsFromTable();

        this.context=context;
    }

    public void SaveImageFromResourceToInternalStorage(){

        File mydir = context.getDir("Images", Context.MODE_PRIVATE);
        File lister = mydir.getAbsoluteFile();


        if (lister.list().length < photos.size())
            SaveAllImageToInternalStorage();


    }

    public void SaveAllImageToInternalStorage(){
        Bitmap scal;

        for (Photos h:photos
                ) {

            if (h.getFileName().contains("gnd")){
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), photosController.getPhotoResourceIDbyContext(context,h));
                 scal = Bitmap.createScaledBitmap(bitmap, 800, 600, false);

            }
            else {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), photosController.getPhotoResourceIDbyContext(context,h));
                 scal = Bitmap.createScaledBitmap(bitmap, 270, 200, false);

            }


            new ImageSaver(context).
                    setFileName(h.getFileName()+".png").
                    setDirectoryName("Images").
                    save(scal);

        }
    }

}
