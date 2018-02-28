package com.kizo.goolge_map;

import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.project.test.database.firebaseEntities.House;

import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zoran on 28.2.2018..
 */

public class GoogleLocation {
    public static final String TAG = "GoogleLocation";
    // LogCat tag



    private static Location mLastLocation;

    // list of permissions

    ArrayList<String> permissions=new ArrayList<>();
    //PermissionUtils permissionUtils;
    private boolean isPermissionGranted;

    private final static int PLAY_SERVICES_REQUEST = 1000;
    private final static int REQUEST_CHECK_SETTINGS = 2000;
    private  static GoogleApiClient mGoogleApiClient;

    public GoogleLocation(Context context) {

/*
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();
        */

    }



    public static Single<Location> getGoogleApiClient(Context context) {

        return Single.create( emitter -> {
            Thread thread = new Thread(() -> {
                try {



                  mGoogleApiClient = new GoogleApiClient.Builder(context)
                            .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                                @Override
                                public void onConnected(@Nullable Bundle bundle) {
                                    emitter.onSuccess(getLocation());



                                }

                                @Override
                                public void onConnectionSuspended(int i) {

                                }
                            })
                            .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                                @Override
                                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                                }
                            })
                            .addApi(LocationServices.API).build();

                    mGoogleApiClient.connect();

                    LocationRequest mLocationRequest = new LocationRequest();
                    mLocationRequest.setInterval(10000);
                    mLocationRequest.setFastestInterval(5000);
                    mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(mLocationRequest);

                    PendingResult<LocationSettingsResult> result =
                            LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

                    result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                        @Override
                        public void onResult(LocationSettingsResult locationSettingsResult) {

                            final Status status = locationSettingsResult.getStatus();

                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:
                                    // All location settings are satisfied. The client can initialize location requests here
                                    emitter.onSuccess(getLocation());
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        //status.startResolutionForResult(GoogleLocation.this, REQUEST_CHECK_SETTINGS);

                                    } catch (Exception e) {
                                        emitter.onError(e);
                                        // Ignore the error.
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    break;
                            }
                        }
                    });


                } catch (Exception e) {
                    emitter.onError(e);
                }
            });
            thread.start();
        });
    }
    private static Location getLocation() {


      //  if (isPermissionGranted) {

            try
            {
             return    mLastLocation = LocationServices.FusedLocationApi
                        .getLastLocation(mGoogleApiClient);

             
            }
            catch (SecurityException e)
            {
                e.printStackTrace();
                return null;
            }

       // }

    }

    private boolean checkPlayServices(Context context) {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {


            } else {

            }
            return false;
        }
        return true;
    }


}
