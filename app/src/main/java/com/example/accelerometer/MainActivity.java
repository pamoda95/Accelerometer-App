package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    Sensor accelerometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);


        final Button button =findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                onActivityChanged(view);
            }
        });

    }

    private  void onActivityChanged(View view){

        Button button =findViewById(R.id.button);

        if(button.getText().equals("START")){

            button.setText("STOP");
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            sensorManager.registerListener(MainActivity.this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL );

        }else if(button.getText().equals("STOP")){
            button.setText("START");

            TextView textView=findViewById(R.id.textView);
            textView.setText("Disabled");

            sensorManager.unregisterListener(this);

        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
//        Log.d(TAG ,"onSensorChanged: X:" + sensorEvent.values[0]+"  Y:"+ sensorEvent.values[1]+"  Z:"+sensorEvent.values[2]);
        String data =  "X:" + sensorEvent.values[0]+"  Y:"+ sensorEvent.values[1]+"  Z:"+sensorEvent.values[2];
        TextView textView=findViewById(R.id.textView);
        textView.setText(data);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {



    }
}
