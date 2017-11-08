package com.kizo.ground_plan.Tab;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kizo.ground_plan.R;
import com.project.test.database.Entities.Ground_plan;
import com.project.test.database.Entities.House_photos;
import com.project.test.database.Entities.Photos;

import java.util.List;

/**
 * Created by Zoran on 28.10.2017..
 */

public class CustomListView extends ArrayAdapter<String> {

    private  String [] fruitname;
    private Integer [] images;
    private Activity context;
    private List<House_photos> plans;

    public CustomListView(Activity context, String[] fruitname, Integer[] images, List<House_photos> plans) {
        super(context, R.layout.tlocrt_item, fruitname);
//plans.clear();
        this.context = context;
        this.fruitname = fruitname;
        this.images = images;
        this.plans = plans;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View r = convertView;
        ViewHolder viewHolder = null;
        if (r==null){
            LayoutInflater inflater = context.getLayoutInflater();
            r=inflater.inflate(R.layout.tlocrt_item,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag();

        }
        if (position < plans.size())
        viewHolder.ivm.setImageBitmap(plans.get(position).getPhoto().getImageBitmapbyContext(viewHolder.ivm.getContext()));
//viewHolder.tvw1.setText("SLika 1");
        return r;
    }
    class ViewHolder{
        TextView tvw1;
        ImageView ivm;
        ViewHolder(View v){
           ivm = (ImageView) v.findViewById(R.id.imageViewGroundListItem);
            //tvw1 = (TextView) v.findViewById(R.id.imageViewGroundListItemTitle);

        }
    }
}
