package com.kizo.web_services;

import com.google.gson.Gson;
import com.kizo.web_services.responses.AirWebServiceResponse;
import com.project.test.database.Entities.Address;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Post;
import com.project.test.database.controllers.AddressController;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Type;
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
    private final String baseUrl = "http://93.142.59.52/FiremanPro-laravel/FiremanPro/server.php/";

    // constructor
    public AirWebServiceCaller(AirWebServiceHandler airWebServiceHandler){
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

        AirWebService serviceCaller = retrofit.create(AirWebService.class);
        Call<List<AirWebServiceResponse>> call = serviceCaller.getStores();

        if(call != null){
            call.enqueue(new Callback<List<AirWebServiceResponse>>() {
                @Override
                public void onResponse(Response<List<AirWebServiceResponse>> response, Retrofit retrofit) {
                    try {
                        if(response.isSuccess()){

                            if(entityType == House.class){
                                System.out.println("Got houses...");
                              System.out.println(response.body().get(0).getNameOwner());
                                handleHouses(response);
                            }  else
                            {
                                System.out.println("Unrecognized class");
                            }
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

    private void handleHouses(Response<List<AirWebServiceResponse>> response) {
        Gson gson = new Gson();
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
        Post p = new Post(12312,"pošta",CurrentDate,CurrentDate);
        Address adre = new Address("sdasd","adas","1",p,12.1515,12.1231,CurrentDate,CurrentDate);
        ArrayList<House> houses = new ArrayList<House>();


        for ( AirWebServiceResponse a :response.body() ) {
            House hou = new House(a.getNameOwner(),
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
                  adre);
            houses.add(hou);


        }
       // House[] storeItems = gson.fromJson(response.body().toString(), House[].class);


        if(mAirWebServiceHandler != null){

            System.out.println("Air service caller dolje... ");
            mAirWebServiceHandler.onDataArrived(houses, true);

        }


    }
}