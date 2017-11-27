package com.kizo.web_services;

import com.kizo.web_services.responses.WS_entities.HousesW;
import com.project.test.database.Entities.PhotoType;
import com.project.test.database.Entities.Post;

import java.util.List;

/**
 * Created by Zoran on 25.11.2017..
 */

public interface AirWebServiceHandler {
    void onDataArrived(List<Post> result, List<PhotoType> photoTypes, List<HousesW> housesWs, boolean ok);
}
