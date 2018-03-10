package com.project.air.firemanpro.newIntervention;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.kizo.goolge_map.GPSTracker;
import com.kizo.goolge_map.GoogleLocation;
import com.kizo.ground_plan.Tab.TabTlocrt;
import com.project.air.firemanpro.BuildConfig;
import com.project.air.firemanpro.MainActivity;
import com.project.air.firemanpro.R;
import com.project.air.firemanpro.profil.TabPodaci;
import com.project.air.firemanpro.profil.TabProfil;
import com.project.test.database.InterventionTrackController;
import com.project.test.database.controllers.HouseController;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Aktivnost  koja sadržava prikazivanje tabova.
 * <p>
 * Svaki tab opisuje neke informacije o odabranoj kući.
 * <p>
 * Prilikom kreiranja potrebno je prosljediti id koristeći Bundle, "EXTRA_SESSION_ID" = id odabrane kuce
 *
 * @author Zoran Hrnčić
 */
public class NewInterventionActivity extends AppCompatActivity implements OnMapReadyCallback {

    String TAG = "ProfilNewActivityLog";

    private GoogleMap mMap;

    // House house;

    com.project.test.database.firebaseEntities.House house;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded currrentDisplayedFragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    /**
     * Provides the entry point to the Fused Location Provider API.
     */

    /**
     * Represents a geographical location.
     */
    CompositeDisposable disposable = new CompositeDisposable();
    Single<com.project.test.database.firebaseEntities.House> cachedSingleHouse;
    private GPSTracker gps;

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    /**
     * Provides the entry point to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    /**
     * Represents a geographical location.
     */
    protected Location mLastLocation;

