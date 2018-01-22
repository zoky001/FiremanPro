package com.project.air.firemanpro.holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.air.firemanpro.R;
import com.project.air.firemanpro.profil.ProfilNewActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * ViewHolder za prikaz rezultata pretraživanja kuća.
 * @author Nikolina Bukovec
 * @version  23.10.2017.
 */

public class SearchingViewHolder extends RecyclerView.ViewHolder {

    public TextView grad;
    public TextView name;
    public ImageView housePicture;
    public TextView ID;
    public  TextView place;
    public TextView surname;
    public TextView address;
    View mItemView;

    public SearchingViewHolder(View v) {
        super(v);
        System.out.println("SearchingViewHolder: ");
        grad = (TextView) v.findViewById(R.id.tv_grad);
        address = (TextView) v.findViewById(R.id.tv_adresa);
        place = (TextView) v.findViewById(R.id.tv_place);
        surname = (TextView) v.findViewById(R.id.tv_surName);
        name = (TextView) v.findViewById(R.id.tv_name);
        housePicture = (ImageView) v.findViewById(R.id.imageResult1);
        ID = (TextView) v.findViewById(R.id.tv_ID);
        mItemView = itemView;
        ButterKnife.bind(this, itemView);
    }

    /**
     * Metoda koja na klik pokreće aktivnost ProfilNewActivity
     *
     */
    @OnClick
    public void clikcOnItem() {
        System.out.println("clikcOnItem()r: ");
        Intent intent = new Intent(itemView.getContext(), ProfilNewActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", ID.getText());
        itemView.getContext().startActivity(intent);
    }
}
