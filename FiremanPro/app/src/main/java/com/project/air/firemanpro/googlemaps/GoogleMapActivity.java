package com.project.air.firemanpro.googlemaps;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;


import butterknife.ButterKnife;

/**
 * Ativnost koja prikauje Google kartu preko cijelog ekrana, sa označenim odrdištima, putem između njih i lokacijama hidranata.
 * @author Nikolina Bukovec
 * @version  22.11.2017.
 */

public class GoogleMapActivity extends AppCompatActivity {
    House house;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        ButterKnife.bind(this);

        int a = Integer.parseInt(getIntent().getStringExtra("IDkuce"));
        System.out.println("MAPACTIVITY_idkuce kartaaaaaaaa: " + a);
        try {

            house = HouseController.getHouse(a);
        }
        catch ( Exception e){

        System.out.println("GREŠKA: "+e);
                }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMap);
        setSupportActionBar(toolbar);

        setTitleOnToolbar(house.getAddressStreet()+ " "+ house.getAddress().getStreetNumber()+", " +house.getAddress().getPlaceNameIfExist());

        Bundle bundle = new Bundle();

        bundle.putString("IDkuce", String.valueOf(a));

        Fragment mapFragment=new com.project.air.firemanpro.googlemaps.MapFragment();
        mapFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()

                .replace(R.id.mapView_container, mapFragment)
                .commit();
    }

    /**
     * Metoda koja postavlja naslov na Toolbar
     *
     */
    private void setTitleOnToolbar(String title){

        getSupportActionBar().setTitle(title);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
