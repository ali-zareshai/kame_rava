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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class CustomDialogAhd extends Dialog {
    public Activity c;
    public Dialog d;
    public Button ok;
    RelativeLayout ahd;
    SeekBar seekBar;
    ImageView imageView;
    TextView progress1;
    Context context;
    public static DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog4);

        ahd=(RelativeLayout)findViewById(R.id.doaahddialodrel);
        ok=(Button)findViewById(R.id.okhoshdare2dialog);
        seekBar=(SeekBar)findViewById(R.id.seekbarvolumeahddialog);
        imageView=(ImageView)findViewById(R.id.ahdimagedialog);
        progress1=(TextView)findViewById(R.id.vol_progress);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();

        db = new DatabaseHandler(context);
//        db.update_Azan("T");

        if (db.get_Ahd()){
            imageView.setImageResource(R.drawable.true1);
        }else {
            imageView.setImageResource(R.drawable.false1);
        }
        seekBar.setProgress(preferences.getInt("vol_ahd",0));
        progress1.setText(getprogress(preferences.getInt("vol_ahd",0)));

        ahd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.get_Ahd()){
                    editor.putBoolean("ahd",false);
                    editor.apply();
                    db.update_Ahd("F");
                    Log.e("ahd","false");
                    imageView.setImageResource(R.drawable.false1);
                }else {
                    editor.putBoolean("ahd",true);
                    editor.apply();
                    db.update_Ahd("T");
                    Log.e("ahd","true");
                    imageView.setImageResource(R.drawable.true1);
                }

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("vol_ahd",seekBar.getProgress());
                editor.apply();
                Intent intent=new Intent(context,PerfernceActivity.class);
                intent.putExtra("mode","1");
                context.startActivity(intent);
                dismiss();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress1.setText(getprogress(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
    }

    private String getprogress(int vol_ahd) {
        return "%"+(int)(6.25*(float)vol_ahd);
    }

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    public CustomDialogAhd(Activity a,Context context) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.context=context;
    }
}
