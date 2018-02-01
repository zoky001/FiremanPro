package com.project.test.database;

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
import com.project.test.database.firebaseEntities.Cost;
import com.project.test.database.firebaseEntities.Fireman;
import com.project.test.database.firebaseEntities.Fireman_patrol;
import com.project.test.database.firebaseEntities.Truck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zoran on 1.2.2018..
 */

public class FirebasePatrolController {


    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static CollectionReference fireman_Patrol_collection = db.collection("fireman_Patrol");

    public static void fireStore() {
        final String TAG = "FIRESTORE:";


        // Create a new user with a first and last name
        final Map<String, Object> user = new HashMap<>();
        user.put("Type", "DVD");
        user.put("Name", "Križovljan Radovečki");


       final Fireman f1 = new Fireman("ante", "Nogalo", "ACTIVE");


       final Truck t1 = new Truck("Tamić", "NAVAL");

        Cost cost = new Cost(21.54,
                15.5,
                445.5,
                45.5,
                45.5,
                74.5,
                21.54,
                15.5,
                445.5,
                45.5,
                45.5,
                74.5,
                15.5,
                15.00);

        Fireman_patrol firemanPar = new Fireman_patrol(
                "DVD",
                "Kolarovec",
                cost

        );

    /*    Map<String, Object> docData = new HashMap<>();
        docData.put("stringExample", "Hello world!");
        docData.put("booleanExample", true);
        docData.put("numberExample", 3.14159265);
        docData.put("dateExample", new Date());
        docData.put("listExample", Arrays.asList(1, 2, 3));
        docData.put("nullExample", null);

        Map<String, Object> nestedData = new HashMap<>();
        nestedData.put("a", 5);
        nestedData.put("b", true);

        docData.put("objectExample", nestedData);
*/
// Add a new document with a generated ID
     /*   fireman_Patrol_collection.document("1")
                .set(docData)
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
*/

        fireman_Patrol_collection.add(firemanPar.toMap())
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        documentReference.collection("Trucks").add(t1);
                        documentReference.collection("Trucks").add(t1);

                        documentReference.collection("Firemans").add(f1);
                        documentReference.collection("Firemans").add(f1);
                        Map<String, Object> nestedData = new HashMap<>();
                        nestedData.put("REFERENCE", documentReference);
                        documentReference.update(nestedData);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });




        fireman_Patrol_collection
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


    }
}
