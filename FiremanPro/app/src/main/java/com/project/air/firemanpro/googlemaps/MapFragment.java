package com.project.air.firemanpro.googlemaps;

//import android.app.Fragment;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;



public class MapFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks, LocationListener, GoogleApiClient.OnConnectionFailedListener {

    MapView mMapView;
    private GoogleMap mMap;
    House house;
    Context context;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentLocationMarker;
    Double latitude, longitude, end_latitude, end_longitude;
    int markerCount;
    public static  final int REQUEST_LOCATION_CODE = 99;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);


        String s = getArguments().getString("IDkuce");
        System.out.println("SESSION FRAGMENT_idkuce: " + s);
        int a = Integer.parseInt(getArguments().getString("IDkuce"));

        if (a != -1) {

            house = HouseController.getHouse(a);

        } else {
            house = HouseController.getFirstHouse();
        }
        end_latitude=house.getAddress().getLatitude();
        end_longitude=house.getAddress().getLongitude();

        System.out.println("Trenutni latitude: " + end_latitude);
        System.out.println("Trenutni longitude: " + end_longitude);


        context = getActivity();

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap1) {
                mMap = mMap1;

                // For showing a move to my location button
                //googleMap.setMyLocationEnabled(true);

                if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {
                    buildGoogleApiClient();
                    mMap.setMyLocationEnabled(true);
                }
            }
        });

        return rootView;
    }

    public  boolean checkLocationPermission() {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            else {
                ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;

        }else
            return true;
    }



    protected synchronized void buildGoogleApiClient(){
        client = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        client.connect();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();

        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;

        if(currentLocationMarker != null){
            currentLocationMarker.remove();
        }

        if(client != null){
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this);
        }

        //Displaying the new location on UI
        displayLocation();
    }

    private void displayLocation() {
        if (lastLocation != null) {
            double latitude = lastLocation.getLatitude();
            double longitude = lastLocation.getLongitude();
            String loc = "" + latitude + " ," + longitude + " ";
            //Toast.makeText(this,loc, Toast.LENGTH_SHORT).show();

            //Add pointer to the map at location
            addMarker(mMap,latitude,longitude);

            Object dataTransfer[] = new Object[2];

            House h = HouseController.getFirstHouse();

            end_latitude = h.getAddress().getLatitude();
            end_longitude = h.getAddress().getLongitude();
            dataTransfer = new Object[3];
            String url = getDirectionsUrl();
            GetDirectionsData getDirectionsData = new GetDirectionsData();
            dataTransfer[0] = mMap;
            dataTransfer[1] = url;
            dataTransfer[2] = new LatLng(end_latitude, end_longitude);
            getDirectionsData.execute(dataTransfer);
        } else {
            Toast.makeText(context, "Couldn't get the location. Make sure location is enabled on the device", Toast.LENGTH_SHORT).show();
        }
    }

    private void addMarker(GoogleMap googleMap, double lat, double lon) {
        // Add A Map Pointer To The MAp
        if(markerCount==1){
            animateMarker(lastLocation,currentLocationMarker);
        }
        else if (markerCount==0){
            //Set Custom BitMap for Pointer
            int height = 80;
            int width = 45;
            BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.mipmap.fireman_truck);
            Bitmap b = bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            mMap = googleMap;

            LatLng latlong = new LatLng(lat, lon);
            currentLocationMarker= mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon))
                    //.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin3))
                    .icon(BitmapDescriptorFactory.fromBitmap((smallMarker))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlong, 16));

            //Set Marker Count to 1 after first marker is created
            markerCount=1;

               /* if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    TODO: Consider calling
                    return;
                }
                //mMap.setMyLocationEnabled(true);
                //startLocationUpdates();
                */
        }
    }

    public static void animateMarker(final Location destination, final Marker marker) {
        if (marker != null) {
            final LatLng startPosition = marker.getPosition();
            final LatLng endPosition = new LatLng(destination.getLatitude(), destination.getLongitude());

            final float startRotation = marker.getRotation();

            final MapFragment.LatLngInterpolator latLngInterpolator = new MapFragment.LatLngInterpolator.LinearFixed();
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
            valueAnimator.setDuration(1000); // duration 1 second
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override public void onAnimationUpdate(ValueAnimator animation) {
                    try {
                        float v = animation.getAnimatedFraction();
                        LatLng newPosition = latLngInterpolator.interpolate(v, startPosition, endPosition);
                        marker.setPosition(newPosition);
                        // marker.setRotation(computeRotation(v, startRotation, destination.getBearing()));
                    } catch (Exception ex) {
                        // I don't care atm..
                    }
                }
            });

            valueAnimator.start();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case REQUEST_LOCATION_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission is granted
                    if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if(client == null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                    else{ //permission is denied
                        Toast.makeText(context, "Permission denied!", Toast.LENGTH_LONG).show();
                    }
                    return;
                }

        }
    }

    private interface LatLngInterpolator {
        LatLng interpolate(float fraction, LatLng a, LatLng b);

        class LinearFixed implements LatLngInterpolator {
            @Override
            public LatLng interpolate(float fraction, LatLng a, LatLng b) {
                double lat = (b.latitude - a.latitude) * fraction + a.latitude;
                double lngDelta = b.longitude - a.longitude;
                // Take the shortest path across the 180th meridian.
                if (Math.abs(lngDelta) > 180) {
                    lngDelta -= Math.signum(lngDelta) * 360;
                }
                double lng = lngDelta * fraction + a.longitude;
                return new LatLng(lat, lng);
            }
        }
    }

    private String getDirectionsUrl()
    {
        latitude = currentLocationMarker.getPosition().latitude;
        longitude = currentLocationMarker.getPosition().longitude;

        StringBuilder googleDirectionsUrl = new StringBuilder("https://maps.googleapis.com/maps/api/directions/json?");
        googleDirectionsUrl.append("origin="+latitude+","+longitude);
        googleDirectionsUrl.append("&destination="+end_latitude+","+end_longitude);
        googleDirectionsUrl.append("&key="+"AIzaSyBJhtyyzFP23u76XfWqXKQ6XdPwHwID2Go");

        return googleDirectionsUrl.toString();
    }
}

