package com.example.ali.shiva;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

//import com.ncorti.slidetoact.SlideToActView;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SlideBar extends Activity {
    SeekBar sb;
    public static DatabaseHandler db;
    SharedPreferences SP;
    long timeSeconds1;
    long timeSeconds2;
    Timer timer2;
    public static Boolean ye=true;
    TextView digitalClock;
    int i=0;
    Timer t;
    String type_problem;
    String data_code;
    Emtiaz emtiaz;
    Button imageButton;
    Util util;
    public static Boolean ss2;
//    SlideToActView sta;

    @Override
    protected void onStop() {
        Util.active=false;
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        if (ss2){
            startService(new Intent(SlideBar.this,StartAlarm2.class));
        }
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {

    }
    public static Activity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ss2=true;
        fa = this;
        setContentView(R.layout.activity_slide_bar);
        Util.active=true;
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        db = new DatabaseHandler(this);



        if (db.get_Final() || Util.active2){
            ye=false;
            ss2=false;
            finish();
            return;
        }

        data_code = getIntent().getExtras().getString("code");




        if (data_code!=null) {
            if (data_code.equals("2")) {
//                stopService(new Intent(SlideBar.this, NotifationService.class));
                emtiaz = new Emtiaz(getApplicationContext());
                emtiaz.cancelNotfi(getApplicationContext());
            }
        }
//        sta = (SlideToActView) findViewById(R.id.myseek);



        util=new Util(getApplicationContext());
        digitalClock=(TextView)findViewById(R.id.simpleDigitalClock);
        Calendar cal = Calendar.getInstance();
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m=cal.get(Calendar.MINUTE);
        String data=FormatHelper.toPersianNumber(String.valueOf(h))+":"+FormatHelper.toPersianNumber(String.valueOf(m));
        digitalClock.setText(data);
        Typeface type = Typeface.createFromAsset(getAssets(),"fonts/Vazir.ttf");
        digitalClock.setTypeface(type);
        sb=(SeekBar)findViewById(R.id.myseek);
        data_code = getIntent().getExtras().getString("code");
//        data_code="1";

        long timeMillis2 = System.currentTimeMillis();
        timeSeconds1 = TimeUnit.MILLISECONDS.toSeconds(timeMillis2);
        /////////////////////// SET HADIS ?????????????????????????
        TextView txt_msg=(TextView)findViewById(R.id.textView_skidbar_message);
        TextView txt_from=(TextView)findViewById(R.id.textView_slidebar_from);
        int iu=cal.get(Calendar.DAY_OF_MONTH);
        txt_msg.setText(getMsg(iu));
        txt_from.setText(R.string.msg_f);
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/Samim.ttf");
        txt_msg.setTypeface(type2);
        txt_from.setTypeface(type2);
        imageButton=(Button) findViewById(R.id.imageButtonSlide);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerTask.cancel();
                util.finshActivity();
                finish();
            }
        });




//        stopService(new Intent(SlideBar.this, StartAlarm2.class));
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        ////////////////////\
        type_problem=SP.getString("type_problem","0");
//        // Get the AudioManager

        if (db.get_martabeh()!=0){
            imageButton.setVisibility(View.INVISIBLE);
        }
        if (!data_code.equals("2")) {
            util.startAlarm();
        }else {
            util.startAlarmFinal();
        }



        int martabeh = db.get_martabeh();
        martabeh++;
        db.update_Martabeh(String.valueOf(martabeh));

//		Log.e("martabeh max",String.valueOf(m));
        timer2 = new Timer();
        timer2.schedule(timerTask, 1000, 3000);

