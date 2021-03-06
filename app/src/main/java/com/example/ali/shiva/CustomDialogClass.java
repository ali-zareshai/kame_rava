package com.example.ali.shiva;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button ok;
    Spinner spinner;
    String[] array,array2;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
//    TextView ostan;
    Context context;

    public CustomDialogClass(Activity a, Context context) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog1);

//        ostan=(TextView)((Activity)context).findViewById(R.id.ostanup);

        ok = (Button) findViewById(R.id.okdialog1);
        ok.setOnClickListener(this);
        spinner=(Spinner)findViewById(R.id.spinnerdialog);

        MySpinnerAdapter adapter = new MySpinnerAdapter(
                getContext(),
                R.layout.view_spinner_item,
                Arrays.asList(context.getResources().getStringArray(R.array.city_name_sp))
        );
        spinner.setAdapter(adapter);

        array=getContext().getResources().getStringArray(R.array.city_value_sp);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
        array2=getContext().getResources().getStringArray(R.array.city_name_sp);
        int indexOf = java.util.Arrays.asList(array).indexOf(preferences.getString("city","31.89:54.36"));
        spinner.setSelection(indexOf);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city=array[position];
                editor.putString("city",city);
                editor.apply();
//                FragmentMain fragmentMain=new FragmentMain();
//                fragmentMain.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                next.setEnabled(false);

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.okdialog1:
//                FragmentTransaction ft =c.getFragmentManager().beginTransaction();
//                ft.replace(R.id.fragment,new FragmentMain());
//                ft.commit();
                Intent intent=new Intent(context,PerfernceActivity.class);
                intent.putExtra("mode","1");
                context.startActivity(intent);
                dismiss();
                break;
//            case R.id.gpsdialog1:
//                getContext().startActivity(new Intent(getContext(),LocationAc.class));
//                dismiss();
//                break;
            default:
                break;
        }
        dismiss();
    }

}