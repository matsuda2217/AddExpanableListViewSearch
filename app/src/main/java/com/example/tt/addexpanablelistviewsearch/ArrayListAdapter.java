package com.example.tt.addexpanablelistviewsearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by TT
 */
public class ArrayListAdapter extends BaseExpandableListAdapter {

   private Context context;
    private ArrayList<Continent> continentList ;
    private ArrayList<Continent> originalList;

    public ArrayListAdapter(Context context, ArrayList<Continent> continentList) {
        this.context = context;

        this.continentList = new ArrayList<Continent>();
        this.continentList.addAll(continentList);
        this.originalList = new ArrayList<Continent>();
        this.originalList.addAll(continentList);
    }
    @Override
    public int getGroupCount() {
        return continentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

      int count =  continentList.get(groupPosition).getContryList().size();
        return count;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return continentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Contry> contries = continentList.get(groupPosition).getContryList();

        return contries.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Continent continent = (Continent) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.group_row, null);

        }
        TextView head = (TextView) convertView.findViewById(R.id.heading);
        head.setText(continent.getName().trim());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Contry contry = (Contry) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_row, null);

        }
        TextView code = (TextView) convertView.findViewById(R.id.code);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView population = (TextView) convertView.findViewById(R.id.population);

        code.setText(contry.getCode().trim());
        name.setText(contry.getName().trim());
        population.setText(NumberFormat.getNumberInstance(Locale.US).format(contry.getPopulation()));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void fillterData(String query) {
        query = query.toLowerCase();
        Log.v("Tag", String.valueOf(continentList.size()));
        continentList.clear();
        if (query.isEmpty()) {
            continentList.addAll(originalList);
        } else {
            for (Continent continent : originalList) {
                ArrayList<Contry> contries = continent.getContryList();
                ArrayList<Contry> newList = new ArrayList<Contry>();
                for (Contry contry : contries) {
                    if (contry.getCode().toLowerCase().contains(query)|| contry.getName().toLowerCase().contains(query)) {
                        newList.add(contry);

                    }
                }
                if (newList.size() > 0) {
                    Continent nContinent = new Continent(continent.getName(), newList);
                    continentList.add(continent);
                }
            }
        }
        Log.d("Tag", String.valueOf(continentList.size()));
        notifyDataSetChanged();
    }
}
