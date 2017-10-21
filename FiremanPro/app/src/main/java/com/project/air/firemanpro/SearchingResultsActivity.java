package com.project.air.firemanpro;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.project.air.firemanpro.adapters.SearchResultsRecyclerAdapter;
import com.project.air.firemanpro.helpers.MockDataLoader;

import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchingResultsActivity extends AppCompatActivity {

    @Bind(R.id.rv_results)
    public RecyclerView recycleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_results);


    }
}
