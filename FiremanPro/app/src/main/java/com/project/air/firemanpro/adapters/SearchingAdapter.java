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

    public SearchingAdapter(List<House> houseItem) {
        this.houseItem = houseItem;
    }

    @Override
    public SearchingViewHolder onCreateViewHolder(ViewGroup vg, int i) {
        View houseView = LayoutInflater.from(vg.getContext()).inflate(R.layout.result_item, vg, false);

        return new SearchingViewHolder(houseView);
    }

    @Override
    public void onBindViewHolder(SearchingViewHolder holder, int position) {
        House h = houseItem.get(position);
        holder.name.setText(h.getName_owner());
        holder.adressInfo.setText(h.getPlaceName());
        //holder.housePicture.setImageURI(h.getHouse_image());
    }

    @Override
    public int getItemCount() {
        return houseItem.size();
    }
}
