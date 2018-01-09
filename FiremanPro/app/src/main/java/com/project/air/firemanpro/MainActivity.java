package com.project.air.firemanpro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import android.widget.TextView;

import com.kizo.core_module.DataLoadedListener;
import com.kizo.core_module.DataLoader;
import com.kizo.web_services.AirWebServiceCaller;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.kizo.core_module.tab_profile.TabFragment;
import com.kizo.report.SavedReportFragment;
import com.project.air.firemanpro.adapters.CustomAutocompleteAdapter;
import com.project.air.firemanpro.loaders.WsDataLoader;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Settings;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.helper.MockData;
import com.project.test.database.imageSaver.SaveResourceImage;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements DataLoadedListener, NavigationView.OnNavigationItemSelectedListener {


    MockData mockData;

    //Test list used by autocompleteTextVie adapter

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;


    List<House> allHouses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //DBflow connect to database
        FlowManager.init(new FlowConfig.Builder(this).build());


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


        View header=navigationView.getHeaderView(0);

        TextView headerTitle = (TextView)header.findViewById(R.id.headerTitle);
        //headerTitle.setText(Settings.getSettings().getPatrolName());





        //imageloader
        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();

        ImageLoader.getInstance().init(config);

        mockData  = new MockData();
      //  mockData.writeAll();
//mockData.writeAll();
        // empty the entire database
        /*
        mockData.deleteAll();*/
       // loadFromService();

        // if table "House" is empty, then fill database with data
        if (SQLite.select().from(House.class).queryList().isEmpty()) {

            System.out.println("Nema zapisa u housessssss: ");

            //write all entries in database
         // mockData.writeAll();

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



    @OnClick(R.id.buttonSearching)
    public void buttonSearchingClicked(View view) {

        Intent Intent = new Intent(view.getContext(), SearchingResultsActivity.class);

        Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());

       startActivity(Intent);


       // AirWebServiceCaller webServiceCaller = new AirWebServiceCaller();
      //  webServiceCaller.getAll("getAll", House.class);




    }

    private void loadFromService(){
        DataLoader dataLoader;

        if(true){
            // empty the entire database
            mockData.deleteAll();

            System.out.println("Loading web data");
            dataLoader = new WsDataLoader(this);
        }

        dataLoader.loadData(this);

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





    private void saveImagesFromResourcesToInternalStorage () {

        File mydir = this.getApplicationContext().getDir("Images", Context.MODE_PRIVATE);
        File lister = mydir.getAbsoluteFile();

        System.out.println("PIPIS U DIREKTORIJUJU PROFILA: ");

        //check if image exist in folder "Images"
        if (true) {

            System.out.println("PIPIS U DIREKTORIJUJU PROFILA je prazan: ");


            //save images from resource to directory in device
            //SaveResourceImage SaveRimg = new SaveResourceImage(this.getApplicationContext());
           // SaveRimg.SaveImageFromResourceToInternalStorage(); //profli and gnd plan images


        }
    }

    @Override
    public void onDataLoaded(ArrayList<House> houses) {

        System.out.println("Data is here... ");
        String[] listItems = new String[houses.size()];

        for (int i = 0; i < houses.size(); i++) {

            listItems[i] = houses.get(i).getName_owner();
        }

       // mockData.writeAll();
        mockData.printAll();

        //SaveResourceImage saveResourceImage = new SaveResourceImage(getApplicationContext());

      // saveResourceImage.SaveAllPhotoFromUrlToInternalStorage();




    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        System.out.println("item selected");

        TabFragment tabFragment;


       if (id == R.id.nav_app_report) {

            Intent Intent = new Intent(this, com.kizo.report.ReportActivity.class);

       /* Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());
       */
            startActivity(Intent);
        }

        if (id == R.id.settings) {

            Intent Intent = new Intent(this, SettingsActivity.class);

       /* Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());
       */
            startActivity(Intent);
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






}
