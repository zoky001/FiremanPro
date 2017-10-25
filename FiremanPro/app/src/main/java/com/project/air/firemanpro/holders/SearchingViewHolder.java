package com.project.air.firemanpro.holders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.air.firemanpro.ProfilActivity;
import com.project.air.firemanpro.R;
import com.project.test.database.Entities.House;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nikol on 23.10.2017..
 */

public class SearchingViewHolder extends RecyclerView.ViewHolder {

    public TextView adressInfo;
    public TextView name;
    public ImageView housePicture;
    public TextView ID;


    View mItemView;

    public SearchingViewHolder(View v) {
        super(v);
        adressInfo = (TextView) v.findViewById(R.id.tv_info);
        name = (TextView) v.findViewById(R.id.tv_name);
        housePicture = (ImageView) v.findViewById(R.id.imageResult1);
        ID = (TextView) v.findViewById(R.id.tv_ID);
        mItemView = itemView;
        ButterKnife.bind(this, itemView);
    }
    @OnClick
    public void clikcOnItem(){

        Intent intent = new Intent(itemView.getContext(), ProfilActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", ID.getText()); // umjesto 01 prosljediš ID kuće
        itemView.getContext().startActivity(intent);


    }


}
