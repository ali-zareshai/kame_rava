package com.example.ali.shiva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class StopAllServiceSer extends BroadcastReceiver {
    public static DatabaseHandler db;
    SharedPreferences SP;
    Intent intent1;

    @Override
    public void onReceive(Context context, Intent intent) {
        db = new DatabaseHandler(context);
        db.update_Tolo("T");

        SP = PreferenceManager.getDefaultSharedPreferences(context);


        context.stopService(new Intent(context,StartAlarm2.class));
        context.stopService(new Intent(context,StopAlarmservice.class));
        context.stopService(new Intent(context,Hoshdareh2Alarm.class));
        context.stopService(new Intent(context,NotifationService.class));

        context.stopService(new Intent(context,AlarmfinalService.class));
        context.stopService(new Intent(context,CheckFinalService.class));


        context.stopService(new Intent(context,EmtiazService.class));
        Emtiaz emtiaz=new Emtiaz(context);
        emtiaz.cancelNotfi(context);
//        new AlarmAlertActivity().finshActivityAll();
        context.startService(new Intent(context,AlarmService.class));
        if (db.get_Ahd()){
            intent1=new Intent(context,DoaAhdActivity.class);
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            StaticWakeLock.lockOn(context);
            context.startActivity(intent1);
        }
        if (!SP.getBoolean("enable_date", false)) {
            ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
            changeNotifi.setContext(context);
            changeNotifi.setNotifi();
        }


    }
}
