package com.example.matth.project1vg;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter {
    private List<Sensor> sensorList;
    private LayoutInflater inflater;

    public ListAdapter(Context context, List<Sensor> sensorList) {
        super(context, android.R.layout.simple_list_item_1,sensorList);
        this.sensorList = sensorList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        TextView tv;
        if (convertView == null){
            tv = (TextView)inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        else{
            tv = (TextView) convertView;
        }
        tv.setText(sensorList.get(position).toString());
        return tv;
    }
}
