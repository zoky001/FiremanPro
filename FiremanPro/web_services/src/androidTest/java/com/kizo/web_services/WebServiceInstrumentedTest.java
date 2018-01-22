package com.kizo.web_services;

import com.kizo.web_services.responses.AirWebServiceResponse;
import com.kizo.web_services.responses.WS_entities.HousesW;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Post;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import retrofit.Response;

import static org.hamcrest.MatcherAssert.assertThat;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import static org.hamcrest.Matchers.is;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class WebServiceInstrumentedTest {

    private static final String FAKE_STRING = "HELLO WORLD";


    @Test
    public void testAirWebServiceCaller() {

        AirWebServiceHandler airWebServiceHandler = new AirWebServiceHandler() {
            @Override
            public void onDataArrived(List<Post> result, List<PhotoType> photoTypes, List<HousesW> housesWs, Response<AirWebServiceResponse> response) {
                // ...then the result should be the expected one.

                System.out.println("KRAJ TESTA");
                assertThat(result.isEmpty(), is(false));
                assertThat(photoTypes.isEmpty(), is(false));
                assertThat(housesWs.isEmpty(), is(false));
                assertThat(response.body() != null, is(false));

            }
        };

        AirWebServiceCaller airWebServiceCaller;
        airWebServiceCaller = new AirWebServiceCaller(airWebServiceHandler);

        System.out.println("Počezak testa");
        airWebServiceCaller.getAll();

    }
}