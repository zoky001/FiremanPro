package com.project.air.firemanpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD

        Button search = (Button) findViewById(R.id.buttonSearching);

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), SearchingResultsActivity.class);
                view.getContext().startActivity(Intent);
            }
        });
=======
        FlowManager.init(new FlowConfig.Builder(this).build());

>>>>>>> 9e14820aa36d3e7c9c9c0e30afc221a3f4632ac9
    }
}
