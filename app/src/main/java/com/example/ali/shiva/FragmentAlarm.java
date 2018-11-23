package com.example.ali.shiva;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


public class FragmentAlarm extends Fragment implements View.OnClickListener {
    ImageView vibreh_img,volume_img;
    RelativeLayout img331;
    CardView vibrehc,volumrc,timealarmc,delaytimec,betweenc,randomc,typemusicc,moazenc;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Spinner time_alarm,delaytispin,betweentimspin,randomalspin,moazenspin;
    TextView time_alarm_txt,delaytitxt,betweentimetxt,randomaltxt,typemusictxt,moazentxt;
    private boolean a,b,c,d,e;





    public static FragmentAlarm newInstance() {
        FragmentAlarm fragment = new FragmentAlarm();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_alarm, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = preferences.edit();
        a=b=c=d=e=true;

        img331=(RelativeLayout) view.findViewById(R.id.img331);
        vibrehc=(CardView)view.findViewById(R.id.vibreh_card);
        vibreh_img=(ImageView)view.findViewById(R.id.img_vibreh);
        volume_img=(ImageView)view.findViewById(R.id.img_volume);
        volumrc=(CardView)view.findViewById(R.id.volume_card);
        timealarmc=(CardView)view.findViewById(R.id.time_alarmc);
        time_alarm=(Spinner)view.findViewById(R.id.spinnertimealarm);
        time_alarm_txt=(TextView)view.findViewById(R.id.timealarmfrag);
        delaytispin=(Spinner)view.findViewById(R.id.delaytimspin);
        delaytitxt=(TextView)view.findViewById(R.id.delaytimetxt);
        delaytimec=(CardView)view.findViewById(R.id.delaytimec);
        betweenc=(CardView)view.findViewById(R.id.betweenc);
        betweentimetxt=(TextView)view.findViewById(R.id.betweentimetxt);
        betweentimspin=(Spinner)view.findViewById(R.id.betweentimspin);
        randomc=(CardView)view.findViewById(R.id.randomc);
        randomaltxt=(TextView)view.findViewById(R.id.randomaltxt);
        randomalspin=(Spinner)view.findViewById(R.id.randomalspin);
        typemusicc=(CardView)view.findViewById(R.id.typemusicc);
        typemusictxt=(TextView)view.findViewById(R.id.typemusictxt);
        moazenc=(CardView)view.findViewById(R.id.moazenc);
        moazentxt=(TextView)view.findViewById(R.id.moazentxt);
        moazenspin=(Spinner)view.findViewById(R.id.moazenspin);


        img331.setOnClickListener(this);
        vibrehc.setOnClickListener(this);
        volumrc.setOnClickListener(this);
        timealarmc.setOnClickListener(this);
        delaytimec.setOnClickListener(this);
        betweenc.setOnClickListener(this);
        randomc.setOnClickListener(this);
        typemusicc.setOnClickListener(this);
        moazenc.setOnClickListener(this);



        setIconVibreh();
        setIconVolume();
        setTxttimeAlarm();
        setdelaytie();
        setbetweentime();
        setrandomtime();
        settypemusic();
        setmoazn();

        ///////////////////////////
        setTxttimeAlarm();
        setdelaytie();
        setbetweentime();
        setrandomtime();
        setmoazn();

///////////////////////////////////// 11111111111111111111111111111111111111111111111111111111111
        MySpinnerAdapter adapter = new MySpinnerAdapter(
                getContext(),
                R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.alarm_time_name))
        );
        time_alarm.setAdapter(adapter);
        time_alarm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (a){
                   a=false;
                   return;
                }
                String[] citygeo=getActivity().getResources().getStringArray(R.array.alarm_time_value);
                String val=citygeo[position];
                editor.putString("alarm_time",val);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /////////////////////////////////////////////////// 22222222222222222222222222222222222222222222222222222
        MySpinnerAdapter adapter2 = new MySpinnerAdapter(
                getContext(),
                R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.alarm_interval_name))
        );
        delaytispin.setAdapter(adapter2);

        delaytispin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (b){
                    b=false;
                    return;
                }
                String[] citygeo=getActivity().getResources().getStringArray(R.array.alarm_interval_value);
                String val=citygeo[position];
                editor.putString("alarm_time_interval",val);
                editor.apply();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /////////////////////////333333333333333333333333333333333333333333
        MySpinnerAdapter adapter3 = new MySpinnerAdapter(
                getContext(),
                R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.intervel_betwen_alarm_name))
        );
        betweentimspin.setAdapter(adapter3);
        betweentimspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (c){
                    c=false;
                    return;
                }
                String[] citygeo=getActivity().getResources().getStringArray(R.array.intervel_betwen_alarm_value);
                String val=citygeo[position];
                editor.putString("intervel_betwen_alarm",val);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ///////////////////4444444444444444444444444444444444444444444444444444
        MySpinnerAdapter adapter4 = new MySpinnerAdapter(
                getContext(),
                R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.type_alarm_name))
        );
        randomalspin.setAdapter(adapter4);
        randomalspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (d){
                    d=false;
                    return;
                }
                String[] citygeo=getActivity().getResources().getStringArray(R.array.type_alarm_value);
                String val=citygeo[position];
                editor.putString("type_alarm_random",val);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /////////////////////5555555555555555555555555555555
        MySpinnerAdapter adapter5 = new MySpinnerAdapter(
                getContext(),
                R.layout.view_spinner_item,
                Arrays.asList(getResources().getStringArray(R.array.type_moazen_name))
        );
        moazenspin.setAdapter(adapter5);
        moazenspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (e){
                    e=false;
                    return;
                }
                String[] citygeo=getActivity().getResources().getStringArray(R.array.type_moazen_value);
                String val=citygeo[position];
                editor.putString("type_moazen",val);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        return view;
    }

    private void settypemusic() {
        String[] city=getActivity().getResources().getStringArray(R.array.type_ahang_name);
        String[] citygeo=getActivity().getResources().getStringArray(R.array.type_ahang_name_value);
        String c=preferences.getString("type_ahang_man","0");
//        int indexOf = java.util.Arrays.asList(citygeo).indexOf(c);
        Log.e("indexof c", c);
        Log.e("city length",String.valueOf(city.length));

        String ci=city[Integer.parseInt(c)];
        typemusictxt.setText(ci);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img331:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_in_up_rtl,R.anim.slide_out_up_rtl);
                ft.addToBackStack(null);
