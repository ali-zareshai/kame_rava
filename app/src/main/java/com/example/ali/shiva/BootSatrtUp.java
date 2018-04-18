package com.example.ali.shiva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class BootSatrtUp extends BroadcastReceiver {
    SharedPreferences SP;


    @Override
    public void onReceive(Context context, Intent intent) {
        SP = PreferenceManager.getDefaultSharedPreferences(context);
        context.startService(new Intent(context,AlarmService.class));
        if (SP.getBoolean("enable_date", false)) {
            ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
            changeNotifi.setContext(context);
            changeNotifi.setNotifi();
//            context.startService(new Intent(context,ChangeNotfiService.class));
        }
    }
}
