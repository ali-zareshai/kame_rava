package com.example.ali.shiva;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.viethoa.DialogUtils;


public class FragmentMain extends Fragment implements View.OnClickListener {
    CardView geo_card,alarmdiv,soaldiv,doadiv,datadiv,resetdiv;
    static TextView ostan,soaltxt,ahd2txt;
    SharedPreferences preferences;
    boolean a = true;
    Dialog myDialog=null;
    ImageView homedialog,fastoptionbtn,shownotiimg;
    SharedPreferences.Editor editor;
    public static DatabaseHandler db;
    Switch aSwitch;




    public static FragmentMain newInstance() {
        FragmentMain fragment = new FragmentMain();
        return fragment;
    }
    public void updateText(){
        String[] city=getActivity().getResources().getStringArray(R.array.city_name_sp);
        String[] citygeo=getActivity().getResources().getStringArray(R.array.city_value_sp);
        String c=preferences.getString("city","31.89:54.36");
        int indexOf = java.util.Arrays.asList(citygeo).indexOf(c);
        String ci=city[indexOf];
        ostan.setText(ci);
        //////////////////////////////
        String c2=preferences.getString("type_problem","5");
        String[] val=getContext().getResources().getStringArray(R.array.type_value);
        String[] name=getContext().getResources().getStringArray(R.array.type_name);
        Log.e("index::",String.valueOf(c2));
        Log.e("val::",String.valueOf(val.length));
        int indexOf2 = java.util.Arrays.asList(val).indexOf(c2);
        Log.e("index2",String.valueOf(indexOf2));
        soaltxt.setText(name[indexOf2]);
        //////////
        switch (preferences.getString("problem_level","3")){
            case "3":
                soaltxt.setBackgroundResource(R.drawable.asan2);
                break;
            case "4":
                soaltxt.setBackgroundResource(R.drawable.midde2);
                break;
            case "5":
                soaltxt.setBackgroundResource(R.drawable.hard2);
                break;
        }
        /////////////////////////////////////////
//        String c3=preferences.getString("alarma","0");
//        String[] val2=getContext().getResources().getStringArray(R.array.alarm_a_value);
//        String[] name2=getContext().getResources().getStringArray(R.array.alarm_a_name);
//        int indexOf3 = java.util.Arrays.asList(val2).indexOf(c3);
//        hoshdare2txt.setText(name2[indexOf3]);
        //////////////////////////////////////////
//        preferences2 = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (db.get_Ahd()){
            ahd2txt.setText("????????");
        }else {
            ahd2txt.setText("??????????????");
        }
        if (!preferences.getBoolean("enable_date", false)){
            shownotiimg.setImageResource(R.drawable.true1);
            ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
            changeNotifi.setContext(getContext());
            changeNotifi.setNotifi();

        }else {
            shownotiimg.setImageResource(R.drawable.false1);

        }

        aSwitch.setChecked(preferences.getBoolean("enableapp", true));


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_main, container, false);
        geo_card=(CardView)view.findViewById(R.id.georo);
        ostan=(TextView)view.findViewById(R.id.ostanup);
        soaltxt=(TextView)view.findViewById(R.id.soaltxt);
