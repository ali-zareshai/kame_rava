package com.example.ali.shiva;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;



public class MyBroadcastReceiver extends BroadcastReceiver {


    public static DatabaseHandler db;
    SharedPreferences SP;
    SharedPreferences.Editor editor;
    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean b=true;
        SP= PreferenceManager.getDefaultSharedPreferences(context);
        editor = SP.edit();
        if (b){
            db = new DatabaseHandler(context);
            db.update_Azan("T");
            db.update_Tolo("F");
            db.update_Bidar("F");
            db.update_final("F");
            db.update_Martabeh("0");
            db.update_send("F");
            Emtiaz emtiaz=new Emtiaz(context);
            emtiaz.clearEmtiat2();
            b=false;
        }
        editor.putBoolean("start_final_alarm",false);
//        editor.apply();
//        editor = SP.edit();
        editor.putInt("number_zekre",0);
        editor.apply();

        context.stopService(new Intent(context,AlarmService.class));
        context.startService(new Intent(context,StopAlarmservice.class));
//        context.startService(new Intent(context,ChangeNotfiService.class));
        StaticWakeLock.lockOn(context);
        Intent math=new Intent(context,StartAlarm2.class);
        context.startService(math);




    }

}
