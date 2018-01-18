package com.kizo.web_services;

import com.kizo.web_services.responses.AirWebServiceResponse;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 *
 * Definira metode za komuniakciju sa WS.
 *
 * Definira moguće ENDPOINT-ove na kojima osluškuje/radi WS.
 *
 *
 * Created by Zoran on 24.11.2017..
 *
 * @author Zoran Hrnčić
 */

public interface AirWebService {


    /**
     * Poziva dohvačanje svih podataka sa WS.
     *
     * @return Objekt klase AirWebServiceResponse koja sadrži sve podatke/zapise koje je vratio WS
     */
    @GET("api/allEntries")
    Call<AirWebServiceResponse> getAllEntries();



}
