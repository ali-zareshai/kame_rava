package com.example.ali.shiva;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollingActivity extends Activity {
    public static DatabaseHandler db;
    TextView txt;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar23);
        toolbar.setTitleMargin(5,5,5,5);
        db = new DatabaseHandler(getApplicationContext());


//        setSupportActionBar(toolbar);
        toolbar.setTitle(db.get_header());
        Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/Samim.ttf");
        txt=(TextView)findViewById(R.id.textViewscroll);

        fab=(FloatingActionButton)findViewById(R.id.fabscrol);
//        txt.setText(Html.fromHtml("<h2>سلام</h2><br><p>چطوری؟</p>", Html.FROM_HTML_MODE_COMPACT));
        if (db.get_msg()!=null){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                txt.setText(Html.fromHtml(db.get_msg(),Html.FROM_HTML_MODE_LEGACY));
            } else {
                txt.setText(Html.fromHtml(db.get_msg()));
            }
        }else {
            txt.setText("پیامی موجود نمی باشد ...");
        }

        txt.setTypeface(type2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();

            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        startActivity(new Intent(ScrollingActivity.this,Main2Activity.class));
    }

    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(ScrollingActivity.this);
        View promptView = layoutInflater.inflate(R.layout.dialog_scroll, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ScrollingActivity.this);
        alertDialogBuilder.setView(promptView);




        final EditText name = (EditText) promptView.findViewById(R.id.editText1_dialog_scroll);
        final EditText nagar = (EditText) promptView.findViewById(R.id.editText2_dialog_scroll);
        final RatingBar ratingBar_scroll=(RatingBar)promptView.findViewById(R.id.ratingBar_scroll);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("ثبت نظر", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name_str=name.getText().toString();
                        String nagar_str=nagar.getText().toString();
                        if (name_str.equals("") || name_str==null){
                            Toast.makeText(ScrollingActivity.this, "لطفا نام خود را وارد کنید", Toast.LENGTH_SHORT).show();
                            name.setFocusable(true);
                        }else if (nagar_str.equals("") || nagar_str==null){
                            Toast.makeText(ScrollingActivity.this, "لطفا نظر خود را وارد کنید", Toast.LENGTH_SHORT).show();
                            nagar.setFocusable(true);
                        }else {
                            float star=ratingBar_scroll.getRating();
                            Server server_nagar=new Server(getApplicationContext());
                            server_nagar.sendNagar(name_str,nagar_str,star);

                        }

                    }
                })
                .setNegativeButton("بستن",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
