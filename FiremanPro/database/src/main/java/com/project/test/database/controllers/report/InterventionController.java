package com.project.test.database.controllers.report;


import com.project.test.database.Entities.House;
import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.Entities.report.Intervention_track_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Kontroliraju se sve akcije vezane za kriranje nove intervencije.
 * <p>
 * Kriranje nove intervencije, popunjavanje sa svim potrebnim podatcima.
 * <p>
 * <p>
 * Created by Zoran on 24.10.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */
public class InterventionController {


    java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());

    public InterventionController() {
    }

    /**
     * Provjera da li postoji nedovršena intervencija za prosljeđenu kuću.
     *
     * @param house objekt kuće
     * @return "TRUE" ako postoji nedovršena intervencija, "FALSE" ako ne postoji nedovršena intervencija
     */
    public boolean checkIfExistUnfinishedInterventionAtHouse(House house) {

        List<Intervention_track> intervention_tracks = SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.house_id_house.is(house.getId_house())).and(Intervention_track_Table.completed_intervention.is(Boolean.FALSE)).queryList();

        if (intervention_tracks.size() > 0)
            return true;
        else return false;
    }

    /**
     * Dohvačanje nedovršene intervencije na odabranoj kući
     *
     * @param house objekt kuće za koju nam je potrebna intervencija
     * @return objek koji sadrži sve inrofamacije o intervenciji
     */
    public Intervention_track getUnfinishedInterventionAtHouse(House house) {


        if (checkIfExistUnfinishedInterventionAtHouse(house)) {
            Intervention_track intervention_tracks = SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.house_id_house.is(house.getId_house())).and(Intervention_track_Table.completed_intervention.is(Boolean.FALSE)).querySingle();
            return intervention_tracks;
        } else {
            return null;
        }


    }

    /**
     * Kriranje nove intervencije na određenoj kući. Krira se novi zapis vrste {@link Intervention_track}
     *
     * @param house objekt koji predstavlja određenu kuću.
     * @return objekt koji predstavlja novu intervenciju
     */
    public Intervention_track addNewIntervention_atHouse(House house) {
        java.util.Date CurrentDate = new java.util.Date(System.currentTimeMillis());
        Intervention_track intervention_track = new Intervention_track(false, house, CurrentDate, CurrentDate);
        intervention_track.save();
        return intervention_track;
    }


    /**
     * Dohvaća iz baze podataka sve zapise kreiranih intervencija.
     *
     * @return lista svih intervencija
     */
    public static List<Intervention_track> getAllIntervention() {

        return SQLite.select().from(Intervention_track.class).queryList();
    }

    /**
     * Varaća zapis intervencije prema prosljeđenom ID intervencije
     *
     * @param id ID intervencije
     * @return objekt zapisa intervenicije ili NULL ako ne postoji zapis sa prosljeđenim ID-om
     */
    public static Intervention_track getInterventionByID(int id) {
        try {
            return SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.id_intervention_track.is(id)).querySingle();

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            return null;
        }
    }

    /**
     * Metoda vraća sve zapise intervencije koji su u potpunosti popunjeni/dovršeni.
     *
     * Atribut klase {@link Intervention_track#completed_intervention}  jednak je TRUE
     *
     * @return listu zapisa intervencije koji su u potpunosti popunjeni/dovršeni
     */
    public static List<Intervention_track> getCompletedIntervention() {

        try {
            return SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.completed_intervention.is(true)).queryList();

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
            return null;
        }

    }

    /**
     * Metoda vraća sve zapise intervencije koji NISU u potpunosti popunjeni/dovršeni.
     *
     * Atribut klase {@link Intervention_track#completed_intervention}  jednak je FALSE
     *
     * @return listu zapisa intervencije koji NISU u potpunosti popunjeni/dovršeni
     */
    public static List<Intervention_track> getUnfinishedIntervention() {

        return SQLite.select().from(Intervention_track.class).where(Intervention_track_Table.completed_intervention.is(false)).queryList();
    }

    /**
     * Dohvaća iz baze podataka sve zapise kreiranih intervencija.
     *
     * @return lista svih intervencija
     *
     * @see Intervention_track
     */
    public List<Intervention_track> GetAllRecordsFromTable_Intervention_track() {

        return SQLite.select().from(Intervention_track.class).queryList();
    }

    /**
     * Dohvaća iz baze podataka sve zapise iz tablice Reports.
     *
     * @return lista svih zapisa u tablici report
     *
     * @see Reports
     */
    public List<Reports> GetAllRecordsFromTable_Reports() {

        return SQLite.select().from(Reports.class).queryList();
    }

    /**
     * Briše sve zapise u tablici Intervention_track
     *
     * @see Intervention_track
     */
    public void DeleteAllRecordsInTable_Intervention_track() {

        final List<Intervention_track> gndPlan = GetAllRecordsFromTable_Intervention_track();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }

    /**
     * Briše sve zapise u tablici Reports
     *
     * @see Reports
     */
    public void DeleteAllRecordsInTable_Reports() {

        final List<Reports> gndPlan = GetAllRecordsFromTable_Reports();
        for (int i = 0; i < gndPlan.size(); i++) {

            gndPlan.get(i).delete();
            //delete all item in table House
        }

    }


}
