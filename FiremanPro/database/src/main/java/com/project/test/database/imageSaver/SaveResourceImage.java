package com.project.test.database.imageSaver;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.project.test.database.Entities.Ground_plan;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by Zoran on 1.11.2017..
 */

public class SaveResourceImage {

   private List<House> houses;
    private  List<Ground_plan> plans;
    private Context context;

    public SaveResourceImage(Context context) {
        this.houses = HouseController.getAllHouseRecords();
        this.plans = SQLite.select().from(Ground_plan.class).queryList();

        this.context=context;
    }

    public void SaveImageFromResourceToInternalStorage(){
        SaveAllProfilImageToInternalStorage();
        SaveAllGroundPlanImageToInternalStorage();

    }

    public void SaveAllProfilImageToInternalStorage(){
        for (House h:houses
                ) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), h.getProfilImageResourceIDbyContext(context));

            new ImageSaver(context).
                    setFileName(h.getHouse_image()+".png").
                    setDirectoryName("ProfilImages").
                    save(bitmap);

        }


    }

    public void SaveAllGroundPlanImageToInternalStorage(){
        for (Ground_plan gnd:plans
                ) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), gnd.getImageResourceIDbyContext(context));

            new ImageSaver(context).
                    setFileName(gnd.getImgAdress()+".png").
                    setDirectoryName("GroundPlanImages").
                    save(bitmap);

        }


    }
}
