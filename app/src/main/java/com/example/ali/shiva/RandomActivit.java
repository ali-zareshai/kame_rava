package com.example.ali.shiva;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class RandomActivit extends Activity implements View.OnClickListener {
    ImageButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    ImageView imageView;
    int[] array;
    int shemareh;
    TextView textView;
    private Vibrator vibrator;
    int emziaz=0;
    AudioManager audioManager;
    Timer t;
    int ivol=0;
    long timeSeconds1;
    long timeSeconds2;
    int level;
    String path;
    SharedPreferences SP;
    ImageView mutebtn;
    String[] lang=new String[2];
    TimerTask timerTask2;

    @Override
    public void onBackPressed() {

    }

    public static DatabaseHandler db;
    int i;
    private MediaPlayer mediaPlayer;
    boolean level2;
    Timer ttimer;
    private GoogleApiClient client;
    Util util;
    public static Activity thisActiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Util.active=true;
        thisActiv=this;
        setContentView(R.layout.activity_random);
        if (Util.active2){
            finish();
        }


        util=new Util(getApplicationContext());
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Vazir.ttf");
        b1 = (ImageButton) findViewById(R.id.imageButton100);
        b2 = (ImageButton) findViewById(R.id.imageButton101);
        b3 = (ImageButton) findViewById(R.id.imageButton102);
        b4 = (ImageButton) findViewById(R.id.imageButton103);
        b5 = (ImageButton) findViewById(R.id.imageButton104);
        b6 = (ImageButton) findViewById(R.id.imageButton105);
        b7 = (ImageButton) findViewById(R.id.imageButton106);
        b8 = (ImageButton) findViewById(R.id.imageButton107);
        b9 = (ImageButton) findViewById(R.id.imageButton108);
        b10 = (ImageButton) findViewById(R.id.imageButton109);
        b11 = (ImageButton) findViewById(R.id.imageButton110);
        b12 = (ImageButton) findViewById(R.id.imageButton111);
        b13 = (ImageButton) findViewById(R.id.imageButton112);
        b14 = (ImageButton) findViewById(R.id.imageButton113);
        b15 = (ImageButton) findViewById(R.id.imageButton114);
        b16 = (ImageButton) findViewById(R.id.imageButton115);

        mutebtn=(ImageView) findViewById(R.id.floatingActionButtonmuterandom331);
        imageView = (ImageView) findViewById(R.id.imageViewshgel);

        textView=(TextView)findViewById(R.id.textviewemtiaz);
        textView.setTypeface(type);


        /////////////
        db = new DatabaseHandler(this);
        StaticWakeLock.lockOn(this);
        ttimer=new Timer();
        ttimer.schedule(timerTask,1000,3000);
        ////////////////////////////////////////////////////////////////////////
        long timeMillis2 = System.currentTimeMillis();
        timeSeconds1 = TimeUnit.MILLISECONDS.toSeconds(timeMillis2);
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        ////////////// AZ INJA ///////////////
        int martabeh;
        db = new DatabaseHandler(this);
        if (!db.get_bidar()){
            if (SP.getString("type_alarm_random","0").equals("0")){
                martabeh= new Random().nextInt(10);
            }else {
                martabeh = db.get_martabeh();
            }
            String s;
            if (SP.getString("type_ahang", "0").equals("0")) {
                s = "a";
            } else {
                s = "b";
            }

            //////////////////////////
            level2=false;

            //////////////////////////
            Log.e("%%%%%%martabel%%%%%",String.valueOf(martabeh));
            switch (martabeh) {
                case 0:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "0";
                    break;
                case 1:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "1";
                    break;
                case 2:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "2";
                    break;
                case 3:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "3";
                    break;
                case 4:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "4";
                    break;
                case 5:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "5";
                    break;
                case 6:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "6";
                    break;
                case 7:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "7";
                    break;
                case 8:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "8";
                    break;
                case 9:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "9";
                    break;
                default:
                    path = "android.resource://com.example.ali.shiva/raw/" + s + "0";

            }
            int i = Integer.parseInt(SP.getString("problem_level", "3"));

            switch (i) {
                case 3:
                    level = 5;
                    break;
                case 4:
                    level = 10;
                    break;
                case 5:
                    level = 15;
                    break;
                default:
                    level = 10;
            }
            textView.setText("مجموع امتیازات:"+String.valueOf(emziaz)+"/"+String.valueOf(level));
        }else {
            switch (SP.getString("type_talevat","0")){
                case "0":
                    path = "android.resource://com.example.ali.shiva/raw/salavaat1";
                    break;
                case "1":
                    path = "android.resource://com.example.ali.shiva/raw/salavaat2";
                    break;
                case "2":
                    path = "android.resource://com.example.ali.shiva/raw/salavaat3";
                    break;
                case "3":
                    path = "android.resource://com.example.ali.shiva/raw/tavashih";
                    break;
                default:
                    path = "android.resource://com.example.ali.shiva/raw/tavashih";

            }
            level=Integer.parseInt(SP.getString("type_level_two","10"));
            level2=true;
            textView.setText("مجموع امتیازات:"+String.valueOf(emziaz)+"/"+String.valueOf(level));
        }
        Log.e("RANDOM","77");

        startAlarm();
        ////////////////////////////////////////////////////////////
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
        /////////////
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        ////////////////
        shemareh=0;
        refresh();

        imageView.setImageResource(fruits(shemareh));


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        mutebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerTask2.cancel();
                t.cancel();
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            };
        });
        t=new Timer();
        if (!(SP.getBoolean("volume",false))){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        }
        timerTask2=new TimerTask() {
            @Override
            public void run() {
                if (SP.getBoolean("volume", true)) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, ivol, 0);
                    ivol++;
                    Log.e("Audio.STREAM_MUSIC", String.valueOf(ivol));
                }
            }
        };
        t.schedule(timerTask2,0,3000);
    }

    private void startAlarm() {
        mediaPlayer = new MediaPlayer();
        if (SP.getBoolean("vibreh",true)) {
            vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            long[] pattern = { 1000, 200, 200, 200 };
            vibrator.vibrate(pattern, 0);
        }
        try {

            mediaPlayer.setDataSource(this,
                    Uri.parse(path));
            mediaPlayer.setLooping(true);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (Exception e) {
            mediaPlayer.release();
        }
    }
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            long timeMillis = System.currentTimeMillis();
            timeSeconds2 = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
            long remin = timeSeconds2 - timeSeconds1;
            Log.e("RUN timertask", "in Random");

                if (remin >= Integer.parseInt(SP.getString("alarm_time", "60")) && !(level2)) {
                    finshActivity();
                    timerTask.cancel();
                    timerTask2.cancel();
                    t.cancel();
                }




        }
    };
    void finshActivity() {
        StaticWakeLock.lockOff(getApplicationContext());
        Intent intent = new Intent(RandomActivit.this, StartAlarm2.class);
        startService(intent);
        ttimer.cancel();
        Log.e("alarmactivity", "start service startalarm2");
        if (vibrator != null)
            vibrator.cancel();
        try {

            mediaPlayer.stop();
        } catch (IllegalStateException ise) {

        }
        try {
            mediaPlayer.release();
        } catch (Exception e) {

        }
        timerTask.cancel();
        timerTask2.cancel();
        t.cancel();
        finish();
    }


    private void refresh() {
        array = RandomWithouseRepeat.getarray(16);

        b1.setImageResource(fruits(array[0]));
        b2.setImageResource(fruits(array[1]));
        b3.setImageResource(fruits(array[2]));
        b4.setImageResource(fruits(array[3]));
        b5.setImageResource(fruits(array[4]));
        b6.setImageResource(fruits(array[5]));
        b7.setImageResource(fruits(array[6]));
        b8.setImageResource(fruits(array[7]));
        b9.setImageResource(fruits(array[8]));
        b10.setImageResource(fruits(array[9]));
        b11.setImageResource(fruits(array[10]));
        b12.setImageResource(fruits(array[11]));
        b13.setImageResource(fruits(array[12]));
        b14.setImageResource(fruits(array[13]));
        b15.setImageResource(fruits(array[14]));
        b16.setImageResource(fruits(array[15]));
    }

    private int fruits(int i) {
        return 0;
    }


    @Override
    public void onClick(View v) {
        String button = (String) v.getTag();
        switch (button) {
            case "1":
                click(array[0]);
                break;
            case "2":
                click(array[1]);
                break;
            case "3":
                click(array[2]);
                break;
            case "4":
                click(array[3]);
                break;
            case "5":
                click(array[4]);
                break;
            case "6":
                click(array[5]);
                break;
            case "7":
                click(array[6]);
                break;
            case "8":
                click(array[7]);
                break;
            case "9":
                click(array[8]);
                break;
            case "10":
                click(array[9]);
                break;
            case "11":
                click(array[10]);
                break;
            case "12":
                click(array[11]);
                break;
            case "13":
                click(array[12]);
                break;
            case "14":
                click(array[13]);
                break;
            case "15":
                click(array[14]);
                break;
            case "16":
                click(array[15]);
                break;
        }


    }

    private void click(int i) {

        if (fruits(shemareh)==fruits(i)){
            shemareh++;
            emziaz++;
            refresh();
            if (shemareh==17){
                shemareh=0;
            }
            imageView.setImageResource(fruits(shemareh));
        }else {
            vibrator.vibrate(400);
        }
        textView.setText("مجموع امتیازات:"+String.valueOf(emziaz)+"/"+String.valueOf(level));
        if (emziaz==level){
            StaticWakeLock.lockOff(getApplicationContext());
            ttimer.cancel();
            Log.e("RANDOM","LINE 463");


            db.update_Bidar("T");

            Emtiaz emtiaz=new Emtiaz(this);
            emtiaz.saveEmtiaz(1);
            if (vibrator != null)
                vibrator.cancel();
            try {

                mediaPlayer.stop();
            } catch (IllegalStateException ise) {

            }
            try {
                mediaPlayer.release();
            } catch (Exception e) {

            }

            finish();
        }


    }
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("RandomActivit Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        Util.active=false;
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

}