//        hoshdare2txt=(TextView)view.findViewById(R.id.hoshdare2txt);
        ahd2txt=(TextView)view.findViewById(R.id.ahd2txt);
        shownotiimg=(ImageView)view.findViewById(R.id.shownotiimg);
        datadiv=(CardView)view.findViewById(R.id.datadiv);
        homedialog=(ImageView)view.findViewById(R.id.homedialog);
        fastoptionbtn=(ImageView)view.findViewById(R.id.fastoptionbtn);
        aSwitch=(Switch)view.findViewById(R.id.switchenable);


        resetdiv=(CardView)view.findViewById(R.id.resetappcard);
        resetdiv.setOnClickListener(this);



        alarmdiv=(CardView)view.findViewById(R.id.alarmdiv);
        alarmdiv.setOnClickListener(this);

        soaldiv=(CardView)view.findViewById(R.id.soaldiv);
        soaldiv.setOnClickListener(this);


        doadiv=(CardView)view.findViewById(R.id.doadiv);
        doadiv.setOnClickListener(this);

        datadiv.setOnClickListener(this);
        homedialog.setOnClickListener(this);
        fastoptionbtn.setOnClickListener(this);
        db = new DatabaseHandler(getContext());



        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
        geo_card.setOnClickListener(this);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (a){
                    a =false;
                    return;
                }
                if (isChecked){
                    getContext().startService(new Intent(getContext(),AlarmService.class));
                    editor.putBoolean("enableapp",true);
                    ////////////
                    Toast toast = Toast.makeText(getContext(), "???????????? ???????? ????", Toast.LENGTH_SHORT);
//                    View view = toast.getView();
//                    view.setBackgroundColor(Color.GREEN);
                    toast.show();
                }else {
                    getContext().stopService(new Intent(getContext(),AlarmService.class));
                    editor.putBoolean("enableapp",false);
                    /////////
                    Toast toast = Toast.makeText(getContext(), "???????????? ?????????????? ????", Toast.LENGTH_SHORT);
//                    View view = toast.getView();
//                    view.setBackgroundColor(Color.RED);
                    toast.show();
                }
                editor.apply();
            }
        });
        updateText();
        
        
        
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.georo:
                CustomDialogClass cdd = new CustomDialogClass(getActivity(),getContext());
                Window window = cdd.getWindow();
                WindowManager.LayoutParams params = window.getAttributes();
                params.dimAmount = 0.6f;
                window.setAttributes(params);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.setCanceledOnTouchOutside(false);
                cdd.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                cdd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                cdd.show();
                break;
            case R.id.alarmdiv:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_out_up,R.anim.slide_in_up);
                ft.addToBackStack(null);
//                ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out);

                ft.replace(R.id.fragment,FragmentAlarm.newInstance());
                ft.commit();
                break;
            case R.id.soaldiv:
                CustomDialogSoal cdd2 = new CustomDialogSoal(getActivity(),getContext());
                cdd2.setCanceledOnTouchOutside(false);
                cdd2.show();
                break;
//            case R.id.hoshdare2div:
//                CustomDialogHoshdar2 cdd3 = new CustomDialogHoshdar2(getActivity(),getContext());
//                cdd3.setCanceledOnTouchOutside(false);
//                cdd3.show();
//                break;
            case R.id.doadiv:
                CustomDialogAhd cdd4 = new CustomDialogAhd(getActivity(),getContext());
                cdd4.setCanceledOnTouchOutside(false);
                cdd4.show();
                break;
            case R.id.datadiv:
                if (preferences.getBoolean("enable_date", false)){
                    editor.putBoolean("enable_date",false);
                    editor.apply();
                    shownotiimg.setImageResource(R.drawable.true1);
                    ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
                    changeNotifi.setContext(getContext());
                    changeNotifi.setNotifi();
                    getContext().startService(new Intent(getContext(),ChangeNotfiService.class));
                }else {
                    editor.putBoolean("enable_date",true);
                    editor.apply();
                    shownotiimg.setImageResource(R.drawable.false1);
                    ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
                    changeNotifi.setContext(getContext());
                    changeNotifi.cancelNotification(001);
                    getContext().stopService(new Intent(getContext(),ChangeNotfiService.class));
                }
                break;
            case R.id.homedialog:
                getContext().stopService(new Intent(getContext(),AlarmService.class));
                getContext().startService(new Intent(getContext(),AlarmService.class));
                Toast.makeText(getContext(), "?????????????? ?????????? ????", Toast.LENGTH_SHORT).show();
//                getContext().startActivity(new Intent(getContext(),Main2Activity.class));
                getActivity().finish();
                break;
            case R.id.fastoptionbtn:
                getContext().startActivity(new Intent(getContext(),StepActivity.class));
                break;
            case R.id.resetappcard:
                getSoal();
                break;




        }
    }

    private void getSoal(){
        String title = "???????? ???????? ????????????";
        String message = "?????? ???? ???????????? ???????????? ???????? ????????";
        String negativeButton = "??????";
        String positiveButton = "??????";

        myDialog = DialogUtils.createDialogMessage(getActivity(), title, message,
                negativeButton, positiveButton, false, new DialogUtils.DialogListener() {
                    @Override
                    public void onPositiveButton() {
                        db.update_D0("");
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


}
