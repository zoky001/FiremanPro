package com.project.air.firemanpro;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.project.air.firemanpro.adapters.SearchingAdapter;
import com.project.air.firemanpro.profil.ProfilController;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Places;
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

        final List<House> houses = ProfilController.getAllHouseRecords();


        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_results);

        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        rv.setAdapter(new SearchingAdapter(houses));
    }


}
