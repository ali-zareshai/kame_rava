package com.example.ali.shiva;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;


public class FragmentSetp2 extends Fragment {
    Spinner spinner;
    String[] array;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private FragmentSetp2() {
    }
    private static FragmentSetp2 fragmentSetp2=new FragmentSetp2();
    public static Fragment getInstance(){
        return fragmentSetp2;
    }
//    Button next;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_setp2,container,false);
        spinner=(Spinner)view.findViewById(R.id.spiner_set2);
//        next=(Button)view.findViewById(R.id.next1);
        MySpinnerAdapter adapter5 = new MySpinnerAdapter(
                getContext(),
                R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.city_name_sp))
        );
        spinner.setAdapter(adapter5);

        array=getResources().getStringArray(R.array.city_value_sp);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();

        String[] array2=getContext().getResources().getStringArray(R.array.city_value);
        int indexOf = java.util.Arrays.asList(array2).indexOf(preferences.getString("city","31.89:54.36"));
        Log.e("index of",String.valueOf(indexOf));
        spinner.setSelection(indexOf-1);
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up);
//                ft.addToBackStack(null);
////                ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out);
//
//                ft.replace(R.id.fragment_container, new FragmentSetp3());
//                ft.commit();
//            }
//        });



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city=array[position];
                editor.putString("city",city);
                editor.apply();
//                next.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                next.setEnabled(false);

            }
        });

        return view;
    }
}
