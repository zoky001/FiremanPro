package com.project.air.firemanpro;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.project.air.firemanpro.adapters.SearchingAdapter;
import com.project.air.firemanpro.profil.ProfilController;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Places;
import com.project.test.database.controllers.HouseController;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class SearchingResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_results);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProfil);
        setSupportActionBar(toolbar);
        //set title (owner name )on toolbar
        //getSupportActionBar().setTitle(house.getSurname_owner()+" "+house.getName_owner()+" - "+house.getPlaceName()); //set title on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } //toolbar


        //Retrieving string that user has entered in autoCompleteTextView bar
        String retrievedAutoCompleteTextString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                retrievedAutoCompleteTextString = null;
            } else {
                retrievedAutoCompleteTextString = extras.getString("valueFromAutoCompleteTextView");
            }
        } else {
            retrievedAutoCompleteTextString = (String) savedInstanceState.getSerializable("valueFromAutoCompleteTextView");
        }

        //Getting List of Houses
        final List<House> houses = HouseController.serachByNameAndSurnameQuery(retrievedAutoCompleteTextString);


        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_results);

        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        rv.setAdapter(new SearchingAdapter(houses));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


}
