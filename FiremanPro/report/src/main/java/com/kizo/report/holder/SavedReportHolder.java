package com.kizo.report.holder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import com.kizo.report.FinishedReportActivity;
import com.kizo.report.R;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.Reports;
import com.project.test.database.Entities.report.Intervention_track;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Zoran on 27.11.2017..
 */

public class SavedReportHolder extends ParentViewHolder {

    public  TextView mStoreName;
    public TextView mStoreDescription;
    public  ImageView mStoreImage;
    public  TextView interventionType;
    private int position = 0;
    public CardView cardView;
    public TextView ID;


    //Intervention_track intervention;



    View mItemView;

    // constructor binds the ButterKnife library and makes itemView available locally
    public SavedReportHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        cardView = (CardView) itemView.findViewById(R.id.card_view);
        interventionType  = (TextView) itemView.findViewById(R.id.interventionType);
        mStoreName  = (TextView) itemView.findViewById(R.id.store_name);
        mStoreImage = (ImageView) itemView.findViewById(R.id.store_image);
        mStoreDescription = (TextView) itemView.findViewById(R.id.store_description);
        ID = (TextView) itemView.findViewById(R.id.textView_ID);

        //viska

/*

*/
        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View v){
                System.out.println("clickOnItem() _ ID: " + ID.getText());
                Intent intent = new Intent(v.getContext(), FinishedReportActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", ID.getText()); // umjesto 01 prosljediš ID intervencije
                v.getContext().startActivity(intent);
            }
        });

        ButterKnife.bind(this, itemView);


    }

    // when the adapter is implemented this method is used to bind list elements with the recycler-view, here, we populate the Views
    public void bind(Intervention_track intervention_track, int position){
        this.position = position;




        if (!intervention_track.isCompleted_intervention())
        {
           // interventionType.setTextColor(Color.parseColor("#c1000d"));

            //unfinished intervention cad has red background
            cardView.setCardBackgroundColor(Color.parseColor("#DEE93C"));
        }
        interventionType.setText(intervention_track.getReports().getSort_of_intervention().getName() +" - " +
        intervention_track.getReports().getFireInterventionDetails().getIntervention_type().getName());
        mStoreDescription.setText(intervention_track.getReports().getDescription());
        mStoreName.setText(intervention_track.getLocation().getStreetNameIfExist() + " " + intervention_track.getLocation().getStreetNumber());
       mStoreImage.setImageBitmap(intervention_track.getHouse().getProfilPhotos().getImageBitmapbyContext(itemView.getContext()));
       // Picasso.with(itemView.getContext()).load(store.getImgUrl()).into(mStoreImage);
        ID.setText(String.valueOf(intervention_track.getId_intervention_track()));
    }


}