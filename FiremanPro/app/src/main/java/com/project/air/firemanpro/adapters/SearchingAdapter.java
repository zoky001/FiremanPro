package com.project.air.firemanpro.adapters;

import android.content.Context;
import android.graphics.Bitmap;
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

        System.out.println("SEarchConstrAdapter - Construktor: ");
    }

    @Override
    public SearchingViewHolder onCreateViewHolder(ViewGroup vg, int i) {
        View houseView = LayoutInflater.from(vg.getContext()).inflate(R.layout.result_item, vg, false);
        System.out.println("SearchingViewHolder onCreateViewHolder: ");
        return new SearchingViewHolder(houseView);
    }

    @Override
    public void onBindViewHolder(SearchingViewHolder holder, int position) {
        House h = houseItem.get(position);
        holder.name.setText(h.getName_owner());
        holder.address.setText(h.getAddressStreet()+" "+h.getAddress().getStreetNumber());
        holder.ID.setText(String.valueOf(h.getId_house()));
        holder.place.setText(h.getPlaceName());
        holder.surname.setText(h.getSurname_owner());
        holder.grad.setText(h.getAddress().getPost().getPostal_code()+ " "+ h.getAddress().getPost().getName());

        holder.housePicture.setImageBitmap(Bitmap.createScaledBitmap(h.getProfilImageBitmapbyContext(holder.housePicture.getContext()), 270, 200, false));

        System.out.println("onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
        return houseItem.size();
    }
}
