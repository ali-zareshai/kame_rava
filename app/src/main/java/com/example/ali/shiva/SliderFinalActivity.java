package com.example.ali.shiva;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class SliderFinalActivity extends AppCompatActivity {
    SeekBar seekBar;
    Emtiaz emtiaz;
    Util util;
    Boolean ye=true;
    Boolean ss2;
    Timer timer;
    SharedPreferences SP;
    SharedPreferences.Editor editor;
    public static DatabaseHandler db;


    @Override
    protected void onDestroy() {
        if (ss2){
            startService(new Intent(SliderFinalActivity.this,AlarmfinalService.class));
            startService(new Intent(SliderFinalActivity.this,CheckFinalService.class));
            util.finshActivityForSlider();
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Util.active2=false;
        super.onStop();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.active2=true;
        setContentView(R.layout.activity_slider_final);
        seekBar=(SeekBar)findViewById(R.id.myseek2);
        db = new DatabaseHandler(this);
        SP= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        try{
            SlideBar.ye=false;
            SlideBar.ss2=false;
            SlideBar.fa.finish();
        }catch (Exception e){
            Log.e("Slidebar","finish activity1");
        }
        try{
            RandomActivit.thisActiv.finish();
        }catch (Exception e){
            Log.e("Slidebar","finish activity2");
        }


        if (db.get_Final() || Util.active){
            ye=false;
            ss2=false;
            finish();
            return;
        }

        editor = SP.edit();
        editor.putBoolean("start_final_alarm",true);
        editor.apply();

        ss2=true;

        emtiaz = new Emtiaz(getApplicationContext());
        emtiaz.cancelNotfi(getApplicationContext());

        util=new Util(getApplicationContext());

        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        util.startAlarmFinal();
        timer=new Timer();
        timer.schedule(t,300000);

        init();


    }
    TimerTask t=new TimerTask() {
        @Override
        public void run() {
            t.cancel();
            timer.cancel();
            util.finshActivityForSlider();
            finish();
        }
    };

    private void init() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (seekBar.getProgress() > 95) {

                } else {
                    seekBar.setProgress(0);


                    seekBar.setThumb(getResources().getDrawable(R.drawable.slidetounlock_thumb));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                if(progress>95){
                    if (ye){
                        ye=false;
                        ss2=false;

                    }
                    t.cancel();
                    timer.cancel();
                    startService(new Intent(SliderFinalActivity.this,AlarmfinalService.class));
                    startService(new Intent(SliderFinalActivity.this,CheckFinalService.class));
                    util.finshActivityForSlider();
                    finish();
                }

            }
        });
    }
}
