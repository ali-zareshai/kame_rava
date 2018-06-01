package com.example.ali.shiva;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class FragmentSetp3 extends Fragment {
    RelativeLayout r1,r2,r3,r4,r5;
    ImageView img1,img2,img3,img4,img5;
//    Button next;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private FragmentSetp3() {
    }
    private static FragmentSetp3 fragmentSetp3=new FragmentSetp3();
    public static Fragment getInstance(){
        return fragmentSetp3;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_setp3,container,false);

        r1=(RelativeLayout)view.findViewById(R.id.afradeh1);
        r2=(RelativeLayout)view.findViewById(R.id.afradeh2);
        r3=(RelativeLayout)view.findViewById(R.id.afradeh3);
        r4=(RelativeLayout)view.findViewById(R.id.afradeh4);
        r5=(RelativeLayout)view.findViewById(R.id.afradeh5);

        img1=(ImageView)view.findViewById(R.id.imgafrade1);
        img2=(ImageView)view.findViewById(R.id.imgafrade2);
        img3=(ImageView)view.findViewById(R.id.imgafrade3);
        img4=(ImageView)view.findViewById(R.id.imgafrade4);
        img5=(ImageView)view.findViewById(R.id.imgafrade5);

//        next=(Button)view.findViewById(R.id.next2);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inVisiableAll();
                img1.setVisibility(View.VISIBLE);
                setType(1);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inVisiableAll();
                img2.setVisibility(View.VISIBLE);
                setType(2);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inVisiableAll();
                img3.setVisibility(View.VISIBLE);
                setType(3);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inVisiableAll();
                img4.setVisibility(View.VISIBLE);
                setType(4);
            }
        });
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inVisiableAll();
                img5.setVisibility(View.VISIBLE);
                setType(5);
            }
        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up);
//                ft.addToBackStack(null);
//
//                ft.replace(R.id.fragment_container, new FragmentSetp4());
//                ft.commit();
//            }
//        });


        return view;


    }

    private void setType(int i) {
        String txttoast=null;
        switch (i){
            case 1:
                editor.putBoolean("vibreh",false);///ویبره
                editor.putBoolean("volume",true);///افزایش تدریجی حجم صدا
                editor.putString("alarm_time","30");///مدت زمان آلارم
                editor.putString("intervel_betwen_alarm","30");//فاصله بین آلارم ها
                editor.putString("type_ahang_man","0");//نوع اهنگ
                editor.putString("type_problem","5");//نوع سوال
                editor.putString("problem_level","3");//سطح سوالات
                editor.putString("alarma","0");//هشدار دوم
                txttoast="بدون ویبره-افزایش تدریجی آلارم-آلارم:30 ثانیه-فاصله آلارم ها:30 دقیقه-نوع آهنگ:موزیکال-نوع سوال:ندارد-سطح سوال:آسان-بدون هشدار دوم";
                break;
            case 2:
                editor.putBoolean("vibreh",false);///ویبره
                editor.putBoolean("volume",true);///افزایش تدریجی حجم صدا
                editor.putString("alarm_time","60");///مدت زمان آلارم
                editor.putString("intervel_betwen_alarm","10");//فاصله بین آلارم ها
                editor.putString("type_ahang_man","0");//نوع اهنگ
                editor.putString("type_problem","2");//نوع سوال
                editor.putString("problem_level","3");//سطح سوالات
                editor.putString("alarma","0");//هشدار دوم
                txttoast="بدون ویبره-افزایش تدریجی آلارم-آلارم:60 ثانیه-فاصله آلارم ها:10 دقیقه-نوع آهنگ:موزیکال-نوع سوال:کپچا ریاضی-سطح سوال:آسان-بدون هشدار دوم";

                break;
            case 3:
                editor.putBoolean("vibreh",true);///ویبره
                editor.putBoolean("volume",true);///افزایش تدریجی حجم صدا
                editor.putString("alarm_time","60");///مدت زمان آلارم
                editor.putString("intervel_betwen_alarm","5");//فاصله بین آلارم ها
                editor.putString("type_ahang_man","1");//نوع اهنگ
                editor.putString("type_problem","1");//نوع سوال
                editor.putString("problem_level","4");//سطح سوالات
                editor.putString("alarma","60");//هشدار دوم
                txttoast="باویبره-افزایش تدریجی آلارم-آلارم:60 ثانیه-فاصله آلارم ها:5 دقیقه-نوع آهنگ:ملایم-نوع سوال:کپچا معمولی-سطح سوال:متوسط-هشدار دوم:60ثانیه";

                break;
            case 4:
                editor.putBoolean("vibreh",true);///ویبره
                editor.putBoolean("volume",false);///افزایش تدریجی حجم صدا
                editor.putString("alarm_time","90");///مدت زمان آلارم
                editor.putString("intervel_betwen_alarm","0");//فاصله بین آلارم ها
                editor.putString("type_ahang_man","1");//نوع اهنگ
                editor.putString("type_problem","0");//نوع سوال
                editor.putString("problem_level","5");//سطح سوالات
                editor.putString("alarma","120");//هشدار دوم
                txttoast="باویبره-بدون افزایش تدریجی صدا-آلارم:90 ثانیه-فاصله آلارم ها:متناوب -نوع آهنگ:ملایم-نوع سوال:مسله ریاضی -سطح سوال:سخت -هشدار دوم:120ثانیه";

                break;
            case 5:
                editor.putBoolean("vibreh",true);///ویبره
                editor.putBoolean("volume",false);///افزایش تدریجی حجم صدا
                editor.putString("alarm_time","90");///مدت زمان آلارم
                editor.putString("intervel_betwen_alarm","0");//فاصله بین آلارم ها
                editor.putString("type_ahang_man","1");//نوع اهنگ
                editor.putString("type_problem","7");//نوع سوال
                editor.putString("problem_level","5");//سطح سوالات
                editor.putString("alarma","180");//هشدار دوم
                txttoast="باویبره-بدون افزایش تدریجی صدا-آلارم:90 ثانیه-فاصله آلارم ها:متناوب -نوع آهنگ:ملایم-نوع سوال:مرتب سازی -سطح سوال:سخت -هشدار دوم:180ثانیه";

                break;
        }
        editor.apply();
        Toast toast = Toast.makeText(getContext(), txttoast, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,-10,0);
        View view = toast.getView();
        view.setBackgroundColor(Color.LTGRAY);
        toast.show();
//        Snackbar snackbar=Snackbar.make(getActivity().getWindow().getDecorView().getRootView(),txttoast,7500);
//        snackbar.show();
    }

    private void inVisiableAll(){
//        next.setEnabled(true);

        img1.setVisibility(View.INVISIBLE);
        img2.setVisibility(View.INVISIBLE);
        img3.setVisibility(View.INVISIBLE);
        img4.setVisibility(View.INVISIBLE);
        img5.setVisibility(View.INVISIBLE);
    }
}
