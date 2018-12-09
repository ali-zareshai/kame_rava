package com.example.ali.shiva;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.Arrays;


public class FragmentSelectTime extends Fragment {
    private Spinner spinner;
    private String[] array;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private  boolean a=true;
    private FragmentSelectTime() {
        // Required empty public constructor
    }

    private static FragmentSelectTime fragment = new FragmentSelectTime();
    public static FragmentSelectTime newInstance() {

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
        View view = inflater.inflate(R.layout.fragment_fragment_select_time, container, false);
        spinner=(Spinner)view.findViewById(R.id.spiner_set3);
//        next=(Button)view.findViewById(R.id.next1);
        array=getResources().getStringArray(R.array.alarm_interval_value);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();

        String[] array2=getContext().getResources().getStringArray(R.array.alarm_interval_value);
//        Log.e("alarm_time_interval",preferences.getString("alarm_time_interval","0a"));
//        Log.e("array2", String.valueOf(array2));
        int indexOf = java.util.Arrays.asList(array2).indexOf(preferences.getString("alarm_time_interval","0a"));

        MySpinnerAdapter adapter5 = new MySpinnerAdapter(
                getContext(),
                R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.alarm_interval_name))
        );
        spinner.setAdapter(adapter5);
//        Log.e("toatal::",String.valueOf(array2.length));
//        Log.e("index of",String.valueOf(indexOf));
//        indexOf--;
        Log.e("index_time",String.valueOf(indexOf));
        spinner.setSelection(indexOf);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (a){
                    a=false;
                    return;
                }
                String city=array[position];
                editor.putString("alarm_time_interval",city);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                next.setEnabled(false);

            }
        });

        return view;
    }


}
