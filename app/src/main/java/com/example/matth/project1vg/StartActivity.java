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

/**
 * Starts up the application and displays an ImageView of all available sensors
 * @author Matthias Falk
 */
public class StartActivity extends AppCompatActivity {
    private ListView listView;
    private List<Sensor> sensorList;
    private List<String> sensorNames = new ArrayList<String>();
    private SensorManager mSensorManager;

    /**
     * basic onCreate method that calls initialize() and setList()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
        setList();
    }

    /**
     * Sets up the list that will be displayed
     */
    private void setList() {
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 0; i <sensorList.size(); i++){
            sensorNames.add(i,sensorList.get(i).getName());
        }
        listView.setAdapter(new ListAdapter(this, sensorNames));
        listView.setOnItemClickListener(new ClickListener());
    }

    /**
     * Initializes all of the components that is being used
     */
    public void initialize(){
        listView = findViewById(R.id.listView);
        sensorList = new ArrayList<>();
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
    }

    /**
     * Starts up the SensorActivity and wires the position of the chosen sensor via intent to SensorActivity
     * @param position
     */
    public void setActivity(int position){
        Intent intent = new Intent(this, SensorActivity.class);
        intent.putExtra("Sensor", position);
        this.startActivity(intent);
    }

    /**
     * Inner class that listens to clicks on the ListView
     */
    private class ClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            setActivity(position);
        }
    }
}