//                ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out);

                ft.replace(R.id.fragment,FragmentMain.newInstance());
                ft.commit();
                break;
            case R.id.vibreh_card:
                if (preferences.getBoolean("vibreh",true)){
                    editor.putBoolean("vibreh",false);
                }else {
                    editor.putBoolean("vibreh",true);
                }
                editor.apply();
                setIconVibreh();
                visableAll();
                break;
            case R.id.volume_card:
                if (preferences.getBoolean("volume",true)){
                    editor.putBoolean("volume",false);
                }else {
                    editor.putBoolean("volume",true);
                }
                editor.apply();
                setIconVolume();
                visableAll();
                break;
            case R.id.time_alarmc:
                visableAll();
                time_alarm_txt.setVisibility(View.INVISIBLE);
                time_alarm.setVisibility(View.VISIBLE);
                break;
            case R.id.delaytimec:
                visableAll();
                delaytitxt.setVisibility(View.INVISIBLE);
                delaytispin.setVisibility(View.VISIBLE);
                break;
            case R.id.betweenc:
                visableAll();
                betweentimetxt.setVisibility(View.INVISIBLE);
                betweentimspin.setVisibility(View.VISIBLE);
                break;
            case R.id.randomc:
                visableAll();
                randomaltxt.setVisibility(View.INVISIBLE);
                randomalspin.setVisibility(View.VISIBLE);
                break;
            case R.id.typemusicc:
                getActivity().startActivity(new Intent(getContext(),SelectMusicActivity.class));
                break;
            case R.id.moazenc:
                visableAll();
                moazentxt.setVisibility(View.INVISIBLE);
                moazenspin.setVisibility(View.VISIBLE);
                break;

        }
    }

    private void visableAll() {
        time_alarm_txt.setVisibility(View.VISIBLE);
        time_alarm.setVisibility(View.INVISIBLE);
        ////////////
        delaytitxt.setVisibility(View.VISIBLE);
        delaytispin.setVisibility(View.INVISIBLE);
        ///////////
        betweentimetxt.setVisibility(View.VISIBLE);
        betweentimspin.setVisibility(View.INVISIBLE);
        ///////////
        randomaltxt.setVisibility(View.VISIBLE);
        randomalspin.setVisibility(View.INVISIBLE);
        //////////
        moazentxt.setVisibility(View.VISIBLE);
        moazenspin.setVisibility(View.INVISIBLE);


        //////////////////////////////////

    }

    private void setmoazn() {
        String[] city=getActivity().getResources().getStringArray(R.array.type_moazen_name);
        String[] citygeo=getActivity().getResources().getStringArray(R.array.type_moazen_value);
        String c=preferences.getString("type_moazen","0");
        int indexOf = java.util.Arrays.asList(citygeo).indexOf(c);

        String ci=city[indexOf];
        moazentxt.setText(ci);
        if (!preferences.getString("type_moazen", "").equals("")){
            moazenspin.setSelection(indexOf);
        }
    }

    private void setrandomtime() {
        String[] city=getActivity().getResources().getStringArray(R.array.type_alarm_name);
        String[] citygeo=getActivity().getResources().getStringArray(R.array.type_alarm_value);
        String c=preferences.getString("type_alarm_random","0");
        int indexOf = java.util.Arrays.asList(citygeo).indexOf(c);

        String ci=city[indexOf];
        randomaltxt.setText(ci);
        if (!preferences.getString("type_alarm_random", "").equals("")){
            randomalspin.setSelection(indexOf);
        }
    }


    private void setbetweentime() {
        String[] city=getActivity().getResources().getStringArray(R.array.intervel_betwen_alarm_name);
        String[] citygeo=getActivity().getResources().getStringArray(R.array.intervel_betwen_alarm_value);
        String c=preferences.getString("intervel_betwen_alarm","5");
        int indexOf = java.util.Arrays.asList(citygeo).indexOf(c);

        String ci=city[indexOf];
        betweentimetxt.setText(ci);
        if (!preferences.getString("intervel_betwen_alarm", "").equals("")){
            betweentimspin.setSelection(indexOf);
        }
    }

    private void setdelaytie() {
        String[] city=getActivity().getResources().getStringArray(R.array.alarm_interval_name);
        String[] citygeo=getActivity().getResources().getStringArray(R.array.alarm_interval_value);
        String c=preferences.getString("alarm_time_interval","0a");
        Log.e("alarm_time_interval",c);
        int indexOf = java.util.Arrays.asList(citygeo).indexOf(c);

        String ci=city[indexOf];
        delaytitxt.setText(ci);
        if (!preferences.getString("alarm_time_interval", "").equals("")){
            delaytispin.setSelection(indexOf);
            Log.e("FragmentAlarm:363",String.valueOf(indexOf));
        }
    }

    private void setIconVibreh(){
        if (preferences.getBoolean("vibreh",true)){
            vibreh_img.setImageResource(R.drawable.true1);
        }else {
            vibreh_img.setImageResource(R.drawable.false1);
        }
    }
    private void setIconVolume(){
        if (preferences.getBoolean("volume",true)){
            volume_img.setImageResource(R.drawable.true1);
        }else {
            volume_img.setImageResource(R.drawable.false1);
        }
    }
    private void setTxttimeAlarm(){
        String[] city=getActivity().getResources().getStringArray(R.array.alarm_time_name);
        String[] citygeo=getActivity().getResources().getStringArray(R.array.alarm_time_value);
        String c=preferences.getString("alarm_time","60");
        int indexOf = java.util.Arrays.asList(citygeo).indexOf(c);

        String ci=city[indexOf];
        time_alarm_txt.setText(ci);
        if (!preferences.getString("alarm_time", "").equals("")){
            time_alarm.setSelection(indexOf);
        }
    }
}
