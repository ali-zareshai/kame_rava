package com.example.ali.shiva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DayChange extends BroadcastReceiver {
//    SharedPreferences.Editor editor;

    @Override
    public void onReceive(Context context, Intent intent) {
//        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
//        editor = SP.edit();
//        editor.putInt("number_zekre",0);
//        editor.apply();
        Intent intent2=new Intent(context,ChangeNotfiService.class);
        context.stopService(intent2);
        Log.e("Day change","start");
//        if (!SP.getBoolean("enable_date", false)) {
//            ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
//            changeNotifi.setContext(context);
//            changeNotifi.setNotifi();
//        }

    }

}
