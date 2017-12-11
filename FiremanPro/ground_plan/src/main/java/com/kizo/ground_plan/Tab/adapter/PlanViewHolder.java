package com.kizo.ground_plan.Tab.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import com.kizo.ground_plan.R;
import com.kizo.ground_plan.Tab.FullScreenActivity;
import com.project.test.database.Entities.House_photos;
import com.squareup.picasso.Picasso;
import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Zoran on 27.11.2017..
 */

public class PlanViewHolder extends ParentViewHolder {
    /*
    @BindView(R.id.store_name)
    TextView mStoreName;
    @BindView(R.id.store_description)
    TextView mStoreDescription;
    @BindView(R.id.store_image)
    ImageView mStoreImage;
    */

    public  TextView mStoreName;
public TextView mStoreDescription;
    public  ImageView mStoreImage;

    private int position = 0;



    View mItemView;

    // constructor binds the ButterKnife library and makes itemView available locally
    public PlanViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;

        mStoreName  = (TextView) itemView.findViewById(R.id.store_name);
        mStoreImage = (ImageView) itemView.findViewById(R.id.store_image);
        mStoreDescription = (TextView) itemView.findViewById(R.id.store_description);

        ButterKnife.bind(this, itemView);


    }

    // when the adapter is implemented this method is used to bind list elements with the recycler-view, here, we populate the Views
    public void bind(House_photos house_photos, int position){
        this.position = position;

        mStoreName.setText(house_photos.getPhoto().getImageName());
        mStoreDescription.setText("opis");
        mStoreImage.setImageBitmap(house_photos.getPhoto().getImageBitmapbyContext(itemView.getContext()));
       // Picasso.with(itemView.getContext()).load(store.getImgUrl()).into(mStoreImage);
    }

    @OnClick
    public void clikcOnItem() {

        System.out.println("clikcOnItem()r: ");




        System.out.println("clikcOnItem()r: ");
        Intent intent = new Intent(itemView.getContext(), FullScreenActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", position); // umjesto 01 prosljediš ID kuće
        itemView.getContext().startActivity(intent);


    }
}