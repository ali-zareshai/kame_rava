package com.example.ali.shiva;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceCheckInternet extends Service {
    Server server;
    Timer timer;
    String emtiaz;
    DatabaseHandler db;
    int h;
    public ServiceCheckInternet() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ServiceCheckInternet","START");

        //should check null because in airplane mode it will be null
            server = new Server(getApplicationContext());
            server.getEmtiaz();

            Calendar cal = Calendar.getInstance();
            timer = new Timer();
            int day = cal.get(Calendar.DAY_OF_WEEK);
            h = cal.get(Calendar.HOUR_OF_DAY);
            db = new DatabaseHandler(getApplicationContext());
            switch (day) {
                case 7:
                    emtiaz = db.get_d0();
                    break;
                case 1:
                    emtiaz = db.get_d1();
                    break;
                case 2:
                    emtiaz = db.get_d2();
                    break;
                case 3:
                    emtiaz = db.get_d3();
                    break;
                case 4:
                    emtiaz = db.get_d4();
                    break;
                case 5:
                    emtiaz = db.get_d5();
                    break;
                case 6:
                    emtiaz = db.get_d6();
                    break;
                default:
                    emtiaz = "0";

            }

//            server.getImage();
            if (h > 6 && (!db.get_send())) {
                Log.e("Check DB", String.valueOf(db.get_send()));

                timer.schedule(new TimerTask() {
                    int i = 0;

                    @Override
                    public void run() {

                        if (db.get_send() || i == 20) {
                            timer.cancel();
                            timer.purge();
                            stopService(new Intent(getApplicationContext(),ServiceCheckInternet.class));
                            Log.e("Check", "FINIFH");
                        } else {
                            server.sendToServer(Integer.parseInt(emtiaz));
                            i++;
                            Log.e("Check", "SEND>>>");
                        }


                    }
                }, 1000, 20000);
            }




            return START_NOT_STICKY;
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

