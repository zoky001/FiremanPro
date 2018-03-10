package com.project.air.firemanpro;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Client;
import com.algolia.search.saas.CompletionHandler;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;
import com.crashlytics.android.Crashlytics;

import com.github.aakira.expandablelayout.Utils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.kizo.goolge_map.GPSTracker;
import com.kizo.goolge_map.GoogleLocation;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.project.air.firemanpro.adapters.CustomAutocompleteAdapter;
import com.project.air.firemanpro.newIntervention.NewInterventionActivity;
import com.project.air.firemanpro.profil.ProfilNewActivity;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.FirebasePatrolController;
import com.project.test.database.FirebaseStorageController;
import com.project.test.database.InterventionTrackController;
import com.project.test.database.RxJava.RxJavaTest;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.report.InterventionController;
import com.project.test.database.firebaseEntities.Post;
import com.project.test.database.firebaseEntities.User;
import com.project.test.database.helper.MockData;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
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
    private String TAG = "MainActivityTEXT";
    private FirebaseAnalytics firebaseAnalytics;
    MockData mockData;
    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    CompositeDisposable disposable = new CompositeDisposable();
    Button crashButton;// = new Button(this);
    private static final int RC_SIGN_IN = 123;
    private String phoneNumber = "";
    private String password = "";
    private boolean sendCode = false;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private boolean mVerificationInProgress = false;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private EditText mPhoneNumberField;
    private EditText mVerificationField;


    LocationManager locationManager;
    private FirebaseUser user;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    /**
     * Code used in requesting runtime permissions.
     */
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    /**
     * Constant used in the location settings dialog.
     */
    private static final int REQUEST_CHECK_SETTINGS = 0x1;

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    // Keys for storing activity state in the Bundle.
    private final static String KEY_REQUESTING_LOCATION_UPDATES = "requesting-location-updates";
    private final static String KEY_LOCATION = "location";
    private final static String KEY_LAST_UPDATED_TIME_STRING = "last-updated-time-string";

    /**
     * Provides access to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    /**
     * Provides access to the Location Settings API.
     */
    private SettingsClient mSettingsClient;

    /**
     * Stores parameters for requests to the FusedLocationProviderApi.
     */
    private LocationRequest mLocationRequest;

    /**
     * Stores the types of location services the client is interested in using. Used for checking
     * settings to determine if the device has optimal location settings.
     */
    private LocationSettingsRequest mLocationSettingsRequest;

    /**
     * Callback for Location events.
     */
    private LocationCallback mLocationCallback;

    /**
     * Represents a geographical location.
     */
    private Location mCurrentLocation;


    /**
     * Tracks the status of the location updates request. Value changes when the user presses the
     * Start Updates and Stop Updates buttons.
     */
    private Boolean mRequestingLocationUpdates;

    /**
     * Time when the location was updated represented as a String.
     */
    private String mLastUpdateTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setLocationSettings();


        FlowManager.init(new FlowConfig.Builder(this).build());
        mockData = new MockData();
        mockData.printAll();
