package com.example.ali.shiva;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.ColorInt;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;
import com.viethoa.DialogUtils;


public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Animation.AnimationListener {

    String id;
//
    String[] lang=new String[2];
    Animation animFadein,animFadein2,animFadein4;
    TextView textView4,textViewazan,goreh,gorub,magreb,nimeh;
    public static DatabaseHandler db;
    SharedPreferences SP;
    private static TextView nimehtxt,magrebtxt,gorubtxt,gorehtxt,mandeh,azan,aftab;
    CardView imageViewsunline;
    CardView line1,line2;
    Toolbar toolbar2,toolbar;
    ColorPicker cp;
    SharedPreferences.Editor editor;
    ImageView imageView;
    Dialog myDialog=null;
    TextView item1,item2,item3,item4,item5;
    boolean number_change;
    ViewPager viewPager;
    TabLayout tabHost;
    RelativeLayout header2;
    public static TextView citytxt;
    ProgressBar progressBar;
    Emtiaz emtiaz;


    @Override
    protected void onDestroy() {
        if (number_change && SP.getBoolean("enable_date",true)){
            ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
            changeNotifi.setContext(getApplicationContext());
            changeNotifi.setNotifi();
        }
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        number_change=false;
        //////////////////////////////////////////////////// ANIMATION ////////////////////////////////
        line1=(CardView) findViewById(R.id.ver2);
//
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        line1.startAnimation(animFadein);
        animFadein.setAnimationListener(this);
//
//        ////////////////////////////////
        line2=(CardView) findViewById(R.id.ver4);
        animFadein2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        animFadein2.setAnimationListener(this);
//        ////////////////////////
//        line3=(CardView) findViewById(R.id.ver3);
//        animFadein3 = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.move);
//        animFadein3.setAnimationListener(this);
//        /////////////////////////////
        imageViewsunline=(CardView) findViewById(R.id.imageViewsun);
        animFadein4 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.var_down);
        animFadein4.setAnimationListener(this);
//
//        //////////////////////////////////
//        line5=(CardView)findViewById(R.id.ver4);
//        animFadein5 = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.move);
//        animFadein5.setAnimationListener(this);


        ////////////////////////////////////////////////////////////////////////////

        azan=(CustomTextView2)findViewById(R.id.azanetobetxt);

        aftab=(CustomTextView2)findViewById(R.id.aftabtxt);
        textView4=(TextView)findViewById(R.id.textView4);
        textViewazan=(TextView)findViewById(R.id.textViewazan);
        gorehtxt=(CustomTextView2)findViewById(R.id.gorehtxt);
        mandeh=(CustomTextView3)findViewById(R.id.hejritxt);
        goreh=(TextView)findViewById(R.id.textView55);
        gorubtxt=(CustomTextView2)findViewById(R.id.gorubtxt);
        gorub=(TextView)findViewById(R.id.textView56);
        magrebtxt=(CustomTextView2)findViewById(R.id.magrebtxt);
        magreb=(TextView)findViewById(R.id.textView57);
        nimehtxt=(CustomTextView2)findViewById(R.id.nimehtxt);
        nimeh=(TextView)findViewById(R.id.textView58);
        imageView=(ImageView)findViewById(R.id.arme_terat);
        ////////////////////////////////////////////////////
        viewPager=(ViewPager)findViewById(R.id.pageview21);
        tabHost=(TabLayout) findViewById(R.id.tabhost21);

        toolbar2=(Toolbar)findViewById(R.id.toolbar3);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        item1=(TextView)findViewById(R.id.item_t1);
        item2=(TextView)findViewById(R.id.item_t2);
        item3=(TextView)findViewById(R.id.item_t3);
        item4=(TextView)findViewById(R.id.item_t4);
        item5=(TextView)findViewById(R.id.item_t5);
        header2=(RelativeLayout)findViewById(R.id.heder2);
        citytxt=(TextView)findViewById(R.id.i32);
        //////////////////////////////
        ViewPageAdapter2 viewPageAdapter=new ViewPageAdapter2(getSupportFragmentManager());
        viewPager.setAdapter(viewPageAdapter);
        tabHost.setupWithViewPager(viewPager);

        progressBar=(ProgressBar)findViewById(R.id.progressBar2);

        emtiaz = new Emtiaz(this);
        //////////////////////////////

//        final int[] ICONS = new int[]{
//                R.drawable.paperplane,
//                R.drawable.koran,
//                R.drawable.calendar};
        Typeface typeface=Typeface.createFromAsset(getAssets(),"fonts/yekan.ttf");

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("پیام روز");
        tabOne.setTypeface(typeface);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.paperplane, 0);
        tabHost.getTabAt(0).setCustomView(tabOne);

        TextView tabtwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab2, null);
        tabtwo.setText("ذکر روز");
        tabtwo.setTypeface(typeface);
        tabtwo.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.koran, 0);
        tabHost.getTabAt(1).setCustomView(tabtwo);

        TextView tab3 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab3, null);
        tab3.setText("مناسبت روز");
        tab3.setTypeface(typeface);
        tab3.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.calendar, 0);
        tabHost.getTabAt(2).setCustomView(tab3);

