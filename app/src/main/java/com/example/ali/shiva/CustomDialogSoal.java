package com.example.ali.shiva;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomDialogSoal extends Dialog{
    public Activity c;
    public Dialog d;
    public Button ok;
    TextView asan,mid,hard;
    Spinner spinner;
    String[] array;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog2);
        array=getContext().getResources().getStringArray(R.array.type_value);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();

        asan=(TextView)findViewById(R.id.asantxt);
        mid=(TextView)findViewById(R.id.middetxt);
        hard=(TextView)findViewById(R.id.hardtxt);
        spinner=(Spinner)findViewById(R.id.spinnersoaldialog);
        ok=(Button)findViewById(R.id.oksoaldialog);
        setEmptyback();
        setColor();
        //////////////////////////////////////////////////


        asan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmptyback();
                asan.setBackgroundResource(R.drawable.asan2);
                editor.putString("problem_level","3");
                editor.apply();
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmptyback();
                mid.setBackgroundResource(R.drawable.midde2);
                editor.putString("problem_level","4");
                editor.apply();
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEmptyback();
                hard.setBackgroundResource(R.drawable.hard2);
                editor.putString("problem_level","5");
                editor.apply();
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city=array[position];
                editor.putString("type_problem",city);
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

    private void setColor() {
        switch (preferences.getString("problem_level","3")){
            case "3":
                asan.setBackgroundResource(R.drawable.asan2);
                break;
            case "4":
                mid.setBackgroundResource(R.drawable.midde2);
                break;
            case "5":
                hard.setBackgroundResource(R.drawable.hard2);
                break;
        }
        if (!preferences.getString("type_problem", "5").equals("")){
            String c=preferences.getString("type_problem","5");
            int indexOf = java.util.Arrays.asList(array).indexOf(c);
            spinner.setSelection(indexOf);
        }
    }


    public CustomDialogSoal(Activity a,Context context) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.context=context;
    }


    private void setEmptyback(){
        asan.setBackgroundResource(R.drawable.asan);
        mid.setBackgroundResource(R.drawable.midde);
        hard.setBackgroundResource(R.drawable.hard);

    }
}
