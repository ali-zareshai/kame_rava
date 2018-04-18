package com.example.ali.shiva;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;


public class EmtiazActivity extends AppCompatActivity {
    public static DatabaseHandler db;
    SharedPreferences preferences3;
    SharedPreferences.Editor editor;
    Boolean excit=true;
    Queue<Integer> queue;


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (excit){
            startActivity(new Intent(getApplicationContext(),Main2Activity.class));
            finish();
        }else {
            StaticWakeLock.lockOff(getApplicationContext());
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emtiaz);

        preferences3 = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences3.edit();


        stopService(new Intent(EmtiazActivity.this,EmtiazService.class));
//        stopService(new Intent(EmtiazActivity.this,NotifationService.class));

        String data_code = getIntent().getExtras().getString("code");
        db = new DatabaseHandler(this);
        queue=new Queue<Integer>(getApplicationContext());



        if (data_code!=null) {
            if (data_code.equals("1")) {
                Emtiaz emtiaz = new Emtiaz(this);
                emtiaz.saveEmtiaz(2);
                emtiaz.cancelNotfi(getApplicationContext());
                editor.putBoolean("bidar_shod",true);
                editor.apply();
                db.update_final("T");
                excit=false;
//                queue.Enqueue();
            }
        }






        BarChart barChart = (BarChart) findViewById(R.id.barchart);
        List<BarEntry> entries=new ArrayList<BarEntry>();
        ArrayList<String> labels = new ArrayList<String>();

        int count=0;
        for (Object i:queue.getQueue()) {
            entries.add(new BarEntry(Float.parseFloat(String.valueOf(i)),count));
            count++;
            labels.add(String.valueOf(count));

        }

        BarDataSet bardataset = new BarDataSet(entries, "Cells");

        BarData data = new BarData(labels, bardataset);
        data.setHighlightEnabled(true);
        data.setDrawValues(false);
//        barChart.setVisibleYRangeMaximum(5200, YAxis.AxisDependency.RIGHT);
        barChart.setData(data); // set the data and list of labels into chart
        barChart.setDescription("امتیازات");  // set the description

//        barChart.invalidate();
//        barChart.setVisibleXRangeMaximum(5220);
        int[] color = new int[entries.size()];
        int i=0;
        for (BarEntry barEntry:entries){
            if ((int)barEntry.getVal()>=4000){
                color[i]=Color.parseColor("#FF22B700");
            }else if ((int)barEntry.getVal()<4000 && (int)barEntry.getVal()>=3000){
                color[i]=Color.parseColor("#FF69FF47");
            }else if ((int)barEntry.getVal()<3000 && (int)barEntry.getVal()>=1500){
                color[i]=Color.parseColor("#FFFF9334");
            }else if ((int)barEntry.getVal()<1500){
                color[i]=Color.RED;
            }
            i++;
        }

        bardataset.setColors(color);
        barChart.animateY(2000);
        barChart.setDrawGridBackground(false);
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMaxValue(5400f);
//        yAxis.setEnabled(false);
        YAxis yAxis2 = barChart.getAxisRight();
        yAxis2.setEnabled(false);
        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
//        yAxis.setAxisMinValue(0f);
//        barChart.getAxisLeft().setLabelCount(0, false);




    }
    public static class Queue <Integer>{
        DatabaseHandler db;

        private ArrayList<Integer> queue;

        public ArrayList<Integer> getQueue() {
            return queue;
        }

        public Queue(Context context) {

            this.queue = new ArrayList<Integer>();
            db = new DatabaseHandler(context);
            try {
                this.toArray(db.get_d0());
            }catch (Exception e){
                Log.e("JSON",e.getMessage());
            }

//            this.toArray(db.get_d0());

        }

        public void Enqueue(java.lang.Integer object){
            printArray();


            queue.add((Integer) object);
            if (size()>40) {

                queue.remove(0);

            }
            try {
                Log.e("save quere","save ");
                db.update_D0(this.outString());
            }catch (Exception e){
                Log.e("save quere","save Error");
            }
            printArray();


        }

        public java.lang.Integer Dequeue() {

            if (size()>40) {

                return (java.lang.Integer) queue.remove(0);

            }

            return null;

        }

        public int size() {

            return queue.size();

        }

        public void print() {
            for (Integer item : queue) {
                System.out.print(String.valueOf(item));
                System.out.println("\n");
            }

        }
        public String outString(){
            String v="";
            for (Integer i:queue) {
                v+=String.valueOf(i);
                v+="#";
            }
            Log.e("qute_outstring",v);
            return v;
        }
        public void toArray(String in){
            ArrayList<Integer> arrayList=new ArrayList<Integer>();
            String[] str=in.split("#");
            for (String i:str) {
                arrayList.add((Integer) i);
                Log.e("toarray",i);
            }
            queue=arrayList;

        }
        public void printArray() {
            for (Integer item : queue) {
                Log.e("print....",String.valueOf(item));
            }

        }

    }
}