//        tabHost.getTabAt(0).setIcon(ICONS[0]);
//        tabHost.getTabAt(1).setIcon(ICONS[1]);
//        tabHost.getTabAt(2).setIcon(ICONS[2]);

//        toolbar2.set
        SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        header2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeaderOstan cdd = new HeaderOstan(Main2Activity.this,getApplicationContext());
                Window window = cdd.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                params.dimAmount = 0.6f;
                window.setAttributes(params);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.setCanceledOnTouchOutside(false);
                cdd.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                cdd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                cdd.show();
            }
        });
//        imageViewsunline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Main2Activity.this,ScrollingActivity.class));
//            }
//        });

        editor = SP.edit();

        if (!SP.getString("color_toolbar","").equals("")){
            toolbar2.setBackgroundColor(Integer.parseInt(SP.getString("color_toolbar","")));
            toolbar.setBackgroundColor(Integer.parseInt(SP.getString("color_toolbar","")));

        }

//        toolbar2.setBackgroundColor(R.color.toolbar_color);
        ///////////////////////// Color picker /////////////////
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp = new ColorPicker(Main2Activity.this, 255, 150, 100);
                cp.show();
                cp.setCallback(new ColorPickerCallback() {
                    @Override
                    public void onColorChosen(@ColorInt int color) {
                        editor.putString("color_toolbar",String.valueOf(color));
                        editor.apply();
                        Log.e("Color",String.valueOf(color));
                        toolbar2.setBackgroundColor(color);
                        toolbar.setBackgroundColor(color);

                    }
                });
            }
        });

        String[] array=getResources().getStringArray(R.array.city_value_sp);

        SP = PreferenceManager.getDefaultSharedPreferences(this);
        editor = SP.edit();
        String[] array2=getResources().getStringArray(R.array.city_name_sp);
        int indexOf = java.util.Arrays.asList(array).indexOf(SP.getString("city","31.89:54.36"));
        citytxt.setText(array2[indexOf]);

        /////////////////
//        number_zekr.setText(String.valueOf(SP.getInt("number_zekre",0)));
//        line1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int i=SP.getInt("number_zekre",0);
//                i++;
//                editor.putInt("number_zekre",i);
//                editor.apply();
//                number_zekr.setText(String.valueOf(i));
//                number_change=true;
//            }
//        });

//        String city=SP.getString("city","31.89:54.36");
//
//        if (city.equals("0")){
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//            String x = preferences.getString("local_x", "");
//            String y = preferences.getString("local_y", "");
//            if(!x.equalsIgnoreCase(""))
//            {
//                lang[0]=x;
//            }
//            if(!y.equalsIgnoreCase(""))
//            {
//                lang[1]=y;
//            }
//        }else{
//            lang=city.split(":");
//        }
        ///////////////////////////////////////////////
        db = new DatabaseHandler(getApplicationContext());
        SQLiteDatabase sqLiteDatabase=this.openOrCreateDatabase("contactsManager",MODE_PRIVATE,null);
//        if (db.delethTable(sqLiteDatabase)){
            db.onCreate(sqLiteDatabase);
//        }

//        db.insetTo(new Contacts("f","f","f",0,"0","0","0","0","0","0","0","0","0","0"));
        db.insetTo(new Contacts("f","f","f",0));
        ///////////////// MAC ADDRESS ||||||||||||||||||||||
        Log.e("MAC UUID",getMacAddr());

        db.update_mac(getMacAddr());

//        Paryer paryer=new Paryer();
        /////////////////////////////////////
