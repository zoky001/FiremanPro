package com.kizo.ground_plan.Tab;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;

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

    House house;

    public static ArrayList<Bitmap> gnd_images = new ArrayList<Bitmap>();

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

        View rootView = inflater.inflate(R.layout.tab_tlocrt, container, false);

        ButterKnife.bind(this, rootView);

        try {
            int a = Integer.parseInt(getArguments().getString("IDkuce"));

            house = HouseController.getHouse(a);

        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }


        final List<House_photos> gndPlans = house.getGroundPlanPhotos();

        /*
        * popunjavanje liste sa fotografijama tlocrta odabrane kuće
        *
        */

        gnd_images.clear();
        for (House_photos house_photos : gndPlans) {
            Bitmap gnd;
            gnd = house_photos.getPhoto().getImageBitmapbyContext(getActivity());
            gnd_images.add(gnd);
        }

        // popounjavanje recycle view adaptera sa listom fotografija

        if (gndPlans != null) {
            RecyclerView mRecycler = (RecyclerView) rootView.findViewById(R.id.main_recycler);
            if (mRecycler != null) {
                PlanRecyclerAdapter planRecyclerAdapter = new PlanRecyclerAdapter(gndPlans);
                mRecycler.setAdapter(planRecyclerAdapter);
                mRecycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
            }
        }
        return rootView;
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