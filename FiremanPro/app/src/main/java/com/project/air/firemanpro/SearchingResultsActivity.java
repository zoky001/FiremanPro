package com.project.air.firemanpro;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.project.air.firemanpro.adapters.SearchingAdapter;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Places;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;


public class SearchingResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    //Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_results);

        final List<House> houses = SQLite.select().from(House.class).queryList();

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //adapter = new SearchingAdapter(houses, con);
        //recyclerView.setAdapter(adapter);

    }


}
