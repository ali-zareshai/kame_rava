package com.example.ali.shiva;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;


public class Fragment_mainAct_2 extends Fragment {

    public Fragment_mainAct_2() {
        // Required empty public constructor
    }


    public static Fragment_mainAct_2 newInstance() {
        Fragment_mainAct_2 fragment = new Fragment_mainAct_2();

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
        View view=inflater.inflate(R.layout.fragment_fragment_main_act_2, container, false);
        TextView zeker=(TextView)view.findViewById(R.id.textzekercard);
        Calendar calendar=Calendar.getInstance();
        zeker.setText(getZekre(calendar.get(Calendar.DAY_OF_WEEK)));
        return view;
    }
    private  String getZekre(int week){
        switch (week){
            case 6:
                return getString(R.string.D6);
            case 7:
                return getString(R.string.D0);
            case 1:
                return getString(R.string.D1);
            case 2:
                return getString(R.string.D2);
            case 3:
                return getString(R.string.D3);
            case 4:
                return getString(R.string.D4);
            case 5:
                return getString(R.string.D5);

        }
        return "Error";
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }



}
