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

import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.kizo.ground_plan.Tab.TabTlocrt;
import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;
import com.project.test.database.controllers.HouseController;


/**
 * Aktivnost  koja sadržava prikazivanje tabova.
 * <p>
 * Svaki tab opisuje neke informacije o odabranoj kući.
 * <p>
 * Prilikom kreiranja potrebno je prosljediti id koristeći Bundle, "EXTRA_SESSION_ID" = id odabrane kuce
 *
 * @author Zoran Hrnčić
 */
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            int a = Integer.parseInt(getIntent().getStringExtra("EXTRA_SESSION_ID"));
            house = HouseController.getHouse(a);
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

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

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
}
