package com.example.ali.shiva;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class CustomDialogPreference extends DialogPreference {
    SeekBar seekBar;
    TextView textView;
    public CustomDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Set the layout here
        setDialogLayoutResource(R.layout.custom_dialog);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_dialog, null);
        seekBar=(SeekBar)view.findViewById(R.id.seekBar2prefer);
        textView=(TextView)view.findViewById(R.id.textView3prefer);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });







        setPositiveButtonText("باشه");
        setNegativeButtonText("بعدا");

        setDialogIcon(null);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {
            // User selected OK
        } else {
            // User selected Cancel
        }
    }

}