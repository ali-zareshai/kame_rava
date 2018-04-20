package com.example.ali.shiva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CheckFinalService extends Service {
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    public CheckFinalService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.e("alarmfinalservice","srart alarmfinal service");
        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), CheckFinalReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+100000, pendingIntent);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
