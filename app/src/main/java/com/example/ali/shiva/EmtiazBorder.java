package com.example.ali.shiva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

public class EmtiazBorder extends BroadcastReceiver {
    SharedPreferences SP;
    SharedPreferences.Editor editor;
    public static DatabaseHandler db;

    public EmtiazBorder() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        SP= PreferenceManager.getDefaultSharedPreferences(context);
        editor = SP.edit();

        editor.putBoolean("bidar_shod",false);
        editor.apply();
        db = new DatabaseHandler(context);

        if (getAzanTobeh()<=System.currentTimeMillis()){
            if (!db.get_Final()){
                Emtiaz.showNotif(context);
                context.stopService(new Intent(context,ServiceForAzan.class));
            }

        }else {
            context.startService(new Intent(context,ServiceForAzan.class));

        }

//        Emtiaz.showNotif(context);
        context.stopService(new Intent(context,EmtiazService.class));
    }
    private long getAzanTobeh(){
        Calendar cal;
        cal = Calendar.getInstance();


        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        Convert_Date convert_date=new Convert_Date();
        String[] lang=new String[2];
        convert_date.gregorian_to_jalali(year,month+1,day,week-1);
        //////////////////////
        String city=SP.getString("city","31.89:54.36");

        if (city.equals("0")){

            String x = SP.getString("local_x", "");
            String y = SP.getString("local_y", "");
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
