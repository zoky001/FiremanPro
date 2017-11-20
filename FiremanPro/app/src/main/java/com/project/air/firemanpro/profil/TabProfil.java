package com.project.air.firemanpro.profil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

<<<<<<< HEAD
=======
        Bundle bundle = new Bundle();

        String IDHouse = "" + house.getId_house();

        bundle.putString("IDkuce", IDHouse);
        Fragment mapFragment=new MapFragment();
        mapFragment.setArguments(bundle);
>>>>>>> a3c51605f4774bd972432f238ec3d0e4645da8f2

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.map_container, new MapFragment())
                .replace(R.id.map_container, mapFragment)
                .commit();
    }

}
