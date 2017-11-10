package com.project.test.database.imageSaver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Photos;

import com.project.test.database.controllers.PhotosController;


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
        SaveAllImageToInternalStorage();

    }

    public void SaveAllImageToInternalStorage(){
        for (Photos h:photos
                ) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), photosController.getPhotoResourceIDbyContext(context,h));

            new ImageSaver(context).
                    setFileName(h.getFileName()+".png").
                    setDirectoryName("Images").
                    save(bitmap);

        }
    }

}
