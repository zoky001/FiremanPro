package com.kizo.report;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.project.test.database.Entities.Settings;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Aktivnost koja prikazuje unutar fragmenta, dovršene i nedovršene izvještaje.
 * <p>
 * Sadrži navigation drawer pomoću kojeg se moguće navigirati na prethodnu aktivost, odnosno pregledavati dovršene ili nedovršene izvještaje.
 *
 * @author Zoran Hrnčić
 */
public class ReportActivity extends AppCompatActivity implements ITabFragment,
        NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        try {
            toolbar = (Toolbar) findViewById(R.id.toolbarReport);
            setSupportActionBar(toolbar);
            //set title (owner name )on toolbar
            setTitleOnToolbar("Nedovršeni izvještaji");

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            View header = navigationView.getHeaderView(0);

            TextView headerTitle = (TextView) header.findViewById(R.id.headerTitle);
            headerTitle.setText(Settings.getSettings().getPatrolName());
            //default load all reports
            Bundle bundle = new Bundle();
            TabFragment tabFragment;
            setTitleOnToolbar("Svi zapisnici");
            bundle.putString("showFinishedReports", "ALL");
            tabFragment = new SavedReportFragment();
            tabFragment.setArguments(bundle);
            tabFragment.loadFrag(this);
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());

        }


    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    //back arrow
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    private void setTitleOnToolbar(String title) {
        //set title (owner name )on toolbar
        getSupportActionBar().setTitle(title); //set title on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } //toolbar
    }


    @Override
    public void getFragment(Fragment f) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_report, f)
                .commit();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.report_nav_home) {
            onBackPressed();
            return true;
        }


        TabFragment tabFragment;
        Bundle bundle = new Bundle();
        if (id == R.id.report_nav_fished_reports) {
            setTitleOnToolbar("Popunjeni zapisnici");
            System.out.println("finished");
            tabFragment = new SavedReportFragment();
            bundle.putString("showFinishedReports", "TRUE");
            tabFragment.setArguments(bundle);
        } else if (id == R.id.report_nav_unfinished_reports) {
            setTitleOnToolbar("Nedovršeni zapisnici");
            System.out.println("unfinished");
            tabFragment = new SavedReportFragment();

            bundle.putString("showFinishedReports", "FALSE");
            tabFragment.setArguments(bundle);
        } else if (id == R.id.report_nav_all_reports) {
            setTitleOnToolbar("Svi zapisnici");
            bundle.putString("showFinishedReports", "ALL");

            tabFragment = new SavedReportFragment();
            tabFragment.setArguments(bundle);
        } else {
            setTitleOnToolbar("Svi zapisnici");
            bundle.putString("showFinishedReports", "ALL");

            tabFragment = new SavedReportFragment();
            tabFragment.setArguments(bundle);
        }

        tabFragment.loadFrag(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
