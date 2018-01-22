package com.kizo.core_module;


/**
 * <p>
 * Created by Zoran on 25.11.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */

public abstract class DataLoader {

    protected DataLoadedListener mDataLoadedListener;

    /**
     * Šalje signal da su svi podatci uspješno učitani i pohranjeni u bazu odataka nakon dohvačanja sa web servisa
     *
     * @param dataLoadedListener implementacija intrface-a sa metoda koje se izvršavaju nakon uspješnog dohvačanja i pohranivanja podataka u bazu
     */
    public void loadData(DataLoadedListener dataLoadedListener) {
        this.mDataLoadedListener = dataLoadedListener;
    }


}