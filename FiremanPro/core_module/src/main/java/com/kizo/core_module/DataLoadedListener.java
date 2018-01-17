package com.kizo.core_module;

/**
 * Definira metode koje osluškuje kada će biti učitani svi podatci dohvačeni putem WS i spremni za daljnju obradu.
 *
 * <p>
 * Created by Zoran on 25.11.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */

public interface DataLoadedListener {

    /**
     * Osluškuje kada će biti učitani svi podatci dohvačeni putem WS i spremni za daljnju obradu.
     *
     * @author Zoran Hrnčić
     */
    void onDataLoaded();
}