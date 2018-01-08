package com.kizo.web_services;

import com.kizo.web_services.responses.AirWebServiceResponse;
import com.kizo.web_services.responses.WS_entities.HousesW;
import com.kizo.web_services.responses.WS_entities.InterventionTypeW;
import com.kizo.web_services.responses.WS_entities.OutdoorTypeW;
import com.kizo.web_services.responses.WS_entities.SortOfInterventionW;
import com.kizo.web_services.responses.WS_entities.SpatialSpreadW;
import com.kizo.web_services.responses.WS_entities.SpreadingSmokeW;
import com.kizo.web_services.responses.WS_entities.TimeSpreadW;
import com.kizo.web_services.responses.WS_entities.TypeOfTruckW;
import com.kizo.web_services.responses.WS_entities.TypeOfUnitW;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Post;
import com.project.test.database.Entities.fire_intervention.Size_of_fire;

import java.sql.Time;
import java.util.List;

import retrofit.Response;

import static android.R.string.ok;

/**
 * Created by Zoran on 25.11.2017..
 */

public interface AirWebServiceHandler {
    void onDataArrived(List<Post> result,
                       List<PhotoType> photoTypes,
                       List<HousesW> housesWs,
                       Response<AirWebServiceResponse> response,
                       /*List<SortOfInterventionW> sortOfInterventionWs,
                       List<InterventionTypeW> interventionTypeWs,
                       List<OutdoorTypeW> outdoorTypeWs,
                       List<Size_of_fire> size_of_fires,
                       List<SpatialSpreadW> spatialSpreadWs,
                       List<SpreadingSmokeW> spreadingSmokeWs,
                       List<TimeSpreadW> timeSpreadWs,
                       List<TypeOfTruckW>typeOfTruckWs,
                       List<TypeOfUnitW> typeOfUnitWs,*/

                       boolean ok);


}
