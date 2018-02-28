package com.project.test.database;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.Hydrants;
import com.project.test.database.Entities.Post;
import com.project.test.database.Entities.fire_intervention.Size_of_fire;
import com.project.test.database.Entities.fire_intervention.Spatial_spread;
import com.project.test.database.Entities.fire_intervention.Spreading_smoke;
import com.project.test.database.Entities.fire_intervention.Time_spread;
import com.project.test.database.Entities.report.Intervention_Type;
import com.project.test.database.Entities.report.Outdoor_type;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.HydrantsController;
import com.project.test.database.controllers.PostController;
import com.project.test.database.controllers.report.Types_all_Controller;
import com.project.test.database.firebaseEntities.Address;
import com.project.test.database.firebaseEntities.Cost;
import com.project.test.database.firebaseEntities.Fireman;
import com.project.test.database.firebaseEntities.Fireman_patrol;
import com.project.test.database.firebaseEntities.House;
import com.project.test.database.firebaseEntities.Truck;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zoran on 1.2.2018..
 */

public class InterventionTrackController {

    final static String TAG = "FIRESTORE:";
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static CollectionReference fireman_Patrol_collection = db.collection("fireman_Patrol");

    private static CollectionReference sort_of_intervention_collection = db.collection("sort_of_intervention");

    private static CollectionReference outdoor_type_collection = db.collection("outdoor_types");
    private static CollectionReference size_of_fire_collection = db.collection("size_of_fire_types");
    private static CollectionReference spatial_spread_collection = db.collection("spatial_spread_types");
    private static CollectionReference spreading_smoke_collection = db.collection("spreading_smoke_types");
    private static CollectionReference time_spread_collection = db.collection("time_spread_types");
    private static CollectionReference post_collection = db.collection("posts");
    private static CollectionReference house_collection = db.collection("houses");
    private static CollectionReference hydrants_collection = db.collection("hydrants");

    private static CollectionReference intervention_track_collection = db.collection("intervention_track");

    public static void sendRecievedCallEvent_fireman(String intervention_id, String fireman_id, Location location){


        // Update one field, creating the document if it does not already exist.
        Map<String, Object> data = new HashMap<>();
        data.put("fireman_id", fireman_id);
        data.put("status","call_recieved");
        data.put("longitude", location.getLongitude());
        data.put("latitude", location.getLatitude());
        intervention_track_collection
                .document(intervention_id)
              .collection("firemans").document(fireman_id).set(data);
              //  .add(data);// id of cestica
              //  .set(data, SetOptions.merge());
    }
    public static void sendComingEvent_fireman(String intervention_id, String fireman_id, Location location){


        // Update one field, creating the document if it does not already exist.
        Map<String, Object> data = new HashMap<>();
        data.put("fireman_id", fireman_id);
        data.put("status","coming");
        data.put("longitude", location.getLongitude());
        data.put("latitude", location.getLatitude());
        intervention_track_collection
                .document(intervention_id)
                .collection("firemans").document(fireman_id).set(data);
        //  .add(data);// id of cestica
        //  .set(data, SetOptions.merge());
    }
    public static void saveFireInterventionType(Sort_of_intervention s) {

        com.project.test.database.firebaseEntities.Sort_of_intervention n = new com.project.test.database.firebaseEntities.Sort_of_intervention(s.getDescription(), s.getName());
        sort_of_intervention_collection.document(String.valueOf(s.getId()))
                .set(n)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        Log.w(TAG, "Error writing document", e);
                    }
                });

    }}


