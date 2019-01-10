package com.example.matth.project1vg;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    private ListView listView;
    private List<Sensor> sensorList;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
        setList();
    }

    private void setList() {
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        listView.setAdapter(new ListAdapter(this, sensorList));
        listView.setOnItemClickListener(new ClickListener());
    }

    public void initialize(){
        listView = findViewById(R.id.listView);
        sensorList = new ArrayList<>();
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
    }
    public void setActivity(int position){
        Intent intent = new Intent(this, SensorActivity.class);
        intent.putExtra("Sensor", position);
        this.startActivity(intent);
    }
    private class ClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setActivity(position);
        }
    }
}

