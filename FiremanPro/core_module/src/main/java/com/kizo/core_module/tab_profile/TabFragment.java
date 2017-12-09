package com.kizo.core_module.tab_profile;

import android.support.v4.app.Fragment;

/**
 * Created by Zoran on 9.12.2017..
 */

public abstract class TabFragment extends Fragment {

    public ITabFragment iTabFragment;

    public void loadFrag(ITabFragment iTabFragment){
       this.iTabFragment = iTabFragment;

    }

}
