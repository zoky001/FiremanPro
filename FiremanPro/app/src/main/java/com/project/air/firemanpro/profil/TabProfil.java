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
import android.widget.ImageView;
import android.widget.TextView;

import com.project.air.firemanpro.googlemaps.GoogleMapActivity;
import com.project.air.firemanpro.googlemaps.MapFragment;
import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Zoran on 27.10.2017..
 */

public class TabProfil extends Fragment {
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



    House house;

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

        bundle.putString("IDkuce", IDHouse);
        Fragment mapFragment=new MapFragment();
        mapFragment.setArguments(bundle);


        getFragmentManager()
                .beginTransaction()
                .replace(R.id.map_container, mapFragment)
                .commit();
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
}
