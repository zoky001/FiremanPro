package com.kizo.web_services;

import com.kizo.web_services.responses.AirWebServiceResponse;
import com.kizo.web_services.responses.WS_entities.PhotoTypeW;
import com.kizo.web_services.responses.WS_entities.PostW;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Post;
import com.project.test.database.Entities.Settings;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Sadrži metode za uspostavu veze sa Web Servisom, te svaka metoda definira koji će se podaci dohvaćati putem WS.
 * <p>
 * Koristi RETROFIT
 * <p>
 * Definra kojim će se metodama upravljati/handlati pristiglim podatcima.
 * <p>
 * <p>
 * Created by Zoran on 24.11.2017..
 *
 * @author Zoran Hrnčić
 */

public class AirWebServiceCaller {
    AirWebServiceHandler mAirWebServiceHandler;
    // retrofit object
    Retrofit retrofit;
    // base URL of the web service


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private String baseUrl;


    // constructor
    public AirWebServiceCaller(AirWebServiceHandler airWebServiceHandler) {

        try {

            setBaseUrl(Settings.getSettings().getWebServicesAddress());

        } catch (Exception e) {
            setBaseUrl("http://fireman-pro.ddns.net/FiremanPro-laravel/api/");
        }

        this.mAirWebServiceHandler = airWebServiceHandler;
        //todo: implement retrofit object creation

        //To verify what's sending over the network, use Interceptors
        OkHttpClient client = new OkHttpClient();

        // for debuggint use HttpInterceptor
        //client.interceptors().add(new HttpInterceptor());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    /**
     * Dohvaća sve zapise iz kompletne baze podataka.
     */
    public void getAll() {
        AirWebService serviceCaller = retrofit.create(AirWebService.class);
        Call<AirWebServiceResponse> call = serviceCaller.getAllEntries();
        System.out.println("TEST LOADED");

        if (call != null) {

            call.enqueue(new Callback<AirWebServiceResponse>() {

                /**
                 * U slučaju uspješnog dovačanja podataka sa WS pokreće definira metode za upravljanje istima.
                 * @param response odgovor WS koji sadrži sve zapise iz baze podataka
                 * @param retrofit
                 */
                @Override
                public void onResponse(Response<AirWebServiceResponse> response, Retrofit retrofit) {

                    try {
                        if (response.isSuccess()) {
                            handleAllEntries(response);

                        } else {
                        }

                    } catch (Exception ex) {
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

    /**
     * Upravlja svim zapisima pristiglom kao odgovor od WS.
     * <p>
     * <p>
     * Prosljeđuje podatke dalje na obradu putem metode onDataArrived iz interface-a mAirWebServiceHandler
     *
     * @param response odgovor sa svim zapisima iz baze podataka
     */
    private void handleAllEntries(Response<AirWebServiceResponse> response) {

        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

        ArrayList<Post> posts = new ArrayList<Post>();
        Post p;
        for (PostW postW :
                response.body().getPostWS()) {
            p = new Post(postW.getPostalCode(), postW.getName(), CurrentDate, CurrentDate);
            posts.add(p);

        }

        ArrayList<PhotoType> photoTypes = new ArrayList<PhotoType>();

        PhotoType photoType;
        for (PhotoTypeW photoTypeW :
                response.body().getPhotoTypeWS()) {
            photoType = new PhotoType(photoTypeW.getId(), photoTypeW.getType(), photoTypeW.getDescription(), CurrentDate, CurrentDate);

            photoTypes.add(photoType);

        }

        if (mAirWebServiceHandler != null) {
            mAirWebServiceHandler.onDataArrived(posts, photoTypes, response.body().getHousesWS(), response);
        }

    }
}