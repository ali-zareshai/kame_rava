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

public class EmtiazService extends Service {
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("EmtiazService","EmtiazService aval");
        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), EmtiazBorder.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+120000, pendingIntent);


        return START_NOT_STICKY;
    }

    public EmtiazService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
