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

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5, expandableLayout6, expandableLayout7, expandableLayout8,
            expandableLayout9, expandableLayout10, expandableLayout11;

    //House house;

    Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_report);

        toolbar = (Toolbar) findViewById(R.id.toolbarReport);
        setSupportActionBar(toolbar);
        setTitleOnToolbar("Izvješće o intervenciji");



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
        expandableLayout1.toggle();
    }

    public void expandableButton2(View view) {
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.toggle();
    }

    public void expandableButton3(View view) {
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout3.toggle();
    }

    public void expandableButton4(View view) {
        expandableLayout4 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout4);
        expandableLayout4.toggle();
    }

    public void expandableButton5(View view) {
        expandableLayout5 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout5);
        expandableLayout5.toggle();
    }

    public void expandableButton6(View view) {
        expandableLayout6 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout6);
        expandableLayout6.toggle();
    }

    public void expandableButton7(View view) {
        expandableLayout7 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout7);
        expandableLayout7.toggle();
    }

    public void expandableButton8(View view) {
        expandableLayout8 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout8);
        expandableLayout8.toggle();
    }

    public void expandableButton9(View view) {
        expandableLayout9 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout9);
        expandableLayout9.toggle();
    }

    public void expandableButton10(View view) {
        expandableLayout10 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout10);
        expandableLayout10.toggle();
    }

    public void expandableButton11(View view) {
        expandableLayout11 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout11);
        expandableLayout11.toggle();
    }
}
