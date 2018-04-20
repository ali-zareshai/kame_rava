package com.example.ali.shiva;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Switch;

import java.util.Calendar;

public class AlarmService extends Service {
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private static int hourse,min,year,month,day,week,Horse_azanSobe,Min_azaneSobe;
    private static Calendar cal,cal2;
    private static String Azan;
    private static String[] az;
    SharedPreferences SP;
    String[] lang=new String[2];

///////////////////
    static Paryer paryer=new Paryer();
    Convert_Date convert_date=new Convert_Date();



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        cal = Calendar.getInstance();
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        /////////////////
        cal2 = Calendar.getInstance();

        cal2.add(Calendar.DAY_OF_YEAR, 1); //Adds a day

        int day_year=cal2.get(Calendar.DAY_OF_YEAR);

//        int DAY_NEXT=cal2.get(Calendar.DAY_OF_MONTH);
//        int year_NEXT=cal2.get(Calendar.YEAR);
//        Log.e("NEXT year::::::::;",String.valueOf(year_NEXT));

        ///////
        hourse = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        week = cal.get(Calendar.DAY_OF_WEEK);
        String today=convert_date.gregorian_to_jalali(year,month+1,day,week-1);
        //////////////////////
        String city=SP.getString("city","31.89:54.36");

        if (city.equals("0")){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
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
        Log.e("lang1 month:::",String.valueOf(convert_date.getMonth()));
        Log.e("lang0 day:::",String.valueOf(convert_date.getDay()));


        paryer.SetGeo(Double.parseDouble(lang[1]), Double.parseDouble(lang[0]), convert_date.getMonth(), convert_date.getDay());
        Azan = paryer.GetAzanSobh();
        Log.e("aaa",Azan);
        az = Azan.split(":");
        Horse_azanSobe = Integer.parseInt(az[0]);
        Min_azaneSobe = Integer.parseInt(az[1]);
        String tolo = paryer.GetTolue();
        String[] tt = tolo.split(":");
        int Horse_toloh = Integer.parseInt(tt[0]);
        int Min_toloh = Integer.parseInt(tt[1]);

        ///////
        Calendar calendarazan = Calendar.getInstance();
        calendarazan.set(Calendar.HOUR_OF_DAY, Horse_azanSobe);
        calendarazan.set(Calendar.MINUTE, Min_azaneSobe);
        long azantobeMili=calendarazan.getTimeInMillis();
        Calendar calendartolo = Calendar.getInstance();
        calendartolo.set(Calendar.HOUR_OF_DAY, Horse_toloh);
        calendartolo.set(Calendar.MINUTE, Min_toloh);
        long tolotobeMili=calendartolo.getTimeInMillis();
        long alarm_time;
        long faselh;
//        long current_time=System.currentTimeMillis();
        switch (SP.getString("alarm_time_interval","0a")){
            case "120a":
                alarm_time=-7200000;
                break;
            case "90a":
                alarm_time=-5400000;
                break;
            case "60a":
                alarm_time=-3600000;
                break;
            case "30a":
                alarm_time=-1800000;
                break;
            case "20a":
                alarm_time=-1200000;
                break;
            case "10a":
                alarm_time=-600000;
                break;
            case "0a":
                alarm_time=0;
                break;
            case "15t":
                faselh=tolotobeMili-900000;
                alarm_time=faselh-azantobeMili;
                break;
            case "30t":
                faselh = tolotobeMili - 1800000;
                alarm_time=faselh-azantobeMili;
                break;
            case "45t":
                faselh = tolotobeMili - 2700000;
                alarm_time=faselh-azantobeMili;
                break;
            case "60t":
                faselh = tolotobeMili - 3600000;
                alarm_time=faselh-azantobeMili;
                break;
            default:
                alarm_time=0;



        }


//        long interval=Integer.parseInt(SP.getString("alarm_time_interval","0a"))*60000;



        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
///////////////////////////////////////////////////////////////////
        Calendar calendar = Calendar.getInstance();
        if (hourse>Horse_azanSobe || (hourse==Horse_azanSobe && min>Min_azaneSobe)){
            if (day_year==1){
                year++;
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.DAY_OF_YEAR,day_year);
            }else {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.DAY_OF_YEAR,day_year);
            }
        }
            calendar.set(Calendar.HOUR_OF_DAY, Horse_azanSobe);
            calendar.set(Calendar.MINUTE, Min_azaneSobe);


//        calendar.set(Calendar.HOUR_OF_DAY, 20);
//        calendar.set(Calendar.MINUTE, 59);



        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()+alarm_time, pendingIntent);
        ///////////////////////////////NOTI /////////////////////////////////////






        return START_STICKY;
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }







    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
