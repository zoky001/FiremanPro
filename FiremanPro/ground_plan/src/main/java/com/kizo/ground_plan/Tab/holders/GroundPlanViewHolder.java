package com.kizo.ground_plan.Tab.holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.kizo.ground_plan.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Nikol on 23.10.2017..
 */

public class GroundPlanViewHolder extends RecyclerView.ViewHolder {


    public ImageView gndPicture;



    View mItemView;

    public GroundPlanViewHolder(View v) {
        super(v);

        gndPicture = (ImageView) v.findViewById(R.id.imageViewGroundListItem);

        mItemView = itemView;
        ButterKnife.bind(this, itemView);
    }


    @OnClick
    public void clikcOnItem(){

       /* Intent intent = new Intent(itemView.getContext(), ProfilNewActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", ID.getText()); // umjesto 01 prosljediš ID kuće
        itemView.getContext().startActivity(intent);
*/



    }


}
