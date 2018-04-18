package com.example.ali.shiva;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class RepeatGame extends Activity implements View.OnClickListener {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    int[]array;
    int click=1;
    private Handler mHandler;

    int martabeh=0;

    Timer timer,ttimer;
    Util util;
    long timeSeconds2;

    ImageView imageView;
    SharedPreferences SP;
    int i;
    long timeSeconds1;
    String[] lang=new String[2];
    TimerTask timerTask2;
    boolean ss2;

    @Override
    protected void onDestroy() {
        if (ss2){
            startService(new Intent(RepeatGame.this,StartAlarm2.class));
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
        Util.active=true;
        ss2=true;
        setContentView(R.layout.activity_repeat_game);
        b1 = (Button) findViewById(R.id.Buttonrepeat100);
        b2 = (Button) findViewById(R.id.Buttonrepeat101);
        b3 = (Button) findViewById(R.id.Buttonrepeat102);
        b4 = (Button) findViewById(R.id.Buttonrepeat103);
        b5 = (Button) findViewById(R.id.Buttonrepeat104);
        b6 = (Button) findViewById(R.id.Buttonrepeat105);
        b7 = (Button) findViewById(R.id.Buttonrepeat106);
        b8 = (Button) findViewById(R.id.Buttonrepeat107);
        b9 = (Button) findViewById(R.id.Buttonrepeat108);
        b10 = (Button) findViewById(R.id.Buttonrepeat109);
        b11 = (Button) findViewById(R.id.Buttonrepeat110);
        b12 = (Button) findViewById(R.id.Buttonrepeat111);
        b13 = (Button) findViewById(R.id.Buttonrepeat112);
        b14 = (Button) findViewById(R.id.Buttonrepeat113);
        b15 = (Button) findViewById(R.id.Buttonrepeat114);
        b16 = (Button) findViewById(R.id.Buttonrepeat115);
        //////////////
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        ///////////////////////
        //////////////
        long timeMillis2 = System.currentTimeMillis();
        timeSeconds1 = TimeUnit.MILLISECONDS.toSeconds(timeMillis2);
        ///////////////////////
        util=new Util(getApplicationContext());

        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        timerTask2 = new TimerTask() {
            @Override
            public void run() {
                long timeMillis = System.currentTimeMillis();
                timeSeconds2 = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
                long remin = timeSeconds2 - timeSeconds1;
                Log.e("SP SHAREED*******", SP.getString("alarm_time", "60"));
                if (remin >= Integer.parseInt(SP.getString("alarm_time", "60"))) {
                    timerTask2.cancel();
                    util.finshActivity();
                    finish();
                }
            }
        };
        ttimer=new Timer();
        ttimer.schedule(timerTask2,1000,3000);
        ////////////////////////////
        array=RandomWithouseRepeat.getarray(16);
        ////////////////////////////////
        //////////////////////
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b14.setOnClickListener(this);
        b15.setOnClickListener(this);
        b16.setOnClickListener(this);
        /////////////////////////////////
        b1.setTag(array[0]);
        b2.setTag(array[1]);
        b3.setTag(array[2]);
        b4.setTag(array[3]);
        b5.setTag(array[4]);
        b6.setTag(array[5]);
        b7.setTag(array[6]);
        b8.setTag(array[7]);
        b9.setTag(array[8]);
        b10.setTag(array[9]);
        b11.setTag(array[10]);
        b12.setTag(array[11]);
        b13.setTag(array[12]);
        b14.setTag(array[13]);
        b15.setTag(array[14]);
        b16.setTag(array[15]);
        ////////////////////////
        timer=new Timer();
        mHandler = new Handler();
        ////////////////////////////////
        resetColor();
        startGame();
        imageView=(ImageView) findViewById(R.id.imagemuterepeat);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.setMute();
            }
        });
        util.startAlarm();

    }
    TimerTask timerTask=new TimerTask() {
        int tick=0;
        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    resetColor();
                }
            });
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    runBtn(tick).setBackgroundColor(Color.RED);
                }
            });
            tick++;
            if (tick==5){
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        lockScreen(true);
                    }
                });
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        resetColor();
                    }
                });
                timer.cancel();
            }

        }
    };
    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        int click2 = (int)v.getTag();
        Log.e("Click2",String.valueOf(click2));
        Log.e("Click",String.valueOf(click));
        if (click2==click){
            Toast.makeText(this, "خب! بعدی", Toast.LENGTH_SHORT).show();
            click++;
        }else {
            Toast.makeText(this, "اشتباهه!!!", Toast.LENGTH_SHORT).show();
        }
        if (click==5){
            ss2=false;
            util.endsus();
            timerTask2.cancel();
            ttimer.cancel();
            finish();
        }

    }
    private void resetColor(){
        b1.setBackgroundColor(Color.WHITE);
        b2.setBackgroundColor(Color.WHITE);
        b3.setBackgroundColor(Color.WHITE);
        b4.setBackgroundColor(Color.WHITE);
        b5.setBackgroundColor(Color.WHITE);
        b6.setBackgroundColor(Color.WHITE);
        b7.setBackgroundColor(Color.WHITE);
        b8.setBackgroundColor(Color.WHITE);
        b9.setBackgroundColor(Color.WHITE);
        b10.setBackgroundColor(Color.WHITE);
        b11.setBackgroundColor(Color.WHITE);
        b12.setBackgroundColor(Color.WHITE);
        b13.setBackgroundColor(Color.WHITE);
        b14.setBackgroundColor(Color.WHITE);
        b15.setBackgroundColor(Color.WHITE);
        b16.setBackgroundColor(Color.WHITE);
    }
    private void startGame(){
        lockScreen(false);
        timer.schedule(timerTask,4000,1500);
    }
    private Button runBtn(int i){
        if (b1.getTag().toString().equals(String.valueOf(i))){
            return b1;
        }else if (b2.getTag().toString().equals(String.valueOf(i))){
            return b2;
        }else if (b3.getTag().toString().equals(String.valueOf(i))){
            return b3;
        }else if (b4.getTag().toString().equals(String.valueOf(i))){
            return b4;
        }else if (b5.getTag().toString().equals(String.valueOf(i))){
            return b5;
        }else if (b6.getTag().toString().equals(String.valueOf(i))){
            return b6;
        }else if (b7.getTag().toString().equals(String.valueOf(i))){
            return b7;
        }else if (b8.getTag().toString().equals(String.valueOf(i))){
            return b8;
        }else if (b9.getTag().toString().equals(String.valueOf(i))){
            return b9;
        }else if (b10.getTag().toString().equals(String.valueOf(i))){
            return b10;
        }else if (b11.getTag().toString().equals(String.valueOf(i))){
            return b11;
        }else if (b12.getTag().toString().equals(String.valueOf(i))){
            return b12;
        }else if (b13.getTag().toString().equals(String.valueOf(i))){
            return b13;
        }else if (b14.getTag().toString().equals(String.valueOf(i))){
            return b14;
        }else if (b15.getTag().toString().equals(String.valueOf(i))){
            return b15;
        }else if (b16.getTag().toString().equals(String.valueOf(i))){
            return b16;
        }
        return b1;
    }
    private void lockScreen(Boolean b){
        b1.setClickable(b);
        b2.setClickable(b);
        b3.setClickable(b);
        b4.setClickable(b);
        b5.setClickable(b);
        b6.setClickable(b);
        b7.setClickable(b);
        b8.setClickable(b);
        b9.setClickable(b);
        b10.setClickable(b);
        b11.setClickable(b);
        b12.setClickable(b);
        b13.setClickable(b);
        b14.setClickable(b);
        b15.setClickable(b);
        b16.setClickable(b);
        //////////////////////
        b1.setEnabled(b);
        b2.setEnabled(b);
        b3.setEnabled(b);
        b4.setEnabled(b);
        b5.setEnabled(b);
        b6.setEnabled(b);
        b7.setEnabled(b);
        b8.setEnabled(b);
        b9.setEnabled(b);
        b10.setEnabled(b);
        b11.setEnabled(b);
        b12.setEnabled(b);
        b13.setEnabled(b);
        b14.setEnabled(b);
        b15.setEnabled(b);
        b16.setEnabled(b);

    }
}
