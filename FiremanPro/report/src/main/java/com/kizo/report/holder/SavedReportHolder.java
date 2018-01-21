package com.kizo.report.holder;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
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
import com.project.test.database.controllers.report.InterventionController;
import com.project.test.database.controllers.report.Types_all_Controller;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Holder koji popunjava svaki element RecycleView-a za prikaz intervencija.
 * <p>
 * popunajva sliku, Naziv intervencije i opis intervencije.
 * <p>
 * <p>
 * Created by Zoran on 27.11.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */
public class SavedReportHolder extends ParentViewHolder {

    public TextView mStoreName;
    public TextView mStoreDescription;
    public ImageView mStoreImage;
    public TextView interventionType;
    private int position = 0;
    public CardView cardView;
    public TextView ID;

    View mItemView;

    // constructor binds the ButterKnife library and makes itemView available locally
    public SavedReportHolder(final View itemView) {
        super(itemView);

        try {
            mItemView = itemView;
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            interventionType = (TextView) itemView.findViewById(R.id.interventionType);
            mStoreName = (TextView) itemView.findViewById(R.id.store_name);
            mStoreImage = (ImageView) itemView.findViewById(R.id.store_image);
            mStoreDescription = (TextView) itemView.findViewById(R.id.store_description);
            ID = (TextView) itemView.findViewById(R.id.textView_ID);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    System.out.println("clickOnItem() _ ID: " + ID.getText());
                    Intent intent = new Intent(v.getContext(), FinishedReportActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID", ID.getText());
                    v.getContext().startActivity(intent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                    builder1.setMessage("Jeste li sigurni da želite obrisati zapisnik intervencije? ");
                    builder1.setCancelable(true);


                    builder1.setPositiveButton(
                            "Da",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    if (InterventionController.deleteInterventionWithID(Integer.valueOf(ID.getText().toString()))) {
                                        itemView.setVisibility(View.INVISIBLE);
                                    }


                                    dialog.cancel();

                                }
                            });

                    builder1.setNegativeButton(
                            "Ne",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                    dialog.cancel();

                                }
                            });


                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                    return false;
                }
            });

            ButterKnife.bind(this, itemView);


        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }

    }

    // when the adapter is implemented this method is used to bind list elements with the recycler-view, here, we populate the Views
    public void bind(Intervention_track intervention_track, int position) {

        this.position = position;


        if (!intervention_track.isCompleted_intervention()) {
            // interventionType.setTextColor(Color.parseColor("#c1000d"));

            //unfinished intervention cad has red background
            cardView.setCardBackgroundColor(Color.parseColor("#DEE93C"));
        }


        ID.setText(String.valueOf(intervention_track.getId_intervention_track()));

        try {

            Types_all_Controller types_all_controller = new Types_all_Controller();

            if (intervention_track.getReports().getSort_of_intervention().getId() == types_all_controller.get_FIRE_Sort_of_intervention().getId()) {

                interventionType.setText(intervention_track.getReports().getSort_of_intervention().getName() + " - " +

                        intervention_track.getReports().getFireInterventionDetails().getIntervention_type().getName());
            } else if (intervention_track.getReports().getSort_of_intervention().getId() == types_all_controller.get_TRHNICAL_Sort_of_intervention().getId()) {

                interventionType.setText(intervention_track.getReports().getSort_of_intervention().getName());
            } else if (intervention_track.getReports().getSort_of_intervention().getId() == types_all_controller.get_OTHER_Sort_of_intervention().getId()) {

                interventionType.setText(intervention_track.getReports().getSort_of_intervention().getName());
            }


            mStoreDescription.setText(intervention_track.getReports().getDescription());
            mStoreName.setText(intervention_track.getLocation().getStreetNameIfExist() + " " + intervention_track.getLocation().getStreetNumber());
            mStoreImage.setImageBitmap(intervention_track.getHouse().getProfilPhotos().getImageBitmapbyContext(itemView.getContext()));

            // Picasso.with(itemView.getContext()).load(store.getImgUrl()).into(mStoreImage);

        } catch (Exception e) {

            System.out.println("EXCEPTION: " + e.getMessage());
        }

    }


}