package com.example.ali.shiva;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class OrderGame extends Activity implements View.OnClickListener {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    int[] array;
    int twice=0;
    String lastBtn;

    int martabeh=0;
    boolean ss2;

    Timer timer,ttimer;
    Util util;
    long timeSeconds2;

    ImageView imageView;
    SharedPreferences SP;
    int i;
    long timeSeconds1;
    String[] lang=new String[2];
    TimerTask timerTask;


    Timer t;

    @Override
    protected void onStop() {
        Util.active=false;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (ss2){
            startService(new Intent(OrderGame.this,StartAlarm2.class));
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ss2=true;
        Util.active=true;
        setContentView(R.layout.activity_order_game);
        b1 = (Button) findViewById(R.id.Buttonorder1);
        b2 = (Button) findViewById(R.id.Buttonorder2);
        b3 = (Button) findViewById(R.id.Buttonorder3);
        b4 = (Button) findViewById(R.id.Buttonorder4);
        b5 = (Button) findViewById(R.id.Buttonorder5);
        b6 = (Button) findViewById(R.id.Buttonorder6);
        b7 = (Button) findViewById(R.id.Buttonorder7);
        b8 = (Button) findViewById(R.id.Buttonorder8);
        b9 = (Button) findViewById(R.id.Buttonorder9);
        /////////////////////////////////////
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        //////////////////////////////////////////////
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
                if (remin >= Integer.parseInt(SP.getString("alarm_time", "60"))) {
                    ttimer.cancel();
                    timerTask.cancel();
                    util.finshActivity();
                    finish();
                }
            }
        };
        ttimer=new Timer();
        ttimer.schedule(timerTask,1000,3000);
        ///////////////
        array=RandomWithouseRepeat.getarray(9);
        /////////////////////////////////////////////////
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        //////////////////////////////////
        b1.setText(String.valueOf(array[0]));
        b2.setText(String.valueOf(array[1]));
        b3.setText(String.valueOf(array[2]));
        b4.setText(String.valueOf(array[3]));
        b5.setText(String.valueOf(array[4]));
        b6.setText(String.valueOf(array[5]));
        b7.setText(String.valueOf(array[6]));
        b8.setText(String.valueOf(array[7]));
        b9.setText(String.valueOf(array[8]));
        ////////////////
        setCheckColor();
        /////
        imageView=(ImageView) findViewById(R.id.imagemuteorder);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.setMute();
            }
        });
        util.startAlarm();
    }

    @Override
    public void onClick(View v) {
        String click = (String) v.getTag();
        setColor(click);
        setMove(click);
        if (setCheckColor()){
            ss2=false;
            timerTask.cancel();
            util.endsus();
            ttimer.cancel();
            finish();
        }

    }
    @Override
    public void onBackPressed() {

    }

    private Boolean setCheckColor() {
        int i=0;
        if (b1.getText().toString().equals("1")){
            b1.setBackgroundColor(Color.GREEN);
            i++;
        }
        if (b2.getText().toString().equals("2")){
            b2.setBackgroundColor(Color.GREEN);
            i++;
        }
        if (b3.getText().toString().equals("3")){
            b3.setBackgroundColor(Color.GREEN);
            i++;
        }
        if (b4.getText().toString().equals("4")){
            b4.setBackgroundColor(Color.GREEN);
            i++;
        }
        if (b5.getText().toString().equals("5")){
            b5.setBackgroundColor(Color.GREEN);
            i++;
        }
        if (b6.getText().toString().equals("6")){
            b6.setBackgroundColor(Color.GREEN);
            i++;
        }
        if (b7.getText().toString().equals("7")){
            b7.setBackgroundColor(Color.GREEN);
            i++;
        }
        if (b8.getText().toString().equals("8")){
            b8.setBackgroundColor(Color.GREEN);
            i++;
        }
        if (b9.getText().toString().equals("9")){
            b9.setBackgroundColor(Color.GREEN);
            i++;
        }
        return i == 9;

    }

    private void setMove(String move){
        switch (lastBtn){
            case "1":
                switch (move){
                    case "2":
                        swap(1,2);
                        break;
                    case "4":
                        swap(1,4);
                        break;
                }
                break;
            case "2":
                switch (move){
                    case "1":
                        swap(1,2);
                        break;
                    case "3":
                        swap(2,3);
                        break;
                    case "5":
                        swap(2,5);
                        break;
                }
                break;
            case "3":
                switch (move){
                    case "2":
                        swap(2,3);
                        break;
                    case "6":
                        swap(3,6);
                        break;
                }
                break;
            case "4":
                switch (move){
                    case "1":
                        swap(1,4);
                        break;
                    case "5":
                        swap(4,5);
                        break;
                    case "7":
                        swap(4,7);
                        break;
                }
                break;
            case "5":
                switch (move){
                    case "2":
                        swap(5,2);
                        break;
                    case "4":
                        swap(5,4);
                        break;
                    case "6":
                        swap(5,6);
                        break;
                    case "8":
                        swap(5,8);
                        break;
                }
                break;
            case "6":
                switch (move){
                    case "3":
                        swap(6,3);
                        break;
                    case "5":
                        swap(6,5);
                        break;
                    case "9":
                        swap(6,9);
                        break;
                }
                break;
            case "7":
                switch (move){
                    case "4":
                        swap(7,4);
                        break;
                    case "8":
                        swap(7,8);
                        break;
                }
                break;
            case "8":
                switch (move){
                    case "5":
                        swap(8,5);
                        break;
                    case "7":
                        swap(8,7);
                        break;
                    case "9":
                        swap(8,9);
                        break;
                }
                break;
            case "9":
                switch (move){
                    case "6":
                        swap(9,6);
                        break;
                    case "8":
                        swap(9,8);
                        break;
                }
                break;
        }
    }

    private void swap(int i, int i1) {
        String l1,l2,temp;
        l1=l2=temp=null;
        switch (i){
            case 1:
                l1=b1.getText().toString();
                break;
            case 2:
                l1=b2.getText().toString();
                break;
            case 3:
                l1=b3.getText().toString();
                break;
            case 4:
                l1=b4.getText().toString();
                break;
            case 5:
                l1=b5.getText().toString();
                break;
            case 6:
                l1=b6.getText().toString();
                break;
            case 7:
                l1=b7.getText().toString();
                break;
            case 8:
                l1=b8.getText().toString();
                break;
            case 9:
                l1=b9.getText().toString();
                break;
        }
        switch (i1){
            case 1:
                l2=b1.getText().toString();
                break;
            case 2:
                l2=b2.getText().toString();
                break;
            case 3:
                l2=b3.getText().toString();
                break;
            case 4:
                l2=b4.getText().toString();
                break;
            case 5:
                l2=b5.getText().toString();
                break;
            case 6:
                l2=b6.getText().toString();
                break;
            case 7:
                l2=b7.getText().toString();
                break;
            case 8:
                l2=b8.getText().toString();
                break;
            case 9:
                l2=b9.getText().toString();
                break;
        }
        temp=l1;
        l1=l2;
        l2=temp;
        switch (i){
            case 1:
                b1.setText(l1);
                break;
            case 2:
                b2.setText(l1);
                break;
            case 3:
                b3.setText(l1);
                break;
            case 4:
                b4.setText(l1);
                break;
            case 5:
                b5.setText(l1);
                break;
            case 6:
                b6.setText(l1);
                break;
            case 7:
                b7.setText(l1);
                break;
            case 8:
                b8.setText(l1);
                break;
            case 9:
                b9.setText(l1);
                break;
        }
        switch (i1){
            case 1:
                b1.setText(l2);
                break;
            case 2:
                b2.setText(l2);
                break;
            case 3:
                b3.setText(l2);
                break;
            case 4:
                b4.setText(l2);
                break;
            case 5:
                b5.setText(l2);
                break;
            case 6:
                b6.setText(l2);
                break;
            case 7:
                b7.setText(l2);
                break;
            case 8:
                b8.setText(l2);
                break;
            case 9:
                b9.setText(l2);
                break;
        }

    }

    private void setColor(String i){
        twice++;
        switch (i){
            case "1":
                b1.setBackgroundColor(Color.GRAY);
                break;
            case "2":
                b2.setBackgroundColor(Color.GRAY);
                break;
            case "3":
                b3.setBackgroundColor(Color.GRAY);
                break;
            case "4":
                b4.setBackgroundColor(Color.GRAY);
                break;
            case "5":
                b5.setBackgroundColor(Color.GRAY);
                break;
            case "6":
                b6.setBackgroundColor(Color.GRAY);
                break;
            case "7":
                b7.setBackgroundColor(Color.GRAY);
                break;
            case "8":
                b8.setBackgroundColor(Color.GRAY);
                break;
            case "9":
                b9.setBackgroundColor(Color.GRAY);
                break;
        }
        if (twice==2){
            resetColor();
            twice=0;
        }else {
            lastBtn=i;
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
    }
}
