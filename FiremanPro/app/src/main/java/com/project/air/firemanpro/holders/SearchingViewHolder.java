package com.project.air.firemanpro.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nikol on 23.10.2017..
 */

public class SearchingViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_info)
    public TextView adressInfo;
    @BindView(R.id.tv_name)
    public TextView name;
    @BindView(R.id.imageResult1)
    public ImageView housePicture;


    public SearchingViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }

    public void bind(House house){
        name.setText(house.getName_owner());
        adressInfo.setText(house.getPlaceName());
        //housePicture.setImageURI(house.getHouse_image());
    }
}
