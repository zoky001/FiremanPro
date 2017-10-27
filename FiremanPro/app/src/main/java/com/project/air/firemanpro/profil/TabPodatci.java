package com.project.air.firemanpro.profil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.air.firemanpro.R;

/**
 * Created by Zoran on 27.10.2017..
 */

public class TabPodatci extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("Tabpodatci");
        View rootView = inflater.inflate(R.layout.tab_podatci_, container, false);
return rootView;
    }
}