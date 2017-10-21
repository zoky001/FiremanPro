package com.project.air.firemanpro.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.air.firemanpro.DbResult;
import com.project.air.firemanpro.R;
import com.project.air.firemanpro.SearchingResultsActivity;
import com.project.air.firemanpro.adapters.SearchResultsRecyclerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Nikol on 21.10.2017..
 */

public class ResultViewHolder extends RecyclerView.ViewHolder {

    public SearchResultsRecyclerAdapter adapter;
    @Bind(R.id.tv_name)
    public TextView name;

    @Bind(R.id.tv_info)
    public  TextView info;


    private Context context;
    private List<DbResult> resItems;

    public ResultViewHolder(final View itemView, SearchResultsRecyclerAdapter adapter, List<DbResult> resItems) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        this.context = itemView.getContext();

        this.adapter = adapter;
        this.resItems = resItems;

    }
}
