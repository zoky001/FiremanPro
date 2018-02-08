package com.project.air.firemanpro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.project.air.firemanpro.adapters.SearchingAdapter;
import com.project.test.database.Entities.House;
import com.project.test.database.RxJava.RxJavaTest;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.firebaseEntities.Post;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class SearchingResultsActivity extends AppCompatActivity {
    private String TAG = "SearchingResultsActivity_TEXT";
    RecyclerView rv;

    //Retrieving string that user has entered in autoCompleteTextView bar
    String retrievedAutoCompleteTextString;
    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_results);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarProfil);
        setSupportActionBar(toolbar);
        //set title (owner name )on toolbar
        //set title (owner name )on toolbar
        setTitleOnToolbar("Rezultati pretraživanja ");
        //getSupportActionBar().setTitle(house.getSurname_owner()+" "+house.getName_owner()+" - "+house.getPlaceName()); //set title on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } //toolbar


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                retrievedAutoCompleteTextString = null;
            } else {
                retrievedAutoCompleteTextString = extras.getString("valueFromAutoCompleteTextView");
            }
        } else {
            retrievedAutoCompleteTextString = (String) savedInstanceState.getSerializable("valueFromAutoCompleteTextView");
        }


        //Getting List of Houses
        //  final List<House> houses = HouseController.serachByNameAndSurnameQuery(retrievedAutoCompleteTextString);

        Disposable subscribe2 = HouseController.getMatchingHouseId(retrievedAutoCompleteTextString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<String>>() {

                    @Override
                    public void onSuccess(List<String> todos) {
                        // work with the resulting todos
                        Log.d("MAGARAC", todos.toString());

                        fetchHousesbyID(todos);

                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                    }
                });
        disposable.add(subscribe2);


        rv = (RecyclerView) findViewById(R.id.rv_results);

        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

    }

    private void fetchHousesbyID(List<String> todos) {

        Disposable subscribe1 = HouseController.getMatchingHouses(todos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<com.project.test.database.firebaseEntities.House>>() {

                    @Override
                    public void onSuccess(List<com.project.test.database.firebaseEntities.House> todos) {
                        // work with the resulting todos
                        updateTheUserInterface(todos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                    }
                });
        disposable.add(subscribe1);

    }

    private void updateTheUserInterface(List<com.project.test.database.firebaseEntities.House> houses) {

        Log.d(TAG, "prije adaptera");
        rv.setAdapter(new SearchingAdapter(houses));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private void setTitleOnToolbar(String title) {
        //set title (owner name )on toolbar
        getSupportActionBar().setTitle(title); //set title on toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } //toolbar
    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }
}
