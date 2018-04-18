package com.example.ali.shiva;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends Activity {
    private static int SPLASH_TIME_OUT = 1000;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();



        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                if (preferences.getString("avalin_run","").equals("")){
                    editor.putString("avalin_run","no");
                    editor.apply();
                    i = new Intent(SplashActivity.this, StepActivity.class);
                }else {
                    i = new Intent(SplashActivity.this, Main2Activity.class);
                }
//                Log.e("Splash",preferences.getString("avalin_run",""));

                startActivity(i);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);



    }


}
