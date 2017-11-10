package com.project.air.firemanpro;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.project.test.database.Entities.House;

import com.project.test.database.controllers.HouseController;
import com.project.test.database.helper.MockData;
import com.project.test.database.imageSaver.SaveResourceImage;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    MockData mockData;

    //Test list used by autocompleteTextVie adapter

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);


        //DBflow
        FlowManager.init(new FlowConfig.Builder(this).build());

        mockData  = new MockData();


        // empty the entire database
        mockData.deleteAll();


        // if table "House" is empty, then fill database with data
        if (SQLite.select().from(House.class).queryList().isEmpty()) {

            System.out.println("Nema zapisa u housessssss: ");

            //write all entries in database
            mockData.writeAll();

            //print entries from database to console (for testing)
            mockData.printAll();


            autoCompleteTextView.setSingleLine();
            autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        Intent Intent = new Intent(MainActivity.this, SearchingResultsActivity.class);
                        Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());
                        startActivity(Intent);
                    }
                    return false;
                }
            });


        }
        //save images from resource to directory in device (only at first startup)
saveImagesFromResourcesToInternalStorage();




        TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.til_autocompleteWithLabel);


        //Saving items in list needed for autoComplete control
        List<String> autocompleteListOfStrings = new ArrayList<String>();
        List<House> allHouses = HouseController.getAllHouseRecords();
        for (int i = 0; i < allHouses.size(); i++) {
            if (!autocompleteListOfStrings.contains(allHouses.get(i).getName_owner())) {
                autocompleteListOfStrings.add(allHouses.get(i).getName_owner());
            }
            if (!autocompleteListOfStrings.contains(allHouses.get(i).getSurname_owner())) {
                autocompleteListOfStrings.add(allHouses.get(i).getSurname_owner());
            }

            if (!autocompleteListOfStrings.contains(allHouses.get(i).getName_owner() + " " + allHouses.get(i).getSurname_owner())) {
                autocompleteListOfStrings.add(allHouses.get(i).getName_owner() + " " + allHouses.get(i).getSurname_owner());
            }
            if (!autocompleteListOfStrings.contains(allHouses.get(i).getPlaceName() + ": " + allHouses.get(i).getAddress())) {
                autocompleteListOfStrings.add(allHouses.get(i).getPlaceName() + ": " + allHouses.get(i).getAddress());
            }
        }
        //ArrayAdapter for autoCompleteTextView and its merging with layout autocompleteTextView item
        final String[] autoCompleteStrings = autocompleteListOfStrings.toArray(new String[autocompleteListOfStrings.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoCompleteStrings);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);


        //Delayed method for inputLayout set error method after item is selected from autoCompleteTextView
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                autoCompleteTextView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.til_autocompleteWithLabel);
                        inputLayout.setError(null); // hide error


                    }
                }, 70);
            }
        });


        //Text watcher to autoCompleteTextView listener for changing text
        TextWatcher watcher = new TextWatcher() {
            TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.til_autocompleteWithLabel);

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {

                    autoCompleteTextView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!autoCompleteTextView.isPopupShowing()) {

                                inputLayout.setError("Ne postoji podatak s željenim upisom!"); // show error
                                //
                            } else {
                                inputLayout.setError(null); // hide error
                            }

                        }
                    }, 50);


                }
            }
        };
        autoCompleteTextView.addTextChangedListener(watcher);

    }


    @OnClick(R.id.buttonSearching)
    public void buttonSearchingClicked(View view) {

        Intent Intent = new Intent(view.getContext(), SearchingResultsActivity.class);
        Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());
        startActivity(Intent);

    }


    private void saveImagesFromResourcesToInternalStorage () {

        File mydir = this.getApplicationContext().getDir("Images", Context.MODE_PRIVATE);
        File lister = mydir.getAbsoluteFile();

        System.out.println("PIPIS U DIREKTORIJUJU PROFILA: ");

        //check if image exist in folder "Images"
        if (lister.list().length < 1) {

            System.out.println("PIPIS U DIREKTORIJUJU PROFILA je prazan: ");


            //save images from resource to directory in device
            SaveResourceImage SaveRimg = new SaveResourceImage(this.getApplicationContext());
            SaveRimg.SaveImageFromResourceToInternalStorage(); //profli and gnd plan images


        }
    }


}
