package com.example.ali.shiva;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Calendar;


public class ChangeNotifi {
    Context context;
    String today;
    int day,week;
    SharedPreferences SP;
//    SharedPreferences.Editor editor;
//    int num;
//    String farsi_num;

    public void setContext(Context context) {
        this.context = context;
    }

    private static ChangeNotifi changeNotifi;
    private ChangeNotifi(){

    }
    static {
        try {
            {
                changeNotifi=new ChangeNotifi();
            }
        }catch (Exception e){

        }
    }
    public static ChangeNotifi getChangeNotifi(){

        return changeNotifi;
    }

//    public ChangeNotifi(Context context) {
//        this.context = context;
//    }

    public void setNotifi(){
        getDate();
//        View v; // Creating an instance for View Object
//        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        v = inflater.inflate(R.layout.activity_custom_notification, null);
//        TextView txt1=(TextView)v.findViewById(R.id.titlenoti);
//        TextView txt2=(TextView)v.findViewById(R.id.textnoti);
//
//        Typeface face = Typeface.createFromAsset(context.getAssets(),
//                "fonts/Vazir.ttf");
//        txt1.setTypeface(face);
//        txt2.setTypeface(face);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        Intent intent1=new Intent(context.getApplicationContext(),Main2Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent1, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext());
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder.setOngoing(true);
        builder.setTicker(today);
        SP = PreferenceManager.getDefaultSharedPreferences(context);
//        num=SP.getInt("number_zekre",0);
//        farsi_num=FormatHelper.toPersianNumber(String.valueOf(num));
//        builder.setContentText(today);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            // build a complex notification, with buttons and such
            //
            builder = builder.setContent(getComplexNotificationView(day,today,week));
        } else {
            // Build a simpler notification, without buttons
            //
            builder = builder.setContentTitle("امروز")
                    .setContentText(today)
                    .setSmallIcon(geticon(day));
        }



        builder.setSmallIcon(geticon(day));

        builder.setContentIntent(pendingIntent);

        Notification n = builder.build();



        n.flags = Notification.FLAG_INSISTENT;
//        n.defaults |= Notification.DEFAULT_SOUND;
        n.flags |= Notification.FLAG_AUTO_CANCEL;
//        n.sound = Uri.parse("android.resource://com.example.ali/" + R.raw.alarmbuzzer);
//        n.vibrate = new long[]{150, 300, 150, 400};
        notificationManager.notify(001, builder.build());

        ////////// SET MEMORY
//        preferences = PreferenceManager.getDefaultSharedPreferences(context);

//
//        editor.putString("notifi","yes");



    }
    public void cancelNotification(int notifyId) {
        String ns = context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) context.getSystemService(ns);
        nMgr.cancel(notifyId);
        /////////////////////
