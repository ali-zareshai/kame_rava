package com.example.ali.shiva;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class DoaAhdActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Timer timer;
    TimerTask timert;
    int i=0;
    int ss;
    private Handler mHandler;
    RelativeLayout relativ;
    AudioManager audioManager;
    SharedPreferences SP;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa_ahd);
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        StaticWakeLock.lockOn(this);
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        mHandler = new Handler();
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int mental=SP.getInt("vol_ahd",10);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mental, 0);


        ///////////////////////////////////////
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setVolume(1.0f, 1.0f);
            mediaPlayer.setDataSource(this,
                    Uri.parse("android.resource://com.example.ali.shiva/raw/doa"));
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(false);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (Exception e) {
            mediaPlayer.release();
        }
        ////////////////////////////////
        relativ=(RelativeLayout)findViewById(R.id.activity_doa_ahd);
        timert=new TimerTask() {
            @Override
            public void run() {

                ss=mediaPlayer.getCurrentPosition();
                switch (ss/1000){
                    case 139:
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                relativ.setBackgroundResource(R.drawable.ahd2);
                            }
                        });

                        break;
                    case 276:
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                relativ.setBackgroundResource(R.drawable.ahd3);
                            }
                        });
                        break;
                    case 407:
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                relativ.setBackgroundResource(R.drawable.ahd4);
                            }
                        });
                        break;
                    case 510:
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                relativ.setBackgroundResource(R.drawable.ahd5);
                            }
                        });
                        break;
                    case 600:
                        finishact();
                        break;

                }

            }
        };
        timer=new Timer();
        timer.schedule(timert,0,500);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doa_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        finishact();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_exist) {
            finishact();
            return true;
        }
        if (id == R.id.action_pause) {
            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();
            }

            return true;
        }
        if (id == R.id.action_play) {
            if (!mediaPlayer.isPlaying()){
                mediaPlayer.start();
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void finishact() {
        StaticWakeLock.lockOff(getApplicationContext());
        timer.cancel();
        timer.purge();
        mediaPlayer.stop();
        finish();
    }
}