//











        Calendar cal = Calendar.getInstance();


        int year=cal.get(Calendar.YEAR);
        int hourse=cal.get(Calendar.HOUR_OF_DAY);
        int min=cal.get(Calendar.MINUTE);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int week=cal.get(Calendar.DAY_OF_WEEK);
        Convert_Date convert_date=new Convert_Date();




        String today=convert_date.gregorian_to_jalali(year,month+1,day,week-1);
        ///////////////////////  CUstom text view toolbar //////////////////////////////////
        Convert_Date convert_date2=new Convert_Date();
        convert_date2.gregorian_to_jalali(year,month+1,day,week-1);
        item5.setText(String.valueOf(convert_date2.getDay()+1));
        ///////
        item3.setText(convert_date2.getDayWeek());
        ////////////////////////
        item1.setText(convert_date2.getMonthFarsi()+"  "+convert_date2.getYearFarsi());
        ///////////////////////
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd");
        Date date = new Date();
        item2.setText(sdf.format(date));
        ///////////////////////
        item4.setText(DateHigri.writeIslamicDate());
        ///////////////////////



        Log.e("MONTH/DAY",convert_date.getDayMonth(year,month+1,day));

//        paryer.SetGeo(Double.parseDouble(lang[1]),Double.parseDouble(lang[0]),convert_date.getMonth(),convert_date.getDay());









        /////start service
        Intent service=new Intent(Main2Activity.this,AlarmService.class);
        startService(service);
        try {
            emtiaz.cancelNotfi(getApplicationContext());
        }catch (Exception e){
            Log.e("Main2Activicy","error in cancelnotifi");
        }
        try{
            Intent intent23=new Intent(Main2Activity.this,EndService.class);
            stopService(intent23);
        }catch (Exception x){
            Log.e("main2activity","error in stop Endservice");
        }


        ////////////////////////////////
