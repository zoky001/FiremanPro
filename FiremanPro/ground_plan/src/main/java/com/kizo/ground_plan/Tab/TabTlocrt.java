package com.kizo.ground_plan.Tab;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.kizo.core_module.tab_profile.ITabFragment;
import com.kizo.core_module.tab_profile.TabFragment;
import com.kizo.ground_plan.R;
import com.kizo.ground_plan.Tab.adapter.PlanRecyclerAdapter;
import com.project.test.database.Entities.House;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.firebaseEntities.Photos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Kreira se fragment koji je jedan Tab.
 * <p>
 * Tab sadrži listu fotografija koje su tlocrti odabrane kuće.
 * <p>
 * Klikom na sliku, ona prelazi u prvi plan, full screen.
 * <p>
 * Potrebno je prsljediti ID kuće u argumentu naziva "IDkuce"
 * <p>
 * Created by Zoran on 27.10.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */
public class TabTlocrt extends TabFragment {

    //House house;

    com.project.test.database.firebaseEntities.House house;

    CompositeDisposable disposable = new CompositeDisposable();

    Single<com.project.test.database.firebaseEntities.House> cachedSingleHouse;

    public static List<Photos> gnd_images = new ArrayList<Photos>();
    View rootView;

    /**
     * Nakon kreiranja View-a dohvaća se kuća i sprema u objke House  prema prosljeđenom ID-u.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState - potrebno je prsljediti ID kuće u argumentu naziva "IDkuce"
     * @return fragment sa prikazom liste fotografija tlocrta
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.tab_tlocrt, container, false);

        ButterKnife.bind(this, rootView);


        return rootView;
    }

    @Override
    public void onHouseLoaded(com.project.test.database.firebaseEntities.House house) {
        super.onHouseLoaded(house);
        setLayoutData(house);
    }

    private void setLayoutData(com.project.test.database.firebaseEntities.House house) {

    /*    Picasso.with(getActivity())
                .load(house.getProfilPicUrl())
                .into(profil);
*/

        final List<Photos> gndPlans = house.getGroundPlanPhotos();
        this.gnd_images = gndPlans;

        /*
        * popunjavanje liste sa fotografijama tlocrta odabrane kuće
        *
        */

       // gnd_images.clear();


        // popounjavanje recycle view adaptera sa listom fotografija

        if (gndPlans != null) {
            RecyclerView mRecycler = (RecyclerView) rootView.findViewById(R.id.main_recycler);
            if (mRecycler != null) {
                PlanRecyclerAdapter planRecyclerAdapter = new PlanRecyclerAdapter(gndPlans);
                mRecycler.setAdapter(planRecyclerAdapter);
                mRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
            }
        }





    }

    @Override
    public void onResume() {
        super.onResume();
    }


    /**
     * Postavljanje ovog fragmenta kao parametar u metodu iz interface-a ITabFragment.
     *
     * U metodu gatFragment
     *
     * @see ITabFragment
     *
     */
    @Override
    public void loadFrag(ITabFragment iTabFragment) {
        super.loadFrag(iTabFragment);
        iTabFragment.getFragment(this);
    }
}