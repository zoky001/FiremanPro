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

    public TextView adressInfo;
    public TextView name;
    public ImageView housePicture;


    public SearchingViewHolder(View v) {
        super(v);
        adressInfo = (TextView) v.findViewById(R.id.tv_info);
        name = (TextView) v.findViewById(R.id.tv_name);
        housePicture = (ImageView) v.findViewById(R.id.imageResult1);
    }


}