//        String Azan = paryer.GetAzanSobh();
//        String[] az = Azan.split(":");
//        int Horse_azanSobe = Integer.parseInt(az[0]);
//        int Min_azaneSobe = Integer.parseInt(az[1]);
//
//        Calendar calendar2 = Calendar.getInstance();
//        Calendar cal2 = Calendar.getInstance();
//        cal2.add(Calendar.DAY_OF_YEAR, 1); //Adds a day
//
//        int day_year=cal2.get(Calendar.DAY_OF_YEAR);
//
//        int DAY_NEXT=cal2.get(Calendar.DAY_OF_MONTH);
//        if (hourse>Horse_azanSobe || (hourse==Horse_azanSobe && min>Min_azaneSobe)){
//            if (day_year==1){
//                year++;
//                calendar2.set(Calendar.YEAR,year);
//                calendar2.set(Calendar.DAY_OF_YEAR,day_year);
//            }else {
//                calendar2.set(Calendar.YEAR,year);
//                calendar2.set(Calendar.DAY_OF_YEAR,day_year);
//            }
//        }
//        calendar2.set(Calendar.HOUR_OF_DAY, Horse_azanSobe);
//        calendar2.set(Calendar.MINUTE, Min_azaneSobe);
//        long interval=calendar2.getTimeInMillis()-System.currentTimeMillis();
//        int h= (int) (interval/3600000);
//        int reindeh= (int) (interval%3600000)/60000;
//        mandeh.setText(String.valueOf(h)+":"+String.valueOf(reindeh));
//
//
//
//
//
//
//        azan.setText(paryer.GetAzanSobh());
//        aftab.setText(paryer.GetTolue());
//        gorehtxt.setText(paryer.GetAzanZohr());
//        gorubtxt.setText(paryer.GetGhorub());
//        magrebtxt.setText(paryer.GetAzanMaghreb());
//        nimehtxt.setText(paryer.GetNimehShab());




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
        if (!SP.getBoolean("enable_date", false)) {
            ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
            changeNotifi.setContext(getApplicationContext());
            changeNotifi.setNotifi();
        }


        sethourse(this);
        if (!SP.getBoolean("enableapp",true)){
            Toast toast = Toast.makeText(this, "برنامه غیرفعال است", Toast.LENGTH_LONG);
            View view = toast.getView();
            view.setBackgroundColor(Color.RED);
            toast.show();
        }

    }
    public static void sethourse(Context context){
        Calendar cal = Calendar.getInstance();
        Paryer paryer=new Paryer();
        Convert_Date convert_date3=new Convert_Date();
        ////////////////////
        String[] lang=new String[2];
        SharedPreferences SP;
        SP = PreferenceManager.getDefaultSharedPreferences(context);
        String city=SP.getString("city","31.89:54.36");

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
        int year22 = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int week = cal.get(Calendar.DAY_OF_WEEK);

        String today=convert_date3.gregorian_to_jalali(year22,month+1,day,week-1);


        paryer.SetGeo(Double.parseDouble(lang[1]),Double.parseDouble(lang[0]),convert_date3.getMonth(),convert_date3.getDay());




        int year=cal.get(Calendar.YEAR);
        int hourse=cal.get(Calendar.HOUR_OF_DAY);
        int min=cal.get(Calendar.MINUTE);
        String Azan = paryer.GetAzanSobh();
        String[] az = Azan.split(":");
        int Horse_azanSobe = Integer.parseInt(az[0]);
        int Min_azaneSobe = Integer.parseInt(az[1]);

        Calendar calendar2 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_YEAR, 1); //Adds a day

        int day_year=cal2.get(Calendar.DAY_OF_YEAR);

        int DAY_NEXT=cal2.get(Calendar.DAY_OF_MONTH);
        if (hourse>Horse_azanSobe || (hourse==Horse_azanSobe && min>Min_azaneSobe)){
            if (day_year==1){
                year++;
                calendar2.set(Calendar.YEAR,year);
                calendar2.set(Calendar.DAY_OF_YEAR,day_year);
            }else {
                calendar2.set(Calendar.YEAR,year);
                calendar2.set(Calendar.DAY_OF_YEAR,day_year);
            }
        }
        calendar2.set(Calendar.HOUR_OF_DAY, Horse_azanSobe);
        calendar2.set(Calendar.MINUTE, Min_azaneSobe);
        long interval=calendar2.getTimeInMillis()-System.currentTimeMillis();
        int h= (int) (interval/3600000);
        int reindeh= (int) (interval%3600000)/60000;
        mandeh.setText(String.valueOf(h)+":"+String.valueOf(reindeh));






        azan.setText(paryer.GetAzanSobh());
        aftab.setText(paryer.GetTolue());
        gorehtxt.setText(paryer.GetAzanZohr());
        gorubtxt.setText(paryer.GetGhorub());
        magrebtxt.setText(paryer.GetAzanMaghreb());
        nimehtxt.setText(paryer.GetNimehShab());
    }
    private void drawChart(){

        EmtiazActivity.Queue<Integer> queue;queue=new EmtiazActivity.Queue<Integer>(getApplicationContext());
        BarChart barChart = (BarChart) findViewById(R.id.barchart2);
        List<BarEntry> entries=new ArrayList<BarEntry>();
        ArrayList<String> labels = new ArrayList<String>();





            int count = 0;

            for (Object i : queue.getQueue()) {
                entries.add(new BarEntry(Float.parseFloat(String.valueOf(i)), count));
                count++;
                labels.add(String.valueOf(count));

            }
            entries.add(new BarEntry(Float.parseFloat("5500"), 0));
            labels.add("0");

            if (count!=1){
                progressBar.setVisibility(View.INVISIBLE);
                barChart.setVisibility(View.VISIBLE);
            }

            BarDataSet bardataset = new BarDataSet(entries, "Cells");

            BarData data = new BarData(labels, bardataset);
            data.setHighlightEnabled(true);
            data.setDrawValues(false);
            barChart.setData(data); // set the data and list of labels into chart
            barChart.setDescription("");  // set the description

            barChart.setDoubleTapToZoomEnabled(false);
            barChart.setPinchZoom(false);

//        barChart.invalidate();
//        barChart.setVisibleXRangeMaximum(5220);
            int[] color = new int[entries.size()];
            int i = 0;

            for (BarEntry barEntry : entries) {
                if ((int) barEntry.getVal() == 5500){
                    color[i] = Color.parseColor("#00FFFFFF");
                }else if ((int) barEntry.getVal() >= 4000) {
                    color[i] = Color.parseColor("#FF22B700");
                } else if ((int) barEntry.getVal() < 4000 && (int) barEntry.getVal() >= 3000) {
                    color[i] = Color.parseColor("#FF69FF47");
                } else if ((int) barEntry.getVal() < 3000 && (int) barEntry.getVal() >= 1500) {
                    color[i] = Color.parseColor("#FFFF9334");
                } else if ((int) barEntry.getVal() < 1500) {
                    color[i] = Color.RED;
                }
                i++;
            }
//           color[0] = Color.parseColor("#00FFFFFF");

            bardataset.setColors(color);
            barChart.animateY(2000);
            barChart.setDrawGridBackground(false);
            YAxis yAxis = barChart.getAxisLeft();
            yAxis.setAxisMaxValue(5400f);
            yAxis.setEnabled(false);
            YAxis yAxis2 = barChart.getAxisRight();
            yAxis2.setEnabled(false);
            XAxis xAxis = barChart.getXAxis();
            xAxis.setEnabled(false);
            Legend legend = barChart.getLegend();
            legend.setEnabled(false);
            barChart.getAxisLeft().setLabelCount(0, false);


    }




    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    // res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            //handle exception
        }
        return "";
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void exist() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                this);
//
//        // set title
//        alertDialogBuilder.setTitle("خروج؟؟؟");
//
//        // set dialog message
//        alertDialogBuilder
//                .setMessage("آیا می خواهید خارج شوید؟")
//                .setCancelable(false)
//                .setPositiveButton("بله",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        // if this button is clicked, just close
//                        // the dialog box and do nothing
//                        stopService(new Intent(Main2Activity.this,AlarmService.class));
//                        finish();
//                    }
//                })
//                .setNegativeButton("نه",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        // if this button is clicked, just close
//                        // the dialog box and do nothing
//                        dialog.cancel();
//                    }
//                });
//
//        // create alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // show it
//        alertDialog.show();
        String title = "خروج";
        String message = "آیا می خواهید خارج شوید؟";
        String negativeButton = "لغو";
        String positiveButton = "بله";

        myDialog = DialogUtils.createDialogMessage(this, title, message,
                negativeButton, positiveButton, false, new DialogUtils.DialogListener() {
                    @Override
                    public void onPositiveButton() {
                        stopService(new Intent(Main2Activity.this,AlarmService.class));
                        finish();
                    }

                    @Override
                    public void onNegativeButton() {
                        myDialog.cancel();
                    }
                });

        if (myDialog != null && !myDialog.isShowing()) {
            myDialog.show();
        }

    }

    public void aboutUS(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("درباره ما");

        // set dialog message
        alertDialogBuilder
                .setMessage("کام روا نسخه 2.0\n برنامه نویس: علی زارعشاهی")
                .setCancelable(false)
                .setNeutralButton("خوبه!!",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_option) {
//            startActivity(new Intent(Main2Activity.this,PerfernceActivity.class));
            Intent intent=new Intent(Main2Activity.this,PerfernceActivity.class);
            intent.putExtra("mode","2");
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            // Handle the camera action
        } else if (id == R.id.nav_about) {
            aboutUS();

        } else if (id == R.id.nav_exit) {
            exist();

        } else if (id==R.id.nav_share){
            ApplicationInfo app = getApplicationContext().getApplicationInfo();
            String filePath = app.sourceDir;

            Intent intent = new Intent(Intent.ACTION_SEND);

            // MIME of .apk is "application/vnd.android.package-archive".
            // but Bluetooth does not accept this. Let's use "*/*" instead.
            intent.setType("*/*");


            // Append file and send Intent
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
            startActivity(Intent.createChooser(intent, "فرستادن از طریق ..."));
        }else if (id==R.id.nav_rotbeh){
            String s = "متاسفانه اطلاعاتی در دسترس نیست";
            if (db.get_rotbeh()!=null){
                if (!db.get_rotbeh().equals("null")){
                    s="رتبه شما "+FormatHelper.toPersianNumber(db.get_rotbeh())+" از مجموع "+FormatHelper.toPersianNumber(db.get_totol())+" نفر شده است.";
                }
            }


            Snackbar snackbar=Snackbar.make(getWindow().getDecorView().getRootView(), s, Snackbar.LENGTH_LONG)
                    .setAction("sssd", null);
            TextView tv = (TextView) (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/yekan.ttf");
            tv.setTypeface(font);
            tv.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            snackbar.show();
        }else if (id==R.id.nav_help){
            startActivity(new Intent(Main2Activity.this,HelpActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    private void testApp() {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
//                this);
//
//        // set title
//        alertDialogBuilder.setTitle("توجه!!!!");
//
//        // set dialog message
//        alertDialogBuilder
//                .setMessage("در حالت پیش نمایش ممکن است برخی از تنظیمات شما اعمال نشود و تست کامل نباشد.")
//                .setCancelable(false)
//                .setNeutralButton("فهمیدم",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        db.update_Azan("T");
//                        db.update_Tolo("F");
//                        db.update_final("F");
//                        db.update_Bidar("F");
//                        db.update_Martabeh("0");
//                        db.update_send("F");
//                        Intent intent=new Intent(Main2Activity.this,SlideBar.class);
//                        intent.putExtra("code","1");
//                        startActivity(intent);
//                    }
//                });
//
//        // create alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // show it
//        alertDialog.show();
//    }

//    @Override
//    public void onAnimationStart(Animation animation) {
//
//    }
    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Samim.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    //
    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation==animFadein){
            line2.startAnimation(animFadein2);
        }
        if (animation==animFadein2){
            imageViewsunline.startAnimation(animFadein4);
        }
        if (animation==animFadein4){
            drawChart();
        }





    }
//
    @Override
    public void onAnimationRepeat(Animation animation) {


    }
//    private boolean isMyServiceRunning(Class<?> serviceClass) {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
//            if (serviceClass.getName().equals(service.service.getClassName())) {
//                return true;
//            }
//        }
//        return false;
//    }



}
