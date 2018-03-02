package com.kizo.goolge_map;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
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


    //new
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    /**
     * Provides the entry point to the Fused Location Provider API.
     */
    private FusedLocationProviderClient mFusedLocationClient;

    /**
     * Represents a geographical location.
     */
    private String mLatitudeLabel;
    private String mLongitudeLabel;

    //new


    private static Location mLastLocation;

    // list of permissions

    ArrayList<String> permissions = new ArrayList<>();
    //PermissionUtils permissionUtils;
    private boolean isPermissionGranted;

    private final static int PLAY_SERVICES_REQUEST = 1000;
    private final static int REQUEST_CHECK_SETTINGS = 2000;
    private static GoogleApiClient mGoogleApiClient;

    private static FusedLocationProviderClient mfusedLocationProviderClient;

    public GoogleLocation(Context context) {

/*
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();
        */

    }

    //start new
    public static Single<Location> getLastLocation(FusedLocationProviderClient mFusedLocationClient, Context context) {

        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {

                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                        mFusedLocationClient.getLastLocation()
                                .addOnCompleteListener(new OnCompleteListener<Location>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Location> task) {
                                        if (task.isSuccessful() && task.getResult() != null) {
                                            mLastLocation = task.getResult();
                                            emitter.onSuccess(mLastLocation);
                                            Log.w(TAG, "getLastLocation:" + task.getResult());

                                        } else {
                                            Log.w(TAG, "getLastLocation:exception", task.getException());
                                            emitter.onError( task.getException());
                                        }
                                    }
                                });


                    }else {
                        emitter.onError(new Exception("No permission.."));
                    }






                } catch (Exception e) {
                    emitter.onError(e);
                }
            });
            thread.start();
        });
    }

    //start new
    public static Single<Location> getLastLocationUpdate(FusedLocationProviderClient mFusedLocationClient, Context context) {

        return Single.create(emitter -> {
            Thread thread = new Thread(() -> {
                try {
                   /* LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
                    builder.addLocationRequest(mLocationRequest);
                    mLocationSettingsRequest = builder.build();
*/


                    LocationCallback mLocationCallback = new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            super.onLocationResult(locationResult);

                          Location  mCurrentLocation = locationResult.getLastLocation();
                            //mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

                        }
                    };

                    if (ActivityCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.

                        mFusedLocationClient.getLastLocation()
                                .addOnCompleteListener(new OnCompleteListener<Location>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Location> task) {
                                        if (task.isSuccessful() && task.getResult() != null) {
                                            mLastLocation = task.getResult();
                                            emitter.onSuccess(mLastLocation);
                                            Log.w(TAG, "getLastLocation:" + task.getResult());

                                        } else {
                                            Log.w(TAG, "getLastLocation:exception", task.getException());
                                            emitter.onError( task.getException());
                                        }
                                    }
                                });


                    }else {
                        emitter.onError(new Exception("No permission.."));
                    }






                } catch (Exception e) {
                    emitter.onError(e);
                }
            });
            thread.start();
        });
    }

    //end new

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
