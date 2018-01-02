package com.project.air.firemanpro.profil;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.kizo.report.NewReportFormActivity;
import com.project.air.firemanpro.googlemaps.GoogleMapActivity;
import com.project.air.firemanpro.googlemaps.MapFragment;
import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.report.InterventionController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Zoran on 27.10.2017..
 */

public class TabProfil extends TabFragment {
    private static final int NEW_ALARM = 1;
    @BindView(R.id.imageViewProfilHouse)
    ImageView profil;

    @BindView(R.id.textViewNameOwner)
    TextView txtNameSurname;

    @BindView(R.id.textViewPost)
    TextView txtPost;

    @BindView(R.id.textView1Address)
    TextView txtAdress;

    @BindView(R.id.textViewTel)
    TextView txtTel;

    @BindView(R.id.textViewMobitel)
    TextView txtMobitel;

    @BindView(R.id.textViewPlace)
    TextView txtPlace;

    @BindView(R.id.btn_new_report)
    Button btnNewReport;

    House house;

    private InterventionController interventionController = new InterventionController();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_profil, container, false);
        ButterKnife.bind(this, rootView);

        //CustomScrollView myScrollView = (CustomScrollView) getView().findViewById(R.id.idScrollView);

        String s = getArguments().getString("IDkuce");
        System.out.println("SESSION FRAGMENT_idkuce: " + s);
        int a = Integer.parseInt(getArguments().getString("IDkuce"));
        if (a != -1) {

            house = HouseController.getHouse(a);

        } else {
            house = HouseController.getFirstHouse();
        }



        System.out.println("LOKACIJAAA ACTT: " + getActivity().getPackageName());
//set profil image


profil.setImageBitmap(Bitmap.createScaledBitmap(house.getProfilImageBitmapbyContext(profil.getContext()),400, 300, false));

        //set owner data
        txtNameSurname.setText(house.getSurname_owner() + " " + house.getName_owner());

      txtPost.setText(house.getAddress().getPost().getPostal_code()+ " " + house.getAddress().getPost().getName());


        txtAdress.setText(house.getAddress().getStreetNameIfExist() + " "+house.getAddress().getStreetNumber());
txtPlace.setText(house.getPlaceName());
        txtMobitel.setText(house.getMobNumber());
        txtTel.setText(house.getTelNumber());

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Bundle bundle = new Bundle();

        String IDHouse = "" + house.getId_house();
/*
zbog rušenja mape u emulatoru,, ovo je zakomentirano*/
        bundle.putString("IDkuce", IDHouse);
        Fragment mapFragment=new MapFragment();
        mapFragment.setArguments(bundle);

/*  ruši mi se na virtualki KIZO
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.map_container, mapFragment)
                .commit();*/
    }

    @OnClick(R.id.buttonMax)
    public void buttonMaxClicked(View view) {



        String IDHouse = "" + house.getId_house();
        Intent intent = new Intent(view.getContext(), GoogleMapActivity.class);
        intent.putExtra("IDkuce", IDHouse); // umjesto 01 prosljediš ID kuće





        startActivity(intent);

    }
    @OnClick(R.id.leadMeButton)
    public void leadMeButton(View view) {

        double latitude,longitude;
        latitude=house.getAddress().getLatitude();
        longitude=house.getAddress().getLongitude();
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @OnClick(R.id.btn_new_report)
    public void newReport(View view){
        Intervention_track intervencija;
        if (!interventionController.checkIfExistUnfinishedInterventionAtHouse(house)) {
         intervencija = interventionController.addNewIntervention_atHouse(house);


            intervencija.add_FIRE_ReportToIntervention();

            intervencija.callReceived();
            intervencija.intervetionStarted();
            intervencija.intervetionArrival();
            intervencija.intervetionEnded();


        }
        else
            intervencija = interventionController.getUnfinishedInterventionAtHouse(house);









        Intent intent = new Intent(view.getContext(), NewReportFormActivity.class);
        intent.putExtra("IDintervencije", String.valueOf(intervencija.getId_intervention_track())); // umjesto 01 prosljediš ID kuće
        startActivityForResult(intent, NEW_ALARM);

    }


    @Override
    public void loadFrag(ITabFragment iTabFragment) {
        super.loadFrag(iTabFragment);
        iTabFragment.getFragment(this);
    }
}
