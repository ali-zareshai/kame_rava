package com.example.ali.shiva;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

public class Emtiaz {
     static Context context;
    static String[] lang=new String[2];

    public static DatabaseHandler db;
    static Convert_Date convert_date=new Convert_Date();
    static NotificationCompat.Builder mBuilder;
    static Calendar cal;
    static NotificationManager mNotificationManager;
    static Paryer paryer=new Paryer();
    public Emtiaz(Context c) {
        this.context=c;
    }

    private static long getToloMili(){
        cal = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        Log.e("AlarmService","START SERVICE");
        /////////////////
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


        ///////
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        convert_date.gregorian_to_jalali(year, month + 1, day, week - 1);


        paryer.SetGeo(Double.parseDouble(lang[1]), Double.parseDouble(lang[0]), convert_date.getMonth(), convert_date.getDay());

        ////////
        String tolo = paryer.GetTolue();
        String[] tt = tolo.split(":");
        int Horse_toloh = Integer.parseInt(tt[0]);
        int Min_toloh = Integer.parseInt(tt[1]);
        calendar.set(Calendar.HOUR_OF_DAY, Horse_toloh);
        calendar.set(Calendar.MINUTE, Min_toloh);
        return calendar.getTimeInMillis();

    }
    private static long currentTime(){
        return System.currentTimeMillis();
    }
    private static long emtiaz(){
        long emtiaz=Math.abs(getToloMili()-currentTime());
        if (emtiaz>5200*1000){
            emtiaz= 5200*1000;
        }
        return emtiaz;

    }
    public void saveEmtiaz(int i){
        db = new DatabaseHandler(context);
        try {
            if (i==1){
                db.update_emtiaz1(String.valueOf(emtiaz()));
                context.startService(new Intent(context,EmtiazService.class));

            }else if (i==2){
                db.update_emtiaz2(String.valueOf(emtiaz()));
                saveEmtiat2();
            }
        }catch (Exception e){
        }

    }
    public static void showNotif(Context c){



        mBuilder = new NotificationCompat.Builder(c);


        Intent intent = new Intent(c,EndService.class);
//        intent.putExtra("code","1");
        PendingIntent pendingIntent = PendingIntent.getService(c, 0, intent, 0);

//        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setAutoCancel(false);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setOngoing(true);
        mBuilder.addAction(R.drawable.sussecc,"بله ممنون",pendingIntent);
        mBuilder.setSound(Uri.parse("android.resource://com.example.ali.shiva/raw/solemn"));

        mBuilder.setSmallIcon(R.drawable.question);
        mBuilder.setContentTitle("سلام");
        mBuilder.setContentText("آیا موفق شدی نمازت را به موقع بخوانی؟");
        mBuilder.setTicker("یک سوال!!!");

        mNotificationManager =

                (NotificationManager) c.getSystemService(c.NOTIFICATION_SERVICE);

        mNotificationManager.notify(002, mBuilder.build());

    }
    public void cancelNotfi(Context c){
//        if (mNotificationManager!=null){
//            mNotificationManager.cancelAll();
//        }
        String ns = c.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) c.getSystemService(ns);
        nMgr.cancel(002);


    }

    private static void saveEmtiat2(){
        cal = Calendar.getInstance();
        int day=cal.get(Calendar.DAY_OF_WEEK);
        int emtiaz=(int)emtiaz()/1000;
        db = new DatabaseHandler(context);
//        db.update_send("F");
        Server server=new Server(context);
        server.sendToServer(emtiaz);
        db.update_Bidar("T");
        Log.e("Emtiaz","run method emtiaz");
        EmtiazActivity.Queue queue=new EmtiazActivity.Queue(context);
        queue.Enqueue((int)emtiaz);

//        switch (day){
//            case 7:
//                db.update_D0(String.valueOf(emtiaz));
//                break;
//            case 1:
//                db.update_D1(String.valueOf(emtiaz));
//                break;
//            case 2:
//                db.update_D2(String.valueOf(emtiaz));
//                break;
//            case 3:
//                db.update_D3(String.valueOf(emtiaz));
//                break;
//            case 4:
//                db.update_D4(String.valueOf(emtiaz));
//                break;
//            case 5:
//                db.update_D5(String.valueOf(emtiaz));
//                break;
//            case 6:
//                db.update_D6(String.valueOf(emtiaz));
//                break;
//
//        }

    }
    public static void clearEmtiat2(){
        db = new DatabaseHandler(context);
        cal = Calendar.getInstance();
        int day=cal.get(Calendar.DAY_OF_WEEK);
        int emtiaz=0;
        switch (day){
            case 7:
                db.update_D0(String.valueOf(emtiaz));
                break;
            case 1:
                db.update_D1(String.valueOf(emtiaz));
                break;
            case 2:
                db.update_D2(String.valueOf(emtiaz));
                break;
            case 3:
                db.update_D3(String.valueOf(emtiaz));
                break;
            case 4:
                db.update_D4(String.valueOf(emtiaz));
                break;
            case 5:
                db.update_D5(String.valueOf(emtiaz));
                break;
            case 6:
                db.update_D6(String.valueOf(emtiaz));
                break;

        }

    }

}
