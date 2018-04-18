package com.example.ali.shiva;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;


public class Fragment_mainAct_3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    TextView monatebattxt;
    List<String> events;


    public Fragment_mainAct_3() {
        // Required empty public constructor
    }


    public static Fragment_mainAct_3 newInstance() {
        Fragment_mainAct_3 fragment = new Fragment_mainAct_3();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_main_act_3, container, false);
        monatebattxt=(TextView)view.findViewById(R.id.monatebattxt);
        Util util=new Util(getContext());
        Convert_Date convert_date=new Convert_Date();
        Calendar cal = Calendar.getInstance();


        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        events=util.getEvents(convert_date.getDayMonth(year,month+1,day));
        getMonatebat();

        return view;
    }
    private void getMonatebat() {
        String list="";

        if (events.size()!=0){
            for (String monatebat:events){
                if (monatebat.equals("(تعطیل)")){
                    monatebattxt.setTextColor(Color.RED);
                }else {
                    list += monatebat+" \n";
                }
            }
            monatebattxt.setText(list);
        }else {
            monatebattxt.setText("رویدادی پیدا نشد");
        }


    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }





}
