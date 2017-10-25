package com.project.air.firemanpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.test.database.Entities.House;
//import com.project.test.database.Entities.House_Table;
import com.project.test.database.Entities.Places;
//import com.project.test.database.Entities.Places_Table;
import com.project.test.database.helper.MockData;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.sql.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //Test list used by autocompleteTextVie adapter
    private static final String[] Ime = new String[] {"Josip","Jana","Igor","Ivan","Domagoj"};
    @BindView(R.id.autoCompleteTextView)AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        FlowManager.init(new FlowConfig.Builder(this).build());

// data for test
        if (SQLite.select().from(House.class).queryList().isEmpty()) {
            //  SQLite.delete().from(Places.class).where(Places_Table.id_place.is(1));
            System.out.println("Nema zapisa u houses: ");

            MockData.writeAll(); //write all entries in database
        }

            Button search = (Button) findViewById(R.id.buttonSearching);

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Intent = new Intent(view.getContext(), SearchingResultsActivity.class);
                    view.getContext().startActivity(Intent);
                }
            });

        //ArrayAdapter for autoCompleteTextView and its merging with layout autocompleteTextView item
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, Ime);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);







    }


        //TEST button,, don't remove!! Kizo
    @OnClick(R.id.test_button)
    public void buttonClicked(){
        System.out.println("Pritisak TEST gumba: ");

        final List<House> discounts = SQLite.select().from(House.class).queryList();
        final List<Places> places = SQLite.select().from(Places.class).queryList();

        String[] listItems = new String[discounts.size()];

        System.out.println("Prije for petlje: ");

        //print all entries from table "House"
        for(int i = 0; i < discounts.size(); i++){
            listItems[i] = discounts.get(i).getName_owner();
            System.out.println("IDKuće"+ i +" je: " + discounts.get(i).getId_house());
            System.out.println("Vlasnik "+ i +" kuće je: " + listItems[i]);
            System.out.println("Selo id"+ i + " : " + discounts.get(i).getPlace_id());
            System.out.println("Selo "+ i + " : " + discounts.get(i).getPlaceName());
          System.out.println("Datum: " + discounts.get(i).getCreated_at());

     //  discounts.get(i).delete();
        }

        //print all entries from table "Places"
        for (int i = 0; i < places.size();i++){
            System.out.println("Selo id "+ i + " je:" + places.get(i).getId_place());
            System.out.println("Selo "+ i + " je:" + places.get(i).getName());

      //  places.get(i).delete();

        }

        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        //   mListView.setAdapter(adapter);
    }

    @OnClick(R.id.test_profil)
    public void buttonProfilClicked(View view){
       /* Intent Intent = new Intent(view.getContext(), ProfilActivity.class);
        view.getContext().startActivity(Intent);
*/
        Intent intent = new Intent(getBaseContext(), ProfilActivity.class);
        intent.putExtra("EXTRA_SESSION_ID", "01"); // umjesto 01 prosljediš ID kuće
        startActivity(intent);

    }
}