///crah test


        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            FirebasePatrolController.saveNotificationID_Mock_Cestica(FirebaseAuth.getInstance().getCurrentUser().getUid(), refreshedToken);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Crashlytics.log("Rušenje app tst");
        crashButton = new Button(this);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // create class object


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


        manageAutocomplete();


        //location update

        mRequestingLocationUpdates = false;
        mLastUpdateTime = "";

        // Update values using data stored in the Bundle.
        updateValuesFromBundle(savedInstanceState);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);

        // Kick off the process of building the LocationCallback, LocationRequest, and
        // LocationSettingsRequest objects.
        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
        startLocationUpdates();

    }

    /**
     * Uses a {@link com.google.android.gms.location.LocationSettingsRequest.Builder} to build
     * a {@link com.google.android.gms.location.LocationSettingsRequest} that is used for checking
     * if a device has the needed location settings.
     */
    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    /**
     * Sets up the location request. Android has two location request settings:
     * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
     * the AndroidManifest.xml.
     * <p/>
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     * <p/>
     * These settings are appropriate for mapping applications that show real-time location
     * updates.
     */
    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    /**
     * Creates a callback for receiving location events.
     */
    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
                updateLocationUI();
            }
        };
    }


    /**
     * Updates fields based on data stored in the bundle.
     *
     * @param savedInstanceState The activity state saved in the Bundle.
     */
    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Update the value of mRequestingLocationUpdates from the Bundle, and make sure that
            // the Start Updates and Stop Updates buttons are correctly enabled or disabled.
            if (savedInstanceState.keySet().contains(KEY_REQUESTING_LOCATION_UPDATES)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(
                        KEY_REQUESTING_LOCATION_UPDATES);
            }

            // Update the value of mCurrentLocation from the Bundle and update the UI to show the
            // correct latitude and longitude.
            if (savedInstanceState.keySet().contains(KEY_LOCATION)) {
                // Since KEY_LOCATION was found in the Bundle, we can be sure that mCurrentLocation
                // is not null.
                mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            }

            // Update the value of mLastUpdateTime from the Bundle and update the UI.
            if (savedInstanceState.keySet().contains(KEY_LAST_UPDATED_TIME_STRING)) {
                mLastUpdateTime = savedInstanceState.getString(KEY_LAST_UPDATED_TIME_STRING);
            }
            updateUI();
        }
    }


    /**
     * Updates all UI fields.
     */
    private void updateUI() {
        setButtonsEnabledState();
        updateLocationUI();
    }


    /**
     * Sets the value of the UI fields for the location latitude, longitude and last update time.
     */
    private void updateLocationUI() {
        if (mCurrentLocation != null) {

            Log.d(TAG, String.format(Locale.ENGLISH, "%f",
                    mCurrentLocation.getLatitude()) + " - " +
                    String.format(Locale.ENGLISH, "%f",
                            mCurrentLocation.getLongitude()) + " - " +
                    String.format(Locale.ENGLISH, "%s", mLastUpdateTime)
            );


        }
    }

    /**
     * Disables both buttons when functionality is disabled due to insuffucient location settings.
     * Otherwise ensures that only one button is enabled at any time. The Start Updates button is
     * enabled if the user is not requesting location updates. The Stop Updates button is enabled
     * if the user is requesting location updates.
     */
    private void setButtonsEnabledState() {
        if (mRequestingLocationUpdates) {


        } else {


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i(TAG, "User agreed to make required location settings changes.");
                        // Nothing to do. startLocationupdates() gets called in onResume again.

                        startLocationUpdates();
                        checkIfExistNewIntervention(true);
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i(TAG, "User chose not to make required location settings changes.");
                        mRequestingLocationUpdates = false;
                        checkIfExistNewIntervention(false);
                        updateUI();
                        break;
                }
                break;
        }
    }

    /**
     * Handles the Start Updates button and requests start of location updates. Does nothing if
     * updates have already been requested.
     */
    public void startUpdatesButtonHandler(View view) {
        if (!mRequestingLocationUpdates) {
            mRequestingLocationUpdates = true;
            setButtonsEnabledState();
            startLocationUpdates();
        }
    }

    /**
     * Requests location updates from the FusedLocationApi. Note: we don't call this unless location
     * runtime permission has been granted.
     */
    private void startLocationUpdates() {
        // Begin by checking if the device has the necessary location settings.
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");
                        // Permission granted.
                        checkIfExistNewIntervention(true);
                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                        updateUI();
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);
                                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                                mRequestingLocationUpdates = false;
                        }

                        updateUI();
                    }
                });
    }

    /**
     * Removes location updates from the FusedLocationApi.
     */
    private void stopLocationUpdates() {
        if (!mRequestingLocationUpdates) {
            Log.d(TAG, "stopLocationUpdates: updates never requested, no-op.");
            return;
        }

        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mRequestingLocationUpdates = false;
                        setButtonsEnabledState();
                    }
                });
    }


    @Override
    protected void onPause() {
        super.onPause();

        // Remove location updates to save battery.
        stopLocationUpdates();
    }

    /**
     * Stores activity data in the Bundle.
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(KEY_REQUESTING_LOCATION_UPDATES, mRequestingLocationUpdates);
        savedInstanceState.putParcelable(KEY_LOCATION, mCurrentLocation);
        savedInstanceState.putString(KEY_LAST_UPDATED_TIME_STRING, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
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
        Snackbar.make(
                findViewById(android.R.id.content),
                getString(mainTextStringId),
                Snackbar.LENGTH_INDEFINITE)
                .setAction(getString(actionStringId), listener).show();
    }

    /**
     * Return the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
            showSnackbar(R.string.permission_rationale,
                    android.R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    });
        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
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
                if (mRequestingLocationUpdates) {
                    Log.i(TAG, "Permission granted, updates requested, starting location updates");
                    startLocationUpdates();
                }
            } else {
                // Permission denied.

                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.

                // Additionally, it is important to remember that a permission might have been
                // rejected without asking the user for permission (device policy or "Never ask
                // again" prompts). Therefore, a user interface affordance is typically implemented
                // when permissions are denied. Otherwise, your app could appear unresponsive to
                // touches or interactions which have required permissions.
                showSnackbar(R.string.permission_denied_explanation,
                        R.string.settings, new View.OnClickListener() {
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

    /**
     * Handles the Stop Updates button, and requests removal of location updates.
     */
    public void stopUpdatesButtonHandler(View view) {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        stopLocationUpdates();
    }
    //end new location

    private void setLocationSettings() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        if (!checkPermissions()) {
            requestPermissions();

        } else {
            checkIfExistNewIntervention(true);

        }


    }

    //end location

    private void CheckIfExistNewIntervention(Intent intent, boolean locationPermission) {
        String message = intent.getStringExtra("message");

        String intervention_id = intent.getStringExtra("intervention_id");
        if (message != null && intervention_id != null) {
            try {

                Log.d(TAG, "EXCEPTION: " + "SESSION FRAGMENT_idkuce: " + message);

                showMyDialog("Message", message, intervention_id, intent, locationPermission);

                if (locationPermission) {
                    Disposable subscribe2 = GoogleLocation.getGoogleApiClient(this)
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

    private void auth() {
        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        // Initialize phone auth callbacks
        // Initialize phone auth callbacks
        // [START phone_auth_callbacks]
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                // [START_EXCLUDE silent]
                // Update the UI and attempt sign in with the phone credential
                // updateUI(STATE_VERIFY_SUCCESS, credential);
                // [END_EXCLUDE]
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    mPhoneNumberField.setError("Invalid phone number.");
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
                // updateUI(STATE_VERIFY_FAILED);
                // [END_EXCLUDE]
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // [START_EXCLUDE]
                // Update UI
                //   updateUI(STATE_CODE_SENT);
                // [END_EXCLUDE]
            }
        };


        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onVerificationCompleted(PhoneAuthCredential credential) {
                    // This callback will be invoked in two situations:
                    // 1 - Instant verification. In some cases the phone number can be instantly
                    //     verified without needing to send or enter a verification code.
                    // 2 - Auto-retrieval. On some devices Google Play services can automatically
                    //     detect the incoming verification SMS and perform verification without
                    //     user action.
                    Log.d(TAG, "onVerificationCompleted:" + credential);

                    // signInWithPhoneAuthCredential(credential);
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    // This callback is invoked in an invalid request for verification is made,
                    // for instance if the the phone number format is not valid.
                    Log.w(TAG, "onVerificationFailed", e);

                    if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        // Invalid request
                        // ...
                    } else if (e instanceof FirebaseTooManyRequestsException) {
                        // The SMS quota for the project has been exceeded
                        // ...
                    }

                    // Show a message and update the UI
                    // ...
                }

                @Override
                public void onCodeSent(String verificationId,
                                       PhoneAuthProvider.ForceResendingToken token) {
                    // The SMS verification code has been sent to the provided phone number, we
                    // now need to ask the user to enter the code and then construct a credential
                    // by combining the code with a verification ID.
                    Log.d(TAG, "onCodeSent:" + verificationId);

                    // Save verification ID and resending token so we can use them later
                    //   mVerificationId = verificationId;
                    //   mResendToken = token;

                    // ...
                }
            };

            LayoutInflater li = LayoutInflater.from(this);
            View prompt = li.inflate(R.layout.login_dialog, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setView(prompt);
            mPhoneNumberField = (EditText) prompt.findViewById(R.id.login_name);
            mVerificationField = (EditText) prompt.findViewById(R.id.login_password);
            mPhoneNumberField.setText(phoneNumber);
            mVerificationField.setText(password);

            if (sendCode) {
                mVerificationField.setVisibility(View.VISIBLE);
            } else {
                mVerificationField.setVisibility(View.VISIBLE);
            }

            //user.setText(Login_USER); //login_USER and PASS are loaded from previous session (optional)
            //pass.setText(Login_PASS);
            alertDialogBuilder.setTitle("My Site LOGIN");
            alertDialogBuilder.setCancelable(false)
                    .setPositiveButton("Prijava", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            password = mVerificationField.getText().toString();
                            phoneNumber = mPhoneNumberField.getText().toString();
                            try {
                                if (phoneNumber.length() < 8) {
                                    Toast.makeText(MainActivity.this, "Morate upisati mobilni broj", Toast.LENGTH_LONG).show();
                                    auth();
                                } else if (phoneNumber.length() >= 8 && password.length() > 5) {


                                    //startPhoneNumberVerification(phoneNumber);
                                    signWithPassword(phoneNumber, password);
                                    mVerificationField.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity.this, "Poslan je kod", Toast.LENGTH_LONG).show();
                                    //  auth();
                                } else {
                                    auth();
                                }
                            } catch (Exception e) {
                                auth();
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });


            alertDialogBuilder.show();
            if (phoneNumber.length() > 1) //if we have the username saved then focus on password field, be user friendly :-)
                mVerificationField.requestFocus();


        }


    }

    private void signWithPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Uspješna prijava!!",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(user);
                        } else {
                            auth();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Neuspješna prijava!!",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });


    }

    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        mVerificationInProgress = true;
    }

    // [START sign_in_with_phone]
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // [START_EXCLUDE]
                            //  updateUI(STATE_SIGNIN_SUCCESS, user);
                            // [END_EXCLUDE]
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                // [START_EXCLUDE silent]
                                mVerificationField.setError("Invalid code.");
                                // [END_EXCLUDE]
                            }
                            // [START_EXCLUDE silent]
                            // Update UI
                            // updateUI(STATE_SIGNIN_FAILED);
                            // [END_EXCLUDE]
                        }
                    }
                });
    }

    // [END sign_in_with_phone]
    private void manageAutocomplete() {

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
                if (s.length() > 3) {

                    Log.d(TAG, "PROMJENA TEXTA: " + s);
                    // Log.d(TAG, "START: " + start);
                    //  Log.d(TAG, "BEFORE: " + before);
                    fillAutoCompleteList(s);


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

    private void fillAutoCompleteList(CharSequence s) {

        Disposable subscribe2 = HouseController.getHouseAutoCompleteList(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<String>>() {


                    @Override
                    public void onSuccess(List<String> strings) {
                        fillAutoCompleteListbyArray(strings);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

        disposable.add(subscribe2);

    }

    private void fillAutoCompleteListbyArray(List<String> autocompleteListOfStrings) {
        //ArrayAdapter for autoCompleteTextView and its merging with layout autocompleteTextView item
        final String[] autoCompleteStrings = autocompleteListOfStrings.toArray(new String[autocompleteListOfStrings.size()]);
        List<String> listAutoCompleteStrings = Arrays.asList(autoCompleteStrings);
        CustomAutocompleteAdapter adapter = new CustomAutocompleteAdapter(this, android.R.layout.simple_dropdown_item_1line, listAutoCompleteStrings);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);

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


        Intent Intent = new Intent(view.getContext(), SearchingResultsActivity.class);

        Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());

        startActivity(Intent);


        // firebase();

        //RxJava();


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
        disposable.add(subscribe1);
        Disposable subscribe2 = HouseController.getAllHouseRecordsCloud()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<com.project.test.database.firebaseEntities.House>>() {

                    @Override
                    public void onSuccess(List<com.project.test.database.firebaseEntities.House> houses) {
                        // work with the resulting todos
                        printHouses(houses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                    }
                });
        disposable.add(subscribe2);

         /*
                .subscribe(post -> {
                    updateTheUserInterface(post); // this methods updates the ui

                });*/


    }

    private void printHouses(List<com.project.test.database.firebaseEntities.House> houses) {
        for (com.project.test.database.firebaseEntities.House h :
                houses
                ) {

            Log.d("HOUSES:", h.getAddressStreet());


        }


    }

    private void updateTheUserInterface(com.project.test.database.firebaseEntities.Post integer) {
        Log.d("RXJAVA", integer.getName());

        autoCompleteTextView.setText(integer.getName() + "_");

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
                //  showMyDialog("Message", b);


                Disposable subscribe2 = GoogleLocation.getGoogleApiClient(this)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Location>() {

                            @Override
                            public void onSuccess(Location location) {
                                Log.d(TAG, "success + " + location.toString());
                                // work with the resulting todos
                                InterventionTrackController.sendRecievedCallEvent_fireman(b, FirebaseAuth.getInstance().getUid(), location);
                            }

                            @Override
                            public void onError(Throwable e) {
                                // handle the error case
                                Log.d(TAG, "error + " + e.getMessage());

                            }
                        });

                disposable.add(subscribe2);


            }
        }


    }

    private void showMyDialog(String t, String b, String Intervention_id, Intent intent, boolean locatioonPermiss) {

        String houses_id = intent.getStringExtra("houses_id");

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.notification_dialog, null);

        dialog.setView(dialogView);

        dialog.setTitle(t);
        TextView tv = (TextView) dialogView.findViewById(R.id.message);
        tv.setText(b);
        dialog.setPositiveButton("Dolazim!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (locatioonPermiss) {
                    Disposable subscribe2 = GoogleLocation.getGoogleApiClient(getBaseContext())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver<Location>() {

                                @Override
                                public void onSuccess(Location location) {
                                    if (houses_id != null) {
                                        Intent intent = new Intent(getBaseContext(), NewInterventionActivity.class);
                                        // Set the Activity to start in a new, empty task
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.putExtra("EXTRA_SESSION_ID", houses_id);
                                        getBaseContext().startActivity(intent);

                                    }

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


                                }
                            });

                    disposable.add(subscribe2);

                } else {
                    InterventionTrackController.sendComingEvent_fireman(Intervention_id, FirebaseAuth.getInstance().getUid(), null);

                }


            }
        });
        dialog.show();
    }
    //firebase message end


    @Override
    protected void onStart() {
        super.onStart();
        auth();

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
        // Within {@code onPause()}, we remove location updates. Here, we resume receiving
        // location updates if the user has requested them.
        if (mRequestingLocationUpdates && checkPermissions()) {
            startLocationUpdates();
        } else if (!checkPermissions()) {
            requestPermissions();
        }

        updateUI();
        checkIfExistUnfinishedIntervention();
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }

    //START location permission
    @SuppressWarnings("MissingPermission")
    private void checkIfExistNewIntervention(boolean location) {
        CheckIfExistNewIntervention(getIntent(), location);

    }


    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }


    /**
     * Callback received when a permissions request has been completed.
     *//*
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
    }*/
    //END location permission
}
