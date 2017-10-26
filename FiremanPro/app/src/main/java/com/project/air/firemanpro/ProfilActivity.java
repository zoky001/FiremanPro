package com.project.air.firemanpro;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.project.air.firemanpro.profil.ProfilController;
import com.project.test.database.Entities.House;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilActivity extends AppCompatActivity {

    @BindView(R.id.imageViewProfilHouse)
    ImageView profil;

    @BindView(R.id.textViewNameOwner)
    TextView txtNameSurname;

    @BindView(R.id.textViewPlace)
    TextView txtPlace;

    @BindView(R.id.textView1Address)
    TextView txtAdress;

    @BindView(R.id.textViewTel)
    TextView txtTel;

    @BindView(R.id.textViewMobitel)
    TextView txtMobitel;


    House house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind(this);


        final TabHost tabHost = (TabHost) findViewById(R.id.tabhostProfil);
        tabHost.setup();

        TabHost.TabSpec tabhoost1 = tabHost.newTabSpec("Tab1");

        tabhoost1.setContent(R.id.tab1);
        tabhoost1.setIndicator("Profil");
        tabHost.addTab(tabhoost1);

        TabHost.TabSpec tabhoost2 = tabHost.newTabSpec("Tab2");
        tabhoost2.setContent(R.id.tab2);
        tabhoost2.setIndicator("Podatci");
        tabHost.addTab(tabhoost2);

        TabHost.TabSpec tabhoost3 = tabHost.newTabSpec("Tab3");
        tabhoost3.setContent(R.id.tab3);
        tabhoost3.setIndicator("Tlocrt");
        tabHost.addTab(tabhoost3);

        int a = Integer.parseInt(getIntent().getStringExtra("EXTRA_SESSION_ID"));
       if (a != -1){

           house = ProfilController.getHouse(a);

       }
        else {
           house = ProfilController.getFirstHouse();
       }
        Toolbar t = (Toolbar) findViewById(R.id.toolbarProfil);
        setSupportActionBar(t);
        getSupportActionBar().setTitle(house.getSurname_owner()+" "+house.getName_owner()+" - "+house.getPlaceName()); //set title on toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        } //toolbar





        String s = getIntent().getStringExtra("EXTRA_SESSION_ID");
System.out.println("SESSION: "+s);




       System.out.println("SlikaProfila: "+house.getHouse_image());

        String url = "R.drawable."+house.getHouse_image();



        String imageName = house.getHouse_image(); //get image name from database

        int resourceId = this.getResources().
                getIdentifier(imageName, "drawable", this.getPackageName());

        //get resource ID
        // now resource ID is as equivalent to R.drawable.imgName
        profil.setImageResource(resourceId ); //set profil image


        //set owner data
        txtNameSurname.setText( house.getSurname_owner()+" "+house.getName_owner());
txtPlace.setText(house.getPlaceName());
txtAdress.setText(house.getAddress());

        txtMobitel.setText(house.getMobNumber());
        txtTel.setText(house.getTelNumber());




    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == android.R.id.home)
           finish();
        return super.onOptionsItemSelected(item);
    }
}
