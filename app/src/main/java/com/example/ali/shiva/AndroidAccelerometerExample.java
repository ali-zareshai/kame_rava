package com.example.ali.shiva;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class AndroidAccelerometerExample extends Activity implements SensorEventListener {


    TextView z;
    boolean i;
    int sh=0;
    ProgressBar progressBar;
    SharedPreferences SP;
    Timer t;
    long timeSeconds1;
    long timeSeconds2;
    int level;
    Timer ttimer;
    Boolean b=true;
    FloatingActionButton floatingActionButton;
    String[] lang=new String[2];
    Util util;
    TimerTask timerTask;
    boolean ss2;


    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onDestroy() {
        if (ss2){
            startService(new Intent(AndroidAccelerometerExample.this,StartAlarm2.class));
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Util.active=false;
        super.onStop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ss2=true;
        Util.active=true;
        setContentView(R.layout.activity_android_accelerometer_example);
        long timeMillis2 = System.currentTimeMillis();
        timeSeconds1 = TimeUnit.MILLISECONDS.toSeconds(timeMillis2);
        z=(TextView)findViewById(R.id.textView40);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButtonmuteaccler);
        progressBar=(ProgressBar)findViewById(R.id.progressBar23);
        progressBar.setProgressTintList(ColorStateList.valueOf(Color.BLUE));




        util=new Util(getApplicationContext());
        ////////////////////////////////////////////////////////////////////////
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                long timeMillis = System.currentTimeMillis();
                timeSeconds2 = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
                long remin = timeSeconds2 - timeSeconds1;
                Log.e("SP SHAREED*******", SP.getString("alarm_time", "60"));
                if (remin >= Integer.parseInt(SP.getString("alarm_time", "60"))) {
                    timerTask.cancel();
                    util.finshActivity();
                    finish();
                }


            }
        };
        ttimer=new Timer();
        ttimer.schedule(timerTask,1000,3000);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        util.setMute();
                                                    };
                                                });
        //////////////////////////

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //////////////
        int i = Integer.parseInt(SP.getString("problem_level", "3"));

        switch (i) {
            case 3:
                level = 10;
                break;
            case 4:
                level = 20;
                break;
            case 5:
                level = 30;
                break;
            default:
                level = 10;
        }
        /////////////////////

        sensorManager.registerListener(this, sensorManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        util.startAlarm();

    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            float xVal = event.values[0];
            float yVal = event.values[1];
            int x=(int)xVal;
            int y=(int)yVal;

            if (x>=4 && !i){
                sh++;
                i=true;
            }
            if (x<=-4 && !i){
                sh++;
                i=true;
            }
            if (x<3 && x>-3 && i){
                i=false;
            }
            z.setText(String.valueOf(sh)+"/"+String.valueOf(level));
            if (sh==level){

                if (b){
                    b=false;
                    ss2=false;
                    timerTask.cancel();
                    util.endsus();
                    ttimer.cancel();
                    finish();
                }
            }
            progressBar.setProgress(sh*((int)100/level));
        }
    }
}