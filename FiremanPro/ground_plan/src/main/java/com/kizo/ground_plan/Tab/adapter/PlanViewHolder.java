package com.kizo.ground_plan.Tab.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.kizo.ground_plan.R;
import com.kizo.ground_plan.Tab.FullScreenActivity;
import com.project.test.database.Entities.House_photos;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * Holder za prikazivanje svake fotografije u listi/glariji fotografija tlocrta.
 *
 * Created by Zoran on 27.11.2017..
 */

public class PlanViewHolder extends ParentViewHolder {

    public TextView mImageName;
    public TextView mImageDescription;
    public ImageView mGroundPlanImage;
    private int position = 0;
    View mItemView;


    /**
     * povezivanje svakog elementa liste sa definiranim layoutom
     *
     * @param itemView
     */
    public PlanViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        mImageName = (TextView) itemView.findViewById(R.id.store_name);
        mGroundPlanImage = (ImageView) itemView.findViewById(R.id.store_image);
        mImageDescription = (TextView) itemView.findViewById(R.id.store_description);
        ButterKnife.bind(this, itemView);
    }


    /**
     * Popounjavanje svakog elementa liste sa podatcima o fotografiji koja se prikazuje.
     *
     *
     * @param house_photos zapis o fotografiji (podatci o lokaciji pohranjene fotografjie u memoriji) iz baze podatake
     * @param position
     */
    public void bind(House_photos house_photos, int position) {
        this.position = position;
        mImageName.setText(house_photos.getPhoto().getImageName());
        mImageDescription.setText("opis");
        mGroundPlanImage.setImageBitmap(house_photos.getPhoto().getImageBitmapbyContext(itemView.getContext()));
    }


    /**
     * Klikom na bilo koji elment liste/ fotografiju tlocrta, otvara se nova aktivnost koja priakzuje odabranu sliku u Full Screen modu
     *
     * Potrebno je prosljediti poziciju odabranje slike.
     *
     */
    @OnClick
    public void clikcOnItem() {
        Intent intent = new Intent(itemView.getContext(), FullScreenActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", position);
        itemView.getContext().startActivity(intent);
    }
}