//        sta.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
//            @Override
//            public void onSlideComplete(SlideToActView slideToActView) {
//                endLine();
//            }
//        });


        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (seekBar.getProgress() > 95) {

                } else {
                    seekBar.setProgress(0);


                    seekBar.setThumb(getResources().getDrawable(R.drawable.slidetounlock_thumb));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                if(progress>95){
                    if (ye){
                        ye=false;
                        ss2=false;
                        if (!data_code.equals("2")) {
                            Log.e("SliderBar",type_problem);
                            switch (type_problem) {
                                case "0":
                                    startActivity(new Intent(SlideBar.this, AlarmAlertActivity.class));
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    break;
                                case "1":
                                    startActivity(new Intent(SlideBar.this, CaptchaActivity.class));
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    break;
                                case "2":
                                    startActivity(new Intent(SlideBar.this, CaptchaActivity.class));
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    break;
                                case "3":
                                    startActivity(new Intent(SlideBar.this, AndroidAccelerometerExample.class));
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    break;
                                case "4":
                                    startActivity(new Intent(SlideBar.this, RandomActivit.class));
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    break;
                                case "5":
                                    if (!SP.getString("alarma","0").equals("0")){
                                        startService(new Intent(SlideBar.this, Hoshdareh2Alarm.class));
                                    }
                                    db.update_Bidar("T");
                                    if ((SP.getString("alarma","0").equals("0"))){
                                        Emtiaz emtiaz=new Emtiaz(getApplicationContext());
                                        emtiaz.saveEmtiaz(1);
                                    }
                                    break;
                                case "6":
                                    startActivity(new Intent(SlideBar.this, MemoryGame.class));
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    break;
                                case "7":
                                    startActivity(new Intent(SlideBar.this, OrderGame.class));
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    break;
                                case "8":
                                    startActivity(new Intent(SlideBar.this, RepeatGame.class));
                                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                                    break;
                            }
                        }

                    }
                    timerTask.cancel();
                    util.finshActivityForSlider();
                    finish();
                }

            }
        });
//        if (db.get_Toloeh()) {
//            timer2.cancel();
//            t.cancel();
//            Log.e("alarmactivity", "start service startalarm2");
//            if (vibrator != null)
//                vibrator.cancel();
//            try {
//                mediaPlayer.stop();
//            } catch (IllegalStateException ise) {
//
//            }
//            try {
//                mediaPlayer.release();
//            } catch (Exception e) {
//
//            }
//            timerTask.cancel();
//            StaticWakeLock.lockOff(this);
//            this.finish();
//        }
        if (!SP.getBoolean("enable_date", false)) {
            ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
            changeNotifi.setContext(getApplicationContext());
            changeNotifi.setNotifi();
        }
    }





    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            long timeMillis = System.currentTimeMillis();
            timeSeconds2 = TimeUnit.MILLISECONDS.toSeconds(timeMillis);
            long remin = timeSeconds2 - timeSeconds1;
            Log.e("SP SHAREED*******", String.valueOf(remin));
            if (!data_code.equals("2")) {
                if (remin>= Integer.parseInt(SP.getString("alarm_time", "60"))) {
                    timerTask.cancel();
                    util.finshActivity();
                    finish();
                }
            }
        }
    };


    public int getMsg(int i) {
        switch (i){
            case 1:
                return R.string.msg1;
            case 2:
                return R.string.msg2;
            case 3:
                return R.string.msg3;
            case 4:
                return R.string.msg4;
            case 5:
                return R.string.msg5;
            case 6:
                return R.string.msg6;
            case 7:
                return R.string.msg7;
            case 8:
                return R.string.msg8;
            case 9:
                return R.string.msg9;
            case 10:
                return R.string.msg10;
            case 11:
                return R.string.msg11;
            case 12:
                return R.string.msg12;
            case 13:
                return R.string.msg13;
            case 14:
                return R.string.msg14;
            case 15:
                return R.string.msg15;
            case 16:
                return R.string.msg16;
            case 17:
                return R.string.msg17;
            case 18:
                return R.string.msg18;
            case 19:
                return R.string.msg19;
            case 20:
                return R.string.msg20;
            case 21:
                return R.string.msg21;
            case 22:
                return R.string.msg22;
            case 23:
                return R.string.msg23;
            case 24:
                return R.string.msg24;
            case 25:
                return R.string.msg25;
            case 26:
                return R.string.msg26;
            case 27:
                return R.string.msg27;
            case 28:
                return R.string.msg28;
            case 29:
                return R.string.msg29;
            case 30:
                return R.string.msg30;
            case 31:
                return R.string.msg31;

        }
        return R.string.msg31;
    }
}

