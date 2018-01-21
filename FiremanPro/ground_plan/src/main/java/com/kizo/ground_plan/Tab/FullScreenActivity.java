package com.kizo.ground_plan.Tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import com.kizo.ground_plan.R;
import com.kizo.ground_plan.Tab.adapter.ViewPagerAdapter;
import com.kizo.ground_plan.Tab.model.ImageBean;

import java.util.ArrayList;

/**
 * Aktivnost prikazuje odabranu sliku u Full Screen modu.
 *
 * Omogućuje zoomiranje, rotaciju i listanje slike (lijevo -> prethodna) (desno -> sljedeća)
 *
 * @author Zoran Hrnčić
 */
public class FullScreenActivity extends AppCompatActivity {

    ImageBean imageBean;
    ArrayList<ImageBean> image_list = new ArrayList<>();
    ViewPager viewPager;
    /**
     * Kreira se lista fotografija i fokusira, tj. u prvom planu se prikaže odabrana slika prema prosljeđenoj poziciji.
     *
     * Svaku sliku je moguće zumirati i klizanjem pregledati sljedeću, odnosno prethosnu sliku.
     *
     * @param savedInstanceState potrebno prosljediti bundle varijablu "EXTRA_SESSION_ID" koja sadrži poziciju odabrane slike za prikaz
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        viewPager = (ViewPager) findViewById(R.id.view_pager);


        Intent intent = getIntent();
        int position = intent.getIntExtra("EXTRA_SESSION_ID", 1);

        for (int i = 0; i < TabTlocrt.gnd_images.size(); i++) {
            imageBean = new ImageBean();
            imageBean.setImgBitmap(TabTlocrt.gnd_images.get(i));
            image_list.add(imageBean);
        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(image_list, getBaseContext());

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}