package com.example.ali.shiva;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CaptchaActivity extends Activity {
    ImageView capchal;
    long timeSeconds1;
    long timeSeconds2;
    Timer timer;
    EditText editText;
    TextCaptcha textCaptcha;
    MathCaptcha mathCaptcha;
    ProgressBar progressBar;
    Button bt,imagebt;
    SharedPreferences SP;
    String m;
    Timer t;
    int i;
    int level;
    FloatingActionButton floatingActionButton;
    Util util;
    TimerTask timerTask2,timerTask;

    String[] lang=new String[2];

    private GoogleApiClient client;
    Boolean ss2;


    @Override
    protected void onDestroy() {
        if (ss2){
            startService(new Intent(CaptchaActivity.this,StartAlarm2.class));
        }
        super.onDestroy();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ss2=true;
        Util.active=true;
        setContentView(R.layout.activity_captcha);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButtonmutecaptal);
        long timeMillis2 = System.currentTimeMillis();
        timeSeconds1 = TimeUnit.MILLISECONDS.toSeconds(timeMillis2);

        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        util=new Util(getApplicationContext());
        ////////////////////////////////////////////////////////
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                util.setMute();
            }
        });
        ////////////////////////////////////////////////////////
        timerTask=new TimerTask() {
            @Override
            public void run() {
                long timeMillis = System.currentTimeMillis();
                timeSeconds2 = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
                long remin = timeSeconds2 - timeSeconds1;
                Log.e("SP SHAREED*******", SP.getString("alarm_time", "60"));
                if (remin >= Integer.parseInt(SP.getString("alarm_time", "60"))) {
                    timer.cancel();
                    timerTask.cancel();
                    timerTask2.cancel();
                    util.finshActivity();
                    finish();
                }
            }
        };
        /////////////////////////////////
      util.startAlarm();
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        int i = Integer.parseInt(SP.getString("problem_level", "3"));

        switch (i) {
            case 3:
                level = 3;
                break;
            case 4:
                level = 5;
                break;
            case 5:
                level = 7;
                break;
            default:
                level = 4;
        }
        editText = (EditText) findViewById(R.id.editTextcap);
        bt = (Button) findViewById(R.id.button_verify);
        imagebt = (Button) findViewById(R.id.imageButtonrefresh);
        imagebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        m = SP.getString("type_problem", "0");
        capchal = (ImageView) findViewById(R.id.imageView);
        refresh();

        try {
            startporgresss();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void refresh() {
        if (m.equals("1")) {
            editText.setHint("لطفا متن را وارد کنید...");
            textCaptcha = new TextCaptcha(600, 150, level, TextCaptcha.TextOptions.NUMBERS_ONLY);
            capchal.setImageBitmap(textCaptcha.getImage());
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!textCaptcha.checkAnswer(editText.getText().toString().trim())) {
                        editText.setError("متن مطابقت ندارد!!!");
                    } else {
                        ss2=false;
                        util.endsus();
                        timerTask.cancel();
                        timerTask2.cancel();
                        finish();
                    }
                }
            });
        } else if (m.equals("2")) {
            editText.setHint("لطفا جواب را وارد کنید...");
            if (level == 3) {
                mathCaptcha = new MathCaptcha(600, 150, MathCaptcha.MathOptions.PLUS_MINUS);
            } else {
                mathCaptcha = new MathCaptcha(600, 150, MathCaptcha.MathOptions.PLUS_MINUS_MULTIPLY);
            }

            capchal.setImageBitmap(mathCaptcha.getImage());
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!mathCaptcha.checkAnswer(editText.getText().toString().trim())) {
                        editText.setError("جواب صحیح نیست!!!");
//            numberOfCaptchaFalse++;
                    } else {
                        ss2=false;
                        util.endsus();
                        timerTask.cancel();
                        timerTask2.cancel();
                        finish();
                    }
                }
            });
        }
    }


    private void startporgresss() throws InterruptedException {
        final int a = Integer.parseInt(SP.getString("alarm_time", "60"));
        Log.e("AAAA**********", String.valueOf(a));
        int interval;
        switch (a) {
            case 30:
                interval = 1000;
                break;
            case 45:
                interval = 870;
                break;
            case 60:
                interval = 570;
                break;
            case 75:
                interval = 715;
                break;
            case 90:
                interval = 780;
                break;
            default:
                interval = 600;

        }
        timer = new Timer();
        timerTask2=new TimerTask() {
            int y;

            @Override
            public void run() {
                y = 100 / a + y;
                Log.e("ffffffff", String.valueOf(y));
                progressBar.setProgress(y);
            }
        };

        timer.schedule(timerTask2, 0, interval);
    }
    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        StaticWakeLock.lockOff(this);
    }






    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Captcha Page") // TODO: Define a title for the content shown.
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
