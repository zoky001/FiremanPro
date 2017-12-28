package com.kizo.report.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kizo.report.R;
import com.kizo.report.holder.SavedReportHolder;
import com.project.test.database.Entities.report.Intervention_track;

import java.util.List;

/**
 * Created by Nikol on 28.12.2017..
 */

public class FinishedReportAdapter extends RecyclerView.Adapter<SavedReportHolder> {

    List<Intervention_track> reports;

    public FinishedReportAdapter(List<Intervention_track> reports) {
        this.reports = reports;
    }

    @Override
    public SavedReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View interventionView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_finished_report, parent, false);
        System.out.println("SavedReportHolder onCreateViewHolder: ");
        return new SavedReportHolder(interventionView);
    }

    @Override
    public void onBindViewHolder(SavedReportHolder holder, int position) {
        Intervention_track intervention = reports.get(position);

        holder.opis.setText(intervention.getReports().getDescription());
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }
}
