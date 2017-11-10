package com.project.air.firemanpro.profil;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.kizo.ground_plan.Tab.TabTlocrt;
import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;

public class ProfilNewActivity extends AppCompatActivity {

    House house;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
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


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

/*  floatin button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }



    /**
     * A placeholder fragment containing a simple view.
     */

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();

            String IDHouse = "" + house.getId_house();

            bundle.putString("IDkuce", IDHouse);

            switch (position) {
                case 0:
                    TabProfil tab1 = new TabProfil();


                    tab1.setArguments(bundle); //pass ID House

                    return tab1;

                case 1:
                    TabPodaci tab2 = new TabPodaci();


                    tab2.setArguments(bundle);
                    return tab2;
                case 2:

                    TabTlocrt tab3 = new TabTlocrt();
                    tab3.setArguments(bundle);

                    return tab3;

                default:
                    return null;
            }
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
    }

    private void setTitleOnToolbar(String title){
        //set title (owner name )on toolbar
        getSupportActionBar().setTitle(title); //set title on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } //toolbar
    }
}
