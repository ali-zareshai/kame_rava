package com.example.ali.shiva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ChangeNotfiService extends Service {
    SharedPreferences SP;
    SharedPreferences.Editor editor;
    int reprat=0;
    public ChangeNotfiService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SP= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!SP.getBoolean("enable_date", false)) {
                    reprat++;
                    ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
                    changeNotifi.setContext(getApplicationContext());
                    changeNotifi.setNotifi();
                    editor = SP.edit();
                    editor.putInt("number_zekre",0);
                    editor.apply();
                    if (reprat==2){
                        timer.cancel();
                        timer.purge();
                        stopSelf();
                    }
                }
            }
        },1000,5000);


        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
}
