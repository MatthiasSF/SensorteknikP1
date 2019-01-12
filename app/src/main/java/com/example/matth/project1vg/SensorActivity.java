package com.example.matth.project1vg;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity that displays information about an sensor.
 * @author Matthias Falk
 */
public class SensorActivity extends AppCompatActivity {
    private Sensor sensor;
    private int position;
    private SensorManager mSensorManager;
    private List<Sensor> sensorList;
    private TextView tView;

    /**
     * Basic onCreate method. Initializes the components being used in the Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        position = getIntent().getIntExtra("Sensor", 0);
        tView = findViewById(R.id.textView2);
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sensorList = new ArrayList<>();
        setList();
        setSensor();
    }

    /**
     * Sets the text that is being displayed in the textview
     * @param t - the text
     */
    public void setText(String t){
        tView.setText(t);
    }

    /**
     * sets up the list
     */
    private void setList() {
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
    }

    /**
     * sets the active sensor that we will display information on depending the sensor picked in the StartActivity
     */
    public void setSensor(){
        if (mSensorManager.getDefaultSensor(sensorList.get(position).getType()) != null) {
            sensor = mSensorManager.getDefaultSensor(sensorList.get(position).getType());
            mSensorManager.registerListener(new SensorListener(), sensorList.get(position), SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Listener registered", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * onResume method that registers the Listener
     */
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(new SensorListener(), sensor, SensorManager.SENSOR_DELAY_NORMAL);
        Toast.makeText(this, "Listener registered", Toast.LENGTH_LONG).show();
    }

    /**
     * onPause method that unregisters the listeners
     */
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(new SensorListener());
        Toast.makeText(this, "Listener unregistered", Toast.LENGTH_LONG).show();
    }

    /**
     * onDestroy method that unregisters the listener
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSensorManager = null;
        sensor = null;
        Toast.makeText(this, "Listener unregistered", Toast.LENGTH_LONG).show();
    }

    /**
     * Inner class that uses the SensorEventListener interface.
     * Gets information about the chosen sensor
     */
    private class SensorListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            String events = "";
            int value = 1;
            for (int i = 0; i<event.values.length; i++){
                events += "value " + value++ + ": " + event.values[i] + "\n";
            }
            setText("Sensor: " + event.sensor.getName() +
                    "\nVendor: " + event.sensor.getVendor() +
                    "\nId: " + event.sensor.getId()+
                    "\nPower consumption: " + event.sensor.getPower() +
                    "\nReadings: " + "\n" + events +
                    "\nTimestamp: " + event.timestamp +
                    "\nAccuracy: "  + event.accuracy);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
}
