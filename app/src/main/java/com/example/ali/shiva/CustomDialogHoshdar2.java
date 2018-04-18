package com.example.ali.shiva;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomDialogHoshdar2 extends Dialog {
    public Activity c;
    public Dialog d;
    public Button ok;
    RelativeLayout hoshdare2;
    Spinner spinner1,spinner2,spinner3;
    String[] hoshdar1,tarevat,level;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public CustomDialogHoshdar2(Activity a,Context context) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog3);

        hoshdar1=getContext().getResources().getStringArray(R.array.alarm_a_value);
        tarevat=getContext().getResources().getStringArray(R.array.type_talevat_value);
        level=getContext().getResources().getStringArray(R.array.type_level_two_value);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
        spinner1=(Spinner)findViewById(R.id.spinnerhoshdar2dialog);
        spinner2=(Spinner)findViewById(R.id.spinnertarevatdialog);
        spinner3=(Spinner)findViewById(R.id.spinnerleveldialog);
        hoshdare2=(RelativeLayout)findViewById(R.id.ra876);
        ok=(Button)findViewById(R.id.okhoshdare2dialog);
        setDefalt();
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city=hoshdar1[position];
                editor.putString("alarma",city);
                editor.apply();
                if (position!=0){
                    hoshdare2.setVisibility(View.VISIBLE);
                }else {
                    hoshdare2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city=tarevat[position];
                editor.putString("type_talevat",city);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city=level[position];
                editor.putString("type_level_two",city);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,PerfernceActivity.class);
                intent.putExtra("mode","1");
                context.startActivity(intent);
                dismiss();
            }
        });

    }

    private void setDefalt() {
        if (!preferences.getString("alarma", "0").equals("")){
            String c=preferences.getString("alarma","0");
            int indexOf = java.util.Arrays.asList(hoshdar1).indexOf(c);
            spinner1.setSelection(indexOf);
        }
        if (!preferences.getString("type_talevat", "0").equals("")){
            String c=preferences.getString("type_talevat","0");
            int indexOf = java.util.Arrays.asList(tarevat).indexOf(c);
            spinner2.setSelection(indexOf);
        }
        if (!preferences.getString("type_level_two", "5").equals("")){
            String c=preferences.getString("type_level_two","5");
            int indexOf = java.util.Arrays.asList(level).indexOf(c);
            spinner3.setSelection(indexOf);
        }
        if (preferences.getString("alarma", "0").equals("0")){
            hoshdare2.setVisibility(View.INVISIBLE);
        }else {
            hoshdare2.setVisibility(View.VISIBLE);
        }
    }


}
