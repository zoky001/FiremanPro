package com.project.air.firemanpro.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ivica on 15.11.2017..
 */


public class CustomAutocompleteAdapter extends ArrayAdapter<String> {
    private final Context mContext;
    private final List<String> mDepartments;
    private final List<String> mDepartments_All;
    private final List<String> mDepartments_Suggestion;
    private final int mLayoutResourceId;

    public CustomAutocompleteAdapter(Context context, int resource, List<String> departments) {
        super(context, resource, departments);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mDepartments = new ArrayList<>(departments);
        this.mDepartments_All = new ArrayList<>(departments);
        this.mDepartments_Suggestion = new ArrayList<>();
    }





    public int getCount() {
        return mDepartments.size();
    }

    public String getItem(int position) {
        return mDepartments.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            String department = getItem(position);
            TextView name = (TextView) convertView.findViewById(android.R.id.text1);
            name.setText(department);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }


    //Normalizing string to get string without diactritic characters
    public static String formatString(String s) {
        char[] charArray = s.toCharArray();
        StringBuilder normalizedString = new StringBuilder();
        for (char character:charArray
             ) {
            if (character == 'đ' || character == 'Đ'){
                normalizedString.append('d');
                continue;
            }
            normalizedString.append(Normalizer.normalize(String.valueOf(character), Normalizer.Form.NFD));
        }

        return normalizedString.toString().replaceAll("[^\\p{ASCII}]", "");
    }


        @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                return ((String) resultValue);
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    mDepartments_Suggestion.clear();

                    for (String department : mDepartments_All) {
                        String department1 = department.replaceAll(",","");
                        String constraintToString = constraint.toString();
                        List<String> splitedConstraints = Arrays.asList(constraintToString.split(" "));
                        String formatedDepartmet = formatString(department);
                        String formatedDepartment1 = formatString(department1);
                        String formatedConstraint = formatString(constraint.toString());

                        if (department.toLowerCase().contains(constraint.toString().toLowerCase()) || department1.toLowerCase().contains(constraint.toString().toLowerCase())||
                        formatedDepartmet.toLowerCase().contains(formatedConstraint.toLowerCase()) || formatedDepartment1.toLowerCase().contains(formatedConstraint.toLowerCase())) {
                            mDepartments_Suggestion.add(department);
                        }
                        boolean isItContained = true;
                        for (String splitedConstraint: splitedConstraints
                             ) {
                            String formatedSplitedConstraint = formatString(splitedConstraint);
                            if (!(department1.toLowerCase().contains(splitedConstraint.toLowerCase())||formatedDepartment1.toLowerCase().contains(formatedSplitedConstraint.toLowerCase()))){
                                isItContained = false;
                            }
                        }
                        if (isItContained){
                            if (!mDepartments_Suggestion.contains(department)){
                                mDepartments_Suggestion.add(department);
                            }
                        }


                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = mDepartments_Suggestion;
                    filterResults.count = mDepartments_Suggestion.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDepartments.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof String) {
                            mDepartments.add((String) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    mDepartments.addAll(mDepartments_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}