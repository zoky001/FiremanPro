package com.kizo.report;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.kizo.report.adapter.SavedReportRecyclerAdapter;
import com.project.test.database.Entities.report.Intervention_track;
import com.project.test.database.controllers.report.InterventionController;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Zoran on 10.12.2017..
 */

public class SavedReportFragment extends TabFragment {
    public static final int NEW_ALARM = 1;

    private static final String DATA_RECEIVED = "data_received";
    private static final String INFORMATION = "information";
    private static final String DISCLAIMER = "disclaimer";

    private FloatingActionButton fab;
    private TextView information, disclaimer;
    private boolean dataReceived = false;

    private boolean showFinishedReports = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_saved_report, container, false);

        ButterKnife.bind(this, rootView);

        information = (TextView) rootView.findViewById(R.id.information);
        disclaimer = (TextView) rootView.findViewById(R.id.disclaimer);


        // fill list
        List<Intervention_track> interventions;
        if(getArguments().getString("showFinishedReports") == "TRUE")
        {
            interventions = InterventionController.getCompletedIntervention();
            showFinishedReports = true;
        }
        else if (getArguments().getString("showFinishedReports") == "FALSE")
        {
            interventions = InterventionController.getUnfinishedIntervention();
            showFinishedReports = false;
        }
        else if (getArguments().getString("showFinishedReports") == "ALL") {
            interventions = InterventionController.getAllIntervention();

        }
        else {
            interventions = InterventionController.getAllIntervention();
        }



        if(interventions != null) {

            RecyclerView mRecycler = (RecyclerView) rootView.findViewById(R.id.report_saved_recycler);
            if(mRecycler != null) {
                SavedReportRecyclerAdapter planRecyclerAdapter = new SavedReportRecyclerAdapter(interventions);
                mRecycler.setAdapter(planRecyclerAdapter);

                mRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));


            }

        }

        return rootView;


    }


    /*
    @Override
    public void onViewStateRestored (Bundle savedInstanceState){
        super.onViewStateRestored(savedInstanceState);

        dataReceived = savedInstanceState.getBoolean(DATA_RECEIVED, false);
        if(dataReceived) {
            disclaimer.setVisibility(View.VISIBLE);
            information.setText(savedInstanceState.getString(INFORMATION));
            disclaimer.setText(savedInstanceState.getString(DISCLAIMER));
        }
    }
*/
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean(DATA_RECEIVED, dataReceived);
        if (dataReceived) {
            savedInstanceState.putString(INFORMATION, information.getText().toString());
            savedInstanceState.putString(DISCLAIMER, disclaimer.getText().toString());
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == NEW_ALARM && data != null) {
            if(data.hasExtra(NewReportFormActivity.NEW_ALARM_ADDED)
                    && data.getExtras().getBoolean(NewReportFormActivity.NEW_ALARM_ADDED, false)) {

                // Handling the data received from the stepper form
                dataReceived = true;
                String title = data.getExtras().getString(NewReportFormActivity.STATE_TITLE);
                //String description = data.getExtras().getString(NewReportFormActivity.STATE_DESCRIPTION);
                int hour = data.getExtras().getInt(NewReportFormActivity.STATE_TIME_HOUR);
                int minutes = data.getExtras().getInt(NewReportFormActivity.STATE_TIME_MINUTES);
                String time = ((hour > 9) ? hour : ("0" + hour))
                        + ":" + ((minutes > 9) ? minutes : ("0" + minutes));
                //boolean[] weekDays = data.getExtras().getBooleanArray(NewReportFormActivity.STATE_WEEK_DAYS);
                information.setText("Alarm \"" + title + "\" set up at " + time);
                disclaimer.setVisibility(View.VISIBLE);
                Snackbar.make(fab, getString(R.string.new_alarm_added), Snackbar.LENGTH_LONG).show();
            }
        }
    }



    @Override
    public void loadFrag(ITabFragment iTabFragment) {
        super.loadFrag(iTabFragment);
        iTabFragment.getFragment(this);
    }



}
