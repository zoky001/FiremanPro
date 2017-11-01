package com.project.air.firemanpro.profil;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Zoran on 27.10.2017..
 */

public class TabProfil extends Fragment {
    @BindView(R.id.imageViewProfilHouse)
    ImageView profil;

    @BindView(R.id.textViewNameOwner)
    TextView txtNameSurname;

    @BindView(R.id.textViewPlace)
    TextView txtPlace;

    @BindView(R.id.textView1Address)
    TextView txtAdress;

    @BindView(R.id.textViewTel)
    TextView txtTel;

    @BindView(R.id.textViewMobitel)
    TextView txtMobitel;


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

            house = ProfilController.getHouse(a);

        } else {
            house = ProfilController.getFirstHouse();
        }


//ID image-resourcee
// int imageresource = getResources().getIdentifier("@drawable/"+house.getHouse_image(), "drawable", getActivity().getPackageName());

        System.out.println("LOKACIJAAA ACTT: " + getActivity().getPackageName());
//set profil image

 //       profil.setImageResource(house.getProfilImageResourceIDbyContext(profil.getContext()));
profil.setImageBitmap(Bitmap.createScaledBitmap(house.getProfilImageBitmapbyContext(profil.getContext()),400, 300, false));

        //set owner data
        txtNameSurname.setText(house.getSurname_owner() + " " + house.getName_owner());

        txtPlace.setText(house.getPlaceName());
        txtAdress.setText(house.getAddress());

        txtMobitel.setText(house.getMobNumber());
        txtTel.setText(house.getTelNumber());

        return rootView;
    }
}
