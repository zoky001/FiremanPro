package com.kizo.report.holder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import com.kizo.report.FinishedReportActivity;
import com.kizo.report.R;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.report.Intervention_track;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Zoran on 27.11.2017..
 */

public class SavedReportHolder extends ParentViewHolder {

    public  TextView mStoreName;
    public TextView mStoreDescription;
    public  ImageView mStoreImage;
    public  TextView interventionType;
    private int position = 0;
    public CardView cardView;

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

    View mItemView;

    // constructor binds the ButterKnife library and makes itemView available locally
    public SavedReportHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        interventionType  = (TextView) itemView.findViewById(R.id.interventionType);
        mStoreName  = (TextView) itemView.findViewById(R.id.store_name);
        mStoreImage = (ImageView) itemView.findViewById(R.id.store_image);
        mStoreDescription = (TextView) itemView.findViewById(R.id.store_description);

        brDojavnica = (TextView) itemView.findViewById(R.id.dojavnica_br);
        brIntervencija = (TextView) itemView.findViewById(R.id.intervencija_br);
        obavijestPrimljena = (TextView) itemView.findViewById(R.id.obav_dat_time);
        izlazNaInt = (TextView) itemView.findViewById(R.id.izlaz_dat_time);
        dolazakNaInt = (TextView) itemView.findViewById(R.id.dolazak_dat_time);
        vrstaTip = (TextView) itemView.findViewById(R.id.vrsta_tip);
        velPoz = (TextView) itemView.findViewById(R.id.vel_pozara);
        brGradevinaPoz = (TextView) itemView.findViewById(R.id.zbroj);
        ponovioPoz = (TextView) itemView.findViewById(R.id.ponovni);
        prostornoPoz = (TextView) itemView.findViewById(R.id.prostorno_sir);
        vremenskoPoz = (TextView) itemView.findViewById(R.id.vremensko_sir);
        dimPoz = (TextView) itemView.findViewById(R.id.dim);
        vrstaOtvorenoPoz = (TextView) itemView.findViewById(R.id.vrsta_na_otvorenom);
        gradOpcina = (TextView) itemView.findViewById(R.id.grad_opcina);
        mjesto = (TextView) itemView.findViewById(R.id.mjesto);
        ulicaKbr = (TextView) itemView.findViewById(R.id.ulica_kucni_br);
        postrojba = (TextView) itemView.findViewById(R.id.postrojba);
        vozilo = (TextView) itemView.findViewById(R.id.vozilo);
        prijedenoKm = (TextView) itemView.findViewById(R.id.km);
        utrosenoSati = (TextView) itemView.findViewById(R.id.sati);
        brVatrogas = (TextView) itemView.findViewById(R.id.br_vatro);
        sredstva = (TextView) itemView.findViewById(R.id.sredstva);
        opis = (TextView) itemView.findViewById(R.id.opis);
        vlas = (TextView) itemView.findViewById(R.id.vlasnik);
        povObjekta = (TextView) itemView.findViewById(R.id.pov_obj);
        vanjskoPr = (TextView) itemView.findViewById(R.id.vanjski);
        javna = (TextView) itemView.findViewById(R.id.ustanova_1);
        navalnoVoz = (TextView) itemView.findViewById(R.id.navalno_sati);
        kombiVoz = (TextView) itemView.findViewById(R.id.kombi_sati);
        navalnoSatiTr = (TextView) itemView.findViewById(R.id.navalno_h);
        autocisternaSatiTr = (TextView) itemView.findViewById(R.id.autocisterna_h);
        tehnickoSatiTr = (TextView) itemView.findViewById(R.id.tehnicko_h);
        ljestveTr = (TextView) itemView.findViewById(R.id.ljestve_h);
        zapovjednoSatiTr = (TextView) itemView.findViewById(R.id.zapovjedno_h);
        specijalnoSatiTr = (TextView) itemView.findViewById(R.id.specijalno_h);
        prijevozTr = (TextView) itemView.findViewById(R.id.prijevoz_h);
        KmTr = (TextView) itemView.findViewById(R.id.km_prijedeno);
        vatrogasacTr = (TextView) itemView.findViewById(R.id.vatrogasac);
        osiguranoTr = (TextView) itemView.findViewById(R.id.osiguran);
        elPumpaTr = (TextView) itemView.findViewById(R.id.el_pumpa_h);
        prahTr = (TextView) itemView.findViewById(R.id.prah);
        co2Tr = (TextView) itemView.findViewById(R.id.co2);
        pjeniloTr = (TextView) itemView.findViewById(R.id.pjenilo);
        apsorbentTr = (TextView) itemView.findViewById(R.id.apsorbent);
        ostaliTr = (TextView) itemView.findViewById(R.id.ostali);
        sudionik1 = (TextView) itemView.findViewById(R.id.sud1);
        zapovjednik = (TextView) itemView.findViewById(R.id.zapovjednik);

        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View v){
                System.out.println("clickOnItem()r: ");
                Intent intent = new Intent(v.getContext(), FinishedReportActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        ButterKnife.bind(this, itemView);


    }

    // when the adapter is implemented this method is used to bind list elements with the recycler-view, here, we populate the Views
    public void bind(Intervention_track intervention_track, int position){
        this.position = position;

        if (!intervention_track.isCompleted_intervention())
        {
           // interventionType.setTextColor(Color.parseColor("#c1000d"));

            //unfinished intervention cad has red background
            cardView.setCardBackgroundColor(Color.parseColor("#DEE93C"));
        }
        interventionType.setText(intervention_track.getReports().getSort_of_intervention().getName() +" - " +
        intervention_track.getReports().getFireInterventionDetails().getIntervention_type().getName());
        mStoreDescription.setText(intervention_track.getReports().getDescription());
        mStoreName.setText(intervention_track.getLocation().getStreetNameIfExist() + " " + intervention_track.getLocation().getStreetNumber());
       mStoreImage.setImageBitmap(intervention_track.getHouse().getProfilPhotos().getImageBitmapbyContext(itemView.getContext()));
       // Picasso.with(itemView.getContext()).load(store.getImgUrl()).into(mStoreImage);

    }


}