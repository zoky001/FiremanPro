package com.project.air.firemanpro.googlemaps;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;



import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;


import butterknife.ButterKnife;

/**
 * Created by Nikol on 22.11.2017..
 */

public class GoogleMapActivity extends FragmentActivity{
    House house;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        ButterKnife.bind(this);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProfil);
      //  setSupportActionBar(toolbar);

        int a = Integer.parseInt(getIntent().getStringExtra("IDkuce"));
        System.out.println("MAPACTIVITY_idkuce kartaaaaaaaa: " + a);




        Bundle bundle = new Bundle();

        bundle.putString("IDkuce", String.valueOf(a));

        Fragment mapFragment=new com.project.air.firemanpro.googlemaps.MapFragment();
        mapFragment.setArguments(bundle);


        getSupportFragmentManager().beginTransaction()

                .replace(R.id.mapView_container, mapFragment)
                .commit();

    }


}
