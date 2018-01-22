package com.kizo.core_module.tab_profile;

import android.support.v4.app.Fragment;

/**
 * Definira metode za rad fragmentima koji predstavljaju po jedan TAB za prikaz podataka.
 * <p>
 * <p>
 * Created by Zoran on 9.12.2017..
 * </p>
 *
 * @author Zoran Hrnčić
 */

public interface ITabFragment {

    /**
     * @param f fragment jednog tab-a
     */
    void getFragment(Fragment f);


}
