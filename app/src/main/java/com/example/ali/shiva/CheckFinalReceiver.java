package com.example.ali.shiva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CheckFinalReceiver extends BroadcastReceiver {
    SharedPreferences SP;
    private static DatabaseHandler db;

    @Override
    public void onReceive(Context context, Intent intent) {
        SP= PreferenceManager.getDefaultSharedPreferences(context);
        db = new DatabaseHandler(context);
        context.stopService(new Intent(context,CheckFinalService.class));

        if (!SP.getBoolean("bidar_shod",false)){
            Intent intent1=new Intent(context,SliderFinalActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (!db.get_Final()){
                context.startActivity(intent1);
            }


        }
    }
}
