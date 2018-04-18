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

public class NotifationService extends Service {
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    SharedPreferences SP;
    String[]lang=new String[2];
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SP= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.e("NotifationService","NotifationService....");
        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), WaitBorder.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        /////////// FINAL ALARM ////////////////////
        Long interval=getTolo()-(20*60000);
        Log.e("NOtif Final interval",String.valueOf(interval));
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, interval, pendingIntent);



        return START_NOT_STICKY;
    }

    public NotifationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }
    private long getTolo(){

        Paryer paryer = new Paryer();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);
//        SP = PreferenceManager.getDefaultSharedPreferences(context);
        String city = SP.getString("city", "31.89:54.36");
        if (city.equals("0")){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String x = preferences.getString("local_x", "");
            String y = preferences.getString("local_y", "");
            if(!x.equalsIgnoreCase(""))
            {
                lang[0]=x;
            }
            if(!y.equalsIgnoreCase(""))
            {
                lang[1]=y;
            }
        }else{
            lang=city.split(":");
        }
        Convert_Date convert_date = new Convert_Date();
        String today = convert_date.gregorian_to_jalali(year, month + 1, day, week - 1);
        paryer.SetGeo(Double.parseDouble(lang[1]), Double.parseDouble(lang[0]), convert_date.getMonth(), convert_date.getDay());
        String Azan = paryer.GetTolue();
        String[] az = Azan.split(":");
        int Horse_tokoSobe = Integer.parseInt(az[0]);
        int Min_toloSobe = Integer.parseInt(az[1]);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, Horse_tokoSobe);
        calendar2.set(Calendar.MINUTE, Min_toloSobe);

        return calendar2.getTimeInMillis();
    }
}
