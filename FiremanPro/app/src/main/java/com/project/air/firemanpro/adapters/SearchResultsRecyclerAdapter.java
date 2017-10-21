package com.project.air.firemanpro.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.air.firemanpro.DbResult;
import com.project.air.firemanpro.R;
import com.project.air.firemanpro.viewholders.ResultViewHolder;

import java.util.List;

/**
 * Created by Nikol on 21.10.2017..
 */

public class SearchResultsRecyclerAdapter extends RecyclerView.Adapter<ResultViewHolder> {
    List<DbResult> results;
    Context context;

    public SearchResultsRecyclerAdapter(List<DbResult> results, Context context) {
        super();
        this.results = results;
        this.context = context;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.result_item, viewGroup, false);
        return new ResultViewHolder(v, this, results);
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int pos) {
        holder.name.setText(results.get(pos).getName() + results.get(pos).getSurName());
        holder.info.setText(results.get(pos).getAdress());
        }

    @Override
    public int getItemCount() {
        return results.size();
    }
}
