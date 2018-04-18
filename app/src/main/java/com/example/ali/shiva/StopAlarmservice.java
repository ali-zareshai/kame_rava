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

public class StopAlarmservice extends Service {
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private static int year,month,day,week,hourse,min,Horse_toloh,Min_toloh;
    private static Calendar cal;
    private static String tolo;
    private static String[]tt;
    static Paryer paryer=new Paryer();
    SharedPreferences SP;
    String[] lang=new String[2];


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        cal = Calendar.getInstance();
        Convert_Date convert_date=new Convert_Date();
        Log.e("AlarmService","START SERVICE");
        /////////////////
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String city=SP.getString("city","31.89:54.36");

        if (city.equals("0")){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String x = preferences.getString("local_x", "");
            String y = preferences.getString("local_y", "");
            if(!x.equalsIgnoreCase(""))
            {
                lang[0]=x;
            }else{
                lang[0]="31.89";
            }
            if(!y.equalsIgnoreCase(""))
            {
                lang[1]=y;
            }else{
                lang[1]="54.36";
            }
        }else{
            lang=city.split(":");
        }


        ///////
        hourse = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        week = cal.get(Calendar.DAY_OF_WEEK);
        convert_date.gregorian_to_jalali(year, month + 1, day, week - 1);


        paryer.SetGeo(Double.parseDouble(lang[1]), Double.parseDouble(lang[0]), convert_date.getMonth(), convert_date.getDay());

        ////////
        tolo = paryer.GetTolue();
        tt = tolo.split(":");
        Horse_toloh = Integer.parseInt(tt[0]);
        Min_toloh = Integer.parseInt(tt[1]);
        ///////////////////////////////////////////////////////////////////


        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), StopAllServiceSer.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Horse_toloh);
        calendar.set(Calendar.MINUTE, Min_toloh);



        ////////////////////////////////////////////////////////
//        calendar.set(Calendar.HOUR_OF_DAY, 14);
//        calendar.set(Calendar.MINUTE, 41);




        Log.e("StopAlarmservice Horse",String.valueOf(Horse_toloh));
        Log.e("StopAlarmservice min",String.valueOf(Min_toloh));


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);




        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
