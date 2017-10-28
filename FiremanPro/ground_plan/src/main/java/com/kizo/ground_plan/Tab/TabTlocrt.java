package com.kizo.ground_plan.Tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.kizo.ground_plan.R;
import com.kizo.ground_plan.Tab.adapter.Ground_plan_adapter;
import com.project.test.database.Entities.Ground_plan;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zoran on 27.10.2017..
 */

public class TabTlocrt extends Fragment {

    House house;

ListView lst;
    String[] fruitname = {"Mango", "Banana", "Eatermelon"};
    Integer [] imgId = {};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.println("TabTlocrt");
        View rootView = inflater.inflate(R.layout.tab_tlocrt, container, false);

        ButterKnife.bind(this, rootView);


        String s = getArguments().getString("IDkuce");
        System.out.println("SESSION FRAGMENT_idkuce: "+s);
        int a = Integer.parseInt(getArguments().getString("IDkuce"));
        if (a != -1){

            house = HouseController.getHouse(a);

        }
        else {
            house = HouseController.getFirstHouse();
        }

      final List<Ground_plan> gndPlans = house.getAllHouseGroundPlans();

        System.out.println("BROJ SLIKA:  "+gndPlans.size());

      lst = (ListView) rootView.findViewById(R.id.listView);
        CustomListView customListView = new CustomListView(getActivity(),fruitname,imgId,gndPlans);
        lst.setAdapter(customListView);
/*
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv.setLayoutManager(llm);

        rv.setAdapter(new Ground_plan_adapter(gndPlans));*/

        return rootView;
    }
}