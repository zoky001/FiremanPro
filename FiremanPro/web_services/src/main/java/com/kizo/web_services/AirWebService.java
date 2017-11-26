package com.kizo.web_services;

import com.kizo.web_services.responses.AirWebServiceResponse;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Zoran on 24.11.2017..
 */

public interface AirWebService {


    @GET("api/allEntries")
    Call<AirWebServiceResponse> getStores();



}
