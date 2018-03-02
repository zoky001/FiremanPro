package com.project.air.firemanpro.profil;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.kizo.report.NewReportFormActivity;
import com.project.air.firemanpro.googlemaps.GoogleMapActivity;
import com.project.air.firemanpro.googlemaps.MapFragment;
import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.fire_intervention.Size_of_fire;
import com.project.test.database.Entities.fire_intervention.Spatial_spread;
import com.project.test.database.Entities.fire_intervention.Spreading_smoke;
import com.project.test.database.Entities.fire_intervention.Time_spread;
import com.project.test.database.Entities.fireman_patrol.Fireman;
import com.project.test.database.Entities.fireman_patrol.Fireman_patrol;
import com.project.test.database.Entities.fireman_patrol.Truck;
import com.project.test.database.Entities.report.Intervention_Type;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.Entities.report.Outdoor_type;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.report.InterventionController;
import com.project.test.database.helper.MockData;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Ova klasa služi za prikazivanje jednog Tab-a u obliku fragmenta.
 * Prikazuje se na vrhu slika profila kuće. Ispod slike je ispis najbitnijih podtaka o kući.
 * Na dnu taba se nalazi fragment u kojem je prikazana google karta sa navigazijom do odabrane kuće.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Created by Zoran Hrnčić on 27.10.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */

public class TabProfil extends TabFragment {
    private static final int NEW_ALARM = 1;
    @BindView(R.id.imageViewProfilHouse)
    ImageView profil;

    @BindView(R.id.textViewNameOwner)
    TextView txtNameSurname;

    @BindView(R.id.textViewPost)
    TextView txtPost;

    @BindView(R.id.textView1Address)
    TextView txtAdress;

    @BindView(R.id.textViewTel)
    TextView txtTel;

    @BindView(R.id.textViewMobitel)
    TextView txtMobitel;

    @BindView(R.id.textViewPlace)
    TextView txtPlace;

    @BindView(R.id.btn_new_report)
    Button btnNewReport;


    ImageView imageView;

    private InterventionController interventionController = new InterventionController();

    LinearLayout relativelayout;

