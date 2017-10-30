package com.project.air.firemanpro;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.air.firemanpro.profil.ProfilNewActivity;
import com.project.test.database.Entities.House;
//import com.project.test.database.Entities.House_Table;
import com.project.test.database.Entities.Places;
//import com.project.test.database.Entities.Places_Table;
import com.project.test.database.controllers.HouseController;
import com.project.test.database.helper.MockData;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    //Test list used by autocompleteTextVie adapter

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        //set title (owner name )on toolbar
        //getSupportActionBar().setTitle(house.getSurname_owner()+" "+house.getName_owner()+" - "+house.getPlaceName()); //set title on toolbar
        //toolbar

        FlowManager.init(new FlowConfig.Builder(this).build());
        MockData.deleteAll();
        // data for test
        if (SQLite.select().from(House.class).queryList().isEmpty()) {
            //  SQLite.delete().from(Places.class).where(Places_Table.id_place.is(1));
            System.out.println("Nema zapisa u houses: ");

            MockData.writeAll(); //write all entries in database


            autoCompleteTextView.setSingleLine();
            autoCompleteTextView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        Intent Intent = new Intent(MainActivity.this, SearchingResultsActivity.class);
                        Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());
                        startActivity(Intent);
                    }
                    return false;
                }
            });


        }

        TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.til_autocompleteWithLabel);
        inputLayout.setError("First name is required"); // show error
        inputLayout.setError(null); // hide error


        //Saving items in list needed for autoComplete control
        List<String> autocompleteListOfStrings = new ArrayList<String>();
        List<House> allHouses = HouseController.getAllHouseRecords();
        for (int i = 0; i < allHouses.size(); i++) {
            if (!autocompleteListOfStrings.contains(allHouses.get(i).getName_owner())) {
                autocompleteListOfStrings.add(allHouses.get(i).getName_owner());
            }
            if (!autocompleteListOfStrings.contains(allHouses.get(i).getSurname_owner())) {
                autocompleteListOfStrings.add(allHouses.get(i).getSurname_owner());
            }

            if (!autocompleteListOfStrings.contains(allHouses.get(i).getName_owner() + " " + allHouses.get(i).getSurname_owner())) {
                autocompleteListOfStrings.add(allHouses.get(i).getName_owner() + " " + allHouses.get(i).getSurname_owner());
            }
            if (!autocompleteListOfStrings.contains(allHouses.get(i).getPlaceName() + ":" + allHouses.get(i).getAddress())) {
                autocompleteListOfStrings.add(allHouses.get(i).getPlaceName() + ":" + allHouses.get(i).getAddress());
            }
        }
        //ArrayAdapter for autoCompleteTextView and its merging with layout autocompleteTextView item
        final String[] autoCompleteStrings = autocompleteListOfStrings.toArray(new String[autocompleteListOfStrings.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoCompleteStrings);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);


    }


    @OnClick(R.id.buttonSearching)
    public void buttonSearchingClicked(View view) {

        Intent Intent = new Intent(view.getContext(), SearchingResultsActivity.class);
        Intent.putExtra("valueFromAutoCompleteTextView", autoCompleteTextView.getText().toString());
        startActivity(Intent);

    }

    //TEST button,, don't remove!! Kizo
    @OnClick(R.id.test_button)
    public void buttonClicked() {
        System.out.println("Pritisak TEST gumba: ");

        final List<House> discounts = SQLite.select().from(House.class).queryList();
        final List<Places> places = SQLite.select().from(Places.class).queryList();

        String[] listItems = new String[discounts.size()];

        System.out.println("Prije for petlje: ");

        //print all entries from table "House"
        for (int i = 0; i < discounts.size(); i++) {
            listItems[i] = discounts.get(i).getName_owner();
            System.out.println("IDKuće" + i + " je: " + discounts.get(i).getId_house());
            System.out.println("Vlasnik " + i + " kuće je: " + listItems[i]);
            System.out.println("Selo id" + i + " : " + discounts.get(i).getPlace_id());
            System.out.println("Selo " + i + " : " + discounts.get(i).getPlaceName());
            System.out.println("Datum: " + discounts.get(i).getCreated_at());

            //  discounts.get(i).delete();
        }

        //print all entries from table "Places"
        for (int i = 0; i < places.size(); i++) {
            System.out.println("Selo id " + i + " je:" + places.get(i).getId_place());
            System.out.println("Selo " + i + " je:" + places.get(i).getName());

            //  places.get(i).delete();

        }

        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        //   mListView.setAdapter(adapter);
    }

    @OnClick(R.id.test_profil)
    public void buttonProfilClicked(View view) {


        Intent intent = new Intent(view.getContext(), ProfilNewActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", "-1");


        startActivity(intent);


    }


}
