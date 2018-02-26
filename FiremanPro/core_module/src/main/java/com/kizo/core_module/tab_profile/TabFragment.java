package com.kizo.core_module.tab_profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.test.database.controllers.HouseController;
import com.project.test.database.firebaseEntities.House;
import com.squareup.picasso.Picasso;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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
    public com.project.test.database.firebaseEntities.House house;

    public CompositeDisposable disposable = new CompositeDisposable();

    public Single<House> cachedSingleHouse;

    /**
     * Metoda prima i postavlja Implementaciju interface-a.
     *
     * @param iTabFragment imaplementacija interface-a ITabFragment
     * @see ITabFragment
     */
    public void loadFrag(ITabFragment iTabFragment) {
        this.iTabFragment = iTabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        try {
            String s = getArguments().getString("IDkuce");
            cachedSingleHouse = HouseController.getHouseByID(s).cache();
            loadHouse(cachedSingleHouse);
            System.out.println("SESSION FRAGMENT_idkuce: " + s);

        } catch (Exception e) {
            System.out.println("EXCEPTON: " + e.getMessage());
        }
        return root;
    }


    private void loadHouse(Single<com.project.test.database.firebaseEntities.House> cachedSingleHouse) {
        Disposable subscribe2 = cachedSingleHouse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<House>() {

                    @Override
                    public void onSuccess(com.project.test.database.firebaseEntities.House todos) {
                        // work with the resulting todos
                        Log.d("MAGARAC", todos.toString());
                        onHouseLoaded(todos);


                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                    }
                });
        disposable.add(subscribe2);

    }

    public void onHouseLoaded(com.project.test.database.firebaseEntities.House house) {
        this.house = house;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
