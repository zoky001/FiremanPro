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
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.Hydrants;
import com.project.test.database.Entities.Photos;
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

    final static String TAG = "FIRESTORE:";
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static CollectionReference fireman_Patrol_collection = db.collection("fireman_Patrol");

    private static CollectionReference sort_of_intervention_collection = db.collection("sort_of_intervention");

    private static CollectionReference outdoor_type_collection = db.collection("outdoor_types");
    private static CollectionReference size_of_fire_collection = db.collection("size_of_fire_types");
    private static CollectionReference spatial_spread_collection = db.collection("spatial_spread_types");
    private static CollectionReference spreading_smoke_collection = db.collection("spreading_smoke_types");
    private static CollectionReference time_spread_collection  = db.collection("time_spread_types");
    private static CollectionReference post_collection = db.collection("posts");
    private static CollectionReference house_collection = db.collection("houses");
    private static CollectionReference hydrants_collection = db.collection("hydrants");


    public static void saveFireInterventionType(Sort_of_intervention s){

        com.project.test.database.firebaseEntities.Sort_of_intervention n = new com.project.test.database.firebaseEntities.Sort_of_intervention(s.getDescription(),s.getName());
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

    }
    public static void saveInterventionType()
    {
        Types_all_Controller types_all_controller = new Types_all_Controller();
        types_all_controller.GetAllRecordsFromTable_Intervention_type();
        for (Intervention_Type i:
        types_all_controller.GetAllRecordsFromTable_Intervention_type()) {

            final Map<String, Object> type = new HashMap<>();
            type.put("Name", i.getName());




            sort_of_intervention_collection
                    .document(String.valueOf(i.getSort_of_intervention().getId()))
                    .collection("types")
                    .document(String.valueOf(i.getId()))
                    .set(type);





        }

    }
    public static void saveOtherType()
    {
        Types_all_Controller types_all_controller = new Types_all_Controller();

        for (Outdoor_type i:
                types_all_controller.GetAllRecordsFromTable_Outdoor_type()) {

            final Map<String, Object> type = new HashMap<>();
            type.put("Name", i.getName());

            outdoor_type_collection
                    .document(String.valueOf(i.getId()))
                    .set(type);
        }

        for (Size_of_fire i:
                types_all_controller.GetAllRecordsFromTable_Size_of_fire()) {

            final Map<String, Object> type = new HashMap<>();
            type.put("Name", i.getName());

            size_of_fire_collection
                    .document(String.valueOf(i.getId()))
                    .set(type);
        }

        for (Spatial_spread i:
                types_all_controller.GetAllRecordsFromTable_Spatial_spread()) {

            final Map<String, Object> type = new HashMap<>();
            type.put("Name", i.getName());

            spatial_spread_collection
                    .document(String.valueOf(i.getId()))
                    .set(type);
        }

        for (Spreading_smoke i:
                types_all_controller.GetAllRecordsFromTable_Spreading_smoke()) {

            final Map<String, Object> type = new HashMap<>();
            type.put("Name", i.getName());

            spreading_smoke_collection
                    .document(String.valueOf(i.getId()))
                    .set(type);
        }

        for (Time_spread i:
                types_all_controller.GetAllRecordsFromTable_Time_spread()) {

            final Map<String, Object> type = new HashMap<>();
            type.put("Name", i.getName());

            time_spread_collection
                    .document(String.valueOf(i.getId()))
                    .set(type);
        }
        for (Post i:
                PostController.GetAllRecordsFromTable()) {

            final Map<String, Object> type = new HashMap<>();
            type.put("Name", i.getName());

            post_collection
                    .document(String.valueOf(i.getPostal_code()))
                    .set(type);
        }
        for (Hydrants i:
                HydrantsController.GetAllRecordsFromTableHydrants()) {

            final Map<String, Object> address = new HashMap<>();
            address.put("StreetName", i.getAddress().getStreetNumber());
            address.put("StreetNumber", i.getAddress().getStreetNumber());
            address.put("Longitude", i.getAddress().getLongitude());
            address.put("Latitude", i.getAddress().getLatitude());
            address.put("Place", i.getAddress().getPlaceNameIfExist());
            address.put("PostRef", post_collection.document(String.valueOf(i.getAddress().getPost().getPostal_code())));



            final Map<String, Object> type = new HashMap<>();
            type.put("Type", i.getType_of_hydrant());
            type.put("Description", i.getDescription());
            type.put("Address", address);



            hydrants_collection
                    .add(type);
        }

    }
    public static void saveAllHouse()
    {Address address;
    House house;
        Types_all_Controller types_all_controller = new Types_all_Controller();

        for (final com.project.test.database.Entities.House i:
                HouseController.getAllHouseRecords()) {

           address = new Address(i.getAddress().getPlaceNameIfExist(),i.getAddress().getStreetNameIfExist(),i.getAddress().getStreetNumber(),i.getAddress().getLongitude(),i.getAddress().getLatitude(),i.getAddress().getPost().getPostal_code());
            house = new House(
                    i.getName_owner(),
                    i.getSurname_owner(),
                    i.getNumber_of_tenants(),
                    i.getNumber_of_floors(),
                    i.getList_of_floors(),
                    i.getNumber_of_children(),
                    i.getYear_children(),
                    i.getNumber_of_adults(),
                    i.getYears_adults(),
                    i.getNumber_of_powerless_and_elders(),i.getYears_powerless_elders(),
                    i.isDisability_person(),i.getPower_supply(),
                    i.isGas_connection(),
                    i.getType_of_heating(),
                    i.getNumber_of_gas_bottle(),
                    i.getType_of_roof(),
                    i.getHydrant_distance(),i.isHigh_risk_object(),
                    i.getHRO_type_of_roof(),
                    i.isHRO_power_supply(),
                    i.getHRO_content(),
                    i.isHRO_animals(),
                    i.getTelNumber(),
                    i.getMobNumber(),address,
                    i.getProfilPhotos().getUrl()
            );
            house_collection
                    .add(house.toMap())
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            for (House_photos p:i.getGroundPlanPhotos()
                                 ) {

                                final Map<String, Object> pic = new HashMap<>();
                                pic.put("Name", p.getPhoto().getImageName());
                                pic.put("Url", p.getPhoto().getUrl());

                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                documentReference.collection("ground_plan_pictures").add(pic);

                            }

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }




    }
    public static void fireStore() {



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
