package com.example.ali.shiva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class StartAlarmBor extends BroadcastReceiver {
    Intent intent1;
    SharedPreferences SP;
    SharedPreferences.Editor editor;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("heloooo","man dar startbo2 asatam!!!");
        intent1=new Intent(context,SlideBar.class);
        intent1.putExtra("code","1");
        ////////// Start alarm final /////////////////
        SP= PreferenceManager.getDefaultSharedPreferences(context);
        editor = SP.edit();
////16-10-96
        editor.putBoolean("bidar_shod",false);
        editor.apply();

//        if (SP.getBoolean("enable_popup",false)){
        context.startService(new Intent(context,NotifationService.class));
//        }
//        SP = PreferenceManager.getDefaultSharedPreferences(context);
//
//        if (m.equals("0")){
//
//        }else {
//            intent1=new Intent(context,CaptchaActivity.class);
//        }
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (!SP.getString("alarm_time_interval","0a").equals("15t")){
            if (!SP.getBoolean("start_final_alarm",false)){
                context.startActivity(intent1);
            }

        }

    }
}
