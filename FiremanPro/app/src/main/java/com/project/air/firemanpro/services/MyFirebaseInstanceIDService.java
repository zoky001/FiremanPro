package com.project.air.firemanpro.services;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.project.test.database.FirebasePatrolController;

/**
 * Created by Zlatko on 29.11.2016..
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
    private static final String FCM_TOKEN = "FCM_TOKEN";
    private static final String TAG = "DISCOUNT_LOCATOR";
    private static FirebaseUser user;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Log.d(TAG, "Refreshed token: " + " _ save new token");
            FirebasePatrolController.saveNotificationID_Mock_Cestica(user.getUid(),refreshedToken);
        }



        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        saveToken(refreshedToken);
    }

    private void saveToken(String token)
    {
        //save to shared preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putString(FCM_TOKEN, token).apply();

        //send to your own web service
        //TODO
    }
}