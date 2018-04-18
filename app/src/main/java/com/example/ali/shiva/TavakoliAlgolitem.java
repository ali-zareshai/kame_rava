package com.example.ali.shiva;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;

public class TavakoliAlgolitem {
    private static DatabaseHandler db;
    static String[] lang=new String[2];


    public  static long getIntervel(Context context){
        db = new DatabaseHandler(context);
        int martabeh = db.get_martabeh();
        Log.e("TAVAKOLI:martabekh",String.valueOf(martabeh));
        long current_time=System.currentTimeMillis();
        Log.e("TAVAKOLI:currenttime",String.valueOf(current_time));
        if (martabeh==0){
            return 0;
        }else {
            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
            String city=SP.getString("city","31.89:54.36");
            if (city.equals("0")){
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
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
            Paryer paryer=new Paryer();
            Calendar cal = Calendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            int month=cal.get(Calendar.MONTH);
            int day=cal.get(Calendar.DAY_OF_MONTH);
            int week=cal.get(Calendar.DAY_OF_WEEK);
            Convert_Date convert_date=new Convert_Date();
            String today=convert_date.gregorian_to_jalali(year,month+1,day,week-1);
            Log.e("TAVAKOLI:today",today);
            paryer.SetGeo(Double.parseDouble(lang[1]),Double.parseDouble(lang[0]),convert_date.getMonth(),convert_date.getDay());
            String tolo = paryer.GetTolue();
            String[] tt = tolo.split(":");
            int Horse_toloh = Integer.parseInt(tt[0]);
            int Min_toloh = Integer.parseInt(tt[1]);
            Log.e("TAVAKOLI:Horse_toloh",String.valueOf(Horse_toloh));
            Log.e("TAVAKOLI:Min_toloh",String.valueOf(Min_toloh));
            Calendar calendar = Calendar.getInstance();
            ///////////CALENDAR ////////////////////////////////////////////////////////
            calendar.set(Calendar.HOUR_OF_DAY, Horse_toloh);
            calendar.set(Calendar.MINUTE, Min_toloh);
//            calendar.set(Calendar.HOUR_OF_DAY, 18);
//            calendar.set(Calendar.MINUTE, 15);
            ///////////////////////////////////////////////////////////
            long tolo_militime=calendar.getTimeInMillis();
            Log.e("TAVAKOLI:tolo_militime",String.valueOf(tolo_militime));
            long faseh=tolo_militime-current_time;
            Log.e("TAVAKOLI:faseh",String.valueOf(faseh));
            long dafeh1=faseh/5;
            Log.e("TAVAKOLI:dafeh1",String.valueOf(dafeh1));

            if (dafeh1>=120000){
                return dafeh1;
            }else {
                return 120000;
            }





        }

    }
}
