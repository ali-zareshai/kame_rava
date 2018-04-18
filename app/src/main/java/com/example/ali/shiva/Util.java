package com.example.ali.shiva;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.annotation.RawRes;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Util {

    private Context context;
    private SharedPreferences SP;
    private boolean inter;
    private String[] lang=new String[2];
    private Vibrator vibrator;
    private static DatabaseHandler db;
    private MediaPlayer mediaPlayer;
    AudioManager audioManager;
    Timer t;
    int finalint=0;
    int ivol=0;
    TimerTask timerTask;
    public static boolean active = false;
    public static boolean active2 = false;


    private List<EventEntity> events;

    public Util(Context context) {
        SP = PreferenceManager.getDefaultSharedPreferences(context);
        db = new DatabaseHandler(context);
        this.context = context;
    }

    private String readStream(InputStream is) {
        // http://stackoverflow.com/a/5445161
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    private String readRawResource(@RawRes int res) {
        return readStream(context.getResources().openRawResource(res));
    }
    private List<EventEntity> readEventsFromJSON() {
        List<EventEntity> result = new ArrayList<>();
        try {
            JSONArray days = new JSONObject(readRawResource(R.raw.events)).getJSONArray("events");

            int length = days.length();
            for (int i = 0; i < length; ++i) {
                JSONObject event = days.getJSONObject(i);

                int year = event.getInt("year");
                int month = event.getInt("month");
                int day = event.getInt("day");
                String title = event.getString("title");
                boolean holiday = event.getBoolean("holiday");

                result.add(new EventEntity(month,day,year,title,holiday));
            }

        } catch (JSONException e) {
            Log.e("UTIL", e.getMessage());
        }
        return result;
    }
    public List<String> getEvents(String date) {
        List<String> event=new ArrayList<>();
        String[] d=date.split("/");
        int day=Integer.parseInt(d[0]);
        int month=Integer.parseInt(d[1]);
        if (events == null) {
            events = readEventsFromJSON();
        }

        for (EventEntity eventEntity : events) {

            if (eventEntity.getYear()==-1 || eventEntity.getYear()==1396){

                if (eventEntity.getMonth()==month && eventEntity.getDay()==day){
                    event.add(eventEntity.getTitle());
                    if (eventEntity.isHoliday()){
                        event.add("(تعطیل)");
                    }
                }


            }
        }
        return event;
    }
    public class EventEntity {
        private int month;
        private int day;
        private int year;
        private String title;
        private boolean holiday;

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isHoliday() {
            return holiday;
        }

        public void setHoliday(boolean holiday) {
            this.holiday = holiday;
        }

        public EventEntity(int month, int day, int year, String title, boolean holiday) {
            this.month = month;
            this.day = day;
            this.year = year;
            this.title = title;
            this.holiday = holiday;
        }
    }
    public void startAlarm() {
//        if (db.get_Toloeh()) {
//            return;
//        }
        increateVolume();

        StaticWakeLock.lockOn(context);

        if (SP.getBoolean("vibreh",true)) {
            vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
            long[] pattern = { 1000, 200, 200, 200 };
            vibrator.vibrate(pattern, 0);
        }
        if (db.get_martabeh()==0 && SP.getString("azan-first","").equals("1")){
            playMusic();
        }else {
            if (SP.getString("type_ahang_man","").equals("2")){
                Log.e("martabeh","play-music######");
                play_mymusic();
            }else {
                playMusic();
            }
        }



    }
    private void playMusic(){
        try {
            inter=true;
            mediaPlayer = new MediaPlayer();

            mediaPlayer.setDataSource(context,
                    Uri.parse(getPath()));
//            mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
            mediaPlayer.setLooping(true);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (Exception e) {
            mediaPlayer.release();
//            alarmActive = false;
        }
    }

    private void play_mymusic() {
        int martabeh;
        String ahangs=null;
        try {
//            ahangs=db.getMusic();
            ahangs=SP.getString("ahang_list","");
        }catch (Exception e){
            playMusic();
        }
        if (!ahangs.equals("")){

        }

//        String ahangs=db.getMusic();

        if (!ahangs.equals("")){
            List<String> list1=new ArrayList<>();
            list1= Arrays.asList(ahangs.split("#"));
            if (SP.getString("type_alarm_random","0").equals("0")){
                martabeh= new Random().nextInt(list1.size());
            }else {
                martabeh = db.get_martabeh();
                if (martabeh>=list1.size()){
                    martabeh= new Random().nextInt(list1.size());
                }
            }
            Log.e("martabeh",String.valueOf(martabeh));

            String uri=list1.get(martabeh);
            String path= null;
            try {
                path = java.net.URLDecoder.decode(uri, "UTF-8");
                String[] pa=path.split(":");
                File filePath = new File("/mnt/extSdCard/"+pa[2]);
                if (filePath.canRead()){
                    Log.e("martabeh","can read");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(filePath.getPath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }else {
                    Log.e("martabeh","play-music");
                    playMusic();
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else {
            playMusic();
        }
    }

    private String getPath(){
        Log.e("UTIL",SP.getString("azan-first",""));
        if (db.get_martabeh()==0 && SP.getString("azan-first","").equals("1")){
            Log.e("UTIL","getPatch ");
            switch (SP.getString("type_moazen", "0")){
                case "0":
                    return  "android.resource://com.example.ali.shiva/raw/aghati";
                case "1":
                    return "android.resource://com.example.ali.shiva/raw/mansoori";
                case "2":
                    return "android.resource://com.example.ali.shiva/raw/moazenzadeh";
                default:
                    return "android.resource://com.example.ali.shiva/raw/moazenzadeh";
            }
        }
        int martabeh;
        String path;
        if (SP.getString("type_alarm_random","0").equals("0")){
            martabeh= new Random().nextInt(10);
        }else {
            martabeh = db.get_martabeh();
        }
        String s;
        if (SP.getString("type_ahang", "0").equals("0")) {
            s = "a";
        } else {
            s = "b";
        }
//

        switch (martabeh) {
            case 0:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "0";
                break;
            case 1:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "1";
                break;
            case 2:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "2";
                break;
            case 3:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "3";
                break;
            case 4:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "4";
                break;
            case 5:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "5";
                break;
            case 6:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "6";
                break;
            case 7:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "7";
                break;
            case 8:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "8";
                break;
            case 9:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "9";
                break;
            default:
                path = "android.resource://com.example.ali.shiva/raw/" + s + "0";

        }
        Log.e("alarmactivity_PATH",path);
        return path;

    }
    public void eramTime() {
        Paryer paryer = new Paryer();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);
//        SP = PreferenceManager.getDefaultSharedPreferences(context);
        String city = SP.getString("city", "31.89:54.36");
        if (city.equals("0")){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            String x = preferences.getString("local_x", "");
            String y = preferences.getString("local_y", "");
            if(!x.equalsIgnoreCase(""))
            {
                lang[0]=x;
            }
            if(!y.equalsIgnoreCase(""))
            {
                lang[1]=y;
            }
        }else{
            lang=city.split(":");
        }
        Convert_Date convert_date = new Convert_Date();
        String today = convert_date.gregorian_to_jalali(year, month + 1, day, week - 1);
        paryer.SetGeo(Double.parseDouble(lang[1]), Double.parseDouble(lang[0]), convert_date.getMonth(), convert_date.getDay());
        String Azan = paryer.GetTolue();
        String[] az = Azan.split(":");
        int Horse_tokoSobe = Integer.parseInt(az[0]);
        int Min_toloSobe = Integer.parseInt(az[1]);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, Horse_tokoSobe);
        calendar2.set(Calendar.MINUTE, Min_toloSobe);
        long interval = calendar2.getTimeInMillis() - System.currentTimeMillis();
        Log.e("Interrrrr@@@@", String.valueOf(interval));
        int min = (int) (interval / 60000);
        LayoutInflater li =LayoutInflater.from(context);
        //Getting the View object as defined in the customtoast.xml file
        View view=li.inflate(R.layout.customtoast,null,false);

//        View layout = li.inflate(R.layout.customtoast,
//                (ViewGroup) view.findViewById(R.id.custom_toast_layout));
        TextView textViewToast = (TextView) view.findViewById(R.id.textViewToast);
        textViewToast.setText(FormatHelper.toPersianNumber(String.valueOf(min)));


        //Creating the Toast object
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(view);//setting the view of custom toast layout
        toast.show();

    }
    public void finshActivity(){
        t.cancel();
        timerTask.cancel();
        context.startService(new Intent(context, StartAlarm2.class));
        StaticWakeLock.lockOff(context);

        Log.e("alarmactivity", "start service startalarm2");
        if (vibrator != null)
            vibrator.cancel();
        try {
            mediaPlayer.stop();
        } catch (IllegalStateException ise) {

        }
        try {
            mediaPlayer.release();
        } catch (Exception e) {

        }

    }
    public void finshActivityForSlider(){
        t.cancel();
        timerTask.cancel();
        alarmFinal.cancel();

        StaticWakeLock.lockOff(context);

        Log.e("alarmactivity", "start service startalarm2");
        if (vibrator != null)
            vibrator.cancel();
        try {
            mediaPlayer.stop();
        } catch (IllegalStateException ise) {

        }
        try {
            mediaPlayer.release();
        } catch (Exception e) {

        }

    }
    public void stop() {
        StaticWakeLock.lockOff(context);
        t.cancel();
        timerTask.cancel();



        if (vibrator != null)
            vibrator.cancel();
        try {

            mediaPlayer.stop();
        } catch (IllegalStateException ise) {

        }
        try {
            mediaPlayer.release();
        } catch (Exception e) {

        }
    }
    public void setMute(){
        t.cancel();
        timerTask.cancel();
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        mediaPlayer.setVolume(0f,0f);
    }
    public void endsus(){
        eramTime();
        t.cancel();
        timerTask.cancel();

        if (!SP.getString("alarma","0").equals("0")){
            context.startService(new Intent(context, Hoshdareh2Alarm.class));
        }
        boolean b=false;
        db.update_Bidar("T");
        Log.e("alarmactivity", "STOP ALARM");
        if ((SP.getString("alarma","0").equals("0"))){
            Emtiaz emtiaz=new Emtiaz(context);
            emtiaz.saveEmtiaz(1);
        }

//        Emtiaz emtiaz=new Emtiaz(context);
//        emtiaz.saveEmtiaz(2);
        stop();
    }
    private void increateVolume(){
        ivol=0;

        audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
        // Set the volume
        t=new Timer();
        if (!(SP.getBoolean("volume",false))){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        }
        timerTask=new TimerTask() {
            @Override
            public void run() {
                if (SP.getBoolean("volume", true)) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, ivol, 0);
                    ivol++;
                    Log.e("Audio.STREAM_MUSIC", String.valueOf(ivol));
                    if (ivol==50){
                        timerTask.cancel();
                        t.cancel();
                    }

                }
            }
        };

        t.schedule(timerTask,0,2000);

    }
    public void startAlarmFinal() {
        increateVolume();
        StaticWakeLock.lockOn(context);

        if (SP.getBoolean("vibreh",true)) {
            vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
            long[] pattern = { 1000, 200, 1000, 200 };
            vibrator.vibrate(pattern, 0);
        }
        Timer timerfinal=new Timer();
        timerfinal.schedule(alarmFinal,100,30000);

    }
    TimerTask alarmFinal=new TimerTask() {
        @Override
        public void run() {
            try {
                if (mediaPlayer!=null){
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.stop();
                    }
                }
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(context,Uri.parse(getFinal()));
                mediaPlayer.prepare();
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }catch (Exception e){
                Log.e("UTIL",e.getMessage());

            }
        }
    };
    private String getFinal(){
        Boolean w=true;
        String path=null;
        while (w){
            int x=new Random().nextInt(3);
            if (x!=finalint){
                w=false;
                finalint=x;
            }
        }
        if (!w){

            Log.e("finalint",String.valueOf(finalint));
            switch (finalint){
                case 0:
                    path="android.resource://com.example.ali.shiva/raw/alarmbuzzer";
                    break;
                case 1:
                    path="android.resource://com.example.ali.shiva/raw/car_alarm";
                    break;
                case 2:
                    path="android.resource://com.example.ali.shiva/raw/real_loud_alarm";
                    break;
                    default:
                        path="android.resource://com.example.ali.shiva/raw/real_loud_alarm";

            }

        }
        return path;
    }





}

