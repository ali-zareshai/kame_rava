package com.example.ali.shiva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class WaitBorder extends BroadcastReceiver {

    SharedPreferences SP;
    private static DatabaseHandler db;

    public WaitBorder() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        Intent intent1=new Intent(context,SlideBar.class);
//        intent1.putExtra("code","2");
//        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.stopService(new Intent(context,NotifationService.class));
        db = new DatabaseHandler(context);

        SP= PreferenceManager.getDefaultSharedPreferences(context);

        if (!SP.getBoolean("bidar_shod",false)){
            context.stopService(new Intent(context,StartAlarm2.class));
            context.stopService(new Intent(context,Hoshdareh2Alarm.class));
            if (!Util.active ){
                if (!db.get_Final()){
                    Intent intent3=new Intent(context,SliderFinalActivity.class);
//                intent3.putExtra("code","1");
                    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent3);
                }
//

            }

        }


    }
}
