package com.project.air.firemanpro.newIntervention;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.kizo.goolge_map.GoogleLocation;
import com.kizo.ground_plan.Tab.TabTlocrt;
import com.project.air.firemanpro.R;
import com.project.air.firemanpro.profil.TabPodaci;
import com.project.air.firemanpro.profil.TabProfil;
import com.project.test.database.InterventionTrackController;
import com.project.test.database.controllers.HouseController;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Aktivnost  koja sadržava prikazivanje tabova.
 * <p>
 * Svaki tab opisuje neke informacije o odabranoj kući.
 * <p>
 * Prilikom kreiranja potrebno je prosljediti id koristeći Bundle, "EXTRA_SESSION_ID" = id odabrane kuce
 *
 * @author Zoran Hrnčić
 */
public class NewInterventionActivity extends AppCompatActivity {

    String TAG = "ProfilNewActivityLog";

   // House house;

    com.project.test.database.firebaseEntities.House house;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded currrentDisplayedFragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    CompositeDisposable disposable = new CompositeDisposable();
    Single<com.project.test.database.firebaseEntities.House> cachedSingleHouse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_new);
        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Create the adapter that will return a currrentDisplayedFragment for each of the three
        // primary sections of the activity.

        CheckIfExistNewIntervention(getIntent());

        try {
            String a = getIntent().getStringExtra("EXTRA_SESSION_ID");
            cachedSingleHouse = HouseController.getHouseByID(a).cache();
            loadHouse(cachedSingleHouse);
            System.out.println("SESSION FRAGMENT_idkuce: " + a);

        } catch (Exception e) {
            Log.d(TAG,"EXCEPTION: " + e.getMessage());
        }

    }

    private void loadHouse(Single<com.project.test.database.firebaseEntities.House> cachedSingleHouse) {
        Disposable subscribe2 = cachedSingleHouse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<com.project.test.database.firebaseEntities.House>() {

                    @Override
                    public void onSuccess(com.project.test.database.firebaseEntities.House todos) {
                        // work with the resulting todos
                        Log.d(TAG, todos.toString());

                        setLayoutData(todos);

                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                    }
                });
        disposable.add(subscribe2);

    }

    private void setLayoutData(com.project.test.database.firebaseEntities.House house) {
        this.house = house;

        setTitleOnToolbar(house.getSurname_owner() + " " + house.getName_owner() + " - " + house.getPlaceName());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    /**
     * postavljanje naslova na toolbar
     *
     * @param title naziv koji se postavlja na toolbar
     */
    private void setTitleOnToolbar(String title) {
        //set title (owner name )on toolbar
        getSupportActionBar().setTitle(title); //set title on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } //toolbar
    }

    private void CheckIfExistNewIntervention(Intent intent) {
        String message = intent.getStringExtra("message");

        String intervention_id = intent.getStringExtra("intervention_id");
        if (message != null && intervention_id != null) {
            try {

                Log.d(TAG, "EXCEPTION: " + "SESSION FRAGMENT_idkuce: " + message);

                showMyDialog("Message", message,intervention_id, intent );


                Disposable subscribe2 = GoogleLocation.getGoogleApiClient(this)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Location>() {

                            @Override
                            public void onSuccess(Location location) {
                                Log.d(TAG, "success + " + location.toString());
                                // work with the resulting todos
                                InterventionTrackController.sendRecievedCallEvent_fireman(intervention_id, FirebaseAuth.getInstance().getUid(), location);
                            }

                            @Override
                            public void onError(Throwable e) {
                                // handle the error case
                                Log.d(TAG, "error + " + e.getMessage());

                            }
                        });

                disposable.add(subscribe2);


            } catch (Exception e) {
                Log.d(TAG, "EXCEPTION: " + e.getMessage());
            }

        }

    }

    private void showMyDialog(String t, String b, String Intervention_id, Intent intent) {

        String houses_id = intent.getStringExtra("houses_id");

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.notification_dialog, null);

        dialog.setView(dialogView);

        dialog.setTitle(t);
        TextView tv = (TextView) dialogView.findViewById(R.id.message);
        tv.setText(b);
        dialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                Disposable subscribe2 = GoogleLocation.getGoogleApiClient(getBaseContext())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Location>() {

                            @Override
                            public void onSuccess(Location location) {
                                if (houses_id != null){
                                    Intent intent = new Intent(getBaseContext(), NewInterventionActivity.class);
                                    intent.putExtra("EXTRA_SESSION_ID", houses_id);
                                    getBaseContext().startActivity(intent);

                                }

                                Log.d(TAG, "success + " + location.toString());
                                // work with the resulting todos
                                InterventionTrackController.sendComingEvent_fireman(Intervention_id, FirebaseAuth.getInstance().getUid(),location);
                                dialog.dismiss();
                            }

                            @Override
                            public void onError(Throwable e) {
                                // handle the error case
                                Log.d(TAG, "error + " + e.getMessage());

                            }
                        });

                disposable.add(subscribe2);



            }
        });
        dialog.show();
    }

    private boolean close = true;
    private boolean postavljeno = false;

    @Override
    public void onBackPressed() {
      //  onBackPressed();
      /*
        final InterventionController in = new InterventionController();

        if (!postavljeno && in.checkIfExistUnfinishedInterventionAtHouse(house)) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Jeste li sigurni da želite obrisati zapisnik intervencije? ");
            builder1.setCancelable(false);


            builder1.setPositiveButton(
                    "Da",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            InterventionController.deleteInterventionWithID(in.getUnfinishedInterventionAtHouse(house).getId_intervention_track());
                            close = true;
                            postavljeno = true;
                          //  onBackPressed();
                            dialog.cancel();

                        }
                    });

            builder1.setNegativeButton(
                    "Ne",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            close = true;
                            postavljeno = true;
                            onBackPressed();
                            dialog.cancel();

                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();
        } else {
            postavljeno = false;
            if (close)
                super.onBackPressed();

        }
*/
        Log.d(TAG, "onBackPressed: ");

    }



    /**
     * A {@link FragmentPagerAdapter} that returns a currrentDisplayedFragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter implements ITabFragment {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        Fragment currrentDisplayedFragment;

        /**
         * Za svaki odabrani tab (1, 2 ili 3) klikom na oznaku taba on se učitava u fragemnt frame.
         *
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();

            String IDHouse = "" + house.getId_house();

            bundle.putString("IDkuce", IDHouse);

            TabFragment tabFragment;
            switch (position) {
                case 0:
                    tabFragment = new TabProfil();
                    tabFragment.setArguments(bundle); //pass ID House
                    break;

                case 1:
                    tabFragment = new TabPodaci();
                    tabFragment.setArguments(bundle);
                    break;

                case 2:
                    tabFragment = new TabTlocrt();//
                    tabFragment.setArguments(bundle);
                    break;

                default:
                    tabFragment = new TabProfil();
                    tabFragment.setArguments(bundle); //pass ID House
                    break;
            }

            tabFragment.loadFrag(this);

            return currrentDisplayedFragment;

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        /**
         * postavljanje naziva taba na gumb za odabir tab-a
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "PROFIL";
                case 1:
                    return "PODACI";
                case 2:
                    return "TLOCRT";
            }
            return null;
        }


        /**
         * Fragment dobiven kroz interface se pohranjeuje u currrentDisplayedFragment koji se kasnije učitava u fragment frame.
         *
         * @param f fragment jednog tab-a
         */
        @Override
        public void getFragment(Fragment f) {
            currrentDisplayedFragment = f;
        }
    }


}