    private String mLatitudeLabel;
    private String mLongitudeLabel;
    private TextView mLatitudeText;
    private TextView mLongitudeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_intervention);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        setLocationSettings();


        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Create the adapter that will return a currrentDisplayedFragment for each of the three
        // primary sections of the activity.


        try {
            String a = getIntent().getStringExtra("EXTRA_SESSION_ID");
            cachedSingleHouse = HouseController.getHouseByID(a).cache();
            loadHouse(cachedSingleHouse);
            System.out.println("SESSION FRAGMENT_idkuce: " + a);

        } catch (Exception e) {
            Log.d(TAG, "EXCEPTION: " + e.getMessage());
        }


    }

    private void setLocationSettings() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        if (!checkPermissions()) {
            requestPermissions();

        } else {
            checkIfExistNewIntervention(true);

        }


    }

    private void loadHouse(Single<com.project.test.database.firebaseEntities.House> cachedSingleHouse) {
        Disposable subscribe2 = cachedSingleHouse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<com.project.test.database.firebaseEntities.House>() {

                    @Override
                    public void onSuccess(com.project.test.database.firebaseEntities.House todos) {
                        // work with the resulting todos
                        Log.d(TAG, todos.toString());

                        setLayoutData(todos);

                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                    }
                });
        disposable.add(subscribe2);

    }

    private void setLayoutData(com.project.test.database.firebaseEntities.House house) {
        this.house = house;

        setTitleOnToolbar(house.getSurname_owner() + " " + house.getName_owner() + " - " + house.getPlaceName());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    /**
     * postavljanje naslova na toolbar
     *
     * @param title naziv koji se postavlja na toolbar
     */
    private void setTitleOnToolbar(String title) {
        //set title (owner name )on toolbar
        getSupportActionBar().setTitle(title); //set title on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } //toolbar
    }

    private void CheckIfExistNewIntervention(Intent intent, boolean locationPermission) {
        String message = intent.getStringExtra("message");

        String intervention_id = intent.getStringExtra("intervention_id");
        if (message != null && intervention_id != null) {
            try {

                Log.d(TAG, "EXCEPTION: " + "SESSION FRAGMENT_idkuce: " + message);

                showMyDialog("Message", message, intervention_id, intent, locationPermission);

                if (locationPermission) {
                    Disposable subscribe2 = GoogleLocation.getLastLocation(mFusedLocationClient, this)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<Location>() {

                                @Override
                                public void onSuccess(Location location) {
                                    Log.d(TAG, "success + " + location.toString());
                                    // work with the resulting todos
                                    InterventionTrackController.sendRecievedCallEvent_fireman(intervention_id, FirebaseAuth.getInstance().getUid(), location);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // handle the error case
                                    Log.d(TAG, "error + " + e.getMessage());
                                    InterventionTrackController.sendRecievedCallEvent_fireman(intervention_id, FirebaseAuth.getInstance().getUid(), null);


                                }
                            });

                    disposable.add(subscribe2);

                } else {
                    InterventionTrackController.sendRecievedCallEvent_fireman(intervention_id, FirebaseAuth.getInstance().getUid(), null);


                }


            } catch (Exception e) {
                Log.d(TAG, "EXCEPTION: " + e.getMessage());
            }

        }

    }

    private void showMyDialog(String t, String b, String Intervention_id, Intent intent, boolean locationPermission) {


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.notification_dialog, null);

        dialog.setView(dialogView);

        dialog.setTitle(t);
        TextView tv = (TextView) dialogView.findViewById(R.id.message);
        tv.setText(b);
        dialog.setPositiveButton("Dolazim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (locationPermission){
                    Disposable subscribe2 = GoogleLocation.getLastLocation(mFusedLocationClient, getBaseContext())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<Location>() {

                                @Override
                                public void onSuccess(Location location) {

                                    Log.d(TAG, "success + " + location.toString());
                                    // work with the resulting todos
                                    InterventionTrackController.sendComingEvent_fireman(Intervention_id, FirebaseAuth.getInstance().getUid(), location);
                                    dialog.dismiss();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // handle the error case
                                    Log.d(TAG, "error + " + e.getMessage());
                                    InterventionTrackController.sendComingEvent_fireman(Intervention_id, FirebaseAuth.getInstance().getUid(), null);

                                    // InterventionTrackController.sendComingEvent_fireman(Intervention_id, FirebaseAuth.getInstance().getUid(),new Location(""));


                                }
                            });

                    disposable.add(subscribe2);
                }else {

                    InterventionTrackController.sendComingEvent_fireman(Intervention_id, FirebaseAuth.getInstance().getUid(), null);

                }





            }
        });
        dialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    //START location permission
    @SuppressWarnings("MissingPermission")
    private void checkIfExistNewIntervention(boolean location) {
        CheckIfExistNewIntervention(getIntent(), location);

    }

    /**
     * Shows a {@link Snackbar} using {@code text}.
     *
     * @param text The Snackbar text.
     */
    private void showSnackbar(final String text) {
        View container = findViewById(R.id.appbar);
        if (container != null) {
            Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * Shows a {@link Snackbar}.
     *
     * @param mainTextStringId The id for the string resource for the Snackbar text.
     * @param actionStringId   The text of the action item.
     * @param listener         The listener associated with the Snackbar action.
     */
    private void showSnackbar(final int mainTextStringId, final int actionStringId,
                              View.OnClickListener listener) {
        Snackbar.make(findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(NewInterventionActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");

            showSnackbar(R.string.permission_rationale, android.R.string.ok,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            startLocationPermissionRequest();
                        }
                    });

        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest();
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                checkIfExistNewIntervention(true);
            } else {
                checkIfExistNewIntervention(false);
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                showSnackbar(R.string.permission_denied_explanation, R.string.settings,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        });
            }
        }
    }
    //END location permission


    private boolean close = true;
    private boolean postavljeno = false;

    @Override
    public void onBackPressed() {
        //  onBackPressed();
      /*
        final InterventionController in = new InterventionController();

        if (!postavljeno && in.checkIfExistUnfinishedInterventionAtHouse(house)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Jeste li sigurni da želite obrisati zapisnik intervencije? ");
            builder1.setCancelable(false);


            builder1.setPositiveButton(
                    "Da",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            InterventionController.deleteInterventionWithID(in.getUnfinishedInterventionAtHouse(house).getId_intervention_track());
                            close = true;
                            postavljeno = true;
                          //  onBackPressed();
                            dialog.cancel();

                        }
                    });

            builder1.setNegativeButton(
                    "Ne",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            close = true;
                            postavljeno = true;
                            onBackPressed();
                            dialog.cancel();

                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            postavljeno = false;
            if (close)
                super.onBackPressed();

        }
*/
        Log.d(TAG, "onBackPressed: ");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

        }


        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(house.getAddress().getLatitude(), house.getAddress().getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title(house.getAddressStreet() + " " + house.getAddress().getStreetNumber()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14));


    }


    /**
     * A {@link FragmentPagerAdapter} that returns a currrentDisplayedFragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter implements ITabFragment {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        Fragment currrentDisplayedFragment;

        /**
         * Za svaki odabrani tab (1, 2 ili 3) klikom na oznaku taba on se učitava u fragemnt frame.
         *
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();

            String IDHouse = "" + house.getId_house();

            bundle.putString("IDkuce", IDHouse);

            TabFragment tabFragment;
            switch (position) {
                case 0:
                    tabFragment = new TabProfil();
                    tabFragment.setArguments(bundle); //pass ID House
                    break;

                case 1:
                    tabFragment = new TabPodaci();
                    tabFragment.setArguments(bundle);
                    break;

                case 2:
                    tabFragment = new TabTlocrt();//
                    tabFragment.setArguments(bundle);
                    break;

                default:
                    tabFragment = new TabProfil();
                    tabFragment.setArguments(bundle); //pass ID House
                    break;
            }

            tabFragment.loadFrag(this);

            return currrentDisplayedFragment;

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        /**
         * postavljanje naziva taba na gumb za odabir tab-a
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PROFIL";
                case 1:
                    return "PODACI";
                case 2:
                    return "TLOCRT";
            }
            return null;
        }


        /**
         * Fragment dobiven kroz interface se pohranjeuje u currrentDisplayedFragment koji se kasnije učitava u fragment frame.
         *
         * @param f fragment jednog tab-a
         */
        @Override
        public void getFragment(Fragment f) {
            currrentDisplayedFragment = f;
        }
    }


}
