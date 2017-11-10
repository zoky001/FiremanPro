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

public class FullScreenActivity extends AppCompatActivity {

    ImageBean imageBean;
    ArrayList<ImageBean> image_list = new ArrayList<>();
    ImageView close;
    ViewPager viewPager;
    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

       //close=(ImageView)findViewById(R.id.closeIB);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        Intent intent = getIntent();
        int position = intent.getIntExtra("id", 1);

        for (int i = 0; i < TabTlocrt.thumbnail.size(); i++) {
            imageBean = new ImageBean();
            imageBean.setImagePath(TabTlocrt.thumbnail.get(i));
            image_list.add(imageBean);


        }

//System.out.println("THUMBNAIL SIZE: "+TabTlocrt.thumbnail.size());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(image_list, getBaseContext());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);


        /*
        close.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View v) {
                onBackPressed();
            }
        });*/
    }



    @Override    public void onBackPressed() {
        super.onBackPressed();
    }
}