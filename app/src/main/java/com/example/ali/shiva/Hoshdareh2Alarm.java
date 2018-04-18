package com.example.ali.shiva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;


public class Hoshdareh2Alarm extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e("Hoshdareh2Alarm","Strart");
        SharedPreferences SP= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.e("Hoshdareh2AlarmSSS",SP.getString("alarma","0"));
        int spi =Integer.parseInt(SP.getString("alarma","0"));
        Log.e("Hoshdareh2Alarm",String.valueOf(spi));
        if (spi>=10){
            AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            Intent myIntent = new Intent(getApplicationContext(), Alarm2broad.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.cancel(pendingIntent);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+spi*1000, pendingIntent);
            Log.e("Hoshdareh2AlarmEND",String.valueOf(System.currentTimeMillis()+spi*1000));
            Log.e("Hoshdareh2AlarmEND2",String.valueOf(System.currentTimeMillis()));
        }




        return START_NOT_STICKY;
    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
