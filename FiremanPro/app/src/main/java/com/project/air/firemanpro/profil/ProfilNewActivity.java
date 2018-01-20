package com.project.air.firemanpro.profil;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.kizo.ground_plan.Tab.TabTlocrt;
import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.controllers.report.InterventionController;

public class ProfilNewActivity extends AppCompatActivity {

    House house;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_new);


        int a = Integer.parseInt(getIntent().getStringExtra("EXTRA_SESSION_ID"));
        if (a != -1) {

            house = HouseController.getHouse(a);

        } else if (a == -1) {
            house = HouseController.getFirstHouse();
        } else {
            house = HouseController.getFirstHouse();
        }

//add tab items with title..


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set title (owner name )on toolbar
        setTitleOnToolbar(house.getSurname_owner() + " " + house.getName_owner() + " - " + house.getPlaceName());


        // Create the adapter that will return a currrentDisplayedFragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder currrentDisplayedFragment containing a simple view.
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a currrentDisplayedFragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter implements ITabFragment {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        Fragment currrentDisplayedFragment;

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

                    tabFragment = new TabTlocrt();
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


        @Override
        public void getFragment(Fragment f) {
            currrentDisplayedFragment = f;
        }
    }

    private void setTitleOnToolbar(String title) {
        //set title (owner name )on toolbar
        getSupportActionBar().setTitle(title); //set title on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } //toolbar
    }


    private boolean close = true;
    private boolean postavljeno = false;

    @Override
    public void onBackPressed() {

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
                            postavljeno=true;
                            onBackPressed();
                            dialog.cancel();

                        }
                    });

            builder1.setNegativeButton(
                    "Ne",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            close = true;
                            postavljeno=true;
                            onBackPressed();
                            dialog.cancel();

                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();
        }else {
            postavljeno = false;
            if (close)
                super.onBackPressed();

        }

        Log.d("KLIK:", "onBackPressed: ");

    }


}
