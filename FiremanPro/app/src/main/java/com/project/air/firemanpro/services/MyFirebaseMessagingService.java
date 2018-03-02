package com.project.air.firemanpro.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.project.air.firemanpro.MainActivity;
import com.project.air.firemanpro.R;
import com.project.air.firemanpro.newIntervention.NewInterventionActivity;


/**
 * Created by Zlatko on 29.11.2016..
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "DISCOUNT_LOCATOR";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        // sendNotification(remoteMessage);
        //  createNotification1(remoteMessage);
        sendNotification1(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), "NEWintervention", remoteMessage);
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(RemoteMessage messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("body", messageBody.getNotification().getBody());

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(messageBody.getNotification().getTitle())
                .setContentText(messageBody.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }




    private void sendNotification1(String title, String messageBody, String click_action, RemoteMessage remoteMessage) {
        Intent intent;
        if (click_action.equals("NEWintervention")) {
            intent = new Intent(this, NewInterventionActivity.class);
            intent.putExtra("EXTRA_SESSION_ID", remoteMessage.getData().get("houses_id"));
            intent.putExtra("message", remoteMessage.getData().get("message"));
            intent.putExtra("intervention_id", remoteMessage.getData().get("intervention_id"));
            intent.putExtra("houses_id", remoteMessage.getData().get("houses_id"));



            Log.d(TAG, remoteMessage.getData().get("houses_id"));
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } else if (click_action.equals("MAINACTIVITY")) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("EXTRA_SESSION_ID", "Cestica_Ulica Ljudevita Gaja_26");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        } else {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("EXTRA_SESSION_ID", "Cestica_Ulica Ljudevita Gaja_26");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}