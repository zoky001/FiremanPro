package com.project.air.firemanpro.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.air.firemanpro.R;

import butterknife.ButterKnife;

/**
 * Created by Nikol on 23.10.2017..
 */

public class SearchingViewHolder extends RecyclerView.ViewHolder {

    //@Bind(R.id.tv_info)
    public TextView taskTitle;

    //@Bind(R.id.tv_name)
    public TextView taskDate;


    public SearchingViewHolder(View v) {
        super(v);

        ButterKnife.bind(this, v);
    }
}
