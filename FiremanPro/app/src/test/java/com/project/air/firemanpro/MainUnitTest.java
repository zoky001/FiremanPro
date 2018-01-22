package com.project.air.firemanpro;

import com.kizo.core_module.DataLoadedListener;
import com.kizo.core_module.DataLoader;
import com.kizo.web_services.AirWebServiceCaller;
import com.kizo.web_services.AirWebServiceHandler;
import com.kizo.web_services.responses.AirWebServiceResponse;
import com.kizo.web_services.responses.WS_entities.HousesW;
import com.project.air.firemanpro.loaders.WsDataLoader;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Post;
import com.project.test.database.controllers.AddressController;
import com.project.test.database.controllers.FiremanPatrolController;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.House_photosController;
import com.project.test.database.controllers.HydrantsController;
import com.project.test.database.controllers.PhotoTypeController;
import com.project.test.database.controllers.PhotosController;
import com.project.test.database.controllers.PostController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import retrofit.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class MainUnitTest {

    @Test
    public void testAirWebServiceCaller() {
        System.out.println("TEST LOADED");


        DataLoadedListener dataLoadedListener = new DataLoadedListener() {
            @Override
            public void onDataLoaded() {

                System.out.println("TEST LOADED");
                assertThat(HouseController.getAllHouseRecords().isEmpty(), is(false));
                assertThat(AddressController.GetAllRecordsFromTable().isEmpty(), is(false));
                assertThat(PhotosController.GetAllRecordsFromTable().isEmpty(), is(false));
                assertThat(PostController.GetAllRecordsFromTable().isEmpty(), is(false));
                assertThat(PhotoTypeController.GetAllRecordsFromTable_Static().isEmpty(), is(false));
                assertThat(HydrantsController.GetAllRecordsFromTableHydrants().isEmpty(), is(false));
                assertThat(House_photosController.GetAllRecordsFromTable().isEmpty(), is(false));
                assertThat(FiremanPatrolController.GetAllRecordsFromTable().isEmpty(), is(false));

            }
        };


        DataLoader dataLoader;// = Mockito.mock(DataLoader.class);

        //when(airWebServiceCaller.getBaseUrl()).thenReturn("http://fireman-pro.ddns.net/FiremanPro-laravel/api/");

        dataLoader = new WsDataLoader();

        dataLoader.loadData(dataLoadedListener);

    }
}