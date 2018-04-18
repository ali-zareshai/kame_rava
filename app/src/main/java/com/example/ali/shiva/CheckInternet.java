package com.example.ali.shiva;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class CheckInternet extends BroadcastReceiver {
    DatabaseHandler db;
    Timer timer;
    int h;
    String emtiaz;
    public CheckInternet() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            context.startService(new Intent(context,ServiceCheckInternet.class));



        }
    }

//        boolean status = false;
//        try {
//            ConnectivityManager cm1 = (ConnectivityManager) context
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//            NetworkInfo netInfo1 = cm1.getNetworkInfo(0);
//
//            if (netInfo1 != null
//                    && netInfo1.getState() == NetworkInfo.State.CONNECTED) {
//                status = true;
//            } else {
//                netInfo1 = cm.getNetworkInfo(1);
//                if (netInfo1 != null
//                        && netInfo1.getState() == NetworkInfo.State.CONNECTED)
//                    status = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        Log.e("STAUTS",String.valueOf(status));
//        Log.e("CHECK INTERNET","REQUEST111");
//        if (status && h>6){
//            db.update_send("F");
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    Log.e("CHECK INTERNET","REQUEST");
//                    if (db.get_send()){
//                        Log.e("CHECK INTERNET","CANCEL");
//                        timer.cancel();
//                        timer.purge();
//                    }else {
//                        server.sendToServer(Integer.parseInt(emtiaz));
//                    }
//
//
//                }
//            },10000,15000);
//        }






    }



