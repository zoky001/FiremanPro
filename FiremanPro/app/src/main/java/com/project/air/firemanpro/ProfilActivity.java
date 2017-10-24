package com.project.air.firemanpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.project.air.firemanpro.profil.ProfilController;
import com.project.test.database.Entities.House;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilActivity extends AppCompatActivity {



    House house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        house = ProfilController.getFirstHouse();

       System.out.println(house.getHouse_image());







        setContentView(R.layout.activity_profil);
    }
}
