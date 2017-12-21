package com.kizo.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;

/**
 * Created by Nikol on 21.12.2017..
 */

public class FinishedReportActivity extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4;

    //House house;

    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_report);

        toolbar = (Toolbar) findViewById(R.id.toolbarReport);
        setSupportActionBar(toolbar);
        setTitleOnToolbar("Prikaz izvještaja");



        /*
        int a = Integer.parseInt(getIntent().getStringExtra("EXTRA_SESSION_ID"));
        if (a != -1) {

            house = HouseController.getHouse(a);

        } else if (a == -1) {
            house = HouseController.getFirstHouse();
        } else {
            house = HouseController.getFirstHouse();
        }
        */
    }

    private void setTitleOnToolbar(String title){
        //set title (owner name )on toolbar
        getSupportActionBar().setTitle(title); //set title on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        expandableLayout1.toggle(); // toggle expand and collapse
    }

    public void expandableButton2(View view) {
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.toggle(); // toggle expand and collapse
    }

    public void expandableButton3(View view) {
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout3.toggle(); // toggle expand and collapse
    }

    public void expandableButton4(View view) {
        expandableLayout4 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout4);
        expandableLayout4.toggle(); // toggle expand and collapse
    }
}
