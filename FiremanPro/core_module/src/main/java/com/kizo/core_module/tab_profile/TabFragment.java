package com.kizo.core_module.tab_profile;

import android.support.v4.app.Fragment;

/**
 * Klasa koja mora biti nasljeđena od bilo kojeg fragmenta koji generira jedan TAB za prikaz podataka.
 * <p>
 * <p>
 * Created by Zoran on 9.12.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */

public abstract class TabFragment extends Fragment {

    public ITabFragment iTabFragment;

    /**
     * Metoda prima i postavlja Implementaciju interface-a.
     *
     * @param iTabFragment imaplementacija interface-a ITabFragment
     * @see ITabFragment
     */
    public void loadFrag(ITabFragment iTabFragment) {
        this.iTabFragment = iTabFragment;
    }

}
