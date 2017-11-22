package com.project.air.firemanpro.googlemaps;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.MapFragment;
import com.project.air.firemanpro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nikol on 22.11.2017..
 */

public class GoogleMapActivity extends AppCompatActivity {

    @BindView(R.id.mapViewMax)
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProfil);
        setSupportActionBar(toolbar);


       MapFragment map = new MapFragment();
       map.setArguments(savedInstanceState);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.mapView_container, map)
                .commit();
    }

}
