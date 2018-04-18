package com.example.ali.shiva;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;


public class StartAlarm2 extends Service {
    public static DatabaseHandler db;
    AlarmManager alarmMgr;
    PendingIntent pendingIntent;
    SharedPreferences SP;
    SharedPreferences.Editor editor;

//    private PendingIntent pendingIntent;
    int martabeh;
    long interval;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        db=new DatabaseHandler(getBaseContext());
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        editor = SP.edit();
//        if (SP.getBoolean("alarm-final",false)){
//            editor.putBoolean("alarm-final",false);
//            editor.apply();
//            Log.e("STARTALARM2","Byyyyyye");
////            getApplicationContext().stopService(new Intent(StartAlarm2.this,StartAlarm2.class));
//            onDestroy();
//        }
        int sp_val=Integer.parseInt(SP.getString("intervel_betwen_alarm","0"));
        if (!db.get_bidar() && !db.get_Toloeh()){
            martabeh=db.get_martabeh();
            if (martabeh==0){
                interval=0;
            }else {
                switch (sp_val){
                    case 0:
                        switch (martabeh){
                            case 1:
                                interval=600000;
                                break;
                            case 2:
                                interval=500000;
                                break;
                            case 3:
                                interval=400000;
                                break;
                            case 4:
                                interval=300000;
                                break;
                            default:
                                interval=200000;
                        }
                        break;
                    case 5:
                        interval=300000;
                        break;
                    case 10:
                        interval=600000;
                        break;
                    case 15:
                        interval=900000;
                        break;
                    case 20:
                        interval=1200000;
                        break;
                    case 30:
                        interval=1800000;
                        break;
                    case 100:
                        interval=TavakoliAlgolitem.getIntervel(getApplicationContext());
                        break;
                    default:
                        interval=300000;

                }

            }


            Log.e("Satalram2:::interval",String.valueOf(interval));




            Intent dialogIntent = new Intent(getBaseContext(), StartAlarmBor.class);


            alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            pendingIntent = PendingIntent.getBroadcast(this, 0, dialogIntent,PendingIntent.FLAG_CANCEL_CURRENT);
            alarmMgr.cancel(pendingIntent);
            Log.e("SYSTEM CURRENT T",String.valueOf(System.currentTimeMillis()));

            alarmMgr.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+interval+90000, pendingIntent);

//            alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(), interval, pendingIntent);












        }




        return START_NOT_STICKY;
    }
    public void stopAlarmManager()
    {
        if(alarmMgr != null)
            alarmMgr.cancel(pendingIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }

    public StartAlarm2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
