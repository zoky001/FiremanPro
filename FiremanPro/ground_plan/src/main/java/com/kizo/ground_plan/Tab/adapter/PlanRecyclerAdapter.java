package com.kizo.ground_plan.Tab.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kizo.ground_plan.R;
import com.project.test.database.Entities.House_photos;

import java.util.List;

/**
 * Adapter koji popunjava listu fotografija tlocrta odabrane kuće.
 * <p>
 * <p>
 * Created by Zoran on 27.11.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */

public class PlanRecyclerAdapter extends RecyclerView.Adapter<PlanViewHolder> {

    List<House_photos> house_photoses;

    /**
     * pohranjuje listu fotografija u lokalnu varijablu house_photoses
     *
     * @param parentList lista fotografija (zapisa iz baze) tlocrta kuće
     */
    public PlanRecyclerAdapter(List<House_photos> parentList) {
        this.house_photoses = parentList;
    }


    /**
     * Krerianje viewHoldera za svaki zapis/sliku.
     *
     * @return jedna instanca klase  PlanViewHolder
     */
    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View houseView = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_list_item, parent, false);
        return new PlanViewHolder(houseView);
    }


    @Override
    public void onBindViewHolder(PlanViewHolder holder, int position) {
        holder.bind(house_photoses.get(position), position);
    }

    @Override
    public int getItemCount() {
        return house_photoses.size();
    }
}