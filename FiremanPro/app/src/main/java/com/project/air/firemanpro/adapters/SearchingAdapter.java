package com.project.air.firemanpro.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.air.firemanpro.R;
import com.project.air.firemanpro.holders.SearchingViewHolder;
import com.project.test.database.Entities.House;

import java.util.List;

/**
 * Created by Nikol on 23.10.2017..
 */

public class SearchingAdapter extends RecyclerView.Adapter<SearchingViewHolder> {
    List<House> houseItem;
    Context context;

    public SearchingAdapter(List<House> houseItem, Context context) {
        super();
        this.houseItem = houseItem;
        this.context = context;
    }

    @Override
    public SearchingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View houseView = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
        SearchingViewHolder vh = new SearchingViewHolder(houseView);
        return vh;
    }

    @Override
    public void onBindViewHolder(SearchingViewHolder holder, int position) {
        holder.name.setText(houseItem.get(position).getName_owner());
        holder.adressInfo.setText(houseItem.get(position).getPlaceName());
        //holder.housePicture.setImageURI(houseItem.get(position).getHouse_image());
    }

    @Override
    public int getItemCount() {
        return houseItem.size();
    }
}
