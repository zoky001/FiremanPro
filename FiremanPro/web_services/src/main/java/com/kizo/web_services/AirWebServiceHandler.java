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
 *
 * Definiraju se metode za upravljanje podatcma pristiglim od Web servisa
 * <p>
 * Created by Zoran on 25.11.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */
public interface AirWebServiceHandler {


    /**
     * Definira podatke prstigle od Web Servisa.
     * @param result lista poštanskih ureda
     * @param photoTypes popis tipova slika
     * @param housesWs popis kuća sa svim podatcima
     * @param response svi ostali podatci pristigli u odgovoru (Tipovi požara, popis vatrogasaca, popis vozila...)
     * @author Zoran Hrnčić
     */
    void onDataArrived(List<Post> result,
                       List<PhotoType> photoTypes,
                       List<HousesW> housesWs,
                       Response<AirWebServiceResponse> response
                    );


}
