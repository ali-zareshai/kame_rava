package com.example.ali.shiva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

import java.util.Calendar;

public class ServiceForAzan extends Service {
    public ServiceForAzan() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), EmtiazBorder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.cancel(pendingIntent);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, getAzanTobeh(), pendingIntent);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private long getAzanTobeh(){
        Calendar cal;
        cal = Calendar.getInstance();

        SharedPreferences SP;
        SP= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        int hourse = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        Convert_Date convert_date=new Convert_Date();
        String[] lang=new String[2];
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
        Paryer paryer=new Paryer();


        paryer.SetGeo(Double.parseDouble(lang[1]), Double.parseDouble(lang[0]), convert_date.getMonth(), convert_date.getDay());
        String Azan = paryer.GetAzanSobh();

        String[] az;
        az = Azan.split(":");
        int Horse_azanSobe = Integer.parseInt(az[0]);
        int Min_azaneSobe = Integer.parseInt(az[1]);


        ///////
        Calendar calendarazan = Calendar.getInstance();
        calendarazan.set(Calendar.HOUR_OF_DAY, Horse_azanSobe);
        calendarazan.set(Calendar.MINUTE, Min_azaneSobe);
        return calendarazan.getTimeInMillis();
    }
}
