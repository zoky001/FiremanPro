
package com.kizo.report;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.Settings;
import com.project.test.database.Entities.fireman_patrol.Costs;
import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Type_of_truck;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.Entities.report.Report_fireman;
import com.project.test.database.Entities.report.Report_truck_patrol;
import com.project.test.database.Entities.report.Sort_of_intervention;
import com.project.test.database.controllers.FiremanPatrolController;
import com.project.test.database.controllers.report.InterventionController;
import com.project.test.database.controllers.report.Types_all_Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Aktivnost koja popunjava završeni izvještaj
 *
 * Ova aktivnost služi za popunjavanje elemenata forme podacima o odabranom izvještaju iz baze
 *
 * @author Nikolina Bukovec
 * @version 2018.0117
 *
 */
public class FinishedReportActivity extends AppCompatActivity {

    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5, expandableLayout6, expandableLayout7, expandableLayout8,
            expandableLayout9, expandableLayout10, expandableLayout11;

    Toolbar toolbar;
    Intervention_track intervention;
    Costs costs;
    Sort_of_intervention sort_of_intervention, sort_of_interventionTehn;
    Type_of_truck type_of_truck_TRANS, type_of_truck_NAVAL;

    public TextView naslov;
    public TextView obavijestPrimljena;
    public TextView izlazNaInt;
    public TextView dolazakNaInt;
    public TextView zavrsetak;
    public TextView vrstaTip;
    public TextView velPoz;
    public TextView brGradevinaPoz;
    public TextView ponovioPoz;
    public TextView prostornoPoz;
    public TextView vremenskoPoz;
    public TextView dimPoz;
    public TextView vrstaOtvorenoPoz;
    public TextView gradOpcina;
    public TextView mjesto;
    public TextView ulicaKbr;
    public TextView postrojba;
    public TextView opis;
    public TextView vlas;
    public TextView povObjekta;
    public TextView vanjskoPr;
    public TextView javna;
    public TextView voziloSati;
    public TextView navalnoSatiTr;
    public TextView autocisternaSatiTr;
    public TextView tehnickoSatiTr;
    public TextView ljestveTr;
    public TextView zapovjednoSatiTr;
    public TextView specijalnoSatiTr;
    public TextView prijevozTr;
    public TextView prijedeno;
    public TextView vatrogasacTr;
    public TextView osiguranoTr;
    public TextView elPumpaTr;
    public TextView prahTr;
    public TextView co2Tr;
    public TextView pjeniloTr;
    public TextView apsorbentTr;
    public TextView sudionik1;
    public TextView zapovjednik;
    public Boolean repeat;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_report);

        toolbar = (Toolbar) findViewById(R.id.toolbarReport);
        setSupportActionBar(toolbar);
        setTitleOnToolbar("Izvješće o intervenciji");

        ButterKnife.bind(this);

        int a = Integer.parseInt(getIntent().getStringExtra("EXTRA_SESSION_ID"));
        if (a != -1) {

            intervention = InterventionController.getInterventionByID(a);

        } else if (a == -1) {
            intervention = InterventionController.getInterventionByID(a);
        } else {
            intervention = InterventionController.getInterventionByID(a);
        }
       System.out.println("ID intervencije _ "+ intervention.getId_intervention_track());

        FiremanPatrolController firemanPatrolController = new FiremanPatrolController();
        costs = firemanPatrolController.GetAllRecordsFromTable().get(0).getCost();
        Types_all_Controller types_all_controller = new Types_all_Controller();
        sort_of_intervention = types_all_controller.get_FIRE_Sort_of_intervention();
        sort_of_interventionTehn= types_all_controller.get_TRHNICAL_Sort_of_intervention();

        type_of_truck_TRANS = types_all_controller.get_transportation_vehicle_type_of_truck();
        type_of_truck_NAVAL = types_all_controller.get_naval_vehicle_type_of_truck();
        bindTextView();
        fillWithData();
    }

    /**
     * Metoda koja dohvaća elemente iz layouta koristeći @Bind
     *
     */
    private void bindTextView() {

        naslov = (TextView) findViewById(R.id.naslov);
        obavijestPrimljena = (TextView) findViewById(R.id.obav_dat_time);
        izlazNaInt = (TextView) findViewById(R.id.izlaz_dat_time);
        dolazakNaInt = (TextView) findViewById(R.id.dolazak_dat_time);
        zavrsetak = (TextView) findViewById(R.id.kraj_dat_time);
        vrstaTip = (TextView) findViewById(R.id.vrsta_tip);
        velPoz = (TextView) findViewById(R.id.vel_pozara);
        brGradevinaPoz = (TextView) findViewById(R.id.zbroj);
        ponovioPoz = (TextView) findViewById(R.id.ponovni);
        prostornoPoz = (TextView) findViewById(R.id.prostorno_sir);
        vremenskoPoz = (TextView) findViewById(R.id.vremensko_sir);
        dimPoz = (TextView) findViewById(R.id.dim);
        vrstaOtvorenoPoz = (TextView) findViewById(R.id.vrsta_na_otvorenom);
        gradOpcina = (TextView) findViewById(R.id.grad_opcina);
        mjesto = (TextView) findViewById(R.id.mjesto);
        ulicaKbr = (TextView) findViewById(R.id.ulica_kucni_br);
        postrojba = (TextView) findViewById(R.id.postrojba);
        opis = (TextView) findViewById(R.id.opis);
        vlas = (TextView) findViewById(R.id.vlasnik);
        povObjekta = (TextView) findViewById(R.id.pov_obj);
        vanjskoPr = (TextView) findViewById(R.id.vanjski);
        javna = (TextView) findViewById(R.id.javne);
        voziloSati = (TextView) findViewById(R.id.vozilo_sati);
        navalnoSatiTr = (TextView)findViewById(R.id.navalno_h);
        autocisternaSatiTr = (TextView) findViewById(R.id.autocisterna_h);
        tehnickoSatiTr = (TextView) findViewById(R.id.tehnicko_h);
        ljestveTr = (TextView) findViewById(R.id.ljestve_h);
        zapovjednoSatiTr = (TextView) findViewById(R.id.zapovjedno_h);
        specijalnoSatiTr = (TextView) findViewById(R.id.specijalno_h);
        prijevozTr = (TextView) findViewById(R.id.prijevoz_h);
        //prijedeno = (TextView) findViewById(R.id.km_prijedeno);
        vatrogasacTr = (TextView) findViewById(R.id.vatrogasac);
        osiguranoTr = (TextView) findViewById(R.id.osiguran);
        elPumpaTr = (TextView) findViewById(R.id.el_pumpa_h);
        prahTr = (TextView) findViewById(R.id.prah);
        co2Tr = (TextView) findViewById(R.id.co2);
        pjeniloTr = (TextView) findViewById(R.id.pjenilo);
        apsorbentTr = (TextView)findViewById(R.id.apsorbent);
        sudionik1 = (TextView) findViewById(R.id.sud1);
        zapovjednik = (TextView) findViewById(R.id.zap);

        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
    }

    /**
     * Metoda koja popunjava elemente sučelja sa podacima iz baze
     *
     */
    private void fillWithData() {

        naslov.setText(Settings.getSettings().getPatrolName().toString());
        String obavijest, izlazak, dolazak, kraj;
        obavijest = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.SHORT, java.text.DateFormat.MEDIUM).format(intervention.getReports().getTime_call_received());
        izlazak = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.SHORT, java.text.DateFormat.MEDIUM).format(intervention.getReports().getTime_intervention_start());
        dolazak = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.SHORT, java.text.DateFormat.MEDIUM).format(intervention.getReports().getTime_arrival_intervention());
        kraj = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.SHORT, java.text.DateFormat.MEDIUM).format(intervention.getReports().getTime_intervention_ended());
        obavijestPrimljena.setText(obavijest);
        izlazNaInt.setText(izlazak);
        dolazakNaInt.setText(dolazak);
        zavrsetak.setText(kraj);

        //pozarna
        if(intervention.getReports().getSort_of_intervention().getId() == sort_of_intervention.getId()) {
            vrstaTip.setText(intervention.getReports().getSort_of_intervention().getName().toString() + "\n" + intervention.getReports().getFireInterventionDetails().getIntervention_type().getName().toString());
            velPoz.setText(intervention.getReports().getFireInterventionDetails().getSize_of_fire().getName().toString());
            brGradevinaPoz.setText(String.valueOf(intervention.getReports().getFireInterventionDetails().getDestroyed_space()));
            repeat = intervention.getReports().getFireInterventionDetails().isRepeated();
            if (repeat == null) {
                ponovioPoz.setText("NE");
            } else {
                ponovioPoz.setText("DA");
            }
            prostornoPoz.setText(intervention.getReports().getFireInterventionDetails().getSpatial_spread().getName().toString());
            vremenskoPoz.setText(intervention.getReports().getFireInterventionDetails().getTime_spread().getName().toString());
            dimPoz.setText(intervention.getReports().getFireInterventionDetails().getSpreading_smoke().getName().toString());
            vrstaOtvorenoPoz.setText(intervention.getReports().getFireInterventionDetails().getOutdoor_type().getName().toString());
        }//tehnicka
        else if (intervention.getReports().getSort_of_intervention().getId() == sort_of_interventionTehn.getId()){
            int colorValue = Color.parseColor("#fcfcfc");
            expandableLayout3.setBackgroundColor(colorValue);
                vrstaTip.setText(intervention.getReports().getSort_of_intervention().getName().toString() + "\n" + intervention.getReports().getTehnicalInterventionDetails().getIntervention_type().getName().toString());
                velPoz.setText("/");
                brGradevinaPoz.setText("/");
                ponovioPoz.setText("/");
                prostornoPoz.setText("/");
                vremenskoPoz.setText("/");
                dimPoz.setText("/");
                vrstaOtvorenoPoz.setText("/");
        }else{//other
            int colorValue = Color.parseColor("#fcfcfc");
            expandableLayout3.setBackgroundColor(colorValue);
            vrstaTip.setText(intervention.getReports().getSort_of_intervention().getName().toString() + "\n" + intervention.getReports().getOtherInterventionDetails().getIntervention_type().getName().toString());
            velPoz.setText("/");
            brGradevinaPoz.setText("/");
            ponovioPoz.setText("/");
            prostornoPoz.setText("/");
            vremenskoPoz.setText("/");
            dimPoz.setText("/");
            vrstaOtvorenoPoz.setText("/");
        }

        gradOpcina.setText(intervention.getLocation().getPost().getName().toString());
        mjesto.setText(intervention.getLocation().getPlaceNameIfExist().toString());
        ulicaKbr.setText(intervention.getLocation().getStreetNameIfExist() + " " + intervention.getLocation().getStreetNumber().toString());

        final List<String> snage = new ArrayList<>();
        Integer num = 1;

        Double navalno = 0.0;
        Double transportno = 0.0;

        for(Report_truck_patrol p: intervention.getReports().getTrucksAndPatrols()){
                snage.add(num + "\n" +
                        "Vrsta jedinice: " + p.getFireman_patrol().getType_of_unit().getName().toString() +
                        "\n" + "\t" + p.getFireman_patrol().getName().toString() +
                        "\nVozilo: " + p.getTruck().getName().toString() +
                        "\nPrijeđeno km: " + p.getKm() +
                        "\nUtrošeno sati: " + p.getHours() +
                        "\nBroj vatrogasaca: " + p.getNumberOfFireman() +
                        "\nUtrošeno sredstva za gašenje: " +
                        "\n" + "\t" + "Voda: " + p.getWater() +
                        "\n" + "\t" + "Pjenilo: " + String.valueOf(p.getFoam()) +
                        "\n" + "\t" + "Prah: " + String.valueOf(p.getPowder()) +
                        "\n" + "\t" + "CO2: " + String.valueOf(p.getCo2()));
                num++;

            if(p.getTruck().getType_of_truck().getType_name().equals(type_of_truck_NAVAL.getType_name())){
                navalno += p.getHours();
            }
            if(p.getTruck().getType_of_truck().getType_name().equals(type_of_truck_TRANS.getType_name())){
               transportno += p.getHours();
            }
        }

        String ispis = "";
        for(String s: snage){
            ispis += s + "\n\n";
        }
        postrojba.setText(ispis.toString());
        opis.setText(intervention.getReports().getDescription().toString());
        vlas.setText(intervention.getHouse().getName_owner().toString() + " " + intervention.getHouse().getSurname_owner().toString());
        povObjekta.setText(String.valueOf(intervention.getReports().getSurface_m2()));
        vanjskoPr.setText(String.valueOf(intervention.getReports().getSuperficies_ha()));

        final List<String> sluzbe = new ArrayList<>();
        sluzbe.add(intervention.getReports().getHelp().toString());
        String sluzbeIspis = "";

        for(String s: sluzbe){
            sluzbeIspis += s + "\n";
        }
        javna.setText(sluzbeIspis.toString());


        String voziloVrste1 = "";
        String voziloVrste2 = "";
        String naval = type_of_truck_NAVAL.getType_name();
        String trans = type_of_truck_TRANS.getType_name();

        if(navalno != 0.0){
            voziloVrste1 = (naval + ": \t\t\t\t\t").toString() ;
            voziloVrste1 +=  navalno.toString() + "\n";
        }
        if(transportno != 0.0){
            voziloVrste2 = (trans + ": \t\t\t\t").toString() ;
            voziloVrste2 += transportno.toString() + "\n";
        }
        if(transportno == 0.0 && navalno == 0.0){
            voziloVrste1 += voziloVrste2 += "__";
        }
        voziloSati.setText(voziloVrste1.toString() + voziloVrste2.toString());
        navalnoSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getNavalVehicle()*costs.getNaval_vehicle()));
        autocisternaSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getRoadTankers() * costs.getRoad_tankers()));
        tehnickoSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getTehnicalVehicle() * costs.getTehnical_vehicle()));
        ljestveTr.setText(String.valueOf(intervention.getReports().getConsumption().getAutomatic_ladder() * costs.getAutomatic_Ladder()));
        zapovjednoSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getCommand_vehicle() * costs.getCommand_Vehicle()));
        specijalnoSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getSpecialVehicle() * costs.getSpecial_vehicle()));
        prijevozTr.setText(String.valueOf(intervention.getReports().getConsumption().getTransportationVehicle() * costs.getTransportation_vehicle()));
        //prijedeno.setText(intervention.getReports().getConsumption());
        vatrogasacTr.setText(String.valueOf(intervention.getReports().getConsumption().getFire_fighter() * costs.getFire_fighter()));
        osiguranoTr.setText(String.valueOf(intervention.getReports().getConsumption().getInsurance() * costs.getInsurance()));
        elPumpaTr.setText(String.valueOf(intervention.getReports().getConsumption().getPowerPumpClock() * costs.getPower_pump_clock()));
        prahTr.setText(String.valueOf(intervention.getReports().getConsumption().getFire_extinguisher() * costs.getFire_extinguisher()));
        co2Tr.setText(String.valueOf(intervention.getReports().getConsumption().getCo2() * costs.getCo2()));
        pjeniloTr.setText(String.valueOf(intervention.getReports().getConsumption().getFoam() * costs.getFoam()));
        apsorbentTr.setText(String.valueOf(intervention.getReports().getConsumption().getApsorbent() * costs.getApsorbent()));

        final List<String> firemanList = new ArrayList<>();
        for(Report_fireman fireman: intervention.getReports().getFiremans() ){
            firemanList.add(fireman.getFireman().getName().toString() + " " + fireman.getFireman().getSurname().toString());
        }

        String firemanIspis = "";

        for(String f: firemanList){
            firemanIspis += f + "\n";
        }
        sudionik1.setText(firemanIspis.toString());

        zapovjednik.setText(intervention.getReports().getSigned().getName().toString() + " " + intervention.getReports().getSigned().getSurname().toString());
    }

    private void setTitleOnToolbar(String title){
        getSupportActionBar().setTitle(title);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metoda koja upravlja akcijom na Gumb1
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        expandableLayout1.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb2
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton2(View view) {
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb3
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton3(View view) {
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout3.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb4
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton4(View view) {
        expandableLayout4 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout4);
        expandableLayout4.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb5
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton5(View view) {
        expandableLayout5 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout5);
        expandableLayout5.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb6
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton6(View view) {
        expandableLayout6 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout6);
        expandableLayout6.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb7
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton7(View view) {
        expandableLayout7 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout7);
        expandableLayout7.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb8
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton8(View view) {
        expandableLayout8 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout8);
        expandableLayout8.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb9
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton9(View view) {
        expandableLayout9 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout9);
        expandableLayout9.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb10
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton10(View view) {
        expandableLayout10 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout10);
        expandableLayout10.toggle();
    }

    /**
     * Metoda koja upravlja akcijom na Gumb11
     *
     *@param 'view' element layouta koji se prikazuje
     *
     */
    public void expandableButton11(View view) {
        expandableLayout11 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout11);
        expandableLayout11.toggle();
    }


}
