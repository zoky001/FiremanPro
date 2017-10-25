package com.project.air.firemanpro;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.project.air.firemanpro.profil.ProfilController;
import com.project.test.database.Entities.House;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilActivity extends AppCompatActivity {

    @BindView(R.id.imageViewProfilHouse)
    ImageView profil;

    @BindView(R.id.textViewNameOwner)
    TextView txtNameSurname;

    @BindView(R.id.textViewPlace)
    TextView txtPlace;

    @BindView(R.id.textView1Address)
    TextView txtAdress;

    House house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind(this);

        house = ProfilController.getFirstHouse();

       System.out.println("SlikaProfila: "+house.getHouse_image());

        String url = "R.drawable."+house.getHouse_image();



        String imageName = house.getHouse_image(); //get image name from database

        int resourceId = this.getResources().
                getIdentifier(imageName, "drawable", this.getPackageName());

        //get resource ID
        // now resource ID is as equivalent to R.drawable.imgName
        profil.setImageResource(resourceId ); //set profil image


        //set owner data
        txtNameSurname.setText( house.getSurname_owner()+" "+house.getName_owner());
txtPlace.setText(house.getPlaceName());
txtAdress.setText(house.getAddress());




    }
}