    /**
     * Prilikom kriranja View-a se iz argumenata/Bundle dohvaća ID odabrane kuće.
     * Pomoću navedenog ID-a se dohvaća kuća iu baze podataka, te pohranjuje u varijablu "house" za daljnje korištenje.
     *
     * @author Zoran Hrnčić
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.tab_profil, container, false);
        ButterKnife.bind(this, rootView);

        relativelayout = (LinearLayout) rootView.findViewById(R.id.tab1);
        relativelayout.setVisibility(View.INVISIBLE);

        imageView = (ImageView) rootView.findViewById(R.id.waitScreen);
        imageView.setImageResource(R.drawable.wait);
        imageView.setVisibility(View.VISIBLE);
        relativelayout.setVisibility(View.INVISIBLE);


        return rootView;
    }


    @Override
    public void onHouseLoaded(com.project.test.database.firebaseEntities.House house) {
        super.onHouseLoaded(house);

        setLayoutData(house);
      // setMapFragment(house);

        relativelayout.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);

    }

    private void setLayoutData(com.project.test.database.firebaseEntities.House house) {
        //set profil image
        //  profil.setImageBitmap(Bitmap.createScaledBitmap(house.getProfilImageBitmapbyContext(profil.getContext()), 400, 300, false));

        Picasso.with(getActivity())
                .load(house.getProfilPicUrl())
                .into(profil);

        //set owner data
        txtNameSurname.setText(house.getSurname_owner() + " " + house.getName_owner());

        // txtPost.setText(house.getAddress().getPost().getPostal_code() + " " + house.getAddress().getPost().getName());


        txtAdress.setText(house.getAddress().getStreetNameIfExist() + " " + house.getAddress().getStreetNumber());
        txtPlace.setText(house.getPlaceName());
        txtMobitel.setText(house.getMobNumber());
        txtTel.setText(house.getTelNumber());


    }

    private void setMapFragment(com.project.test.database.firebaseEntities.House house) {

        try {
            Bundle bundle = new Bundle();
            String IDHouse = "" + house.getId_house();
            bundle.putString("IDkuce", IDHouse);
            Fragment mapFragment = new MapFragment();
            mapFragment.setArguments(bundle);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.map_container, mapFragment)
                    .commit();
            //  loadMapFragment(IDHouse);


        } catch (Exception e) {
            System.out.println("EXCEPTON: " + e.getMessage());

        }




/*  temp commented
        Intervention_track intervencija;
        boolean nekaj = interventionController.checkIfExistUnfinishedInterventionAtHouse(house);
        if (nekaj) {

            intervencija = interventionController.getUnfinishedInterventionAtHouse(house);
            String a, b, c, d;
            if (intervencija.getReports().getTime_call_received() != null) {
                btnNewReport.setText("Započeti intervenciju");
            }
            if (intervencija.getReports().getTime_intervention_start() != null) {
                btnNewReport.setText("Dolazak na mjesto");
            }
            if (intervencija.getReports().getTime_arrival_intervention() != null) {
                btnNewReport.setText("Kraj Intervencije");

            }
            if (intervencija.getReports().getTime_intervention_ended() != null) {
                btnNewReport.setText("Kreiraj izvještaj");
            }

        } else {
            btnNewReport.setText("Poziv zaprimljen");
        }


        */
    }


    /**
     * Nakon kriranja View-a se ID kuće stavlja u Bundel.
     * U fragment frame se učitava Map fragment koji prikazije put do kuče od trenutne lokacije.
     *
     * @author Zoran Hrnčić
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void loadMapFragment(String idHouse) {
        Disposable subscribe2 = cachedSingleHouse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<com.project.test.database.firebaseEntities.House>() {

                    @Override
                    public void onSuccess(com.project.test.database.firebaseEntities.House todos) {
                        // work with the resulting todos
                        Log.d("MAGARAC", todos.toString());
                        loadMap(todos);


                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                    }
                });
        disposable.add(subscribe2);


    }

    private void loadMap(com.project.test.database.firebaseEntities.House todos) {

        Bundle bundle = new Bundle();
        bundle.putString("IDkuce", todos.getId());
        Fragment mapFragment = new MapFragment();
        mapFragment.setArguments(bundle);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.map_container, mapFragment)
                .commit();

    }


    /**
     * @param view Zoran Grđan
     */
    @OnClick(R.id.buttonMax)
    public void buttonMaxClicked(View view) {
        String IDHouse = "" + house.getId_house();
        Intent intent = new Intent(view.getContext(), GoogleMapActivity.class);
        intent.putExtra("IDkuce", IDHouse);
        startActivity(intent);
    }

    /**
     * @param view Zoran Grđan
     */
    @OnClick(R.id.leadMeButton)
    public void leadMeButton(View view) {
        double latitude, longitude;
        latitude = house.getAddress().getLatitude();
        longitude = house.getAddress().getLongitude();
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @OnClick(R.id.btn_new_report)
    public void newReport(View view) {
/*  temp commented
        String buttonText = btnNewReport.getText().toString();

        String callRecievedText = "Poziv zaprimljen";
        String interventionStartedText = "Započeti intervenciju";
        String interventionArrivalText = "Dolazak na mjesto";
        String interventionEndedText = "Kraj Intervencije";
        String startReport = "Kreiraj izvještaj";
        Intervention_track intervencija;
        if (buttonText.toLowerCase().trim().equals(callRecievedText.toLowerCase().trim())) {
            if (!interventionController.checkIfExistUnfinishedInterventionAtHouse(house)) {

                intervencija = interventionController.addNewIntervention_atHouse(house);


                intervencija.add_FIRE_ReportToIntervention();
                intervencija.callReceived();


                btnNewReport.setText(interventionStartedText);

            }
        }

        if (buttonText.toLowerCase().trim().equals(interventionStartedText.toLowerCase().trim())) {
            intervencija = interventionController.getUnfinishedInterventionAtHouse(house);

            btnNewReport.setText(interventionArrivalText);
            intervencija.intervetionStarted();

        }
        if (buttonText.toLowerCase().trim().equals(interventionArrivalText.toLowerCase().trim())) {
            intervencija = interventionController.getUnfinishedInterventionAtHouse(house);

            btnNewReport.setText(interventionEndedText);
            intervencija.intervetionArrival();
        }
        if (buttonText.toLowerCase().trim().equals(interventionEndedText.toLowerCase().trim())) {
            intervencija = interventionController.getUnfinishedInterventionAtHouse(house);

            btnNewReport.setText(startReport);
            intervencija.intervetionEnded();

        }
        if (buttonText.toLowerCase().trim().equals(startReport.toLowerCase().trim())) {
            intervencija = interventionController.getUnfinishedInterventionAtHouse(house);

            btnNewReport.setText(callRecievedText);
            Intent intent = new Intent(view.getContext(), NewReportFormActivity.class);
            intent.putExtra("IDintervencije", String.valueOf(intervencija.getId_intervention_track())); // umjesto 01 prosljediš ID kuće
            startActivityForResult(intent, NEW_ALARM);

        }


        */
    }

    /**
     * omogućuje učitavanje ovog fragmenta u metodu interface-a
     *
     * @param iTabFragment imaplementacija interface-a ITabFragment
     */
    @Override
    public void loadFrag(ITabFragment iTabFragment) {
        super.loadFrag(iTabFragment);
        iTabFragment.getFragment(this);
    }


}


























