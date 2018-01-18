
package com.kizo.report;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.Entities.report.Report_fireman;
import com.project.test.database.Entities.report.Report_truck_patrol;
import com.project.test.database.controllers.report.InterventionController;
import com.project.test.database.controllers.report.Types_all_Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public TextView brDojavnica;
    public TextView brIntervencija;
    public TextView obavijestPrimljena;
    public TextView izlazNaInt;
    public TextView dolazakNaInt;
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
    public TextView navalnoVoz;
    public TextView kombiVoz;
    public TextView navalnoSatiTr;
    public TextView autocisternaSatiTr;
    public TextView tehnickoSatiTr;
    public TextView ljestveTr;
    public TextView zapovjednoSatiTr;
    public TextView specijalnoSatiTr;
    public TextView prijevozTr;
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

        bindTextView();
        fillWithData();
    }

    /**
     * Metoda koja dohvaća elemente iz layouta koristeći @Bind
     *
     */
    private void bindTextView() {
        brDojavnica = (TextView) findViewById(R.id.dojavnica_br);
        brIntervencija = (TextView) findViewById(R.id.intervencija_br);
        obavijestPrimljena = (TextView) findViewById(R.id.obav_dat_time);
        izlazNaInt = (TextView) findViewById(R.id.izlaz_dat_time);
        dolazakNaInt = (TextView) findViewById(R.id.dolazak_dat_time);
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
        navalnoVoz = (TextView) findViewById(R.id.navalno_sati);
        kombiVoz = (TextView) findViewById(R.id.kombi_sati);
        navalnoSatiTr = (TextView)findViewById(R.id.navalno_h);
        autocisternaSatiTr = (TextView) findViewById(R.id.autocisterna_h);
        tehnickoSatiTr = (TextView) findViewById(R.id.tehnicko_h);
        ljestveTr = (TextView) findViewById(R.id.ljestve_h);
        zapovjednoSatiTr = (TextView) findViewById(R.id.zapovjedno_h);
        specijalnoSatiTr = (TextView) findViewById(R.id.specijalno_h);
        prijevozTr = (TextView) findViewById(R.id.prijevoz_h);
        vatrogasacTr = (TextView) findViewById(R.id.vatrogasac);
        osiguranoTr = (TextView) findViewById(R.id.osiguran);
        elPumpaTr = (TextView) findViewById(R.id.el_pumpa_h);
        prahTr = (TextView) findViewById(R.id.prah);
        co2Tr = (TextView) findViewById(R.id.co2);
        pjeniloTr = (TextView) findViewById(R.id.pjenilo);
        apsorbentTr = (TextView)findViewById(R.id.apsorbent);
        sudionik1 = (TextView) findViewById(R.id.sud1);
        zapovjednik = (TextView) findViewById(R.id.zap);
    }

    /**
     * Metoda koja popunjava elemente sučelja sa podacima iz baze
     *
     */
    private void fillWithData() {
        obavijestPrimljena.setText(intervention.getReports().getTime_call_received().toString());
        izlazNaInt.setText(intervention.getReports().getTime_intervention_start().toString());
        dolazakNaInt.setText(intervention.getReports().getTime_arrival_intervention().toString());
        vrstaTip.setText(intervention.getReports().getSort_of_intervention().getName().toString() + "\n" + intervention.getReports().getFireInterventionDetails().getIntervention_type().getName().toString());
        velPoz.setText(intervention.getReports().getFireInterventionDetails().getSize_of_fire().getName().toString());
        brGradevinaPoz.setText(String.valueOf(intervention.getReports().getFireInterventionDetails().getDestroyed_space()));
        repeat = intervention.getReports().getFireInterventionDetails().isRepeated();
        if(repeat == null){
            ponovioPoz.setText("NE");
        }else{
            ponovioPoz.setText("DA");
        }
        prostornoPoz.setText(intervention.getReports().getFireInterventionDetails().getSpatial_spread().getName().toString());
        vremenskoPoz.setText(intervention.getReports().getFireInterventionDetails().getTime_spread().getName().toString());
        dimPoz.setText(intervention.getReports().getFireInterventionDetails().getSpreading_smoke().getName().toString());
        vrstaOtvorenoPoz.setText(intervention.getReports().getFireInterventionDetails().getOutdoor_type().getName().toString());
        gradOpcina.setText(intervention.getLocation().getPost().getName().toString());
        mjesto.setText(intervention.getLocation().getPlaceNameIfExist().toString());
        ulicaKbr.setText(intervention.getLocation().getStreetNameIfExist() + " " + intervention.getLocation().getStreetNumber().toString());

        final List<String> snage = new ArrayList<>();
        Integer num = 1;

        for(Report_truck_patrol p: intervention.getReports().getTrucksAndPatrols()){
            snage.add(num + "\n" +
                    p.getFireman_patrol().getType_of_unit().getName().toString() + " "
                            + p.getFireman_patrol().getName().toString() +
                    "\nVozilo (vrsta i broj): " + p.getTruck().getName().toString() +
                    "\nPrijeđeno km: "+ p.getKm() +
                    "\nUtrošeno sati: " + p.getHours() +
                    "\nBroj vatrogasaca: " + p.getNumberOfFireman() +
                    "\nUtrošeno sredstva za gašenje: " +
                    "\n" + "\t" + "Voda: " + p.getWater() +
                    "\n" + "\t" + "Pjenilo: " + String.valueOf(p.getFoam()) +
                    "\n" + "\t" + "Prah: " + String.valueOf(p.getPowder()) +
                    "\n" + "\t" + "CO2: " + String.valueOf(p.getCo2()));
            num++;

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

        final List<String> sluzbe = new ArrayList<String>();
        sluzbe.add(intervention.getReports().getHelp().toString());
        String sluzbeIspis = "";

        for(String s: sluzbe){
            sluzbeIspis += s + "\n";
        }
        javna.setText(sluzbeIspis.toString());

        navalnoVoz.setText(String.valueOf(intervention.getReports().getConsumption().getNavalVehicle()));
        //kombiVoz.setText(intervention.getReports().getConsumption());
        navalnoSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getNavalVehicle()));
        autocisternaSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getRoadTankers()));
        tehnickoSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getTehnicalVehicle()));
        ljestveTr.setText(String.valueOf(intervention.getReports().getConsumption().getAutomatic_ladder()));
        zapovjednoSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getCommand_vehicle()));
        specijalnoSatiTr.setText(String.valueOf(intervention.getReports().getConsumption().getSpecialVehicle()));
        prijevozTr.setText(String.valueOf(intervention.getReports().getConsumption().getTransportationVehicle()));
        //KmTr.setText(intervention.getReports().getConsumption());
        vatrogasacTr.setText(String.valueOf(intervention.getReports().getConsumption().getFire_fighter()));
        osiguranoTr.setText(String.valueOf(intervention.getReports().getConsumption().getInsurance()));
        elPumpaTr.setText(String.valueOf(intervention.getReports().getConsumption().getPowerPumpClock()));
        prahTr.setText(String.valueOf(intervention.getReports().getPowden_kg()));
        co2Tr.setText(String.valueOf(intervention.getReports().getCo2_kg()));
        pjeniloTr.setText(String.valueOf(intervention.getReports().getFoam_l()));
        apsorbentTr.setText(String.valueOf(intervention.getReports().getConsumption().getApsorbent()));
       // ostaliTr.setText(intervention.getReports().getConsumption());

        final List<String> firemanList = new ArrayList<String>();
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
