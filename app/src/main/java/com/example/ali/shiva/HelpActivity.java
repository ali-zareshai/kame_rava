package com.example.ali.shiva;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HelpActivity extends Activity {
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        startActivity(new Intent(HelpActivity.this,Main2Activity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new HelpFragment()).commit();
    }
    public static class HelpFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.help);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_qhelp) {
            //        super.onBackPressed();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("خروج؟");

            // set dialog message
            alertDialogBuilder
                    .setMessage("آیا می خواهید خارج شود؟")
                    .setCancelable(false)
                    .setPositiveButton("بله",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {

                            finish();
                        }
                    })
                    .setNegativeButton("خیر",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {

                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
