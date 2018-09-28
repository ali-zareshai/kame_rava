package com.example.ali.shiva;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class PerfernceActivity extends AppCompatActivity {
    SharedPreferences SP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfernce2);
        SP = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


//        getFragmentManager().beginTransaction().replace(android.R.id.content,
//                new PrefsFragment()).commit();
//         Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        String mode=getIntent().getExtras().getString("mode");
        if (mode.equals("2")){
            ft.setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up);
            ft.addToBackStack(null);
        }

//        ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out);
// Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment,new FragmentMain());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("ذخیره؟");

        // set dialog message
        alertDialogBuilder
                .setMessage("آیا می خواهید تنظیمات ذخیره شود؟")
                .setCancelable(false)
                .setPositiveButton("بله",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        yesDialog();
                    }
                })
                .setNegativeButton("خیر",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
//                        startActivity(new Intent(PerfernceActivity.this,Main2Activity.class));
                        finish();

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    private void yesDialog() {
        stopService(new Intent(PerfernceActivity.this,AlarmService.class));
        startService(new Intent(PerfernceActivity.this,AlarmService.class));
        Toast.makeText(PerfernceActivity.this, "تنظیمات ذخیره شد", Toast.LENGTH_SHORT).show();
        ///////////////////////////////////
//        if (SP.getBoolean("enable_date", false)) {
//            ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
//            changeNotifi.setContext(getApplicationContext());
//            changeNotifi.setNotifi();
//        }else{
//            ChangeNotifi changeNotifi=ChangeNotifi.getChangeNotifi();
//            changeNotifi.setContext(getApplicationContext());
//            changeNotifi.cancelNotification(001);
//        }
        finish();
//        startActivity(new Intent(PerfernceActivity.this,Main2Activity.class));
    }

//    public static class PrefsFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//
//            addPreferencesFromResource(R.xml.preferences);
////            getPreferenceManager().setSharedPreferencesMode(View.LAYOUT_DIRECTION_RTL);
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
////                getListView().setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
//        }
//
//
//    }
}
