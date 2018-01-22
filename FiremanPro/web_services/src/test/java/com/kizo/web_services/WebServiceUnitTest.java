package com.kizo.web_services;

import android.content.Context;
import android.content.SharedPreferences;

import com.kizo.core_module.DataLoadedListener;
import com.kizo.core_module.DataLoader;
import com.kizo.web_services.responses.AirWebServiceResponse;
import com.kizo.web_services.responses.WS_entities.HousesW;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Post;
import com.project.test.database.helper.MockData;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import retrofit.Response;

import static org.hamcrest.core.IsInstanceOf.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class WebServiceUnitTest {

    private static final String FAKE_STRING = "HELLO WORLD";


    @Test
    public void testAirWebServiceCaller() {

        AirWebServiceHandler airWebServiceHandler = new AirWebServiceHandler() {
            @Override
            public void onDataArrived(List<Post> result, List<PhotoType> photoTypes, List<HousesW> housesWs, Response<AirWebServiceResponse> response) {
                // ...then the result should be the expected one.
                assertThat(result.isEmpty(), is(false));
                assertThat(photoTypes.isEmpty(), is(false));
                assertThat(housesWs.isEmpty(), is(false));
                assertThat(response.body() != null, is(false));
            }
        };

        AirWebServiceCaller airWebServiceCaller = Mockito.mock(AirWebServiceCaller.class);
        when(airWebServiceCaller.getBaseUrl()).thenReturn("http://fireman-pro.ddns.net/FiremanPro-laravel/api/");


        airWebServiceCaller = new AirWebServiceCaller(airWebServiceHandler);


        airWebServiceCaller.getAll();


    }
}