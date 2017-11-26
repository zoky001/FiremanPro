package com.kizo.web_services;

/**
 * Created by Zoran on 25.11.2017..
 */

public interface AirWebServiceHandler {
    void onDataArrived(Object result, Object address,boolean ok);
}
