package com.project.test.database.Entities.firebase;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * Created by Zoran on 6.2.2018..
 */

public abstract class Firestore {

    public static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static CollectionReference fireman_Patrol_collection = db.collection("fireman_Patrol");
    public static CollectionReference sort_of_intervention_collection = db.collection("sort_of_intervention");
    public static CollectionReference outdoor_type_collection = db.collection("outdoor_types");
    public static CollectionReference size_of_fire_collection = db.collection("size_of_fire_types");
    public static CollectionReference spatial_spread_collection = db.collection("spatial_spread_types");
    public static CollectionReference spreading_smoke_collection = db.collection("spreading_smoke_types");
    public static CollectionReference time_spread_collection = db.collection("time_spread_types");
    public static CollectionReference post_collection = db.collection("posts");
    public static CollectionReference house_collection = db.collection("houses");
    public static CollectionReference hydrants_collection = db.collection("hydrants");


}
