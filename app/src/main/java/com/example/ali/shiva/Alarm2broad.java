package com.example.ali.shiva;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class Alarm2broad extends BroadcastReceiver {
    Intent intent1;
    SharedPreferences SP;


    @Override
    public void onReceive(Context context, Intent intent) {
//        StaticWakeLock.lockOn(context);
        context.stopService(new Intent(context,Hoshdareh2Alarm.class));
        intent1=new Intent(context,RandomActivit.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        SP= PreferenceManager.getDefaultSharedPreferences(context);
        if (!SP.getBoolean("start_final_alarm",false)){
            context.startActivity(intent1);
        }

//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        Intent intent1=new Intent(context,Alarm2Activity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//
//        builder.setTicker("آلارم دوم:جهت اطمینان از بیدار بودن!!!");
//        builder.setContentTitle("هشدار دوم");
//        builder.setContentText("جهت اطمینان از بیدار بودن شما");
//        builder.setSmallIcon(R.drawable.alarm2);
//
//        builder.setContentIntent(pendingIntent);
//
//        Notification n = builder.build();
//
//        // create the notification
//        n.sound = Uri.parse("android.resource://"
//                + context.getPackageName() + "/" + R.raw.alarmbuzzer);
//
//        n.flags = Notification.FLAG_INSISTENT;
////        n.defaults |= Notification.DEFAULT_SOUND;
//        n.flags |= Notification.FLAG_AUTO_CANCEL;
////        n.sound = Uri.parse("android.resource://com.example.ali/" + R.raw.alarmbuzzer);
//        n.vibrate = new long[]{150, 300, 150, 400};
//        notificationManager.notify(R.drawable.alarm22, n);

//
    }
}
