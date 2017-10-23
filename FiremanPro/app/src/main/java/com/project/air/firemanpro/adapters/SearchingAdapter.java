package com.project.air.firemanpro.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.air.firemanpro.R;
import com.project.air.firemanpro.holders.SearchingViewHolder;

/**
 * Created by Nikol on 23.10.2017..
 */

public class SearchingAdapter extends RecyclerView.Adapter<SearchingViewHolder> {
    public SearchingAdapter() {

    }

    @Override
    public SearchingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);

        SearchingViewHolder vh = new SearchingViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SearchingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
