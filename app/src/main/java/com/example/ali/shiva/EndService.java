package com.example.ali.shiva;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

public class EndService extends Service {
    public static DatabaseHandler db;
    SharedPreferences SP;
    SharedPreferences.Editor editor;
    Emtiaz emtiaz;
    public EndService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        db = new DatabaseHandler(getApplicationContext());
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = SP.edit();
        emtiaz=new Emtiaz(getApplicationContext());

        emtiaz.saveEmtiaz(2);

        editor.putBoolean("bidar_shod",true);
        editor.apply();
        db.update_final("T");
        Log.e("Main2","Set save emtiaz & update final");

        Intent dialogIntent = new Intent(this, Main2Activity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dialogIntent);

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
