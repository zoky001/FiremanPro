package com.project.air.firemanpro.googlemaps;

import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;

import butterknife.OnClick;

/**
 * Created by Matea on 11/13/2017.
 */

public class GetDirectionsData extends AsyncTask<Object,String,String> {


    GoogleMap mMap;
    String url;
    String googleDirectionsData;
    String duration, distance;
    LatLng latLng;

    /**
     * Metoda kod omogućuje downlodanje url-a
     *
     * @param  objects - - poslali smko 3 jer nam oni trebaju ( a to su naša karta, url koji je  LINK na stranicu na kojoj je u jsonu ispisan put,
     *                 odnosno podaci za sasstavljanje polyline kako bi class Download url mogao downloadati put,
     *                 latlng koji nam treba kod dodavanja markera u displayDirection
     */

    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        latLng = (LatLng)objects[2];

        DownloadUrl downloadUrl = new DownloadUrl();

        try {
            googleDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {
        String[] directionsList;
        DataParser parser = new DataParser();
        directionsList = parser.parseDirections(s);
        displayDirection(directionsList);
    }

    /**
     * Methoda koja stilizira polyli
     *
     * @param directionsList string[] koja dodaje markere i polylin tako da prikazu put(polyline)
     */

    public void displayDirection(String[] directionsList)
    {
        int count = directionsList.length;
        for(int i = 0;i<count;i++)
        {
            PolylineOptions options = new PolylineOptions();
            options.color(Color.RED);
            options.width(10);
            options.addAll(PolyUtil.decode(directionsList[i]));

            mMap.addPolyline(options);

            mMap.addMarker(new MarkerOptions().position(new LatLng(latLng.latitude, latLng.longitude)));

        }
    }
}


