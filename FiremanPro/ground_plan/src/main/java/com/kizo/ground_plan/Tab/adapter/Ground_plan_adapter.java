package com.kizo.ground_plan.Tab.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kizo.ground_plan.R;
import com.kizo.ground_plan.Tab.holders.GroundPlanViewHolder;
import com.project.test.database.Entities.Ground_plan;


import com.project.test.database.Entities.House;

import java.util.List;

/**
 * Created by Nikol on 23.10.2017..
 */

public class Ground_plan_adapter extends RecyclerView.Adapter<GroundPlanViewHolder> {
    List<Ground_plan> houseItem;

    public Ground_plan_adapter(List<Ground_plan> houseItem) {
        this.houseItem = houseItem;

        System.out.println("konstruktor_ adapter size: "+ houseItem.size());
    }

    @Override
    public GroundPlanViewHolder onCreateViewHolder(ViewGroup vg, int i) {
        View tabTlocertView = LayoutInflater.from(vg.getContext()).inflate(R.layout.tlocrt_item, vg, false);
System.out.println("on createHolder GND");
        return new GroundPlanViewHolder(tabTlocertView);
    }

    @Override
    public void onBindViewHolder(GroundPlanViewHolder holder, int position) {
        Ground_plan h = houseItem.get(position);
        System.out.println("OnBIndVIew_: "+ h.getImgAdress());
        holder.gndPicture.setImageResource(h.getImageResourceIDbyContext(holder.gndPicture.getContext()));
    }

    @Override
    public int getItemCount() {
        return houseItem.size();
    }
}