//        preferences = PreferenceManager.getDefaultSharedPreferences(context);
//        editor = preferences.edit();
//
//        editor.putString("notifi","no");
    }

    private  int geticon(int day) {
        switch (day){
            case 12:
                return R.drawable.c12;
            case 13:
                return R.drawable.c13;
            case 14:
                return R.drawable.c14;
            case 15:
                return R.drawable.c15;
            case 16:
                return R.drawable.c16;
            case 17:
                return R.drawable.c17;
            case 18:
                return R.drawable.c18;
            case 19:
                return R.drawable.c19;
            case 20:
                return R.drawable.c20;
            case 21:
                return R.drawable.c21;
            case 22:
                return R.drawable.c22;
            case 23:
                return R.drawable.c23;
            case 24:
                return R.drawable.c24;
            case 25:
                return R.drawable.c25;
            case 26:
                return R.drawable.c26;
            case 27:
                return R.drawable.c27;
            case 28:
                return R.drawable.c28;
            case 29:
                return R.drawable.c29;
            case 30:
                return R.drawable.c30;
            case 31:
                return R.drawable.c31;
            case 1:
                return R.drawable.c1;
            case 2:
                return R.drawable.c2;
            case 3:
                return R.drawable.c3;
            case 4:
                return R.drawable.c4;
            case 5:
                return R.drawable.c5;
            case 6:
                return R.drawable.c6;
            case 7:
                return R.drawable.c7;
            case 8:
                return R.drawable.c8;
            case 9:
                return R.drawable.c9;
            case 10:
                return R.drawable.c10;
            case 11:
                return R.drawable.c11;


        }
        return 0;
    }
    private  RemoteViews getComplexNotificationView(int day, String today, int week) {
        RemoteViews notificationView = new RemoteViews(
                context.getPackageName(),
                R.layout.activity_custom_notification
        );



        // Locate and set the Image into customnotificationtext.xml ImageViews
        notificationView.setImageViewResource(
                R.id.imagenotileft,
                geticon(day));


        // Locate and set the Text into customnotificationtext.xml TextViews
//        notificationView.setTextViewText(R.id.titlenoti, getZekre(week));
        notificationView.setImageViewBitmap(R.id.titlenoti,buildUpdate(getZekre(week)));
        notificationView.setImageViewBitmap(R.id.textnoti, buildUpdate2(today));
//        if (week==5 || week==6){
//            notificationView.setTextViewTextSize(R.id.titlenoti,0,18);
//            notificationView.setTextViewTextSize(R.id.textnoti,0,19);
//        }else {
//            notificationView.setTextViewTextSize(R.id.titlenoti,0,28);
//            notificationView.setTextViewTextSize(R.id.textnoti,0,20);
//        }

        return notificationView;
    }
    public Bitmap buildUpdate(String time)
    {
        Bitmap myBitmap = Bitmap.createBitmap(220, 25, Bitmap.Config.ARGB_8888);
        Canvas myCanvas = new Canvas(myBitmap);
        Paint paint = new Paint();
        Typeface clock = Typeface.createFromAsset(context.getAssets(),"fonts/yekan.ttf");
        paint.setTypeface(clock);
        paint.setAntiAlias(true);
//        paint.setSubpixelText(true);
//        paint.setAlpha(255);
//        paint.setDither(true);
        paint.setFakeBoldText(true);
        paint.setLinearText(true);
        paint.setColor(Color.WHITE);
        paint.setTextSize(13);
        myCanvas.drawText(time, 20, 20, paint);
        return myBitmap;
    }
    public Bitmap buildUpdate2(String time)
    {
        Bitmap myBitmap = Bitmap.createBitmap(220, 25, Bitmap.Config.ARGB_8888);
        Canvas myCanvas = new Canvas(myBitmap);
        Paint paint = new Paint();
        Typeface clock = Typeface.createFromAsset(context.getAssets(),"fonts/Vazir.ttf");
        paint.setTypeface(clock);
//        paint.setAlpha(255);
//        paint.setDither(true);

        paint.setFakeBoldText(true);

        paint.setLinearText(true);
        paint.setAntiAlias(true);
//        paint.setSubpixelText(true);
//        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTextSize(12);
//        paint.setTextAlign(Paint.Align.CENTER);
        myCanvas.drawText(time, 20, 20, paint);
        return myBitmap;
    }
    private  String getZekre(int week){
        switch (week){
            case 6:
                return context.getString(R.string.D6);
            case 7:
                return context.getString(R.string.D0);
            case 1:
                return context.getString(R.string.D1);
            case 2:
                return context.getString(R.string.D2);
            case 3:
                return context.getString(R.string.D3);
            case 4:
                return context.getString(R.string.D4);
            case 5:
                return context.getString(R.string.D5);

        }
        return "Error";
    }
    private void getDate(){
        Calendar cal=Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        week = cal.get(Calendar.DAY_OF_WEEK);
        //////
        Convert_Date convert_date=new Convert_Date();
        today=FormatHelper.toPersianNumber(convert_date.gregorian_to_jalali(year,month+1,day,week-1));
        day=convert_date.getDay()+1;
    }
}
