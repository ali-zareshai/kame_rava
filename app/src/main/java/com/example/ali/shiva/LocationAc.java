package com.example.ali.shiva;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class LocationAc extends AppCompatActivity implements LocationListener {
    Button getLocationBtn,btnok;
    EditText locationTextx,locationTexty;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        getLocationBtn = (Button)findViewById(R.id.getLocationBtn);
        locationTextx = (EditText) findViewById(R.id.editTextlocalx);
        locationTexty = (EditText) findViewById(R.id.editTextlocaly);
        btnok=(Button)findViewById(R.id.buttonlocalok);
        /////////////
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String x = preferences.getString("local_x", "");
        String y = preferences.getString("local_y", "");
        if(!x.equalsIgnoreCase(""))
        {
            locationTextx.setText(x);
        }
        if(!y.equalsIgnoreCase(""))
        {
            locationTexty.setText(y);
        }
        /////////////
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationTextx.getText().toString().equals("") || locationTexty.getText().toString().equals("")){
                    Toast.makeText(LocationAc.this, "لطفا مقادیر را وارد کنید", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("local_x",locationTextx.getText().toString());
                    editor.putString("local_y",locationTexty.getText().toString());
                    editor.apply();
                    Toast.makeText(LocationAc.this, "مقادیر مختصات ذخیره شد", Toast.LENGTH_SHORT).show();
                }

            }
        });

        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(LocationAc.this, "لطفا اینترنت و GPS موبایل را فعال کنید", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
//        locationText.setText("Current Location: " + location.getLatitude() + ", " + location.getLongitude());
        DecimalFormat df = new DecimalFormat("##.##");
        locationTextx.setText(String.valueOf(df.format(location.getLatitude())));
        locationTexty.setText(String.valueOf(df.format(location.getLongitude())));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }
}