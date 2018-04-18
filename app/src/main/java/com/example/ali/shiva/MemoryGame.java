package com.example.ali.shiva;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import android.graphics.Color;

import android.view.View;
import android.widget.ImageView;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class MemoryGame extends Activity implements View.OnClickListener {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;
    int[] array;
    int martabeh=0;
    int btnclick1=0;
    int btnclick2=0;
    int btn1,btn2,lastbtn=0;
    int shomareh=0;
    Timer timer,ttimer;
    private Handler mHandler;
    Util util;
    long timeSeconds2;

    ImageView imageView;
    SharedPreferences SP;
    int i;
    long timeSeconds1;
    String[] lang=new String[2];
    TimerTask timerTask;
    Boolean ss2;


    Timer t;

    @Override
    protected void onDestroy() {
        if (ss2){
            startService(new Intent(MemoryGame.this,StartAlarm2.class));
        }
        super.onDestroy();
    }


    @Override
    protected void onStop() {
        Util.active=false;
        super.onStop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ss2=true;
        Util.active=true;
        setContentView(R.layout.activity_memory_game);
        b1 = (Button) findViewById(R.id.Button100);
        b2 = (Button) findViewById(R.id.Button101);
        b3 = (Button) findViewById(R.id.Button102);
        b4 = (Button) findViewById(R.id.Button103);
        b5 = (Button) findViewById(R.id.Button104);
        b6 = (Button) findViewById(R.id.Button105);
        b7 = (Button) findViewById(R.id.Button106);
        b8 = (Button) findViewById(R.id.Button107);
        b9 = (Button) findViewById(R.id.Button108);
        b10 = (Button) findViewById(R.id.Button109);
        b11 = (Button) findViewById(R.id.Button110);
        b12 = (Button) findViewById(R.id.Button111);

        //////////////
        long timeMillis2 = System.currentTimeMillis();
        timeSeconds1 = TimeUnit.MILLISECONDS.toSeconds(timeMillis2);
        ///////////////////////
        util=new Util(getApplicationContext());

        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
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

        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        ///////////////////////
        array=RandomWithouseRepeat.getarray(12);
        /////////////////////////////
        resetColor();
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

        /////////////////////////////////
        imageView=(ImageView) findViewById(R.id.imagemutememery);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.setMute();
            }
        });
        util.startAlarm();

        mHandler = new Handler();
    }
    private int getColorButton(String button){
        switch (button){
            case "1":
                return getColorFromArray(array[0]);
            case "2":
                return getColorFromArray(array[1]);
            case "3":
                return getColorFromArray(array[2]);
            case "4":
                return getColorFromArray(array[3]);
            case "5":
                return getColorFromArray(array[4]);
            case "6":
                return getColorFromArray(array[5]);
            case "7":
                return getColorFromArray(array[6]);
            case "8":
                return getColorFromArray(array[7]);
            case "9":
                return getColorFromArray(array[8]);
            case "10":
                return getColorFromArray(array[9]);
            case "11":
                return getColorFromArray(array[10]);
            case "12":
                return getColorFromArray(array[11]);


        }
        return 0;
    }

    private int getColorFromArray(int i) {
        switch (i){
            case 1:
            case 2:
                return Color.BLUE;
            case 3:
            case 4:
                return Color.CYAN;
            case 5:
            case 6:
                return Color.GREEN;
            case 7:
            case 8:
                return Color.YELLOW;
            case 9:
            case 10:
                return Color.MAGENTA;
            case 11:
            case 12:
                return Color.RED;


        }
        return Color.BLACK;
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


    }


    @Override
    public void onClick(View v) {
        String click = (String) v.getTag();
        switch (click){
            case"1":
                twiceBtn(1);
                b1.setBackgroundColor(getColorButton("1"));
                break;
            case"2":
                twiceBtn(2);
                b2.setBackgroundColor(getColorButton("2"));
                break;
            case"3":
                twiceBtn(3);
                b3.setBackgroundColor(getColorButton("3"));
                break;
            case"4":
                twiceBtn(4);
                b4.setBackgroundColor(getColorButton("4"));
                break;
            case"5":
                twiceBtn(5);
                b5.setBackgroundColor(getColorButton("5"));
                break;
            case"6":
                twiceBtn(6);
                b6.setBackgroundColor(getColorButton("6"));
                break;
            case"7":
                twiceBtn(7);
                b7.setBackgroundColor(getColorButton("7"));
                break;
            case"8":
                twiceBtn(8);
                b8.setBackgroundColor(getColorButton("8"));
                break;
            case"9":
                twiceBtn(9);
                b9.setBackgroundColor(getColorButton("9"));
                break;
            case"10":
                twiceBtn(10);
                b10.setBackgroundColor(getColorButton("10"));
                break;
            case"11":
                twiceBtn(11);
                b11.setBackgroundColor(getColorButton("11"));
                break;
            case"12":
                twiceBtn(12);
                b12.setBackgroundColor(getColorButton("12"));
                break;

        }


    }
    private void twiceBtn(int btn){


if (lastbtn!=btn){
    martabeh++;
    if (martabeh==2){
        martabeh=0;
        btnclick1=btnclick2=0;
        resetColor();
    }
            lastbtn=btn;
    if (btnclick1==0){
        btnclick1=getColorButton(String.valueOf(btn));
        btn1=btn;
        Log.e("BTN-COLOR 1",String.valueOf(btnclick1));
    }else {
        btnclick2=getColorButton(String.valueOf(btn));
        btn2=btn;
        Log.e("BTN-COLOR 2",String.valueOf(btnclick2));
    }
    okBtn();
}

    }
    private void okBtn(){
        if (btnclick1==btnclick2){
            lockScreen(false);
            shomareh++;
            reScheduleTimer();
            if (shomareh==6){
                ss2=false;
                timerTask.cancel();
                util.endsus();
                ttimer.cancel();
                finish();
            }


        }
    }
    @Override
    public void onBackPressed() {

    }
    private void removeBtn(int i){
        switch (i){
            case 1:
                b1.setVisibility(View.INVISIBLE);
                break;
            case 2:
                b2.setVisibility(View.INVISIBLE);
                break;
            case 3:
                b3.setVisibility(View.INVISIBLE);
                break;
            case 4:
                b4.setVisibility(View.INVISIBLE);
                break;
            case 5:
                b5.setVisibility(View.INVISIBLE);
                break;
            case 6:
                b6.setVisibility(View.INVISIBLE);
                break;
            case 7:
                b7.setVisibility(View.INVISIBLE);
                break;
            case 8:
                b8.setVisibility(View.INVISIBLE);
                break;
            case 9:
                b9.setVisibility(View.INVISIBLE);
                break;
            case 10:
                b10.setVisibility(View.INVISIBLE);
                break;
            case 11:
                b11.setVisibility(View.INVISIBLE);
                break;
            case 12:
                b12.setVisibility(View.INVISIBLE);
                break;

        }

    }

    private void reScheduleTimer() {

        // Cancel previous timer first
        if (timer!=null){
            timer.cancel();
        }

        timer=new Timer();

        timerTask=new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        removeBtn(btn1);
                    }
                });
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        removeBtn(btn2);
                    }
                });
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        lockScreen(true);
                    }
                });


            }
        };
        timer.schedule(timerTask,500);
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


    }

}
