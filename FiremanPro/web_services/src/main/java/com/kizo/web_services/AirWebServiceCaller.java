package com.kizo.web_services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.kizo.web_services.responses.AirWebServiceResponse;
import com.kizo.web_services.responses.WS_entities.PhotoTypeW;
import com.kizo.web_services.responses.WS_entities.PostW;
import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Post;
import com.project.test.database.controllers.AddressController;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Zoran on 24.11.2017..
 */

public class AirWebServiceCaller {
    AirWebServiceHandler mAirWebServiceHandler;
    // retrofit object
    Retrofit retrofit;
    // base URL of the web service
    private final String baseUrl = "http://93.139.143.125/FiremanPro-laravel/FiremanPro/server.php/";

    // constructor
    public AirWebServiceCaller(AirWebServiceHandler airWebServiceHandler){

        System.out.println("serviceLaravel: airservice caller");
        this.mAirWebServiceHandler = airWebServiceHandler;
        //todo: implement retrofit object creation

        //To verify what's sending over the network, use Interceptors
        OkHttpClient client = new OkHttpClient();

        // for debuggint use HttpInterceptor
        //client.interceptors().add(new HttpInterceptor());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    // get all records from a web service
    public void getAll(String method, final Type entityType){

        //todo: implement web service call and response handling
        if(entityType == House.class) {
            AirWebService serviceCaller = retrofit.create(AirWebService.class);
            Call<AirWebServiceResponse> call = serviceCaller.getStores();
            System.out.println("serviceLaravel: get ALL");
            if (call != null) {
                System.out.println("serviceLaravel: call nije nunll");
                call.enqueue(new Callback<AirWebServiceResponse>() {
                    @Override
                    public void onResponse(Response<AirWebServiceResponse> response, Retrofit retrofit) {
                        try {
                            System.out.println("serviceLaravel: on response");
                            if (response.isSuccess()) {
                                System.out.println("serviceLaravel: on response Success");
                                if (entityType == House.class) {
                                    System.out.println("Got records...");
                                   // System.out.println(response.body().get(0).getNameOwner());
                                    handleHouses(response);
                                } else {
                                    System.out.println("Unrecognized class");
                                }
                            } else {
                                System.out.println("serviceLaravel: on response NOOSuccess");
                                System.out.println("serviceLaravel: on response error" + response.toString());
                            }

                        } catch (Exception ex) {
                            System.out.println("exception response");
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }










    }

    private void handleHouses(Response<AirWebServiceResponse> response) {
        Gson gson = new Gson();
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        ArrayList<Post> posts = new ArrayList<Post>();
        Post p;
        for (PostW postW :
                response.body().getPostWS()) {
            p = new Post(postW.getPostalCode(),postW.getName(),CurrentDate,CurrentDate);
        posts.add(p);

        }

        ArrayList<PhotoType> photoTypes = new ArrayList<PhotoType>();

        PhotoType photoType;
        for (PhotoTypeW photoTypeW :
                response.body().getPhotoTypeWS()) {
            photoType = new PhotoType(photoTypeW.getId(),photoTypeW.getType(),photoTypeW.getDescription(),CurrentDate,CurrentDate);

            photoTypes.add(photoType);

        }

        ArrayList<House> houses = new ArrayList<House>();

/*
        for ( AirWebServiceResponse a :response.body() ) {

            Post p = new Post(42208,"pošta",CurrentDate,CurrentDate);
            Address adre = new Address(a.getAddressId(),"0ime vulice", "String streetNumber", 16.1212, 46.1515,CurrentDate,CurrentDate,p);
         House hou = new House(
                    a.getId(),
                    a.getNameOwner(),
                    a.getSurnameOwner(),
                    a.getNumberOfTenants(),
                    a.getNumberOfFloors(),
                    a.getListOfFloors(),
                    a.getNumberOfChildren(),
                    a.getYearChildren(),
                    a.getNumberOfAdults(),
                    a.getYearsAdults(),
                    a.getNumberOfPowerlessAndElders(),
                    a.getYearsPowerlessElders(),
                    a.getDisabilityPerson()==1?true:false,
                    a.getPowerSupply(),
                    a.getGasConnection()==1?true:false,
                    a.getTypeOfHeating(),
                    a.getNumberOfGasBottle(),
                    a.getTypeOfRoof(),
                    a.getHydrantDistance(),
                    a.getHighRiskObject()==1?true:false,
                    a.getHROTypeOfRoof(),
                    a.getHROPowerSupply()==1?true:false,
                    a.getHROContent(),
                    a.getHROAnimals()==1?true:false,
                    a.getTelNumber(),
                    a.getMobNumber(),
                    CurrentDate,
                    CurrentDate,
               adre


            );
            houses.add(hou);


        }*/
       // House[] storeItems = gson.fromJson(response.body().toString(), House[].class);

        System.out.println(response.body().getPostWS().get(0).getName());

      /*  Post[] posts = gson.fromJson(response.body().getPost(),Post[].class);

        System.out.println(response.body().getAddress());
        Address[] addresses = gson.fromJson(response.body().getAddress(),Address[].class);
*/
System.out.println("velisicna posat je: " + posts.size());
        System.out.println("velisicna tipova  je: " + photoTypes.size());
        if(mAirWebServiceHandler != null){
            mAirWebServiceHandler.onDataArrived(posts, photoTypes, response.body().getHousesWS(),true);
        }

    }
}