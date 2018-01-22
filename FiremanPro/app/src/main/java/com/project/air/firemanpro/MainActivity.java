package com.project.air.firemanpro;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.project.air.firemanpro.adapters.CustomAutocompleteAdapter;
import com.project.air.firemanpro.profil.ProfilNewActivity;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Settings;
import com.project.test.database.Entities.fireman_patrol.Costs;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.controllers.FiremanPatrolController;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.report.InterventionController;
import com.project.test.database.helper.MockData;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Glavna aktivnost koja se prva pokreće po pokretanu aplikacije. Sadrži navigation drawer, toolbar, te textbox i button.
 *
 * Omogućuje pretraživanje pohranjenih kuća.
 *
 *
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

MockData mockData;
    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DBflow connect to database
        FlowManager.init(new FlowConfig.Builder(this).build());
        mockData= new MockData();
mockData.printAll();
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain_new);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.kizo.report.R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.kizo.report.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();

        ImageLoader.getInstance().init(config);


        if (SQLite.select().from(House.class).queryList().isEmpty()) {

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

            String FullAddress = allHouses.get(i).getAddress().getStreetNameIfExist() + " " + allHouses.get(i).getAddress().getStreetNumber() + ", " +allHouses.get(i).getAddress().getPlaceNameIfExist()+", "+allHouses.get(i).getAddress().getPost().getPostal_code()+" "+allHouses.get(i).getAddress().getPost().getName();
            String AddressWithoutPlace = allHouses.get(i).getAddress().getStreetNameIfExist()+" "+ allHouses.get(i).getAddress().getStreetNumber()+", "+allHouses.get(i).getAddress().getPost().getPostal_code()+" "+allHouses.get(i).getAddress().getPost().getName();
            String AddressWithoutStreet = allHouses.get(i).getAddress().getPlaceNameIfExist()+" "+ allHouses.get(i).getAddress().getStreetNumber()+", "+allHouses.get(i).getAddress().getPost().getPostal_code()+" "+allHouses.get(i).getAddress().getPost().getName();

            if (allHouses.get(i).getAddress().getPlaceNameIfExist()!=""&& allHouses.get(i).getAddress().getStreetNameIfExist()!="" &&!autocompleteListOfStrings.contains(FullAddress)) {
                autocompleteListOfStrings.add(FullAddress);
            }
            if(allHouses.get(i).getAddress().getPlaceNameIfExist()=="" && !autocompleteListOfStrings.contains(AddressWithoutPlace)){
                autocompleteListOfStrings.add(AddressWithoutPlace);
            }
            if(allHouses.get(i).getAddress().getStreetNameIfExist()=="" && !autocompleteListOfStrings.contains(AddressWithoutStreet)){
                autocompleteListOfStrings.add(AddressWithoutStreet);
            }


        }
        //ArrayAdapter for autoCompleteTextView and its merging with layout autocompleteTextView item
        final String[] autoCompleteStrings = autocompleteListOfStrings.toArray(new String[autocompleteListOfStrings.size()]);
        List<String> listAutoCompleteStrings = Arrays.asList(autoCompleteStrings);
        CustomAutocompleteAdapter adapter = new CustomAutocompleteAdapter(this, android.R.layout.simple_dropdown_item_1line, listAutoCompleteStrings);
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
                if (s.length() > 2) {

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



                }else if (s.length()< 3){
                    autoCompleteTextView.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                                inputLayout.setError(null); // hide error
                            

                        }
                    }, 50);
                }
            }
        };
        autoCompleteTextView.addTextChangedListener(watcher);

    }

    private void checkIfExistUnfinishedIntervention() {
        InterventionController interventionController = new InterventionController();


        if (InterventionController.getUnfinishedIntervention().size() > 0)
        {
            final Intervention_track unfinshed = InterventionController.getInterventionByID(InterventionController.getUnfinishedIntervention().get(0).getId_intervention_track());

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Postoji započeta intervencija. Želite li je dovršiti? ");
            builder1.setCancelable(true);


            builder1.setPositiveButton(
                    "Da",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            System.out.println("clikcOnItem()r: ");
                            Intent intent = new Intent(getBaseContext(), ProfilNewActivity.class);
                            intent.putExtra("EXTRA_SESSION_ID",String.valueOf(unfinshed.getHouse().getId_house())); // umjesto 01 prosljediš ID kuće
                            getBaseContext().startActivity(intent);



                            dialog.cancel();

                        }
                    });

            builder1.setNegativeButton(
                    "Ne",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {


                            dialog.cancel();

                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();




        }






    }


    @OnClick(R.id.buttonSearching)
    public void buttonSearchingClicked(View view) {

        Intent Intent = new Intent(view.getContext(), SearchingResultsActivity.class);

        Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());

       startActivity(Intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
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

            String FullAddress = allHouses.get(i).getAddress().getStreetNameIfExist() + " " + allHouses.get(i).getAddress().getStreetNumber() + ", " +allHouses.get(i).getAddress().getPlaceNameIfExist()+", "+allHouses.get(i).getAddress().getPost().getPostal_code()+" "+allHouses.get(i).getAddress().getPost().getName();
            String AddressWithoutPlace = allHouses.get(i).getAddress().getStreetNameIfExist()+" "+ allHouses.get(i).getAddress().getStreetNumber()+", "+allHouses.get(i).getAddress().getPost().getPostal_code()+" "+allHouses.get(i).getAddress().getPost().getName();
            String AddressWithoutStreet = allHouses.get(i).getAddress().getPlaceNameIfExist()+" "+ allHouses.get(i).getAddress().getStreetNumber()+", "+allHouses.get(i).getAddress().getPost().getPostal_code()+" "+allHouses.get(i).getAddress().getPost().getName();

            if (allHouses.get(i).getAddress().getPlaceNameIfExist()!=""&& allHouses.get(i).getAddress().getStreetNameIfExist()!="" &&!autocompleteListOfStrings.contains(FullAddress)) {
                autocompleteListOfStrings.add(FullAddress);
            }
            if(allHouses.get(i).getAddress().getPlaceNameIfExist()=="" && !autocompleteListOfStrings.contains(AddressWithoutPlace)){
                autocompleteListOfStrings.add(AddressWithoutPlace);
            }
            if(allHouses.get(i).getAddress().getStreetNameIfExist()=="" && !autocompleteListOfStrings.contains(AddressWithoutStreet)){
                autocompleteListOfStrings.add(AddressWithoutStreet);
            }


        }
        //ArrayAdapter for autoCompleteTextView and its merging with layout autocompleteTextView item
        final String[] autoCompleteStrings = autocompleteListOfStrings.toArray(new String[autocompleteListOfStrings.size()]);
        List<String> listAutoCompleteStrings = Arrays.asList(autoCompleteStrings);
        CustomAutocompleteAdapter adapter = new CustomAutocompleteAdapter(this, android.R.layout.simple_dropdown_item_1line, listAutoCompleteStrings);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
    }


    /**
     * ovisno o odabranome iz navigation drawer-a, pokreće se nova aktivnost.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        System.out.println("item selected");


       if (id == R.id.nav_app_report) {

            Intent Intent = new Intent(this, com.kizo.report.ReportActivity.class);

            startActivity(Intent);
        }

        if (id == R.id.settings) {

            Intent Intent = new Intent(this, SettingsActivity.class);

            startActivity(Intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkIfExistUnfinishedIntervention();
    }
}
