package com.kizo.report;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.kizo.report.adapter.FinishedReportAdapter;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.report.InterventionController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nikol on 21.12.2017..
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
    public TextView vozilo;
    public TextView prijedenoKm;
    public TextView utrosenoSati;
    public TextView brVatrogas;
    public TextView sredstva;
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
    public TextView KmTr;
    public TextView vatrogasacTr;
    public TextView osiguranoTr;
    public TextView elPumpaTr;
    public TextView prahTr;
    public TextView co2Tr;
    public TextView pjeniloTr;
    public TextView apsorbentTr;
    public TextView ostaliTr;
    public TextView sudionik1;
    public TextView zapovjednik;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_report);

        toolbar = (Toolbar) findViewById(R.id.toolbarReport);
        setSupportActionBar(toolbar);
        setTitleOnToolbar("Izvješće o intervenciji");

        ButterKnife.bind(this);

        //need ID of selected intervetion
       String i = getIntent().getStringExtra("EXTRA_SESSION_ID");

        int a = Integer.parseInt(getIntent().getStringExtra("EXTRA_SESSION_ID"));
        if (a != -1) {

            intervention = InterventionController.getInterventionByID(a);

        } else if (a == -1) {
            intervention = InterventionController.getInterventionByID(a);
        } else {
            intervention = InterventionController.getInterventionByID(a);
        }

       System.out.println("ID intervencije _ "+ intervention.getId_intervention_track());

        final List<Intervention_track> listOfIntervetions = InterventionController.getCompletedIntervention();
        //fillWithData();

        FinishedReportAdapter  finishedReportAdapter = new FinishedReportAdapter(listOfIntervetions);


   bindTextView();



fillWithData();

    }

    private void bindTextView() {
        //bind

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
        vozilo = (TextView) findViewById(R.id.vozilo);
        prijedenoKm = (TextView) findViewById(R.id.km);
        utrosenoSati = (TextView) findViewById(R.id.sati);
        brVatrogas = (TextView) findViewById(R.id.br_vatro);
        sredstva = (TextView) findViewById(R.id.sredstva);
        opis = (TextView) findViewById(R.id.opis);
        vlas = (TextView) findViewById(R.id.vlasnik);
        povObjekta = (TextView) findViewById(R.id.pov_obj);
        vanjskoPr = (TextView) findViewById(R.id.vanjski);
        javna = (TextView) findViewById(R.id.ustanova_1);
        navalnoVoz = (TextView) findViewById(R.id.navalno_sati);
        kombiVoz = (TextView) findViewById(R.id.kombi_sati);
        navalnoSatiTr = (TextView)findViewById(R.id.navalno_h);
        autocisternaSatiTr = (TextView) findViewById(R.id.autocisterna_h);
        tehnickoSatiTr = (TextView) findViewById(R.id.tehnicko_h);
        ljestveTr = (TextView) findViewById(R.id.ljestve_h);
        zapovjednoSatiTr = (TextView) findViewById(R.id.zapovjedno_h);
        specijalnoSatiTr = (TextView) findViewById(R.id.specijalno_h);
        prijevozTr = (TextView) findViewById(R.id.prijevoz_h);
        KmTr = (TextView) findViewById(R.id.km_prijedeno);
        vatrogasacTr = (TextView) findViewById(R.id.vatrogasac);
        osiguranoTr = (TextView) findViewById(R.id.osiguran);
        elPumpaTr = (TextView) findViewById(R.id.el_pumpa_h);
        prahTr = (TextView) findViewById(R.id.prah);
        co2Tr = (TextView) findViewById(R.id.co2);
        pjeniloTr = (TextView) findViewById(R.id.pjenilo);
        apsorbentTr = (TextView)findViewById(R.id.apsorbent);
        ostaliTr = (TextView) findViewById(R.id.ostali);
        sudionik1 = (TextView) findViewById(R.id.sud1);
        zapovjednik = (TextView) findViewById(R.id.zapovjednik);
    }

    private void fillWithData() {

        obavijestPrimljena.setText(intervention.getReports().getTime_call_received().toString());
        izlazNaInt.setText(intervention.getReports().getTime_intervention_start().toString());
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

    public void expandableButton1(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout1);
        expandableLayout1.toggle();
    }

    public void expandableButton2(View view) {
        expandableLayout2 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout2);
        expandableLayout2.toggle();
    }

    public void expandableButton3(View view) {
        expandableLayout3 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout3);
        expandableLayout3.toggle();
    }

    public void expandableButton4(View view) {
        expandableLayout4 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout4);
        expandableLayout4.toggle();
    }

    public void expandableButton5(View view) {
        expandableLayout5 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout5);
        expandableLayout5.toggle();
    }

    public void expandableButton6(View view) {
        expandableLayout6 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout6);
        expandableLayout6.toggle();
    }

    public void expandableButton7(View view) {
        expandableLayout7 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout7);
        expandableLayout7.toggle();
    }

    public void expandableButton8(View view) {
        expandableLayout8 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout8);
        expandableLayout8.toggle();
    }

    public void expandableButton9(View view) {
        expandableLayout9 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout9);
        expandableLayout9.toggle();
    }

    public void expandableButton10(View view) {
        expandableLayout10 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout10);
        expandableLayout10.toggle();
    }

    public void expandableButton11(View view) {
        expandableLayout11 = (ExpandableRelativeLayout) findViewById(R.id.expandableLayout11);
        expandableLayout11.toggle();
    }

    /*
    public void fillWithData(){
        txtOpis.setText(intervention.getReports().getDescription());
    }
    */
}
