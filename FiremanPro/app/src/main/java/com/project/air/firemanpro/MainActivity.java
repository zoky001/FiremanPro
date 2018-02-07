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
import android.text.format.Time;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.project.air.firemanpro.adapters.CustomAutocompleteAdapter;
import com.project.air.firemanpro.profil.ProfilNewActivity;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.FirebaseStorageController;
import com.project.test.database.RxJava.RxJavaTest;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.report.InterventionController;
import com.project.test.database.firebaseEntities.Post;
import com.project.test.database.firebaseEntities.User;
import com.project.test.database.helper.MockData;
import com.project.test.database.interfaces.IPost;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Glavna aktivnost koja se prva pokreće po pokretanu aplikacije. Sadrži navigation drawer, toolbar, te textbox i button.
 * <p>
 * Omogućuje pretraživanje pohranjenih kuća.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAnalytics firebaseAnalytics;
    MockData mockData;
    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    CompositeDisposable disposable = new CompositeDisposable();
    Button crashButton;// = new Button(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckExtrasForNotificationData(getIntent());
        //DBflow connect to database

        FlowManager.init(new FlowConfig.Builder(this).build());
        mockData = new MockData();
        mockData.printAll();
///crah test

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Crashlytics.log("Rušenje app tst");
        crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FirebaseStorageController.updateDatabaseWithPhoto();
                //Crashlytics.getInstance().crash(); // Force a crash
            }
        });
        addContentView(crashButton,
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));


        //crash END
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain_new);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.kizo.report.R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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

            String FullAddress = allHouses.get(i).getAddress().getStreetNameIfExist() + " " + allHouses.get(i).getAddress().getStreetNumber() + ", " + allHouses.get(i).getAddress().getPlaceNameIfExist() + ", " + allHouses.get(i).getAddress().getPost().getPostal_code() + " " + allHouses.get(i).getAddress().getPost().getName();
            String AddressWithoutPlace = allHouses.get(i).getAddress().getStreetNameIfExist() + " " + allHouses.get(i).getAddress().getStreetNumber() + ", " + allHouses.get(i).getAddress().getPost().getPostal_code() + " " + allHouses.get(i).getAddress().getPost().getName();
            String AddressWithoutStreet = allHouses.get(i).getAddress().getPlaceNameIfExist() + " " + allHouses.get(i).getAddress().getStreetNumber() + ", " + allHouses.get(i).getAddress().getPost().getPostal_code() + " " + allHouses.get(i).getAddress().getPost().getName();

            if (allHouses.get(i).getAddress().getPlaceNameIfExist() != "" && allHouses.get(i).getAddress().getStreetNameIfExist() != "" && !autocompleteListOfStrings.contains(FullAddress)) {
                autocompleteListOfStrings.add(FullAddress);
            }
            if (allHouses.get(i).getAddress().getPlaceNameIfExist() == "" && !autocompleteListOfStrings.contains(AddressWithoutPlace)) {
                autocompleteListOfStrings.add(AddressWithoutPlace);
            }
            if (allHouses.get(i).getAddress().getStreetNameIfExist() == "" && !autocompleteListOfStrings.contains(AddressWithoutStreet)) {
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


                } else if (s.length() < 3) {
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

    private void firebase() {
        Crashlytics.log("Rušenje app tst");
        final DatabaseReference mDatabase;
// ...
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");


        Log.d("BAZA:", "upis");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("BAZA", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("BAZA", "Failed to read value.", error.toException());
            }
        });


        DatabaseReference users = database.getReference("users");
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                Log.d("BAZA", "Value is: " + dataSnapshot.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("BAZA", "Failed to read value.", error.toException());
            }
        });


        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
        User user = new User(1, "Marko", "Kralj");

        mDatabase.child("users").child("1").setValue(user);

        // mDatabase.child("users").child("1").child("username").setValue("Zoran");

    }

    private void checkIfExistUnfinishedIntervention() {
        InterventionController interventionController = new InterventionController();


        if (InterventionController.getUnfinishedIntervention().size() > 0) {
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
                            intent.putExtra("EXTRA_SESSION_ID", String.valueOf(unfinshed.getHouse().getId_house())); // umjesto 01 prosljediš ID kuće
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
        // FirebasePatrolController.fireStore();
        // FirebasePatrolController.saveOtherType();


        /*
        Intent Intent = new Intent(view.getContext(), SearchingResultsActivity.class);

        Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());

        startActivity(Intent);
        */


        // firebase();

        RxJava();


        IPost iPost = new IPost() {
            @Override
            public void onPostArrived(com.project.test.database.firebaseEntities.Post post) {
                String s = String.valueOf(post.getPostal_code());
                Log.d("POŠTA: ", post.getName() + "__" + s);
            }
        };
        com.project.test.database.firebaseEntities.Post.getPostById("42208", iPost);


    }

    private void RxJava() {

        Disposable subscribe = RxJavaTest.serverDownload_Observable().

                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(post -> {
                    updateTheUserInterface(post); // this methods updates the ui

                });
        disposable.add(subscribe);




         Disposable subscribe1 = RxJavaTest.serverDownlaod_single()
                 .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(new DisposableSingleObserver<Post>() {

                     @Override
                     public void onSuccess(Post todos) {
                         // work with the resulting todos
                         updateTheUserInterface(todos);
                     }

                     @Override
                     public void onError(Throwable e) {
                         // handle the error case
                     }
                 });
         /*
                .subscribe(post -> {
                    updateTheUserInterface(post); // this methods updates the ui

                });*/

        disposable.add(subscribe1);

    }

    private void updateTheUserInterface(com.project.test.database.firebaseEntities.Post integer) {
        Log.d("RXJAVA", integer.getName());

        autoCompleteTextView.setText(integer.getName() + "_" );

    }

    //firebase message
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        CheckExtrasForNotificationData(intent);
    }

    private void CheckExtrasForNotificationData(Intent i) {
        Bundle data = i.getExtras();

        if (data != null) {
            String b = data.containsKey("body") ? data.getString("body") : "";
            if (!b.isEmpty()) {
                showMyDialog("Message", b);
            }
        }
    }

    private void showMyDialog(String t, String b) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.notification_dialog, null);

        dialog.setView(dialogView);

        dialog.setTitle(t);
        TextView tv = (TextView) dialogView.findViewById(R.id.message);
        tv.setText(b);
        dialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    //firebase message end


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

            String FullAddress = allHouses.get(i).getAddress().getStreetNameIfExist() + " " + allHouses.get(i).getAddress().getStreetNumber() + ", " + allHouses.get(i).getAddress().getPlaceNameIfExist() + ", " + allHouses.get(i).getAddress().getPost().getPostal_code() + " " + allHouses.get(i).getAddress().getPost().getName();
            String AddressWithoutPlace = allHouses.get(i).getAddress().getStreetNameIfExist() + " " + allHouses.get(i).getAddress().getStreetNumber() + ", " + allHouses.get(i).getAddress().getPost().getPostal_code() + " " + allHouses.get(i).getAddress().getPost().getName();
            String AddressWithoutStreet = allHouses.get(i).getAddress().getPlaceNameIfExist() + " " + allHouses.get(i).getAddress().getStreetNumber() + ", " + allHouses.get(i).getAddress().getPost().getPostal_code() + " " + allHouses.get(i).getAddress().getPost().getName();

            if (allHouses.get(i).getAddress().getPlaceNameIfExist() != "" && allHouses.get(i).getAddress().getStreetNameIfExist() != "" && !autocompleteListOfStrings.contains(FullAddress)) {
                autocompleteListOfStrings.add(FullAddress);
            }
            if (allHouses.get(i).getAddress().getPlaceNameIfExist() == "" && !autocompleteListOfStrings.contains(AddressWithoutPlace)) {
                autocompleteListOfStrings.add(AddressWithoutPlace);
            }
            if (allHouses.get(i).getAddress().getStreetNameIfExist() == "" && !autocompleteListOfStrings.contains(AddressWithoutStreet)) {
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
