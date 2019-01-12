package com.example.matth.project1vg;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter used by the ListView to display all of the sensors
 * @author Matthias Falk
 */
public class ListAdapter extends ArrayAdapter {
    private List<String> sensorList;
    private LayoutInflater inflater;

    /**
     * @param context the active context
     * @param sensorList the list that will be used to pupulate the ListView
     */
    public ListAdapter(Context context, List<String> sensorList) {
        super(context, android.R.layout.simple_list_item_1,sensorList);
        this.sensorList = sensorList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @param position the position of the item
     * @param convertView
     * @param parent
     * @return returns the text associated with the item clicked
     */
    public View getView(int position, View convertView, ViewGroup parent){
        TextView tv;
        if (convertView == null){
            tv = (TextView)inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        else{
            tv = (TextView) convertView;
        }
        tv.setText(sensorList.get(position));
        return tv;
    }
}
